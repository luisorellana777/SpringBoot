package cl.test.controlers;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.test.datamodel.Course;
import cl.test.services.CourseService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

@RestController
@ApiModel("Course")
@Produces({"application/json"})
@RequestMapping(path = "/courses")
public class CourseControler {
    
	@Autowired
	CourseService courseService;
	
	@PostMapping(path="/")
	@ApiOperation(value = "Create Course")
	public HttpStatus cerateCourses(@RequestBody Course course)
	{
		return this.courseService.createCourses(course);
	}
	
	@GetMapping(path="/all")
	@ApiOperation(value = "Get All Courses")
	public String getAllCourses()
	{
		return this.courseService.getAllCourses();
	}
	
	@GetMapping(path="/")
	@ApiOperation(value = "Get A Course")
	public ResponseEntity<?> getCourse(String code)
	{
		return this.courseService.getCourse(code);
	}
	
	@DeleteMapping(path="/")
	@ApiOperation(value = "Delete A Course")
	public HttpStatus deleteCourse(String code)
	{
		return this.courseService.deleteCourse(code);
	}
	
	@PutMapping(path="/")
	@ApiOperation(value = "Put A Course")
	public HttpStatus updateCourse(String code, @RequestBody Course course)
	{
		return this.courseService.updateCourse(code, course);
	}
	
}
