package com.laptrinhjavaweb.model.request;

import java.util.List;

public class BuildingSearchRequest {
	private String name;
	private Long floorArea;
	private Long districtId;
	private String ward;
	private String street;
	private String direction;
	private String level;
	private Long fromRentArea;
	private Long toRentArea;
	private Long fromRentPrice;
	private Long toRentPrice;
	private List<String> rentType;
	private String staffName;
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public Long getFromRentArea() {
		return fromRentArea;
	}

	public void setFromRentArea(Long fromRentArea) {
		this.fromRentArea = fromRentArea;
	}

	public Long getToRentArea() {
		return toRentArea;
	}

	public void setToRentArea(Long toRentArea) {
		this.toRentArea = toRentArea;
	}

	public Long getFromRentPrice() {
		return fromRentPrice;
	}

	public void setFromRentPrice(Long fromRentPrice) {
		this.fromRentPrice = fromRentPrice;
	}

	public Long getToRentPrice() {
		return toRentPrice;
	}

	public void setToRentPrice(Long toRentPrice) {
		this.toRentPrice = toRentPrice;
	}

	public List<String> getRentType() {
		return rentType;
	}

	public void setRentType(List<String> rentType) {
		this.rentType = rentType;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
