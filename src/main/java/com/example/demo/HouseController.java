package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
public class HouseController {
	@Autowired
	private HouseRepository houseRepository;
	
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public ResponseEntity<?> findHouse(@RequestBody String request) {
		Gson gson = new Gson();
		


		List<House> realHouses = Lists.newArrayList(houseRepository.findAll());
		List<House> houses = new ArrayList<>();
		copyList(realHouses, houses);
		
		JsonObject jsonObject = new Gson().fromJson(request, JsonObject.class);
		
		House house = gson.fromJson(request, House.class);
		House tempHouse = new House();
		House maxHouse = new House();
		House minHouse = new House();
		
		
		// Abstract 
		for (House house2 : houses) {
			house2.computeDelta(house);
		}
		// vector normalize
		
		for (House house2 : houses) {
			tempHouse.computeSum(house2);
		}
		
		tempHouse.computeSqrt();
		
		for (House house2 : houses) {
			house2.normalise(tempHouse);;
		}
		
		// Compute v = w * r
		House weightHouse = new House(jsonObject.get("distance_score").getAsDouble(),
				jsonObject.get("acreage_score").getAsDouble(),
				jsonObject.get("term_score").getAsDouble(),
				jsonObject.get("price_score").getAsDouble(),
				jsonObject.get("bus_score").getAsDouble(),
				jsonObject.get("isWithHost_score").getAsDouble());
		weightHouse.computePercentOfWeight();
		
		for (House house2 : houses) {
			house2.multiWeight(weightHouse);
		}
		
		// Find Sgood, Sbad
		for (House house2 : houses) {
			maxHouse.findMax(house2);
			minHouse.findMin(house2);
		}
		
		for (House house2 : houses) {
			house2.computeFinalScore(maxHouse, minHouse);
		}
		
		Collections.sort(houses);
		
		List<House> result = new ArrayList<>();
		for (int i = 0; i<12; i ++) {
			House house2 = houseRepository.findByIdBlah(houses.get(i).getId()).get(0);
			house2.setScore(houses.get(i).getScore());
			result.add(house2);
		}

		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	
	
	
	
	
	public void copyList(List<House> raw, List<House> copy) {
		for (House house : raw) {
			copy.add(new House(house));
		}
	}
}
