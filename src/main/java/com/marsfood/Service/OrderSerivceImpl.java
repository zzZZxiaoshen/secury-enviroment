package com.marsfood.Service;

import com.marsfood.dao.OrderMapper;
import com.marsfood.domain.OrderDo;
import com.marsfood.dto.OrderDto;
import com.marsfood.dto.response.Response;
import com.marsfood.dto.response.ResponseCode;
import com.marsfood.entity.OrderEntity;
import com.marsfood.utils.BeanHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 订单服务层
 * @author shenkai
 * @date 2018/12/24
 */
@Service
public class OrderSerivceImpl implements OrderSerivce {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Response<List<OrderDto>> findOrderList() {
        Response<List<OrderDto>> response = Response.newInstance();

        List<OrderDo> orderDos = orderMapper.selectOrderList();

        if (CollectionUtils.isEmpty(orderDos)) {
            return response.fill(ResponseCode.BIZ_ERROR, ResponseCode.BIZ_ERROR.getMessage(), null);
        }
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), BeanHelper.convertBeans(orderDos,OrderDto::new));
    }

}
