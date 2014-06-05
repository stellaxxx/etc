package kr.pe.stella.base.service;

import java.io.InputStream;
import java.util.List;

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

	}

	protected abstract void handleData(List<?> list, Object extraObj) throws Exception;
}
