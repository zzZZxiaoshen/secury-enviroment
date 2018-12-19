package com.marsfood.controller;

import com.marsfood.Service.OrderService;
import com.marsfood.dto.OrderDto;
import com.marsfood.dto.PaymentOrderDto;
import com.marsfood.dto.response.Response;
import com.marsfood.dto.response.ResponseCode;
import com.marsfood.emus.OrderChannel;
import com.marsfood.emus.PayStatus;
import com.marsfood.entity.*;
import com.marsfood.entity.response.HttpBizCode;
import com.marsfood.entity.response.ResponseEntity;
import com.marsfood.utils.AliPayOrderClient;
import com.marsfood.utils.WeChatOrderClient;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class OrderController {

    @Autowired
    private OrderService orderSevice;
    @Autowired
    private WeChatOrderClient weChatOrderClient;
    @Autowired
    private AliPayOrderClient aliPayOrderClient;

    /**
     * 订单支付
     */
    public ResponseEntity payOrder(@ModelAttribute OrderPayEntity orderPayEntity) {
        ResponseEntity response = ResponseEntity.newInstance();
        if (StringUtils.isBlank(orderPayEntity.getOrderNo())) {

            return response.fill(HttpBizCode.ILLEGAL, HttpBizCode.ILLEGAL.getMessage(), null);
        }
        if (orderPayEntity.getPrice() == null || orderPayEntity.getPrice().compareTo(new BigDecimal(0)) < 1) {
            return response.fill(HttpBizCode.ILLEGAL, "订单价格错误", null);
        }
        OrderChannel orderChannel = OrderChannel.getOrderChannelByCode(orderPayEntity.getChannel());
        if (orderChannel == null) {
            return response.fill(HttpBizCode.ILLEGAL, "支付渠道错误", null);
        }
        // 如果是微信支付，并且没有传入openid返回错误
        if (orderChannel == OrderChannel.WXPAY && StringUtils.isBlank(orderPayEntity.getOpenId())) {
            return response.fill(HttpBizCode.ILLEGAL, "openid错误", null);
        }

        Response<OrderDto> orderByOrderNo = orderSevice.findOrderByOrderNo(orderPayEntity.getOrderNo());
        if (orderByOrderNo.getResult() == null) {
            return response.fill(HttpBizCode.BIZ_ERROR, HttpBizCode.BIZ_ERROR.getMessage(), null);
        }
        PayStatus payStatus = PayStatus.getByCode(orderByOrderNo.getCode());
        if (payStatus == null) {
            return response.fill(HttpBizCode.BIZ_ERROR, "订单状态异常", null);
        }
        if (orderByOrderNo.getResult().getStatus() == PayStatus.PAY.getCode()) {
            return response.fill(HttpBizCode.BIZ_ERROR, "订单已经支付请勿重复支付", null);
        }
        // 如果页面价格和真实价格不一致
        if (orderPayEntity.getPrice().compareTo(orderByOrderNo.getResult().getTotalPrice()) != 0) {
            return response.fill(HttpBizCode.BIZ_ERROR, HttpBizCode.BIZ_ERROR.getMessage(), null);
        }
        Response<PaymentOrderDto> payOrderRes = orderSevice.findPaymentOrderByOrderNo(orderPayEntity.getOrderNo());
        PaymentOrderDto payOrder = payOrderRes.getResult();
        if (payOrder == null) {
            PaymentOrderEntity paymentOrderEntity = new PaymentOrderEntity();
            paymentOrderEntity.setOrderNo(orderPayEntity.getOrderNo());
        }
        payOrder.setOpenId(orderPayEntity.getOpenId());
        payOrder.setPayChannel(orderChannel.getCode());
        payOrder.setStatus(PayStatus.NOT_PAY.getCode());
        payOrder.setPayAmount(orderByOrderNo.getResult().getPaid());
        Response<Boolean> booleanResponse;
        if (payOrder.getId() == null) {
            booleanResponse = orderSevice.addPaymentOrder(payOrder);
        } else {
            booleanResponse = orderSevice.updatePayInfo(payOrder);
        }

        if (booleanResponse.getCode() != ResponseCode.SUCCESS.getCode()) {
            return response.fill(HttpBizCode.BIZ_ERROR, "新增支付订单失败", null);
        }
        if (orderPayEntity.getChannel().equals(OrderChannel.WXPAY)) {
          /*  WeChatOrderClient weChatOrder;
            try {
                weChatOrder = WeChatOrderClient.createWeChatOrder(payOrder);
            } catch (Exception e) {
                return response.fill(HttpBizCode.BIZ_ERROR, "下单失败", null);
            }
            if (weChatOrder == null) {
                return response.fill(HttpBizCode.BIZ_ERROR, "下单失败", null);
            }*/
           // return response.fill(HttpBizCode.SUCCESS, HttpBizCode.SUCCESS.getMessage(), weChatOrder);
        } else if (orderPayEntity.getChannel().equals(OrderChannel.ALIPAY)) {
            String s = aliPayOrderClient.acquireTradeBody(payOrder.getOrderNo(), orderByOrderNo.getResult().getPaid().toString());
            if (s == null) {
                return response.fill(HttpBizCode.BIZ_ERROR, "下单失败", null);
            }
            return response.fill(HttpBizCode.SUCCESS, HttpBizCode.SUCCESS.getMessage(), aliPayOrderClient);
        }
        return response.fill(HttpBizCode.ILLEGAL, "支付方式错误", null);
    }

    public String aliPayCallBack(HttpServletRequest request) {
        String erro = "failure";
        HashMap<String, String> hashMap = new HashMap<>();
        // 获取回调传回得参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> map : parameterMap.entrySet()) {
            String key = map.getKey();
            String[] value = map.getValue();
            String str = "";
            for (int i = 0; i < value.length; i++) {
                str += (i == value.length - 1) ? value[i] : value[i] + ",";
            }
            hashMap.put(key, str);
        }
        // 进行验签
        if (!aliPayOrderClient.checkSign(hashMap)) {
            return erro;
        }
        String orderNo = hashMap.get("orderNo");
        if (StringUtils.isBlank(orderNo)) {
            return erro;
        }
        Response<OrderDto> orderByOrderNo = orderSevice.findOrderByOrderNo(orderNo);
        if (orderByOrderNo.getCode() != HttpBizCode.SUCCESS.getCode()) {
            return erro;
        }
        // 校验支付金额是否与实际金额一致
        if (orderByOrderNo.getResult().getTotalPrice().equals(new BigDecimal(hashMap.get("totalPrice")))) {
            return erro;
        }
        Response<PaymentOrderDto> paymentOrderByOrderNo = orderSevice.findPaymentOrderByOrderNo(orderNo);
        PaymentOrderDto paymentOrder = paymentOrderByOrderNo.getResult();
        if (paymentOrder == null) {
            return erro;
        }
        paymentOrder.setOrderNo(orderNo);
        paymentOrder.setTradeAccount(hashMap.get("tradeAccount"));
        paymentOrder.setStatus(PayStatus.PAY.getCode());
        paymentOrder.setGmtCreated(new Date());
        if (orderSevice.updatePaymentOrder(paymentOrder).getResult()) {
            return erro;
        }
        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }

}
