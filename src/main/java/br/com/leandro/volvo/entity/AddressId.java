package br.com.leandro.volvo.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class AddressId implements Serializable {
	private static final long serialVersionUID = 5122611549929037674L;
	
	@NotNull
	private String zipCode;
	@NotNull
	private int number;

	public AddressId() {
	}

	public AddressId(String zipCode, int number) {
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

	@Override
	public String toString() {
		return "AddressId [zipCode=" + zipCode + ", number=" + number + "]";
	}

}
