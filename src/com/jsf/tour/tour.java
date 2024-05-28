package com.jsf.tour;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="serv")
public class tour {
	
private String role;
	
	
	public String startTheTour()
	{

		System.out.println("the role is "+role);
		if(role!=null &&role.equals("Doctor"))
				{return "doctorLogin";}
		else 
		{
		return "studentLogin";
		}
		
	}

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}




}
