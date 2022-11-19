package com.laptrinhjavaweb.model.respone;

public class BuildingSearchRespone {
	private Long id;
	private String name;
	private Integer floorArea;
	private String address; // quận - phường đường
	private Integer numberOfBasement;
	private String direction;
	private String level;
	private Long rentPrice;
	private String rentArea;
	private String buildingRenttype;
	private String staffName;
	private String staffPhoneNumber;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRentArea() {
		return rentArea;
	}

	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}

	public Long getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffPhoneNumber() {
		return staffPhoneNumber;
	}

	public void setStaffPhoneNumber(String staffPhoneNumber) {
		this.staffPhoneNumber = staffPhoneNumber;
	}

	public String getBuildingRenttype() {
		return buildingRenttype;
	}

	public void setBuildingRenttype(String buildingRenttype) {
		this.buildingRenttype = buildingRenttype;
	}

}
