package cl.test.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cl.test.datamodel.Course;

public abstract class CourseService {

	abstract public HttpStatus createCourses(Course course);
	abstract public String getAllCourses();
	abstract public ResponseEntity<?> getCourse(String code);
	abstract public HttpStatus deleteCourse(String code);
}
