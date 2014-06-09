package kr.pe.stella.base.view;

import static kr.pe.stella.base.constant.ExcelConstant.EXCEL_DATA_LIST;
import static kr.pe.stella.base.constant.ExcelConstant.EXCEL_MAX_ROW;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.stella.base.annotation.ExcelColumn;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.net.HttpHeaders;

/**
 * 기본 엑셀 다운로드 처리 뷰. 생성자를 통해 파일명 지정 가능
 *
 * @author garam park
 *
 */
public class BaseExcelView extends AbstractExcelView {

	private String fileName;

	public BaseExcelView() {
	}

	/**
	 * 지정된 파일명으로 엑셀 생성
	 *
	 * @param fileName
	 */
	public BaseExcelView(String fileName) {
		this.fileName = fileName;
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> paramMap, HSSFWorkbook workbook, HttpServletRequest req, HttpServletResponse res) throws Exception {

		List<?> dataList = (List<?>) paramMap.get(EXCEL_DATA_LIST);
		if (CollectionUtils.isEmpty(dataList)) {
			throw new RuntimeException("There are no data for create excel file");
		}

		List<?> splitedList = Lists.partition(dataList, EXCEL_MAX_ROW);

		for (Object innerObj : splitedList) {
			createSheet(workbook.createSheet(), (List<?>) innerObj);
		}

		setResponsFileName(req, res, fileName);
	}

	private void createSheet(HSSFSheet sheet, List<?> dataList) throws IllegalArgumentException, IllegalAccessException {

		for (int idx = 0; idx < dataList.size(); idx++) {
			Object obj = dataList.get(idx);
			HSSFRow row = sheet.createRow(idx++);
			Field[] fields = obj.getClass().getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);

			for (Field field : fields) {
				createCell(row, field, obj);
			}
		}
	}

	private void createCell(HSSFRow row, Field field, Object obj) throws IllegalArgumentException, IllegalAccessException {

		if (field.isAnnotationPresent(ExcelColumn.class)) {

			ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);

			HSSFCell cell = row.createCell(excelColumn.index());
			if (row.getRowNum() == 0) {
				cell.setCellValue(Objects.toString(excelColumn.header(), field.getName()));
			} else {
				cell.setCellValue(Objects.toString(field.get(obj), ""));
			}
		}
	}

	/**
	 * 다운로드 파일명 지정
	 *
	 * @param req
	 * @param res
	 * @param fileName
	 */
	private void setResponsFileName(HttpServletRequest req, HttpServletResponse res, String fileName) {

		if (Strings.isNullOrEmpty(fileName)) {
			return;
		}

		String userAgent = req.getHeader(HttpHeaders.USER_AGENT);

		try {
			if (userAgent.contains("MSIE")) {
				res.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()) + "\";");
			} else {
				res.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1)
						+ "\";");
			}

		} catch (UnsupportedEncodingException e) {
			res.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\";");
		}
	}

}
