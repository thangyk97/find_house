package com.example.demo;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Cacheable(false)
public class House implements Comparable<House> {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	private double distance;
	private double acreage;
	private double term;
	private double price;
	private double bus;
	private double isWithHost;
	private double score;
	

	private String phone;
	
	public House(double distance, double acreage, double term, double price, double bus, double isWithHost) {
		super();
		this.distance = distance;
		this.acreage = acreage;
		this.term = term;
		this.price = price;
		this.bus = bus;
		this.isWithHost = isWithHost;
	}

	public House() {
		this.distance = 0.0;
		this.acreage = 0.0;
		this.term = 0.0;
		this.price = 0.0;
		this.bus = 0.0;
		this.isWithHost = 0.0;
	}
	
	public void maxMinNormalization(House tempMax, House tempMin) {
		this.distance = (this.distance - tempMin.distance) / (tempMax.distance - tempMin.distance);
		this.acreage = (this.acreage - tempMin.acreage) / (tempMax.acreage - tempMin.acreage);
		this.term = (this.term - tempMin.term) / (tempMax.term - tempMin.term);
		this.price = (this.price - tempMin.price) / (tempMax.price - tempMin.price);
		this.bus = (this.bus - tempMin.bus) / (tempMax.bus - tempMin.bus);
		this.isWithHost = (this.isWithHost - tempMin.isWithHost) / (tempMax.isWithHost - tempMin.isWithHost);
	}

	public void computeDelta(House house) {
		
//		this.distance = - this.distance;
//		this.term     = - this.term;
//		this.price    = - this.price;
		
		if (house.getBus() == 0) {
			this.bus = 0.0;
		}
		
		if (this.isWithHost == house.isWithHost) {
			this.isWithHost = 1.0;
		} else {
			this.isWithHost = 0.0;
		}
	}
	
	public void computeSum(House house) {
		this.distance += house.distance * house.distance;
		this.acreage  += house.acreage * house.acreage;
		this.term     += house.term * house.term;
		this.price    += house.price * house.price;
		this.bus      += house.bus * house.bus;
		this.isWithHost += house.isWithHost * house.isWithHost;
	}
	
	public void computeSqrt() {
		this.distance = Math.sqrt(this.distance);
		this.acreage  = Math.sqrt(this.acreage);
		this.term     = Math.sqrt(this.term);
		this.price    = Math.sqrt(this.price);
		this.bus      = Math.sqrt(this.bus);
		this.isWithHost = Math.sqrt(this.isWithHost);
	}
	
	public void normalise(House house) {
		if (house.distance != 0.0)
			this.distance /= house.distance;
		else this.distance = 0.0;
		if (house.acreage != 0.0)
			this.acreage  /= house.acreage;
		else this.acreage = 0.0;
		if (house.term != 0.0)
			this.term     /= house.term;
		else this.term = 0.0;
		if (house.price != 0.0)
			this.price    /= house.price;
		else this.price = 0.0;
		if (house.bus != 0.0)
			this.bus      /= house.bus;
		else this.bus = 0.0;
		if (house.isWithHost != 0.0)
			this.isWithHost /= house.isWithHost;
		else this.isWithHost = 0.0;
	}
	
	public void findMax(House house) {
//		if (this.distance < house.distance) this.distance = house.distance;
//		if (this.acreage < house.acreage) this.acreage = house.acreage;
//		if (this.term < house.term) this.term = house.term;
//		if (this.price < house.price) this.price = house.price;
//		if (this.bus < house.bus) this.bus = house.bus;
//		if (this.isWithHost < house.isWithHost) this.isWithHost = house.isWithHost;
		
		//test
		if (this.distance > house.distance) this.distance = house.distance;
		if (this.acreage < house.acreage) this.acreage = house.acreage;
		if (this.term > house.term) this.term = house.term;
		if (this.price > house.price) this.price = house.price;
		if (this.bus < house.bus) this.bus = house.bus;
		if (this.isWithHost < house.isWithHost) this.isWithHost = house.isWithHost;
	}
	
	public void findMin(House house) {
//		if (this.distance > house.distance) this.distance = house.distance;
//		if (this.acreage > house.acreage) this.acreage = house.acreage;
//		if (this.term > house.term) this.term = house.term;
//		if (this.price > house.price) this.price = house.price;
//		if (this.bus > house.bus) this.bus = house.bus;
//		if (this.isWithHost > house.isWithHost) this.isWithHost = house.isWithHost;
		
		//test
		if (this.distance < house.distance) this.distance = house.distance;
		if (this.acreage > house.acreage) this.acreage = house.acreage;
		if (this.term < house.term) this.term = house.term;
		if (this.price < house.price) this.price = house.price;
		if (this.bus > house.bus) this.bus = house.bus;
		if (this.isWithHost > house.isWithHost) this.isWithHost = house.isWithHost;
	}
	
	public void computePercentOfWeight() {
		double sum = this.distance + this.acreage + this.term + this.bus + this.isWithHost + this.price;
		if (sum != 0) {
			this.distance /= sum;
			this.acreage /= sum;
			this.term /= sum;
			this.bus /= sum;
			this.isWithHost /= sum;
			this.price /= sum;
		}
	}
	
	public void multiWeight(House house) {
		this.distance *= house.distance;
		this.acreage  *= house.acreage;
		this.term     *= house.term;
		this.price    *= house.price;
		this.bus      *= house.bus;
		this.isWithHost *= house.isWithHost;
	}
	
	public double computeDiff(House house) {
		double distance = Math.pow(this.distance - house.distance, 2);
		double acreage = Math.pow(this.acreage - house.acreage, 2);
		double term = Math.pow(this.term - house.term, 2);
		double price = Math.pow(this.price - house.price, 2);
		double bus = Math.pow(this.bus - house.bus, 2);
		double isWithHost = Math.pow(this.isWithHost - house.isWithHost, 2);
		
		return Math.sqrt(distance + acreage + term + price + bus + isWithHost);
	}
	
	public void computeFinalScore(House maxHouse, House minHouse) {
		double Sgood = computeDiff(maxHouse);
		double Sbad  = computeDiff(minHouse);
		
		if (Sgood + Sbad != 0) {
			this.score =  Sbad / (Sgood + Sbad);
		} else {
			this.score = 0;
		}
		
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getAcreage() {
		return acreage;
	}
	public void setAcreage(double acreage) {
		this.acreage = acreage;
	}
	public double getTerm() {
		return term;
	}
	public void setTerm(double term) {
		this.term = term;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getBus() {
		return bus;
	}
	public void setBus(double bus) {
		this.bus = bus;
	}
	public double getIsWithHost() {
		return isWithHost;
	}
	public void setIsWithHost(double isWithHost) {
		this.isWithHost = isWithHost;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public House(House house) {
		this.id = house.id;
		this.distance = house.distance;
		this.acreage = house.acreage;
		this.term = house.term;
		this.price = house.price;
		this.bus = house.bus;
		this.isWithHost = house.isWithHost;
		this.score = house.score;
	}

	@Override
	public int compareTo(House o) {
		// TODO Auto-generated method stub
		if (this.score == o.score) return 0;
		else if (this.score > o.score) return -1;
		else return 1;
	}
	
	
	
	
	
}
