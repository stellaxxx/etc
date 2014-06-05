package kr.pe.stella.sample.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Person {

	@Column(nullable = true)
	private String name;

	@Id
	@GeneratedValue
	private Long ssn;

	public Person() {
		// TODO Auto-generated constructor stub
	}

}
