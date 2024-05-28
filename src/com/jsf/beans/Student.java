package com.jsf.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.Query;
import org.hibernate.annotations.GenericGenerator;

import com.jsf.dao.DatabaseOperations;
@Entity

public class Student {
	@Transient
	private String addedCourse;
	/*
	@Transient
	private String CPassword;
	
	
	
	
	public String getCPassword() {
		return CPassword;
	}
	public void setCPassword(String cPassword) {
		CPassword = cPassword;
	}
	public String validatePassword()
	{
		try
		{
		if(this.getCPassword().equals(this.getPassword()))
		{
			return "doctorForm";
		}
		else
		{

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"confirm password must be identical to password",
							"confirm password must be identical to password"));
			return "addStudentForm";
			}	
		}
		catch(Exception exceptionObj)
		{
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"confirm password must be identical to password",
							"confirm password must be identical to password"));
			return "addStudentForm";
		}
	}
	*/
	public String getAddedCourse() {
		return addedCourse;
	}

	public void setAddedCourse(String addedCourse) {
		this.addedCourse = addedCourse;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="username")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="address")
	private String address;
	@OneToMany(mappedBy="student")
	private Collection<JoinCourseStudent> joinCourse=new ArrayList();
	
//	@ManyToMany
//	private Collection<Course>studentCourse=new ArrayList();
	public Student()
	{}
	
	public Student(int id,String userName,String password,String address)
	{
		this.id=id;
		this.userName=userName;
		this.password=password;
		this.address=address;
	}
	public void setStudent(Student student)
	{
		this.id=student.getId();
		this.userName=student.getUserName();
		this.password=student.getPassword();
		this.address=student.getAddress();
		//this.studentCourse=student.getStudentCourse();
		this.joinCourse=student.getJoinCourse();
		//this.studentCourse=student.getStudentCourse();
	}
	public Collection<JoinCourseStudent> getJoinCourse() {
		return joinCourse;
	}

	public void setJoinCourse(Collection<JoinCourseStudent> joinCourse) {
		this.joinCourse = joinCourse;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
//	@SuppressWarnings("finally")
//	public String studentLogin()
//	{
//		
//			Student particularStuDObj = new Student();
//	        List particularStuList = new ArrayList();
//			System.out.println("the username is "+userName);
//			System.out.println("the password is "+password);
//			dbObj=new DatabaseOperations();
//			Query queryObj=dbObj.validate(this);
//			try
//			{
//			particularStuDObj = (Student)queryObj.uniqueResult();
//	        this.setStudent(particularStuDObj);
//	        particularStuList = queryObj.list();
//	        
//	        if (particularStuList.size()>0) {
//				
//				return "studentForm";
//			} else {
//				FacesContext.getCurrentInstance().addMessage(
//						null,
//						new FacesMessage(FacesMessage.SEVERITY_WARN,
//								"Incorrect Username and Passowrd",
//								"Please enter correct username and Password"));
//				return "studentLogin";
//			}
//			}
//	        catch(Exception exceptionObj)
//	        {
//	        	exceptionObj.printStackTrace();
//	        	FacesContext.getCurrentInstance().addMessage(
//						null,
//						new FacesMessage(FacesMessage.SEVERITY_WARN,
//								"Incorrect Username and Passowrd",
//								"Please enter correct username and Password"));
//				return "studentLogin";
//			
//	        }
//			
//		}
//	
//	public List<String> getRestOfCoursesForComboBox()
//	{
//		System.out.println("rest of courses for student id "+this.getId());
//		dbObj=new DatabaseOperations();
//
//		List<Course> listOfCoursesWithStudentId=new ArrayList();
//		
//		
//
//		List<String> listOfCoursesNames =new ArrayList();
//		List<Integer> listOfCoursesId =new ArrayList();
//		List<Course> listOfRestCourses=new ArrayList();
//		listOfCoursesWithStudentId=dbObj.getCoursesByStudentIdDB(this.getId());
//		for(int i=0;i<listOfCoursesWithStudentId.size();i++)
//		{
//			listOfCoursesId.add((int)listOfCoursesWithStudentId.get(i).getId());
//		}
//		listOfRestCourses=dbObj.restOfCourses(listOfCoursesId);
//		
//		for(int i=0;i<listOfRestCourses.size();i++)
//		{
//			listOfCoursesNames.add(listOfRestCourses.get(i).getName().toString());
//		}
//		
//		return listOfCoursesNames;
//	}
//	
//	public List<Course> getCoursesByStudentId()
//	{
//		System.out.println("get courses for student with id "+this.getId());
//		dbObj=new DatabaseOperations();
//		List particularCoursesIdList=new ArrayList();
//		List<Course> particularCoursesList=new ArrayList();
//		particularCoursesList=dbObj.getCoursesByStudentIdDB(this.getId());
//		return particularCoursesList;
//		
//	}
//	
//	public void addCourse()
//	{
//		System.out.println("adddddddddddddddddd course");
//		dbObj=new DatabaseOperations();
//		Course c1=dbObj.getCourseByName(this.addedCourse);
//		dbObj.assignCourseToStudent(this,c1);
//	}
//	
//	public void deleteCourse(Course c1)
//	{
//
//		System.out.println("deassigning coursessssssssssssssssssssssssssss");
//		dbObj=new DatabaseOperations();
//		dbObj.deassignCourse(this,c1);
//	}
//	
//	public String createStudent()
//	{
//		dbObj=new DatabaseOperations();
//		dbObj.createStudentDb(this);
//		return "doctorForm";
//		
//	}
//	public String studentLogout()throws IOException
//	{
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        ExternalContext externalContext = facesContext.getExternalContext();
//        externalContext.invalidateSession();
//        return "studentLogin";
//	}
//	

}
