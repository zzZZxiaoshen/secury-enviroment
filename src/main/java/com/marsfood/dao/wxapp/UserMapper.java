package com.marsfood.dao.wxapp;

import com.marsfood.domain.wxapp.CountUserRegisteDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户mapper
 * @author shenkai
 * @date 2018/11/01
 */
public interface UserMapper {

    /**
     * 查询用户注册数量
     * @return 今天之前的前七天用户注册数量
     */
    List<CountUserRegisteDo> selectUserList();

    /**
     * 查询当前用户注册总数量
     * @return
     */
    Integer selectUserCount();

}
