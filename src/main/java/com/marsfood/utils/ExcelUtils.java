package com.marsfood.utils;

import com.marsfood.dto.OrderDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    /**
     * 创建 cell  ## row 中的每一列
     */
    public static void createCell(Sheet sheet, List<String> cells) {
        Row row1 = sheet.createRow(sheet.getPhysicalNumberOfRows());
        for (int i = 0; i < cells.size(); i++) {
            row1.createCell(i).setCellValue(cells.get(i));
        }
    }


    public static ByteArrayOutputStream build(Workbook book) {
        // 将excel数据写入流中
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            book.write(baos);
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos;
    }


    /**
     * 创建 sheet
     */
    public static Sheet createSheet(Workbook book, String fileNameMap) {
        Sheet sheet = book.createSheet(fileNameMap);
        return sheet;
    }

    /**
     * 创建excel
     */
    public static Workbook createWorkBook() {
        Workbook book = new XSSFWorkbook();
        CellStyle cellStyle = book.createCellStyle();
        cellStyle.setWrapText(true);
        return book;
    }

    /**
     * 创建订单excel
     */
    public static ByteArrayOutputStream createOderExcel(List<OrderDto> orderDtos) {

        Workbook workBook = createWorkBook();
        Sheet sheet = createSheet(workBook, "订单excel");
        List<String> headers = new ArrayList<>();
        headers.add("area");
        headers.add("adress");
        createCell(sheet, headers);
        HashMap<Object, Object> map = new HashMap<>();
        for (OrderDto orderDto : orderDtos) {
            ArrayList<String> cells = new ArrayList<>();
            if (StringUtils.isNotBlank(orderDto.getArea())) {
                cells.add(orderDto.getArea());
            } else {
                cells.add("没有area");
            }
            if (StringUtils.isNotBlank(orderDto.getAddress())) {
                cells.add(orderDto.getAddress());
            } else {
                cells.add("没有adress");
            }
            createCell(sheet, cells);
        }

        return build(workBook);
    }

}
