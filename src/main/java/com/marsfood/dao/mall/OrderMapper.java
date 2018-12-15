package com.marsfood.dao.mall;


import com.marsfood.domain.mall.OrderCountDo;

import java.util.List;

/**
 * 订单mapper
 * @author shenkai
 * @date 2018/12/13
 */
public interface OrderMapper {

    /**
    * 统计订单总数
    * @return 订单总数
    */
    Integer selectOrderCount();

    /**
    * 查询订单当日数量
    * @return 当日订单总数
    */
    List<OrderCountDo> selectOrderList();

}
