package br.com.leandro.volvo.config;

import br.com.leandro.volvo.dto.AddressRequest;
import br.com.leandro.volvo.entity.Address;
import br.com.leandro.volvo.entity.AddressId;

public class AddressTestMass {
	public static Address buildAddress(String zipCode) {
		Address a = new Address(new AddressId(zipCode, 10));
		return a;
	}

	public static AddressRequest buildAddressRequest(String zipCode) {
		return new AddressRequest(zipCode, 10);
	}

	public static String buildAddressRequestAsString() {
		//@formatter:off
		return "{\r\n" + 
				"  \"number\": 0,\r\n" + 
				"  \"zipCode\": \"00000-000\"\r\n" + 
				"}";
		//@formatter:on
	}
	
	public static String buildAddressRequestInvalidZipCodeAsString() {
		//@formatter:off
		return "{\r\n" + 
				"  \"number\": 0,\r\n" + 
				"  \"zipCode\": \"000-000\"\r\n" + 
				"}";
		//@formatter:on
	}
}
