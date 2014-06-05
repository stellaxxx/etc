package kr.pe.stella.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "kr.pe.stella", excludeFilters = { @ComponentScan.Filter(Controller.class) })
@EnableAspectJAutoProxy
@EnableJpaRepositories
public class AppConfig {

}
