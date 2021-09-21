package com.lt.restcontroller;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lt.bean.Course;
import com.lt.business.AdminImplService;
import com.lt.dao.AdminDaoImpl;
import com.lt.exception.CourseFoundException;

@RestController
@RequestMapping("/admin")
public class AdminRestApi {

	AdminImplService admin = new AdminImplService();
	AdminDaoImpl adminimpl = AdminDaoImpl.getInstance();
	private static Logger logger = Logger.getLogger(AdminRestApi.class);

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/addCourse")
	@ResponseBody
	public Response addCourse(@RequestBody Course course) {

		// We need to call the service layer over here and set all the values
		List<Course> courseList = adminimpl.viewCourses();

		Course course1 = new Course(course.getCourseCode(), course.getCourseName(), null, course.getSeats());
		try {
			admin.addCourse(courseList, course1, "1000.00");
			return Response.status(200).entity("Course " + course.getCourseCode() + " added successfully!").build();
		} catch (CourseFoundException e) {
			e.printStackTrace();
			return Response.status(409).entity(e.getMessage(course.getCourseCode())).build();
		}
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewCoursesInCatalogue")
	public @ResponseBody List<Course> viewCoursesInCatalogue() {

		List<Course> courseList = admin.viewCourses();
		if (courseList.size() == 0) {
			return courseList;
		}

		courseList.forEach(course -> System.out.println(String.format("%10s | %10s | %10s", course.getCourseCode(),
				course.getCourseName(), course.getProfid())));
		return courseList;
	}
}