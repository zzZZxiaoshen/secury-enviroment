package com.marsfood.controller;

import com.marsfood.dto.CountUserRegisteDto;
import com.marsfood.dto.response.Response;
import com.marsfood.entity.CountUserRegisteEntity;
import com.marsfood.entity.response.HttpBizCode;
import com.marsfood.entity.response.ResponseEntity;
import com.marsfood.service.UserService;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息controller
 * @author shenkai
 * @date 2018/12/14
 */
@Controller
public class UserController {

    private static final FastDateFormat FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");


    @Autowired
    private UserService userService;

    /**
     * 查询用户注册数量
     */
    @ResponseBody
    @RequestMapping("/phone/user/register/list")
    public ResponseEntity findRegisterCount(){
        ResponseEntity response = ResponseEntity.newInstance();
        Response<List<CountUserRegisteDto>> userRegisteRes = userService.findUserList();
        if (userRegisteRes == null || userRegisteRes.getResult() == null) {
            return response.fill(HttpBizCode.BIZ_ERROR,HttpBizCode.BIZ_ERROR.getMessage(),null);
        }
        return response.fill(HttpBizCode.SUCCESS, HttpBizCode.SUCCESS.getMessage(), this.buildCountUserRegisteEntity(userRegisteRes.getResult()));
    }

    /**
    * 构建用户注册实体
    */
    private List<CountUserRegisteEntity> buildCountUserRegisteEntity(List<CountUserRegisteDto> countUserRegisteDtos){
        ArrayList<CountUserRegisteEntity> list = new ArrayList<>();
        for (CountUserRegisteDto countUserRegisteDto : countUserRegisteDtos) {
            CountUserRegisteEntity countUserRegisteEntity = new CountUserRegisteEntity();
            countUserRegisteEntity.setAllUser(countUserRegisteDto.getAllUser());
            countUserRegisteEntity.setCountUser(countUserRegisteDto.getCountUser());
            countUserRegisteEntity.setRegisteDate(FORMAT.format(countUserRegisteDto.getRegisteDate()));
            list.add(countUserRegisteEntity);
        }
        return list;
    }

}
