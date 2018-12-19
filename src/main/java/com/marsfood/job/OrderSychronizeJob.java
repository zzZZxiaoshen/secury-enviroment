package com.marsfood.job;

import com.alibaba.fastjson.JSON;
import com.marsfood.Service.OrderService;
import com.marsfood.domain.DataSouce1OrderDetailDo;
import com.marsfood.domain.dataSource1order.DataSouce1OrderDo;
import com.marsfood.dto.OrderDetailDto;
import com.marsfood.dto.OrderDto;
import com.marsfood.dto.response.Response;
import com.marsfood.dto.response.ResponseCode;
import com.marsfood.emus.OrderStatus;
import com.marsfood.utils.MyRoutingDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*public class OrderSychronizeJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderSychronizeJob.class);

    @Autowired
    private OrderService orderService;

    @Scheduled(cron = "0 0/5 0 * * ?")
    public void orderJob() {
        Response<List<DataSouce1OrderDo>> response;
        try {
            MyRoutingDataSource.setKey(MyRoutingDataSource.DATASOURCE1);
            response = orderService.selectDataSource1();
        } finally {
            MyRoutingDataSource.removeKey();
        }
        List<DataSouce1OrderDo> dataSourceOrder = response.getResult();
        List<OrderDto> orders = new ArrayList<>();
        List<OrderDetailDto> orderDetails = new ArrayList<>();
        for (DataSouce1OrderDo orderDo : dataSourceOrder) {
            OrderDto order = new OrderDto();
            order.setOrderNo(orderDo.getOrderNo());
            order.setOrderDate(orderDo.getCreateDate());
            order.setTotalPrice(new BigDecimal(orderDo.getAmount()));
            order.setDiscount(new BigDecimal(orderDo.getCheapFee()));
            order.setPaid(new BigDecimal(orderDo.getPaymentAmount()));
            *//*客户名为店名*//*
            order.setCustomerName(StringUtils.defaultString(orderDo.getStoreName(), StringUtils.EMPTY));
            order.setReceiver(orderDo.getReceiverName());
            order.setMobile(orderDo.getReceiverTel());
            order.setProvince(orderDo.getProvince());
            order.setCity(orderDo.getCity());
            order.setArea(orderDo.getArea());
            order.setAddress(orderDo.getDetailAddress());
            order.setBuyerMark(orderDo.getRemark());
            order.setDeliveryTime(orderDo.getDeliveryTime());
            order.setRouteNo(0);
            order.setStatus(OrderStatus.PAI.getCode());
            for (DataSouce1OrderDetailDo orderDetailDo : orderDo.getOrderDetailDos()) {
                OrderDetailDto orderDetail = new OrderDetailDto();
                orderDetail.setName(orderDetailDo.getCommodityName());
                orderDetail.setCount(orderDetailDo.getCommodityNum());
                orderDetail.setUnit(orderDetailDo.getExtraName());
                orderDetail.setSkuNo(orderDetailDo.getCommodityCode());
                orderDetail.setPrice(new BigDecimal(orderDetailDo.getItemPrice()));
                orderDetail.setTotalPrice(new BigDecimal(orderDetailDo.getAmount()));
                orderDetail.setOrderNo(order.getOrderNo());
                orderDetails.add(orderDetail);
            }
            orders.add(order);
        }
        try {
            MyRoutingDataSource.setKey(MyRoutingDataSource.DATASOURCE2);
            int limit = 15;
            int size = orders.size();
            int start = 0;
            while (size > start) {
                List<OrderDto> collect = orders.parallelStream().skip(start).limit(limit).collect(Collectors.toList());
                Response<Boolean> booleanResponse = orderService.addOrder(collect);
                if (booleanResponse.getResult() == null || !booleanResponse.getResult()) {
                    LOGGER.error("[orderSync] addOrder error. params:[{}]. result:[{}]", JSON.toJSONString(collect), JSON.toJSONString(booleanResponse));
                    return;
                }
                size += limit;

                size = orderDetails.size();
                start = 0;

                List<OrderDetailDto> collect1 = orderDetails.parallelStream().skip(start).limit(limit).collect(Collectors.toList());
                Response<Boolean> booleanResponse1 = orderService.addOrderDetail(collect1);
                if (booleanResponse.getCode() != ResponseCode.SUCCESS.getCode()) {
                    LOGGER.error("[orderSync] addOrderDetail error. params:[{}]. result:[{}]", JSON.toJSONString(collect1), JSON.toJSONString(booleanResponse1));
                    return;
                }
                size += limit;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("[orderSync] error.", e.getMessage());
        } finally {
            MyRoutingDataSource.removeKey();
        }
    }
}*/
