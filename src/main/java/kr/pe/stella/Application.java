package kr.pe.stella;

import kr.pe.stella.config.WebConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import(WebConfig.class)
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		// app 설정 더 가능
		app.setShowBanner(true); // 콘솔에 배너 출력 여부

		app.run(args);
	}
}
