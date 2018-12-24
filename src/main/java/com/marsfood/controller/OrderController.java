package com.marsfood.controller;

import com.marsfood.Service.OrderSerivce;
import com.marsfood.dto.OrderDto;
import com.marsfood.dto.response.Response;
import com.marsfood.entity.OrderEntity;
import com.marsfood.entity.response.HttpBizCode;
import com.marsfood.entity.response.ResponseEntity;
import com.marsfood.utils.BeanHelper;
import com.marsfood.utils.ExcelUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;
import jodd.http.HttpResponse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.FileNameMap;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


/**
 * 订单控制器
 *
 * @author shenkai
 * @date 2018/12/24
 */
@Controller
public class OrderController extends BaseController {

    @Autowired
    private OrderSerivce orderSerivce;

    @ResponseBody
    @RequestMapping("/import/order")
    public void importOrderExcel(HttpServletResponse response) {
        Response<List<OrderDto>> listResponse = orderSerivce.findOrderList();
        try {
            writeFile(response, ExcelUtils.createOderExcel(listResponse.getResult()).toByteArray(), "dingdan.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
