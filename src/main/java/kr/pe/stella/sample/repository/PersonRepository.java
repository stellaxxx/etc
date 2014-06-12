package kr.pe.stella.sample.repository;

import java.util.List;

import kr.pe.stella.sample.vo.Person;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 대소문자 주의
 *
 * @author garam.park
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Integer>, JpaSpecificationExecutor<Person> {

	public Person findByName(String name);

	@Query("select distinct name from Person")
	public List<Person> findDistinctName();
}
