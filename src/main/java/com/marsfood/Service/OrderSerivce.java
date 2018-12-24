package com.marsfood.Service;

import com.marsfood.dto.OrderDto;
import com.marsfood.dto.response.Response;
import com.marsfood.entity.OrderEntity;

import java.util.List;

/**
 * 订单服务层
 * @author shenkai
 * @date 2018/12/24
 */
public interface OrderSerivce {


    /**
    * 查询搜有订单
    * @return 订单信息
    */
    Response<List<OrderDto>> findOrderList();
}
