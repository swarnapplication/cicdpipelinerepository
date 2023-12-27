package com.swarn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swarn.entity.EmployeeInfoEntity;
import com.swarn.repository.EmployeeInfoRepo;

@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoServiceIntrfc{
	
	
	@Autowired
	private EmployeeInfoRepo empRepo;

	@Override
	public List<EmployeeInfoEntity> fetchAllEmployees() {
		// TODO Auto-generated method stub
		return empRepo.findAll();
	}

}
