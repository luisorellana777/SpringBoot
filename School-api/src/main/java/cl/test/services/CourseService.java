package cl.test.services;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cl.test.datamodel.Course;
import cl.test.datamodel.Student;

public abstract class CourseService {

	abstract public HttpStatus createCourses(Course course);
	abstract public String getAllCourses();
	abstract public ResponseEntity<?> getCourse(String code);
	abstract public HttpStatus deleteCourse(String code);
	abstract public HttpStatus updateCourse(String code, Course course);
	abstract public ResponseEntity<?> paginationCourses(int page, int size);
	
	protected void prepareUpdateCourse(Course courseFrom, Course courseTo) {
		
		if(Objects.nonNull(courseFrom.getName())) { courseTo.setName(courseFrom.getName()); }
		
		if(Objects.nonNull(courseFrom.getStudent())) {
			List<Student> studentsFrom = courseFrom.getStudent();
			List<Student> studentsTo = courseTo.getStudent();
			
			studentsFrom.forEach(studentFrom -> {
				
				studentsTo.forEach(studentTo -> {
					
					if(studentFrom.getId().compareTo(studentTo.getId()) == 0) {
						
						if(Objects.nonNull(studentFrom.getRut())) { studentTo.setRut(studentFrom.getRut()); }
						if(Objects.nonNull(studentFrom.getAge())) { studentTo.setAge(studentFrom.getAge()); }
						if(Objects.nonNull(studentFrom.getLastName())) { studentTo.setLastName(studentFrom.getLastName()); }
						if(Objects.nonNull(studentFrom.getName())) { studentTo.setName(studentFrom.getName()); }
					}
				});
			});
		}
		
	}
	
}
