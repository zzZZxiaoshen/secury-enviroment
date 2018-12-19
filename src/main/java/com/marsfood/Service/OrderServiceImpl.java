package com.marsfood.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.marsfood.base.pojo.IArrayList;
import com.marsfood.dao.OrderDetailMapper;
import com.marsfood.dao.OrderMapper;
import com.marsfood.dao.OrderModifyLogMapper;
import com.marsfood.dao.PaymentOrderMapper;
import com.marsfood.domain.order.*;
import com.marsfood.dto.order.*;
import com.marsfood.dto.response.ListResponse;
import com.marsfood.dto.response.Response;
import com.marsfood.dto.response.ResponseCode;
import com.marsfood.enums.OrderStatus;
import com.marsfood.enums.PaymentOrderStatus;
import com.marsfood.utils.BeanHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 订单服务层
 *
 * @author huangxingguang
 * @date 2018/12/04
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static final FastDateFormat FORMAT = FastDateFormat.getInstance("MM-dd");

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private PaymentOrderMapper paymentOrderMapper;
    @Autowired
    private OrderModifyLogMapper orderModifyLogMapper;

    /**
     * 新增支付单
     */
    @Override
    public Response<Boolean> addPaymentOrder(PaymentOrderDto paymentOrder) {
        Response<Boolean> response = Response.newInstance();
        if (paymentOrder == null) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), false);
        }
        if (paymentOrderMapper.insert(paymentOrder.toClass(PaymentOrderDo.class)) < 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response.fill(ResponseCode.BIZ_ERROR, ResponseCode.BIZ_ERROR.getMessage(), false);
        }
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), true);
    }

    /**
     * 根据订单号查询订单
     */
    @Override
    public Response<OrderDto> findOrderByOrderNo(String orderNo) {
        Response<OrderDto> response = Response.newInstance();
        if (StringUtils.isBlank(orderNo)) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), null);
        }
        OrderDo orderDo = orderMapper.selectOrderByOrderNo(orderNo);
        if (orderDo == null) {
            return response.fill(ResponseCode.NOT_EXISTS, ResponseCode.NOT_EXISTS.getMessage(), null);
        }
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), orderDo.toClass(OrderDto.class));
    }

    /**
     * 根据查询条件查询订单信息
     */
    @Override
    public ListResponse<OrderDto> findByOrderQuery(OrderQueryDto orderQuery) {
        ListResponse<OrderDto> response = ListResponse.newInstance();
        if (orderQuery == null) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), 0, false);
        }
        OrderQueryDo orderQueryDo = orderQuery.toClass(OrderQueryDo.class);
        Integer total = orderMapper.countByOrderQuery(orderQueryDo);
        if (total == null || total < 1) {
            return response.fill(ResponseCode.NOT_EXISTS, ResponseCode.NOT_EXISTS.getMessage(), 0, false);
        }
        IArrayList<OrderDo> orderDos = orderMapper.selectByOrderQuery(orderQueryDo);
        if (CollectionUtils.isEmpty(orderDos)) {
            return response.fill(ResponseCode.NOT_EXISTS, ResponseCode.NOT_EXISTS.getMessage(), 0, false);
        }
        IArrayList<OrderDto> orderDtos = orderDos.toListClass(OrderDto.class);
        boolean hasNext = total > (orderQuery.getStart() + orderQuery.getLimit());
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), orderDtos, total, hasNext);

    }

    /**
     * 根据订单id查询订单
     */
    @Override
    public Response<OrderDto> findOrderById(int id) {
        Response<OrderDto> response = Response.newInstance();
        if (id < 1) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), null);
        }
        OrderDo orderDo = orderMapper.selectById(id);
        if (orderDo == null) {
            return response.fill(ResponseCode.NOT_EXISTS, ResponseCode.NOT_EXISTS.getMessage(), null);
        }
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), orderDo.toClass(OrderDto.class));
    }

    /**
     * 更新交易信息
     */
    @Override
    public Response<Boolean> updatePayInfo(PaymentOrderDto paymentOrder) {
        Response<Boolean> response = Response.newInstance();
        if (paymentOrder == null) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), false);
        }
        PaymentOrderDo oldPaymentOrder = paymentOrderMapper.selectByOrderNo(paymentOrder.getOrderNo());
        if (oldPaymentOrder == null) {
            return response.fill(ResponseCode.NOTICE, "交易单不存在", false);
        }
        // 获取到订单
        OrderDo orderDo = orderMapper.selectOrderByOrderNo(paymentOrder.getOrderNo());
        if (orderDo == null) {
            return response.fill(ResponseCode.NOT_EXISTS, "订单不存在", false);
        }
        OrderModifyLog orderModifyLog = new OrderModifyLog();
        // 订单未修改前
        orderModifyLog.setSource(JSON.toJSONString(orderDo, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero));
        orderDo.setVoucher(paymentOrder.getVoucher());
        orderDo.setStatus(OrderStatus.PAID.getCode());
        orderDo.setPaid(paymentOrder.getPayAmount());
        orderDo.setRemark(paymentOrder.getRemark());
        // 修改订单
        if (orderMapper.update(orderDo) < 1) {
            return response.fill(ResponseCode.BIZ_ERROR, "修改订单失败", false);
        }
        PaymentOrderDo paymentOrderDo = paymentOrder.toClass(PaymentOrderDo.class);
        paymentOrderDo.setGmtPaid(new Date());
        // 表示没有交易单，新增一个
        paymentOrderDo.setId(oldPaymentOrder.getId());
        // 如果交易单存在，修改这个交易单
        if (paymentOrderMapper.update(paymentOrderDo) < 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response.fill(ResponseCode.BIZ_ERROR, "修改旧的交易单失败", false);
        }
        // 订单修改后，新增日志
        orderModifyLog.setTarget(JSON.toJSONString(orderDo, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero));
        orderModifyLog.setOperator(paymentOrder.getOperator());
        orderModifyLog.setOperatorName(paymentOrder.getOperatorName());
        orderModifyLog.setOrderNo(paymentOrder.getOrderNo());
        if (orderModifyLogMapper.insert(orderModifyLog) < 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response.fill(ResponseCode.BIZ_ERROR, "新增交易日志失败", false);
        }
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), true);
    }

    /**
     * 添加交易信息
     */
    @Override
    public Response<Boolean> addPayInfo(PaymentOrderDto paymentOrder) {
        Response<Boolean> response = Response.newInstance();
        if (paymentOrder == null) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), false);
        }
        // 获取交易单
        if (paymentOrderMapper.selectByOrderNo(paymentOrder.getOrderNo()) != null) {
            return response.fill(ResponseCode.NOTICE, "已有交易单，禁止添加", false);
        }
        // 获取到订单
        OrderDo orderDo = orderMapper.selectOrderByOrderNo(paymentOrder.getOrderNo());
        if (orderDo == null) {
            return response.fill(ResponseCode.NOT_EXISTS, "订单不存在", false);
        }
        OrderModifyLog orderModifyLog = new OrderModifyLog();
        // 订单未修改前
        orderModifyLog.setSource(JSON.toJSONString(orderDo));
        orderDo.setVoucher(paymentOrder.getVoucher());
        orderDo.setStatus(OrderStatus.PAID.getCode());
        orderDo.setPaid(paymentOrder.getPayAmount());
        orderDo.setRemark(paymentOrder.getRemark());
        // 修改订单
        if (orderMapper.update(orderDo) < 1) {
            return response.fill(ResponseCode.BIZ_ERROR, "修改订单失败", false);
        }
        PaymentOrderDo paymentOrderDo = paymentOrder.toClass(PaymentOrderDo.class);
        paymentOrderDo.setGmtPaid(new Date());
        // 新增交易单
        if (paymentOrderMapper.insert(paymentOrderDo) < 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response.fill(ResponseCode.BIZ_ERROR, "新增交易单失败", false);
        }
        // 订单修改后，新增日志
        orderModifyLog.setTarget(JSON.toJSONString(orderDo));
        orderModifyLog.setOperator(paymentOrder.getOperator());
        orderModifyLog.setOperatorName(paymentOrder.getOperatorName());
        orderModifyLog.setOrderNo(paymentOrder.getOrderNo());
        if (orderModifyLogMapper.insert(orderModifyLog) < 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response.fill(ResponseCode.BIZ_ERROR, "新增交易日志失败", false);
        }
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), true);
    }

    /**
     * 根据订单号和支付账户查询支付单
     */
    @Override
    public Response<PaymentOrderDto> findPaymentOrderByOrderNo(String orderNo) {
        Response<PaymentOrderDto> response = Response.newInstance();
        if (StringUtils.isBlank(orderNo)) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), null);
        }
        PaymentOrderDo paymentOrderDo = orderMapper.selectPaymentOrderByOrderNo(orderNo);
        if (paymentOrderDo == null) {
            return response.fill(ResponseCode.NOT_EXISTS, ResponseCode.NOT_EXISTS.getMessage(), null);
        }
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), paymentOrderDo.toClass(PaymentOrderDto.class));
    }

    /**
     * 根据订单id修改线路
     */
    @Override
    public Response<Boolean> updateOrderRouteNoByOrderId(Set<Integer> ids, int routeNo) {
        Response<Boolean> response = Response.newInstance();
        if (CollectionUtils.isEmpty(ids)) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), false);
        }
        if (routeNo < 1) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), false);
        }
        Integer[] userIds = ids.toArray(new Integer[0]);
        if (orderMapper.updateRouteNoById(routeNo, userIds) < 1) {
            return response.fill(ResponseCode.BIZ_ERROR, "修改线路失败", false);
        }
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), true);
    }

    /**
     * 根据订单id查询订单详情
     */
    @Override
    public Response<OrderDto> findOrderDetailById(Integer id) {
        Response<OrderDto> response = Response.newInstance();
        if (id < 1) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), null);
        }
        OrderDo orderDo = orderMapper.selectOrderDetailById(id);
        if (orderDo == null) {
            return response.fill(ResponseCode.NOT_EXISTS, ResponseCode.NOT_EXISTS.getMessage(), null);
        }
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), orderDo.toClass(OrderDto.class));
    }

    /**
     * 根据订单号修改订单支付价格信息和更新签收单
     */
    @Override
    public Response<Boolean> updatePidPriceByOrderNo(OrderDto order) {
        Response<Boolean> response = Response.newInstance();
        if (StringUtils.isBlank(order.getOrderNo())) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), false);
        }
        if (StringUtils.isBlank(order.getVoucher())) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), false);
        }
        // 获取到订单
        OrderDo orderDo = orderMapper.selectOrderByOrderNo(order.getOrderNo());
        if (orderDo == null) {
            return response.fill(ResponseCode.NOT_EXISTS, "订单不存在", false);
        }
        //判断订单是否已经支付
        if (orderDo.getStatus() == OrderStatus.PAID.getCode()) {
            return response.fill(ResponseCode.NOTICE, "订单已支付", false);
        }
        // 订单未修改前
        OrderModifyLog orderModifyLog = new OrderModifyLog();
        orderModifyLog.setSource(JSON.toJSONString(orderDo));
        if (order.getPaid() != null) {
            orderDo.setPaid(order.getPaid());
        }
        if (order.getTotalPrice() != null) {
            orderDo.setTotalPrice(order.getTotalPrice());
        }
        orderDo.setVoucher(order.getVoucher());
        // 更新签收单地址信息
        if (orderMapper.updateVoucher(order.getOrderNo(), order.getVoucher()) < 1) {
            return response.fill(ResponseCode.BIZ_ERROR, "保存签收单失败", false);
        }
        // 修改订单价格
        if (orderMapper.updatePidPriceByOrderNo(order.toClass(OrderDo.class)) < 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response.fill(ResponseCode.BIZ_ERROR, ResponseCode.BIZ_ERROR.getMessage(), false);
        }
        // 订单修改后，新增日志
        orderModifyLog.setTarget(JSON.toJSONString(orderDo));
        orderModifyLog.setOperator(order.getOperator());
        orderModifyLog.setOperatorName(order.getOperatorName());
        orderModifyLog.setOrderNo(order.getOrderNo());
        if (orderModifyLogMapper.insert(orderModifyLog) < 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response.fill(ResponseCode.BIZ_ERROR, "修改价格失败", false);
        }
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), true);
    }

    /**
     * 根据订单号查询订单详情
     */
    @Override
    public Response<OrderDto> findOrderDetailByOrderNo(String orderNo) {
        Response<OrderDto> response = Response.newInstance();
        if (StringUtils.isBlank(orderNo)) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), null);
        }
        OrderDo orderDo = orderMapper.selectOrderDetailByOrderNo(orderNo);
        if (orderDo == null) {
            return response.fill(ResponseCode.NOT_EXISTS, ResponseCode.NOT_EXISTS.getMessage(), null);
        }
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), orderDo.toClass(OrderDto.class));
    }

    /**
     * 修改支付订单
     */
    @Override
    public Response<Boolean> updatePaymentOrder(PaymentOrderDto paymentOrderDto) {
        Response<Boolean> response = Response.newInstance();
        if (paymentOrderDto == null) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), null);
        }
        OrderDo orderDo = orderMapper.selectOrderByOrderNo(paymentOrderDto.getOrderNo());
        if (orderDo == null) {
            return response.fill(ResponseCode.NOT_EXISTS, "订单不存在", null);
        }
        if (paymentOrderDto.getStatus() == PaymentOrderStatus.PAID.getCode()) {
            orderDo.setStatus(OrderStatus.PAID.getCode());
        } else {
            orderDo.setStatus(OrderStatus.NOT_PAID.getCode());
        }
        if (orderMapper.update(orderDo) < 1) {
            return response.fill(ResponseCode.NOT_EXISTS, "修改订单状态失败", null);
        }
        if (paymentOrderMapper.update(paymentOrderDto.toClass(PaymentOrderDo.class)) < 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response.fill(ResponseCode.BIZ_ERROR, "修改支付订单失败", null);
        }
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), null);
    }

    /**
     * 批量添加订单
     */
    @Override
    public Response<Boolean> addOrder(List<OrderDto> orders) {
        Response<Boolean> response = Response.newInstance();
        if (CollectionUtils.isEmpty(orders)) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), false);
        }
        if (orderMapper.inserts(BeanHelper.deepCopys(orders, OrderDo.class)) < 1) {
            return response.fill(ResponseCode.BIZ_ERROR, "新增订单失败", false);
        }
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), true);
    }

    /**
     * 批量添加订单详情
     */
    @Override
    public Response<Boolean> addOrderDetail(List<OrderDetailDto> orderDetails) {
        Response<Boolean> response = Response.newInstance();
        if (CollectionUtils.isEmpty(orderDetails)) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), false);
        }
        if (orderDetailMapper.inserts(BeanHelper.deepCopys(orderDetails, OrderDetailDo.class)) < 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response.fill(ResponseCode.BIZ_ERROR, "新增订单详情失败", false);
        }
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), true);
    }


}
