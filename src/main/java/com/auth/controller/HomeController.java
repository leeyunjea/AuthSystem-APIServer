package com.auth.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<String> home(@RequestBody String token) {
		
		//토큰이 있으면 redis로 검증 
		System.out.println("API Home " + token);
		
		//토큰이 없으면 API로 들어올 수 없음 error메세지 보내기 
		
		String helloWorld = "HelloWorld";

		return new ResponseEntity<String>(helloWorld, HttpStatus.OK);
//		return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
	}
}