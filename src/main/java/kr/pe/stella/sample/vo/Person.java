package kr.pe.stella.sample.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Person {

	@Id
	@GeneratedValue
	private Integer no;

	private String name;

	private String email;

	public Person() {
	}

	public Person(String name, String email) {
		this.name = name;
		this.email = email;
	}
}
