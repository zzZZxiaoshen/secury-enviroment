package com.marsfood.service;

import com.marsfood.dto.OrderCountDto;
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
    * 统计订单
    * @return 订单前七天的订单数量
    */
    Response<List<OrderCountDto>> findOrderCount();

}
