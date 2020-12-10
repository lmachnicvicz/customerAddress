package br.com.leandro.volvo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class CustomerAddressDto {
	@NotNull
	@ApiModelProperty(value = "Zip Code", example = "00000-000")
	@Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Wrong pattern 00000-000")
	private String zipCode;
	@NotNull
	@ApiModelProperty(value = "Address Number", example = "10")
	private int number;

	public CustomerAddressDto() {

	}

	public CustomerAddressDto(String zipCode, int number) {
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
