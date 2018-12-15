package com.marsfood.service;

import com.marsfood.dto.CountUserRegisteDto;
import com.marsfood.dto.response.ListResponse;
import com.marsfood.dto.response.Response;

import java.util.Date;
import java.util.List;

/**
 * 用户业务层
 * @author shenkai
 * @date 2018/12/14
 */
public interface UserService {


    /**
     * 查询用户注册数量
     * @return 今天之前的七天注册用户数量
     */
    Response<List<CountUserRegisteDto>> findUserList();
}
