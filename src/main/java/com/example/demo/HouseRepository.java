package com.example.demo;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HouseRepository extends CustomRepository<House, Integer>{

	@Query( "select o from House o where id = :id" )
	List<House> findByIdBlah(@Param("id") int id);
	
}
