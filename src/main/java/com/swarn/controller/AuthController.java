package com.swarn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.swarn.entity.EmployeeInfoEntity;
import com.swarn.service.EmployeeInfoServiceIntrfc;
import com.swarn.util.JwtUtil;

@RestController
@RequestMapping("/authorization")
public class AuthController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private EmployeeInfoServiceIntrfc empService;
	
	@GetMapping("/createtoken")
	public String createToken()
	{
		String generatedToken = jwtUtil.generateAccessToken();
		
		System.out.println("Token: "+generatedToken);
		String authHeader = "Bearer "+generatedToken;
		
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authHeader = authHeader.substring(7);
        }
		
		jwtUtil.validateAccessToken(authHeader);
		
		return "Hello: "+generatedToken;
	}
	
	
	@GetMapping(value = "/fetchAllEmployees",produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<EmployeeInfoEntity>> fetchEmployees()
	{
	
		
		return new ResponseEntity<>(empService.fetchAllEmployees(), null, HttpStatus.OK);
		
	}

}
