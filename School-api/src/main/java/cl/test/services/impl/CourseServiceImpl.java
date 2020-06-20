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

@Service
public class CourseServiceImpl extends CourseService{

    @Autowired
    CourseRepository courseRepository;
    
    @Autowired
    StudentRepository studentRepository;
	
	@Override
	public HttpStatus createCourses(Course course) {

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
	
    
}
