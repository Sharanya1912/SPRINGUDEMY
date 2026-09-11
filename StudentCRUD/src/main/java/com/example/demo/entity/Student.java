package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="testscore")
	private Integer testscore;
	
	public Student() {
		
	}
	
	public Student(String name, Integer testscore) {
		this.name=name;
		this.testscore=testscore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTestscore() {
		return testscore;
	}

	public void setTestscore(Integer testscore) {
		this.testscore = testscore;
	}

	@Override
	public String toString() {
		return "Student {id=" + id + ", name=" + name + ", testscore=" + testscore + "}";
	}
	


}
