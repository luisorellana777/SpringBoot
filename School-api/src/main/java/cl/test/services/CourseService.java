package cl.test.services;

import org.springframework.http.HttpStatus;

import cl.test.datamodel.Course;

public abstract class CourseService {

	abstract public HttpStatus createCourses(Course course);
	abstract public String getAllCourses();
}
