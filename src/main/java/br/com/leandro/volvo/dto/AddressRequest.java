package br.com.leandro.volvo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddressRequest {
	@NotNull
	@Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Wrong pattern 00000-000")
	private String zipCode;
	@NotNull
	private int number;

	public AddressRequest() {

	}

	public AddressRequest(String zipCode, int number) {
		super();
		this.zipCode = zipCode;
		this.number = number;
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

}
