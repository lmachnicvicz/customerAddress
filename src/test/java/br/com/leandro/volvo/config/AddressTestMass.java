package br.com.leandro.volvo.config;

import java.util.Arrays;
import java.util.List;

import br.com.leandro.volvo.dto.AddressCustomerDto;
import br.com.leandro.volvo.dto.AddressResponse;
import br.com.leandro.volvo.dto.CustomerAddressDto;
import br.com.leandro.volvo.entity.Address;
import br.com.leandro.volvo.entity.AddressId;

public class AddressTestMass {
	public static Address buildAddress(String zipCode) {
		Address a = new Address(new AddressId(zipCode, 10));
		return a;
	}

	public static List<Address> buildListAddress() {
		return Arrays.asList(buildAddress("00000-000"), buildAddress("00000-001"));
	}

	public static CustomerAddressDto buildAddressRequest(String zipCode) {
		return new CustomerAddressDto(zipCode, 10);
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

	public static List<AddressResponse> buildAddressResponseList() {
		return Arrays.asList(new AddressResponse("00000-000", 10, buildAddressCustomerDtoList()));
	}

	public static List<AddressCustomerDto> buildAddressCustomerDtoList() {
		return Arrays.asList(new AddressCustomerDto(1L, "mock"));
	}
}
