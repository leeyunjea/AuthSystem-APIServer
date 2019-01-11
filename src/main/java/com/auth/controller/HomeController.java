package com.auth.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
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
	
	@Resource(name="redisTemplate")
	private HashOperations<String, String, String> hashOperations;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<String> home(@RequestBody String token) {
		
		//토큰이 있으면 redis로 검증 
		System.out.println("API Home " + token);
		token = token.replace("=", "").replace('/', '_').replace('+', '-');
		System.out.println("API Home " + token);
		// test -> 조회해보기 
		String userId = hashOperations.get(token, "userId");
		String ip = hashOperations.get(token, "ip");
		String timeStamp = hashOperations.get(token, "timeStamp");
		
		System.out.println(userId + " " + ip + " " + timeStamp);
		
		String helloWorld = "";
		
		if(userId != null && ip != null && timeStamp !=null) {
			helloWorld = "HelloWorld";
			
			return new ResponseEntity<String>(helloWorld, HttpStatus.OK);
		}else { //토큰이 없으면 API로 들어올 수 없음 error메세지 보내기 
			helloWorld = "error";
			return new ResponseEntity<String>(helloWorld, HttpStatus.OK);
		}
		
		
	}
}