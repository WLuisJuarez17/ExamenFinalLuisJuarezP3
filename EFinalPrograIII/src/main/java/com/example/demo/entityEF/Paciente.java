package com.example.demo.entityEF;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pacientes")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre1")
	private String name1;
	@Column(name = "nombre2")
	private String name2;
	@Column(name = "apellido1")
	private String lname1;
	@Column(name = "apellido2")
	private String lname2;
	@Column(name = "edad")
	private String age;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getLname1() {
		return lname1;
	}
	public void setLname1(String lname1) {
		this.lname1 = lname1;
	}
	public String getLname2() {
		return lname2;
	}
	public void setLname2(String lname2) {
		this.lname2 = lname2;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
}