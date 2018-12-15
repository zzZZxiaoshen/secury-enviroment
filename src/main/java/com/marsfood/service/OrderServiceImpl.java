package com.marsfood.service;

import com.marsfood.dao.mall.OrderMapper;
import com.marsfood.domain.mall.OrderCountDo;
import com.marsfood.dto.OrderCountDto;
import com.marsfood.dto.response.Response;
import com.marsfood.dto.response.ResponseCode;
import com.marsfood.utils.BeanHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 订单服务层
 * @author huangxingguang
 * @date 2018/12/04
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static final FastDateFormat FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 统计订单
     */
    @Override
    public Response<List<OrderCountDto>> findOrderCount() {
        Response<List<OrderCountDto>> response = Response.newInstance();
        // 查询订单总数
        Integer total = orderMapper.selectOrderCount();
        if (total == null || total < 1) {
            return response.fill(ResponseCode.ILLEGAL, ResponseCode.ILLEGAL.getMessage(), null);
        }
        // 查询当日订单数量
        List<OrderCountDo> orderCountDos = orderMapper.selectOrderList();
        if (CollectionUtils.isEmpty(orderCountDos)) {
            return response.fill(ResponseCode.BIZ_ERROR, ResponseCode.BIZ_ERROR.getMessage(), null);
        }
        Map<String, OrderCountDo> map = new HashMap<>();
        for (OrderCountDo orderCountDo : orderCountDos) {
            orderCountDo.setAllOrder(total);
            map.put(FORMAT.format(orderCountDo.getOrderDate()), orderCountDo);
        }
        // 填充当日未注册数据
        for (int i = 0; i < 7; i++) {
            if (map.get(FORMAT.format(DateUtils.addDays(new Date(), -1 - i))) == null) {
                OrderCountDto orderCountDto = new OrderCountDto();
                orderCountDto.setOrderDate(DateUtils.addDays(new Date(), -1 - i));
                orderCountDto.setAllOrder(total);
                orderCountDto.setCountOrder(0);
            }
        }
        orderCountDos = orderCountDos.stream().sorted(Comparator.comparing(OrderCountDo::getOrderDate)).collect(Collectors.toList());
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), BeanHelper.convertBeans(orderCountDos, OrderCountDto::new));
    }

}
