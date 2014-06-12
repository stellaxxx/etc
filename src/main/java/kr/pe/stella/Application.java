package kr.pe.stella;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring App 구동클래스 <br>
 * war를 만들기 편하도록 AutoConfig 만 넣음.
 *
 * @author garam.park
 *
 */
@EnableAutoConfiguration
@ComponentScan(useDefaultFilters = false, includeFilters = @ComponentScan.Filter(Configuration.class))
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		// app 설정 더 가능
		app.setShowBanner(true); // 콘솔에 배너 출력 여부
		app.run(args);
	}
}
