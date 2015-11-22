package quite.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xupeng
 * Date: 13-12-22
 * Time: ����1:21
 * To change this template use File | Settings | File Templates.
 */
public class ExcelUtil {

    public static String nativeExcelPath="../webapps/ROOT/excel/experts.xls";
    /**
     * �����ṩ��ȡexcel�ķ���
     * */
    public static List<List<Object>> readExcel(String fileName, InputStream inputStream) throws IOException {
        //String fileName = file.getName();
        String extension = fileName.lastIndexOf(".")==-1?"":fileName.substring(fileName.lastIndexOf(".")+1);
        if("xls".equals(extension)){
            return read2003Excel(inputStream);
        }else if("xlsx".equals(extension)){
            return read2007Excel(inputStream);
        }else{
            throw new IOException("��֧�ֵ��ļ�����");
        }
    }
    /**
     * ��ȡ office 2003 excel
     * @throws IOException
     * @throws java.io.FileNotFoundException */
    private static List<List<Object>> read2003Excel(InputStream inputStream) throws IOException{
        List<List<Object>> list = new LinkedList<List<Object>>();
        HSSFWorkbook hwb = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = hwb.getSheetAt(0);
        Object value = null;
        HSSFRow row = null;
        HSSFCell cell = null;
        for(int i = sheet.getFirstRowNum();i<= sheet.getPhysicalNumberOfRows();i++){
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> linked = new LinkedList<Object>();
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                DecimalFormat df = new DecimalFormat("0");// ��ʽ�� number String �ַ�
                switch (cell.getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        System.out.println("�ַ����ݣ�"+value);
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        if(HSSFDateUtil.isCellDateFormatted(cell)){
                            value = new SimpleDateFormat("yyyy-MM-dd").format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                            System.out.println("�������ݣ�" + value);
                            break;
                        }
//                        value = df.format(cell.getNumericCellValue());
                        if (cell.toString().contains("E"))
                            value = df.format(cell.getNumericCellValue());
                        else
                            value = cell.toString();
                        value = value.toString().matches("^-?\\d+.0+$")?(long)Double.parseDouble(value.toString())+"":value;
//                        value = new BigDecimal(cell.toString());
                        System.out.println("�������ݣ�"+value);
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        value = "";
                        break;
                    default:
                        value = cell.toString();
                }
                linked.add(value);
            }
            list.add(linked);
        }
        return list;
    }
    /**
     * ��ȡOffice 2007 excel
     * */
    private static List<List<Object>> read2007Excel(InputStream inputStream) throws IOException {
        List<List<Object>> list = new LinkedList<List<Object>>();
        // ���� XSSFWorkbook ����strPath �����ļ�·��
        XSSFWorkbook xwb = new XSSFWorkbook(inputStream);//����excel�ļ�
        // ��ȡ��һ�±������
        XSSFSheet sheet = xwb.getSheetAt(0);//excel�ļ��е�һ��sheet�ļ�
        Object value = null;
        XSSFRow row = null;//һ��
        XSSFCell cell = null;//һ����Ԫ��
        for (int i = sheet.getFirstRowNum(); i <= sheet
                .getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> linked = new LinkedList<Object>();
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                DecimalFormat df = new DecimalFormat("0");
                switch (cell.getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        System.out.println("�ַ����ݣ�"+value);
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        if(HSSFDateUtil.isCellDateFormatted(cell)){
                            value = new SimpleDateFormat("yyyy-MM-dd").format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                            System.out.println("�������ݣ�" + value);
                            break;
                        }
                        if (cell.toString().contains("E"))
                            value = df.format(cell.getNumericCellValue());
                        else
                            value = cell.toString();
                        value = value.toString().matches("^-?\\d+.0+$")?(long)Double.parseDouble(value.toString())+"":value;

//                        value = new BigDecimal(cell.toString());
                        System.out.println("�������ݣ�"+value);
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        value = "";
                        break;
                    default:
                        value = cell.toString();
                }
                linked.add(value);
            }
            list.add(linked);
        }
        return list;
    }
}
