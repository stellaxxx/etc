package kr.pe.stella.sample.vo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
@Entity
public class Person {

	@Id
	@GeneratedValue
	private Integer no;

	private String name;

	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Role role;

	public Person() {
	}

	public Person(String name, String email) {
		this.name = name;
		this.email = email;
	}
}
