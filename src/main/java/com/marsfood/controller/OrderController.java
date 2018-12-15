package com.marsfood.controller;

import com.marsfood.dto.OrderCountDto;
import com.marsfood.dto.response.Response;
import com.marsfood.entity.OrderCountEntity;
import com.marsfood.entity.response.HttpBizCode;
import com.marsfood.entity.response.ResponseEntity;
import com.marsfood.service.OrderService;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单控制器
 * @author zhuhongxin
 * @date 2018/12/04
 */
@Controller
public class OrderController {

    private static final FastDateFormat FORMAT = FastDateFormat.getInstance("MM-dd");

    @Autowired
    private OrderService orderService;

    /**
     * 统计订单
     */
    @ResponseBody
    @RequestMapping("/phone/order/count")
    public ResponseEntity findOrderCount(){
        ResponseEntity response = ResponseEntity.newInstance();
        Response<List<OrderCountDto>> orderCountRes = orderService.findOrderCount();
        if (orderCountRes == null || orderCountRes.getResult() == null) {
            return response.fill(HttpBizCode.BIZ_ERROR,HttpBizCode.BIZ_ERROR.getMessage(),null);
        }
        return response.fill(HttpBizCode.SUCCESS, HttpBizCode.SUCCESS.getMessage(), this.buildOrderCountEntity(orderCountRes.getResult()));
    }

    /**
     * 构建订单统计实体
     */
    private List<OrderCountEntity> buildOrderCountEntity(List<OrderCountDto> orderCountDtos){
        ArrayList<OrderCountEntity> list = new ArrayList<>();
        for (OrderCountDto orderCountDto : orderCountDtos) {
            OrderCountEntity orderCountEntity = new OrderCountEntity();
            orderCountEntity.setAllOrder(orderCountDto.getAllOrder());
            orderCountEntity.setCountOrder(orderCountDto.getCountOrder());
            orderCountEntity.setOrderDate(FORMAT.format(orderCountDto.getOrderDate()));
            list.add(orderCountEntity);
        }
        return list;
    }

}
