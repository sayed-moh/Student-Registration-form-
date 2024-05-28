package com.jsf.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jsf.beans.Course;
import com.jsf.beans.Doctor;
import com.jsf.beans.JoinCourseStudent;
import com.jsf.beans.Student;
import com.jsf.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
 
public class DatabaseOperations {


 
    private static Transaction transObj;
    private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();
	
    
    @SuppressWarnings("finally")
	public List<Course> getCoursesByStudentIdDB(int studentId)
    {
    	List<JoinCourseStudent>particularJoin=new ArrayList();
    	List<Course> listOfCourses=new ArrayList();
		Query queryObj=null;
		try {
			transObj = sessionObj.beginTransaction();
			queryObj=sessionObj.createQuery("From JoinCourseStudent where student_id=?");
			queryObj.setInteger(0,studentId);
			particularJoin=queryObj.list();
			for(int i=0;i<particularJoin.size();i++)
			{
				listOfCourses.add(particularJoin.get(i).getCourse());
			}
			System.out.println("the result of quert get courses by student id is "+queryObj.getResultList());
		
		}
		catch(Exception exceptionObj)
		{
	        exceptionObj.printStackTrace();
	    } 
		finally 
		{
            transObj.commit();

	        return listOfCourses;
	    }
    }
    
    
    public void assignCourseToStudent(Student student,Course c1)
    {
    	Query queryObj=null;
    	JoinCourseStudent j1=new JoinCourseStudent();


    	try {
    		
    		transObj = sessionObj.beginTransaction();
    		
    		
    		j1.setCourse(c1);
    		j1.setStudent(student);
    		sessionObj.save(j1);
			System.out.println("    ");
			System.out.println("    ");
			System.out.println("    ");
			System.out.println("Course asssigned");
			System.out.println("    ");
			System.out.println("    ");
			System.out.println("    ");
            
		} 
     catch(Exception exceptionObj) {
        exceptionObj.printStackTrace();}
    	finally {
    		transObj.commit();
			// TODO: handle finally clause
		}

    }
    
    
    @SuppressWarnings("finally")
	public Course getCourseByName(String courseName)
    {
		Query queryObj=null;
		Course particularCourse = null;

    	try {
    		transObj = sessionObj.beginTransaction();
			queryObj=sessionObj.createQuery("From Course where name=?");
			queryObj.setString(0, courseName);
			particularCourse=(Course)queryObj.uniqueResult();
			System.out.println("    ");
			System.out.println("    ");
			System.out.println("    ");
			System.out.println("cccccccccccccccccccccccourse     "+queryObj.list());
			System.out.println("    ");
			System.out.println("    ");
			System.out.println("    ");
            
		} 
     catch(Exception exceptionObj) {
        exceptionObj.printStackTrace();}
    	finally {
            transObj.commit();

	        return particularCourse;
			// TODO: handle finally clause
		}
    	
    }
    
    
    
    
    @SuppressWarnings("finally")
	public static String getCourseName(int courseId)
    { 
    	String courseName=null;
		Query queryObj=null;

    	try {
    		transObj = sessionObj.beginTransaction();
			queryObj=sessionObj.createQuery("From Course where id=?");
			queryObj.setInteger(0, courseId);
			courseName=queryObj.toString();
            
		} 
     catch(Exception exceptionObj) {
        exceptionObj.printStackTrace();}
    	finally {
            transObj.commit();

	        return courseName;
			// TODO: handle finally clause
		}
    }
    @SuppressWarnings("finally")
	public List<Course> getAllCourses()
    {
    	List<Course> listOfCourses=new ArrayList();
		Query queryObj=null;
		try {
			transObj = sessionObj.beginTransaction();
			queryObj=sessionObj.createQuery("From Course");
            System.out.println("query Reeeeeeeeeeeeeeeeest couuuuuuuuuuuuurses "+queryObj);
            
            listOfCourses=(List<Course>)queryObj.list();
            System.out.println("query Reeeeeeeeeeeeeeeeest couuuuuuuuuuuuurses "+queryObj.getResultList());
            
		
	    }catch(Exception exceptionObj) {
	        exceptionObj.printStackTrace();
	        //listOfCourses=new ArrayList();
	    } finally {
            transObj.commit();

	        return listOfCourses;
	    }
    }
    @SuppressWarnings("finally")
    public List<Course> restOfCourses(List<Integer>listOfCoursesOfStudent)
    {
    	List<Course> listOfCourses=new ArrayList();
		Query queryObj=null;
		try {
			transObj = sessionObj.beginTransaction();
			queryObj=sessionObj.createQuery("From Course where id Not in (:list)");
            queryObj.setParameterList("list", listOfCoursesOfStudent);
            System.out.println("query Reeeeeeeeeeeeeeeeest couuuuuuuuuuuuurses "+queryObj);
            
            listOfCourses=(List<Course>)queryObj.list();
            
            System.out.println("query Reeeeeeeeeeeeeeeeest couuuuuuuuuuuuurses "+queryObj.getResultList());
            
		
	    }catch(Exception exceptionObj) {
	        exceptionObj.printStackTrace();
	        //listOfCourses=new ArrayList();
	    } finally {
            transObj.commit();

	        return listOfCourses;
	    }
    	
    }
	@SuppressWarnings("finally")
	public Course selectCoursesByID(int courseId) {
		Course particularcourse = null;
		Query queryObj=null;
		
		try {
			transObj = sessionObj.beginTransaction();
            queryObj = sessionObj.createQuery("FROM Course where id=? ");
            queryObj.setInteger(0, courseId);
            particularcourse=(Course)queryObj.uniqueResult();
			
		
	    }catch(Exception exceptionObj) {
	        exceptionObj.printStackTrace();
	    } finally {
	        transObj.commit();
	        return particularcourse;
	    }
	}
    // Method To Add New Student Details In Database
    @SuppressWarnings({ "finally", "deprecation" })
	public static Query validate(Student student) {
//        Student particularStuDObj = new Student();
//        List particularStudentList = new ArrayList();
        Query queryObj = null;
    	try {
            transObj = sessionObj.beginTransaction();
            queryObj = sessionObj.createQuery("FROM Student where userName=? and password=?");
            queryObj.setString(0, student.getUserName());
            queryObj.setString(1, student.getPassword());
            System.out.println("queeeeeeeeeeeeeery"+(Student)queryObj.uniqueResult());
            }
    	
    	catch(Exception exceptionObj) {
            exceptionObj.printStackTrace();
        } finally {
            transObj.commit();
            return queryObj;
        }
 
    }
    
    
    
    public void deassignCourse(Student s1,Course c1)
    {
    	JoinCourseStudent j1=new JoinCourseStudent();
    	Query queryObj=null;
    	try {
    		transObj = sessionObj.beginTransaction();

			queryObj=sessionObj.createQuery("From JoinCourseStudent where student_id=? and course_id=?");
			queryObj.setInteger(0, s1.getId());
			queryObj.setInteger(1, c1.getId());
    		j1=(JoinCourseStudent)queryObj.uniqueResult();
    		sessionObj.delete(j1);
    		System.out.println("Delete join course student done");
			
		} catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
		}
    	finally
    	{
    		
    		transObj.commit();
    	}
    }
 
    // Method To Update Particular Student Details In The Database  
    ///
    ///
    ///
    ///
    ///doctor database operations
    //
    //
    //
    //
    @SuppressWarnings({ "finally", "deprecation"})
	public static Query validateDoctor(Doctor doctor) {

      Query queryObj = null;
  	try {
          transObj = sessionObj.beginTransaction();
          queryObj = sessionObj.createQuery("FROM Doctor where name=? and password=?");
          queryObj.setString(0, doctor.getName());
          queryObj.setString(1, doctor.getPassword());
          System.out.println("queeeeeeeeeeeeeery Doctor Login result"+(Doctor)queryObj.uniqueResult());
          }
  	
  	catch(Exception exceptionObj) {
  		
        exceptionObj.printStackTrace();
      } finally {
          transObj.commit();
          return queryObj;
      }

  }
    
    @SuppressWarnings("finally")
	public List<Course> getCoursesByDrId(int DrId)
    {
    	List<Course> listOfCourses=new ArrayList();
        Query queryObj = null;
    	try {
    		 transObj = sessionObj.beginTransaction();
             queryObj = sessionObj.createQuery("FROM Course where doctor=? ");
             queryObj.setInteger(0,DrId);
             listOfCourses=queryObj.list();
            
		} catch (Exception exceptionObj ) {
	          exceptionObj.printStackTrace();
		}
    	finally {
            transObj.commit();
            return listOfCourses;

		}
    }
    
    @SuppressWarnings("finally")
	public List<Integer> getStudentsAssociatedToCourseName(Course course)
    {
    	List<Integer> listOfStudentsIds=new ArrayList();
    	List<JoinCourseStudent>joinStudentCourseList=new ArrayList();
    	Query queryObj=null;
    	try {
   		    transObj = sessionObj.beginTransaction();
            queryObj = sessionObj.createQuery("FROM JoinCourseStudent where course_id=?");
            queryObj.setParameter(0,course.getId());
            joinStudentCourseList=(List<JoinCourseStudent>)queryObj.list();
            for(int i=0;i<joinStudentCourseList.size();i++)
            {
            	listOfStudentsIds.add(joinStudentCourseList.get(i).getStudent().getId());
            }
           
		} catch (Exception exceptionObj ) {
	          exceptionObj.printStackTrace();
		}
   	finally {
           transObj.commit();
           return listOfStudentsIds;

    	
   		}
    }
    
    @SuppressWarnings("finally")
	public Student getStudentById(int studentId)
    {	Student s1=new Student();
    	Query queryObj=null;
	
    	try {
		    transObj = sessionObj.beginTransaction();
	        queryObj = sessionObj.createQuery("FROM Student where id=? ");
	        queryObj.setInteger(0,studentId);
	        s1=(Student) queryObj.uniqueResult();
	        System.out.println(queryObj.list());
       
	} catch (Exception exceptionObj ) {
          exceptionObj.printStackTrace();
	}
	finally {
       transObj.commit();
       return s1;

	
		}

    	
    }
    @SuppressWarnings("finally")
	public Doctor getDoctorById(int doctorId)
    {
    	Doctor d1=new Doctor();
    	Query queryObj=null;
    	
    	try {
		    transObj = sessionObj.beginTransaction();
	        queryObj = sessionObj.createQuery("FROM Doctor where id=? ");
	        queryObj.setInteger(0,doctorId);
	        d1=(Doctor) queryObj.uniqueResult();
	        System.out.println(queryObj.list());
       
	} catch (Exception exceptionObj ) {
          exceptionObj.printStackTrace();
	}
	finally {
       transObj.commit();
       return d1;
		}
    }
    
    public void addCourseDB(Course c1)
    {
    	Query queryObj=null;
    	try {
		    transObj = sessionObj.beginTransaction();
		    sessionObj.save(c1);
       
	} catch (Exception exceptionObj ) {
          exceptionObj.printStackTrace();
	}
	finally {
       transObj.commit();
		}
    
    }
    
   public void createStudentDb(Student s1)
   {
		Query queryObj=null;
    	try {
		    transObj = sessionObj.beginTransaction();
		    sessionObj.save(s1);
       
	} catch (Exception exceptionObj ) {
          exceptionObj.printStackTrace();
	}
	finally {
       transObj.commit();
		}
       
   }
   
//   public String logoutDoctor()
//   {
//	   
//	   sessionObj.close();
//	   return "loginDoctor";
//   }
}