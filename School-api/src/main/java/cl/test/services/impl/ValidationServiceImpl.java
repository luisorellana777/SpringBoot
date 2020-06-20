package cl.test.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.test.datamodel.Student;
import cl.test.services.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService{
 
	@Override
	public boolean isValidRut(List<Student> students) {

		boolean validacion = false;
		
		for(Student student : students){
			String rut = student.getRut();

			try {
				rut =  rut.toUpperCase();
				rut = rut.replace(".", "");
				rut = rut.replace("-", "");
				int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

				char dv = rut.charAt(rut.length() - 1);

				int m = 0, s = 1;
				for (; rutAux != 0; rutAux /= 10) {
					s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
				}
				if (dv == (char) (s != 0 ? s + 47 : 75)) {
					validacion = true;
				}

			} catch (Exception e) {
				return false;
			}
		}
		
		return validacion;

	}

	@Override
	public boolean isValidAge(List<Student> students) {
		for(Student student : students){
			Integer age = student.getAge();
			if(age.intValue() < 18) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isValidPagination(Integer pageNumber) {
		
		return pageNumber >= 0;
	}
	
}
