package com.lt.validator;

import java.util.List;

import com.lt.bean.Course;
import com.lt.exception.CourseNotFoundException;

/**
 * 
 * @author CRS
 * Class for Student Validator 
 * 
 */
public class StudentValidator {

	/**
	 * Method to validate if student is already registered for this particular course (courseCode) or not 
	 * @param courseCode
	 * @param studentId
	 * @param registeredCourseList  
	 * @return Student Registration Status
	 * @throws CourseNotFoundException
	 */
	public static boolean isRegistered(String courseCode,int studentId,List<Course>registeredCourseList) throws CourseNotFoundException
	{
		for(Course course : registeredCourseList)
		{
			if(courseCode.equalsIgnoreCase(course.getCourseCode())) 
			{
				return true; 
			}
		}
		
		return false;
	}
	
	
	/**
	 * Method to validate if couseCode is valid or not
	 * @param courseCode
	 * @param availableCourseList
	 * @return couseCode is valid or not
	 */
	public static boolean isValidCourseCode(String courseCode,List<Course>availableCourseList) 
	{
		for(Course course : availableCourseList)
		{
			if(courseCode.equalsIgnoreCase(course.getCourseCode())) 
			{
				return true; 
			}
		}
		
		return false;
	
	}
	
	public static boolean isValidCard(String cardNo) 
	{
		if(cardNo.length()==16){
			return true;
		}
		
		return false;
	
	}

}
