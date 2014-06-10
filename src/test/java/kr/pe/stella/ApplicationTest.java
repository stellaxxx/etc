package kr.pe.stella;

import kr.pe.stella.sample.repository.PersonRepository;
import kr.pe.stella.sample.vo.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTest {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private PersonRepository repository;

	@Test
	public void test_ContextLoads() {
		log.debug("test context load by spring boot");

		Person tester = repository.save(new Person("tester", "sample@gmail.com"));
		log.info("insert new user : {}", tester);
		log.info("find user :{}", repository.findByName("stella").toString());
	}

}
