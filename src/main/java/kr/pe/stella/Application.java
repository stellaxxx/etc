package kr.pe.stella;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "kr.pe.stella", includeFilters = @ComponentScan.Filter(value = Configuration.class))
@EnableJpaRepositories(basePackages = "kr.pe.stella")
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		// app 설정 더 가능
		app.setShowBanner(true); // 콘솔에 배너 출력 여부
		app.run(args);
	}
}
