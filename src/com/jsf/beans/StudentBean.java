package com.jsf.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.Query;

import com.jsf.dao.DatabaseOperations;

@ManagedBean(name="sss")
@ApplicationScoped
public class StudentBean {
	
	
	public static DatabaseOperations dbObj;
	Student s1=new Student();
	

	public Student getS1() {
		return s1;
	}

	public void setS1(Student s1) {
		this.s1 = s1;
	}

	@SuppressWarnings("finally")
	public String studentLogin()
	{
		
			Student particularStuDObj = new Student();
	        List particularStuList = new ArrayList();
			System.out.println("the username is "+s1.getUserName());
			System.out.println("the password is "+s1.getPassword());
			dbObj=new DatabaseOperations();
			Query queryObj=dbObj.validate(s1);
			try
			{
			particularStuDObj = (Student)queryObj.uniqueResult();
	        s1.setStudent(particularStuDObj);
	        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("student", particularStuDObj);
	        particularStuList = queryObj.list();
	        
	        if (particularStuList.size()>0) {
				
				return "studentForm";
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Incorrect Username and Passowrd",
								"Please enter correct username and Password"));
				return "studentLogin";
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
				return "studentLogin";
			
	        }
			
		}
	
	public List<String> getRestOfCoursesForComboBox()
	{
		//Student s1 = (Student) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("student");
		System.out.println("rest of courses for student id "+this.s1.getId());
		dbObj=new DatabaseOperations();

		List<Course> listOfCoursesWithStudentId=new ArrayList();
		
		

		List<String> listOfCoursesNames =new ArrayList();
		List<Integer> listOfCoursesId =new ArrayList();
		List<Course> listOfRestCourses=new ArrayList();
		listOfCoursesWithStudentId=dbObj.getCoursesByStudentIdDB(this.s1.getId());
		
		if(listOfCoursesWithStudentId.isEmpty())
		{
			listOfRestCourses=dbObj.getAllCourses();	
		}
		else
		{
			for(int i=0;i<listOfCoursesWithStudentId.size();i++)
			{
				if((int)listOfCoursesWithStudentId.get(i).getId()==0)
				{
					dbObj.deassignCourse(s1, listOfCoursesWithStudentId.get(i));
				}
				else
				{
				listOfCoursesId.add((int)listOfCoursesWithStudentId.get(i).getId());
				}
			}
			listOfRestCourses=dbObj.restOfCourses(listOfCoursesId);
		}
		for(int i=0;i<listOfRestCourses.size();i++)
		{
			listOfCoursesNames.add(listOfRestCourses.get(i).getName().toString());
		}
		
		return listOfCoursesNames;
	}
	
	public List<Course> getCoursesByStudentId()
	{
		//Student s1 = (Student) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("student");
		
		System.out.println("get courses for student with id "+this.s1.getId());
		dbObj=new DatabaseOperations();
		List particularCoursesIdList=new ArrayList();
		List<Course> particularCoursesList=new ArrayList();
		particularCoursesList=dbObj.getCoursesByStudentIdDB(this.s1.getId());
		return particularCoursesList;
		
	}
	
	public void addCourse()
	{
		//Student s1 = (Student) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("student");
		
		System.out.println("adddddddddddddddddd course");
		dbObj=new DatabaseOperations();
		if(this.s1.getAddedCourse().hashCode()!=0)
		{
		Course c1=dbObj.getCourseByName(this.s1.getAddedCourse());
		dbObj.assignCourseToStudent(this.s1,c1);
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"you are assigned in all courses",
							"you are assigned in all courses"));
			
		}
	}
	
	public void deleteCourse(Course c1)
	{
		//Student s1 = (Student) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("student");
		
		System.out.println("deassigning coursessssssssssssssssssssssssssss");
		dbObj=new DatabaseOperations();
		dbObj.deassignCourse(this.s1,c1);
	}
	
	public String createStudent()
	{
		//Student s1 = (Student) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("student");
		
		dbObj=new DatabaseOperations();
		dbObj.createStudentDb(this.s1);
		return "doctorForm";
		
	}
	public String studentLogout()throws IOException
	{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        return "studentLogin";
	}
	

	


}
