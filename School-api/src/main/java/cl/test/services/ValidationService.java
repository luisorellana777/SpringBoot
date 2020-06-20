package cl.test.services;

import java.util.List;

import cl.test.datamodel.Student;

public interface ValidationService {

	boolean isValidRut(List<Student> students);
	boolean isValidAge(List<Student> students);
	boolean isValidPagination(Integer pageNumber);
}
