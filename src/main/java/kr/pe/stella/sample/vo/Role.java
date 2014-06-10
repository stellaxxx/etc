package kr.pe.stella.sample.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Role {

	@Id
	private Integer person_no;
	private String grade;

	public Role() {
		// TODO Auto-generated constructor stub
	}

}
