package kr.pe.stella;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTest {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void test_ContextLoads() {
		log.debug("test context load by spring boot");
	}

	@Test
	public void test_jodaTime() {
		String dt = LocalDateTime.now().toString(DateTimeFormat.mediumDateTime());
		log.info(dt);
		log.info(DateTime.now().toString(DateTimeFormat.mediumDateTime()));
	}
}
