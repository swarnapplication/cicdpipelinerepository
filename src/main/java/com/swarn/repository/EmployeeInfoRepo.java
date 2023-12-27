package com.swarn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swarn.entity.EmployeeInfoEntity;

@Repository
public interface EmployeeInfoRepo extends JpaRepository<EmployeeInfoEntity, Integer>{

}
