package kr.pe.stella.sample.repository;

import kr.pe.stella.sample.vo.Person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

	public Person findByName(String name);
}
