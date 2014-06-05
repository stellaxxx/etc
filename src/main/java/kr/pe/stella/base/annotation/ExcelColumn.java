package kr.pe.stella.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 엑셀 셀 컨트롤을 위한 어노테이션. <br>
 * 클래스 <b>"필드"</b>에만 적용 가능하다.
 *
 * @author LOEN
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ExcelColumn {
	/**
	 * 컬럼 위치. 0부터 시작함.
	 *
	 * @return
	 */
	int index();

	/**
	 * 헤더명. 공백일 경우 해당 필드의 이름으로 세팅됨
	 *
	 * @return
	 */
	String header() default "";

}
