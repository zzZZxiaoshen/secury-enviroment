package com.marsfood.dao;

import com.marsfood.domain.OrderDo;

import java.util.List;

public interface OrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(OrderDo record);

    int insertSelective(OrderDo record);

    OrderDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderDo record);

    int updateByPrimaryKey(OrderDo record);

    List<OrderDo> selectOrderList();
}