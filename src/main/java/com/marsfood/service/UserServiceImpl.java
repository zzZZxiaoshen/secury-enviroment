package com.marsfood.service;

import com.marsfood.dao.wxapp.UserMapper;
import com.marsfood.domain.wxapp.CountUserRegisteDo;
import com.marsfood.dto.CountUserRegisteDto;
import com.marsfood.dto.response.Response;
import com.marsfood.dto.response.ResponseCode;
import com.marsfood.utils.BeanHelper;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户信息服务
 * @author shenkai
 * @date 2018/12/14
 */
@Service
public class UserServiceImpl implements UserService {

    private static final FastDateFormat FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");


    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户注册数量
     */
    @Override
    public Response<List<CountUserRegisteDto>> findUserList() {
        Response<List<CountUserRegisteDto>> response = Response.newInstance();
        //查询当前用户注册总数量
        Integer total =userMapper.selectUserCount();
        if (total == null || total < 1) {
            return response.fill(ResponseCode.BIZ_ERROR, ResponseCode.BIZ_ERROR.getMessage(), Collections.emptyList());
        }
        //查询当日注册数量
        List<CountUserRegisteDo> userDos = userMapper.selectUserList();
        // 用于判断是否含有对应日期
        HashMap<String, CountUserRegisteDo> map = new HashMap<>();
        for (CountUserRegisteDo userDo : userDos) {
            userDo.setAllUser(total);
            map.put(FORMAT.format(userDo.getRegisteDate()), userDo);
        }
         // 填充当日未注册数据
        for (int i =0; i <7 ; i++) {
            //if (FORMAT.format(DateUtils.addDays(new Date(), -1-i)).equals(FORMAT.format(userDos.get(i).getRq()))) {
            if (map.get(FORMAT.format(DateUtils.addDays(new Date(), -1 - i))) == null) {
                CountUserRegisteDo countUserRegisteDo = new CountUserRegisteDo();
                countUserRegisteDo.setRegisteDate(DateUtils.addDays(new Date(), -1 - i));
                countUserRegisteDo.setCountUser(0);
                countUserRegisteDo.setAllUser(total);
                userDos.add(countUserRegisteDo);
            }
        }
        userDos = userDos.stream().sorted(Comparator.comparing(CountUserRegisteDo::getRegisteDate)).collect(Collectors.toList());
        return response.fill(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage(), BeanHelper.convertBeans(userDos, CountUserRegisteDto::new));
    }
}
