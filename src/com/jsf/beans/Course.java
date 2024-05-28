package com.jsf.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.jsf.dao.DatabaseOperations;


@Entity
@ManagedBean(name="ccc")
public class Course {

	public static DatabaseOperations dbObj;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="appr")
	private String appr;
	
//
	@OneToMany(mappedBy="course")
	private Collection<JoinCourseStudent> joinStudent=new ArrayList();
	
	
	
	//@ManyToMany(mappedBy = "studentCourse")
	//private Collection<Student>studentsList=new ArrayList();
	

	@ManyToOne
	@JoinColumn(name="DOCTOR_ID")
	private Doctor doctor;
	

	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAppr() {
		return appr;
	}
	public void setAppr(String appr) {
		this.appr = appr;
	}
//	public Collection<Student> getStudentsList() {
//		return studentsList;
//	}
//	public void setStudentsList(Collection<Student> studentsList) {
//		this.studentsList = studentsList;
//	}
//	
//	public void getCourseDoctor()
//	{
//		this.get
//		
//		
//	}
	public Collection<JoinCourseStudent> getJoinStudent() {
		return joinStudent;
	}
	public void setJoinStudent(Collection<JoinCourseStudent> joinStudent) {
		this.joinStudent = joinStudent;
	}
	
	
	public String createCourse(int doctorId)
	{
		Course c1=new Course();
		dbObj=new DatabaseOperations();
		Doctor d1=dbObj.getDoctorById(doctorId);
		this.setDoctor(d1);
		dbObj.addCourseDB(this);
		return "doctorForm";
	}

}
