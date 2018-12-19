package com.marsfood.Service;

import com.marsfood.dto.OrderDetailDto;
import com.marsfood.dto.OrderDto;
import com.marsfood.dto.OrderQueryDto;
import com.marsfood.dto.PaymentOrderDto;
import com.marsfood.dto.response.ListResponse;
import com.marsfood.dto.response.Response;

import java.util.List;
import java.util.Set;

/**
 * 订单服务层
 * @author huangxingguang
 * @date 2018/12/04
 */
public interface OrderService {

    /**
     * 根据订单号查询订单
     * @param orderNo 订单号
     * @return 订单信息
     */
    Response<OrderDto> findOrderByOrderNo(String orderNo);

    /**
     * 新增支付单
     * @param paymentOrder 支付单
     * @return 是否新增成功
     */
    Response<Boolean> addPaymentOrder(PaymentOrderDto paymentOrder);

    /**
     * 根据查询条件查询订单信息
     * @param orderQuery 查询条件
     * @return 订单信息
     */
    ListResponse<OrderDto> findByOrderQuery(OrderQueryDto orderQuery);

    /**
     * 根据订单id查询订单
     * @param id 订单id
     * @return 订单信息
     */
    Response<OrderDto> findOrderById(int id);

    /**
     * 更新交易信息
     * @param paymentOrder 交易信息
     * @return true：成功
     */
    Response<Boolean> updatePayInfo(PaymentOrderDto paymentOrder);

    /**
     * 添加交易信息
     * @param paymentOrder 交易信息
     * @return true：成功
     */
    Response<Boolean> addPayInfo(PaymentOrderDto paymentOrder);

    /**
     * 根据订单号和支付账户查询支付单
     * @param orderNo 订单号
     * @return 支付单信息
     */
    Response<PaymentOrderDto> findPaymentOrderByOrderNo(String orderNo);

    /**
     * 根据订单id修改线路
     * @param ids 订单id
     * @param routeNo 线路号
     * @return true：成功
     */
    Response<Boolean> updateOrderRouteNoByOrderId(Set<Integer> ids, int routeNo);

    /**
     * 根据订单id查询订单详情
     * @param id 订单id
     * @return 订单详情
     */
    Response<OrderDto> findOrderDetailById(Integer id);

    /**
     * 根据订单号修改订单支付价格
     * @param order 订单价格信息
     * @return 是否修改成功
     */
    Response<Boolean> updatePidPriceByOrderNo(OrderDto order);

    /**
     * 根据订单号 查询订单详情
     * @param orderNo 订单号
     * @return 订单详情
     */
    Response<OrderDto> findOrderDetailByOrderNo(String orderNo);

    /**
     * 修改支付订单
     * @param paymentOrderDto 支付订单
     * @return true：成功
     */
    Response<Boolean> updatePaymentOrder(PaymentOrderDto paymentOrderDto);

    /**
     * 批量添加订单
     * @param orders 订单
     * @return true:成功
     */
    Response<Boolean> addOrder(List<OrderDto> orders);

    /**
     * 批量添加订单详情
     * @param orderDetails 订单详情
     * @return true:成功
     */
    Response<Boolean> addOrderDetail(List<OrderDetailDto> orderDetails);

}
