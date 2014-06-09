package kr.pe.stella.base.service;

import java.io.InputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import kr.pe.stella.base.annotation.ExcelColumn;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.primitives.Primitives;

public abstract class AbstractExcelUploadService {
	/**
	 * <pre>
	 * 엑셀 업로드.
	 *
	 * 전제조건 : 모든 시트의 데이터는 같은 기준으로 데이터를 저장하고 있어야 함.
	 * 즉, 엑셀의 전체 시트 내 모든 Row들이 동일한 Type Class를 가짐.
	 *
	 * 주의! 엑셀 각 시트의 첫번째 ROW는 헤더라 생각하고 패스한다.
	 * </pre>
	 *
	 * @param is multiPartFile inputStream
	 * @param clazz 엑셀데이터에서 추출해서 처리 대상이 될 객체의 클래스명
	 * @param extraObj 추가로 같이 처리해야할 데이터가 있을 경우 이 필드를 사용
	 * @throws Exception
	 */
	public <T> void work(InputStream is, Class<T> clazz, Object extraObj) throws Exception {
		Preconditions.checkNotNull(is);

		List<T> dataList = Lists.newArrayList();
		Workbook book = WorkbookFactory.create(is);
		is.close();

		int sheetCnt = book.getNumberOfSheets();

		for (int i = 0; i < sheetCnt; i++) {
			Sheet sheet = book.getSheetAt(i);
			Iterator<Row> rowIte = sheet.iterator();
			boolean isFirst = true;

			while (rowIte.hasNext()) {
				Row row = rowIte.next();

				if (isFirst) {
					// pass header column (at 0th row)
					isFirst = false;
					continue;
				}

				Field[] fields = clazz.getDeclaredFields();
				AccessibleObject.setAccessible(fields, true);
				T t = clazz.newInstance();

				for (Field f : fields) {
					ExcelColumn excelColumn = f.getAnnotation(ExcelColumn.class);
					if (excelColumn == null) {
						continue;
					}
					setValueByType(f, t, row.getCell(excelColumn.index()).getStringCellValue());
				}

				dataList.add(t);
			}
		}

		handleData(dataList, extraObj);
	}

	/**
	 * 엑셀에서 추출한 데이터를 처리하는 메소드
	 *
	 * @param dataList
	 * @param extraObj
	 * @throws Exception
	 */
	protected abstract void handleData(List<?> dataList, Object extraObj) throws Exception;

	private <T> void setValueByType(Field field, T target, String cellValue) throws NumberFormatException, IllegalArgumentException, IllegalAccessException {

		Class<?> fieldTypeClazz = Primitives.wrap(field.getType());

		if (Integer.class.isAssignableFrom(fieldTypeClazz)) {
			field.setInt(target, Integer.valueOf(cellValue));
		} else if (Long.class.isAssignableFrom(fieldTypeClazz)) {
			field.setLong(target, Long.valueOf(cellValue));
		} else if (Float.class.isAssignableFrom(fieldTypeClazz)) {
			field.setFloat(target, Float.valueOf(cellValue));
		} else if (Double.class.isAssignableFrom(fieldTypeClazz)) {
			field.setDouble(target, Double.valueOf(cellValue));
		} else {
			field.set(target, cellValue);
		}
	}
}
