package cl.test.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.test.config.JwtResponse;
import cl.test.config.JwtTokenUtil;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public ResponseEntity<?> createAuthenticationToken() throws Exception {

		final String token = jwtTokenUtil.generateToken();

		return ResponseEntity.ok(new JwtResponse(token));
	}
}