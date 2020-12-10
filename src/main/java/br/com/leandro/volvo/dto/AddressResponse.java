package br.com.leandro.volvo.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class AddressResponse {
	@ApiModelProperty(value = "Zip Code", example = "00000-000")
	private String zipCode;
	@ApiModelProperty(value = "Address number", example = "10")
	private int number;
	@ApiModelProperty(value = "List of Customers in this address")
	private List<AddressCustomerDto> customers = new ArrayList<>();

	public AddressResponse() {
	}

	public AddressResponse(String zipCode, int number, List<AddressCustomerDto> customers) {
		super();
		this.zipCode = zipCode;
		this.number = number;
		this.customers = customers;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<AddressCustomerDto> getCustomers() {
		return customers;
	}

	public void setCustomers(List<AddressCustomerDto> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "AddressResponse [zipCode=" + zipCode + ", number=" + number + ", customers=" + customers + "]";
	}

}
