package com.marsfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 统计控制器
 * @author zhuhongxin
 * @date 2018/12/13
 */
@Controller
public class StatisticsController extends BaseController {

    /**
     * 订单统计
     */
    @RequestMapping("/view/order")
    public String viewOrderStatistics() {
        return "/order";
    }

    /**
     * 注册人数统计
     */
    @RequestMapping("/view/user")
    public String viewWxAppStatistics() {
        return "/user";
    }

}
