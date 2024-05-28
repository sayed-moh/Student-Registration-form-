package com.jsf.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.Query;

import com.jsf.dao.DatabaseOperations;

@ManagedBean(name="ddd")
@Entity
@SessionScoped
public class Doctor {

	public static DatabaseOperations dbObj;
	@Transient
	private String selectedCourse;



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	@OneToMany(mappedBy = "doctor")
	private Collection<Course>doctorCourses=new ArrayList();
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
	public Collection<Course> getDoctorCourses() {
		return doctorCourses;
	}
	public void setDoctorCourses(Collection<Course> doctorCourses) {
		this.doctorCourses = doctorCourses;
	}
	
	public void setDoctor(Doctor doctor)
	{
		this.setId(doctor.getId());
		this.setName(doctor.getName());
		this.setPassword(doctor.getPassword());
		this.setDoctorCourses(doctor.getDoctorCourses());
	}
	public String getSelectedCourse() {
		return selectedCourse;
	}
	public void setSelectedCourse(String selectedCourse) {
		System.out.println("the selected form is "+selectedCourse);
		this.selectedCourse = selectedCourse;
	}
	@SuppressWarnings("finally")
	public String doctorLogin()
	{
		Doctor particularDrDObj = new Doctor();
        List particularDrList = new ArrayList();
		System.out.println("the username is "+name);
		System.out.println("the password is "+password);
		dbObj=new DatabaseOperations();
		Query queryObj=dbObj.validateDoctor(this);
		try
		{
		particularDrDObj = (Doctor)queryObj.uniqueResult();
        this.setDoctor(particularDrDObj);
        particularDrList = queryObj.list();
        
        if (particularDrList.size()>0) {
			
			return "doctorForm";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "doctorLogin";
		}
		}
        catch(Exception exceptionObj)
        {
        	exceptionObj.printStackTrace();
        	FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "doctorLogin";
		
        }
		}
			
	
	public List<String>getDoctorCoursesName()
	{
		List<String> coursesName=new ArrayList();
		
		dbObj=new DatabaseOperations();
		List<Course> listOfCourses=dbObj.getCoursesByDrId(this.getId());
		
		for(int i=0;i<listOfCourses.size();i++)
		{
			coursesName.add(listOfCourses.get(i).getName());
			System.out.println("zzzzzzzz "+coursesName.get(i));
		}
		
		return coursesName;
	}
	
	public List<Student>getStudentsByCourseName()
	{
		List<Student> listOfStudents=new ArrayList();
		List<Integer> listOfStudentsId=new ArrayList();
		
		dbObj=new DatabaseOperations();
		Course course=dbObj.getCourseByName(this.getSelectedCourse());
		listOfStudentsId=dbObj.getStudentsAssociatedToCourseName(course);
		for(int i=0;i<listOfStudentsId.size();i++)
		{
			listOfStudents.add(dbObj.getStudentById(listOfStudentsId.get(i)));
		}
		return listOfStudents;
	}
	
	
	public String getDoctorForm()
	{
		return "doctorForm";
	}
	public String toCreateCourse() {return "courseForm";}
	public String tocreateStudent() {return "addStudentForm";}
	public void deassignStudent(Student student)
	{
		dbObj=new DatabaseOperations();
		Course course=dbObj.getCourseByName(this.getSelectedCourse());
		dbObj.deassignCourse(student,course);
	}

	public String doctorLogout() throws IOException
	{
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        ExternalContext externalContext = facesContext.getExternalContext();
	        externalContext.invalidateSession();
	        return "doctorLogin";
	}
	
	public String backToRegistration()
	{
		System.out.println("back to registration Form");
		return "registerationForm";
	}
	

}
