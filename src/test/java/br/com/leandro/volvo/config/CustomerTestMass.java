package br.com.leandro.volvo.config;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.leandro.volvo.dto.CustomerRequest;
import br.com.leandro.volvo.dto.CustomerResponse;
import br.com.leandro.volvo.entity.Address;
import br.com.leandro.volvo.entity.Customer;

public class CustomerTestMass {
	public static Customer buildCustomer() {
		Set<Address> a = new HashSet<>();
		a.add(AddressTestMass.buildAddress("00000-000"));

		return new Customer(1L, "mock", 30, new Date(), new Date(), a);
	}

	public static Customer buildCustomer2Address() {
		Set<Address> a = new HashSet<>();
		a.add(AddressTestMass.buildAddress("00000-000"));
		a.add(AddressTestMass.buildAddress("00000-001"));

		return new Customer(1L, "mock", 30, new Date(), new Date(), a);
	}

	public static List<Customer> buildListCustomer() {
		Set<Address> a = new HashSet<>();
		a.add(AddressTestMass.buildAddress("00000-000"));

		return Arrays.asList(new Customer(1L, "mock", 30, new Date(), new Date(), a),
				new Customer(2L, "mock", 31, new Date(), new Date(), a));
	}

	public static CustomerRequest buildCustomerRequest() {
		return new CustomerRequest("mock", 30, "00000-000", 10);
	}

	public static CustomerResponse buildCustomerResponse() {
		return new CustomerResponse(1L, "mock", 30, new Date(), new Date(),
				Arrays.asList(AddressTestMass.buildAddressRequest("00000-000")));
	}

	public static List<CustomerResponse> buildListCustomerResponse() {
		return Arrays.asList(
				new CustomerResponse(1L, "mock", 30, new Date(), new Date(),
						Arrays.asList(AddressTestMass.buildAddressRequest("00000-000"))),
				new CustomerResponse(2L, "mock2", 32, new Date(), new Date(),
						Arrays.asList(AddressTestMass.buildAddressRequest("00000-000"))));
	}

	public static String buildCustomerRequestAsString() {
		//@formatter:off
		return "{\r\n" + 
				"  \"age\": 0,\r\n" + 
				"  \"name\": \"string\",\r\n" + 
				"  \"number\": 0,\r\n" + 
				"  \"zipCode\": \"00000-000\"\r\n" + 
				"}";
		//@formatter:on
	}
	
	public static String buildCustomerRequestInvalidZipCodeAsString() {
		//@formatter:off
		return "{\r\n" + 
				"  \"age\": 0,\r\n" + 
				"  \"name\": \"string\",\r\n" + 
				"  \"number\": 0,\r\n" + 
				"  \"zipCode\": \"000-000\"\r\n" + 
				"}";
		//@formatter:on
	}

}
