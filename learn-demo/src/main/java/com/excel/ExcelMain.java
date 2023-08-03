package com.excel;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelMain {
    public static void main(String[] args) {

        ExcelReader baoguo = ExcelUtil.getReader("/Users/chenxiao/IdeaProjects/demo/learn-demo/src/main/java/com/excel/包裹分解流水.xlsx");
        ExcelReader jiangli = ExcelUtil.getReader("/Users/chenxiao/IdeaProjects/demo/learn-demo/src/main/java/com/excel/奖励流水_0400_0430.xlsx");

        Sheet baoguoSheet = baoguo.getSheet();

        for (int i = 1; i < baoguoSheet.getLastRowNum(); i++) {
            String userId = baoguoSheet.getRow(i).getCell(1).getStringCellValue();
            int point = (int)baoguoSheet.getRow(i).getCell(5).getNumericCellValue();

            boolean result = false;
            for (Row row : jiangli.getSheet()) {
                String userId2 = row.getCell(2).getStringCellValue();
                String point2 = row.getCell(8).getStringCellValue();
                if (userId.equals(userId2) && point == Integer.parseInt(point2)){
                    result = true;
                }

            }

            if (!result){
                System.out.print(userId+",");
                System.out.println(point);
            }

        }

    }
}
