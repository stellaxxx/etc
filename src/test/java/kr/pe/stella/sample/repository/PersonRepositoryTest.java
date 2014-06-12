package kr.pe.stella.sample.repository;

import static org.hamcrest.Matchers.is;

import java.util.Iterator;
import java.util.List;

import kr.pe.stella.Application;
import kr.pe.stella.sample.vo.Person;

import org.junit.Assert;
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
public class PersonRepositoryTest {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void testFindByName() throws Exception {

		log.info("{}", personRepository.findOne(1));
	}

	@Test
	public void testFindDistinctName() throws Exception {

		List<Person> distinctNameList = personRepository.findDistinctName();
		Assert.assertThat(distinctNameList.size(), is(2));

		log.info("{}", distinctNameList);
	}

	@Test
	public void testFindAll() throws Exception {

		Iterable<Person> allList = personRepository.findAll();

		Assert.assertNotNull(allList);
		printAll(allList);

	}

	private void printAll(Iterable<?> itb) {
		Iterator<?> ite = itb.iterator();
		while (ite.hasNext()) {
			log.info("{}", ite.next());
		}
	}
}
