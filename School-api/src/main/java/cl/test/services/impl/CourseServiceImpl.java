package cl.test.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import cl.test.datamodel.Course;
import cl.test.datamodel.CourseRepository;
import cl.test.datamodel.StudentRepository;
import cl.test.services.CourseService;
import cl.test.services.ValidationService;

@Service
public class CourseServiceImpl extends CourseService{

	@Autowired
	ValidationService validationService;
	
    @Autowired
    CourseRepository courseRepository;
    
    @Autowired
    StudentRepository studentRepository;
	
	@Override
	public HttpStatus createCourses(Course course) {

		if(!validationService.isValidRut(course.getStudent()) || !validationService.isValidAge(course.getStudent())) {return HttpStatus.BAD_REQUEST;}

		try {
			if(courseRepository.existsById(course.getCode())) {return HttpStatus.BAD_REQUEST;}
			
			course.getStudent().forEach(students -> students.setCourse(course));//set courses to every student in list.
			courseRepository.save(course);
			return HttpStatus.CREATED;
		}catch(Exception ex) {
			return HttpStatus.BAD_GATEWAY;
		}

	}

	@Override
	public String getAllCourses() {
		try {

			List<Course> rows = (ArrayList<Course>) courseRepository.findAll();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(rows);
			return json;

		}catch(Exception ex){
			return ex.getMessage();
		}
	}
	
	private Course getCourseEntity(String code){
		
		try {
			
			Optional<Course> rowOption= courseRepository.findById(code);
			
			if (rowOption.isPresent()){
				Course course = rowOption.get();
				return course;
			}else {
				return null;
			}
			
		
		}catch(IndexOutOfBoundsException ex){//if not got any
			return null;
		}
	}
	
	@Override
	public ResponseEntity<?> getCourse(String code) {

		try {
			
			Course course = this.getCourseEntity(code);
			return course == null ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<>(course, HttpStatus.ACCEPTED);
		
		}catch(IndexOutOfBoundsException ex){//if not got any
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	public HttpStatus deleteCourse(String code) {

		try {
			
			Course course = this.getCourseEntity(code);
			studentRepository.deleteByCode(course.getCode());
			courseRepository.deleteByCode(course.getCode());

			return HttpStatus.OK;
		
		}catch(Exception ex){//if not got any
			return HttpStatus.BAD_GATEWAY;
		}
	}
	
	@Override
	public HttpStatus updateCourse(String code, Course course) {
		
		try {

			Course recoveredCourse = this.getCourseEntity(code);
			super.prepareUpdateCourse(course, recoveredCourse);
			
			courseRepository.save(recoveredCourse);
			return HttpStatus.OK;
		
		}catch(Exception ex){//if not got any
			return HttpStatus.NOT_FOUND;
		}
	}
	
	@Override
	public ResponseEntity<?> paginationCourses(int page, int size) {
		
		if(!validationService.isValidPagination(size) || !validationService.isValidPagination(page)) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			Pageable firstPageWithTwoElements = PageRequest.of(page, size);
			Page<Course> allCourses = courseRepository.findAll(firstPageWithTwoElements);

			List<Course> courseList = allCourses.getContent();

			return new ResponseEntity<>(courseList, HttpStatus.ACCEPTED);

		}catch(Exception ex){
			return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
		}
	}
}
