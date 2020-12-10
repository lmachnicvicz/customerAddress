package br.com.leandro.volvo.dto;

import javax.validation.constraints.Pattern;

public class CustomerRequest {
	private String name;
	private Integer age;
	@Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Wrong pattern 00000-000")
	private String zipCode;
	private Integer number;

	public CustomerRequest() {

	}

	public CustomerRequest(String name, Integer age, String zipCode, Integer number) {
		super();
		this.name = name;
		this.age = age;
		this.zipCode = zipCode;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
