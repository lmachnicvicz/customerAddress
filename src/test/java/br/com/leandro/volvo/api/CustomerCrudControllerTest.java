package br.com.leandro.volvo.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.leandro.volvo.config.AddressTestMass;
import br.com.leandro.volvo.config.CustomerTestMass;
import br.com.leandro.volvo.exception.handler.BaseExceptionHandler;
import br.com.leandro.volvo.service.CustomerCrudService;

public class CustomerCrudControllerTest {
	private AutoCloseable autoCloseable;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());

	protected MockMvc mockMvc;
	protected HttpHeaders httpHeaders;

	@InjectMocks
	private CustomerCrudController controller;

	@Mock
	private CustomerCrudService service;

	@Before
	public void setup() {
		autoCloseable = MockitoAnnotations.openMocks(this);

		httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new BaseExceptionHandler(new ObjectMapper())).build();
	}

	@After
	public void close() throws Exception {
		autoCloseable.close();
	}

	@Test
	public void shouldGetCustomerById() throws Exception {
		Mockito.when(service.findById(Mockito.anyLong())).thenReturn(CustomerTestMass.buildCustomerResponse());

		//@formatter:off
		mockMvc.perform(get("/api/v1/customer/1").headers(httpHeaders))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(contentType))
			.andExpect(jsonPath("$", Matchers.notNullValue()))
			.andExpect(jsonPath("$.*", Matchers.hasSize(6)))
			.andExpect(jsonPath("$.documentId", Matchers.notNullValue()))
			.andExpect(jsonPath("$.name", Matchers.notNullValue()))
			.andExpect(jsonPath("$.age", Matchers.notNullValue()))
			.andExpect(jsonPath("$.registrationDate", Matchers.notNullValue()))
			.andExpect(jsonPath("$.lastUpdatedDate", Matchers.notNullValue()))
			.andExpect(jsonPath("$.addresses", Matchers.notNullValue()))
			.andExpect(jsonPath("$.addresses", Matchers.hasSize(1)))
			.andExpect(jsonPath("$.addresses[0].*", Matchers.hasSize(2)))
			.andExpect(jsonPath("$.addresses[0].zipCode", Matchers.notNullValue()))
			.andExpect(jsonPath("$.addresses[0].number", Matchers.notNullValue()))
			;
		//@formatter:on

		Mockito.verify(service).findById(Mockito.eq(1L));
		Mockito.verifyNoMoreInteractions(service);
	}

	@Test
	public void shouldGetAllCustomers() throws Exception {
		Mockito.when(service.findAll()).thenReturn(CustomerTestMass.buildListCustomerResponse());

		//@formatter:off
		mockMvc.perform(get("/api/v1/customer").headers(httpHeaders))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(contentType))
			.andExpect(jsonPath("$", Matchers.notNullValue()))
			.andExpect(jsonPath("$.*", Matchers.hasSize(2)))
			.andExpect(jsonPath("$[0].*", Matchers.hasSize(6)))
			.andExpect(jsonPath("$[0].documentId", Matchers.notNullValue()))
			.andExpect(jsonPath("$[0].name", Matchers.notNullValue()))
			.andExpect(jsonPath("$[0].age", Matchers.notNullValue()))
			.andExpect(jsonPath("$[0].registrationDate", Matchers.notNullValue()))
			.andExpect(jsonPath("$[0].lastUpdatedDate", Matchers.notNullValue()))
			.andExpect(jsonPath("$[0].addresses", Matchers.notNullValue()))
			.andExpect(jsonPath("$[0].addresses", Matchers.hasSize(1)))
			.andExpect(jsonPath("$[0].addresses[0].*", Matchers.hasSize(2)))
			.andExpect(jsonPath("$[0].addresses[0].zipCode", Matchers.notNullValue()))
			.andExpect(jsonPath("$[0].addresses[0].number", Matchers.notNullValue()))
			;
		//@formatter:on

		Mockito.verify(service).findAll();
		Mockito.verifyNoMoreInteractions(service);
	}

	@Test
	public void shouldCreateCustomer() throws Exception {
		Mockito.when(service.create(Mockito.any())).thenReturn(CustomerTestMass.buildCustomerResponse());

		//@formatter:off
		mockMvc.perform(post("/api/v1/customer").headers(httpHeaders)
				.content(CustomerTestMass.buildCustomerRequestAsString()))
			.andExpect(status().isCreated())
			.andExpect(content().contentTypeCompatibleWith(contentType))
			.andExpect(jsonPath("$", Matchers.notNullValue()))
			.andExpect(jsonPath("$.*", Matchers.hasSize(6)))
			.andExpect(jsonPath("$.documentId", Matchers.notNullValue()))
			.andExpect(jsonPath("$.name", Matchers.notNullValue()))
			.andExpect(jsonPath("$.age", Matchers.notNullValue()))
			.andExpect(jsonPath("$.registrationDate", Matchers.notNullValue()))
			.andExpect(jsonPath("$.lastUpdatedDate", Matchers.notNullValue()))
			.andExpect(jsonPath("$.addresses", Matchers.notNullValue()))
			.andExpect(jsonPath("$.addresses", Matchers.hasSize(1)))
			.andExpect(jsonPath("$.addresses[0].*", Matchers.hasSize(2)))
			.andExpect(jsonPath("$.addresses[0].zipCode", Matchers.notNullValue()))
			.andExpect(jsonPath("$.addresses[0].number", Matchers.notNullValue()))
			;
		//@formatter:on

		Mockito.verify(service).create(Mockito.any());
		Mockito.verifyNoMoreInteractions(service);
	}

	@Test
	public void shouldThrowExceptionWhenCreateCustomerInvalidZipCode() throws Exception {
		Mockito.when(service.create(Mockito.any())).thenReturn(CustomerTestMass.buildCustomerResponse());

		//@formatter:off
		mockMvc.perform(post("/api/v1/customer").headers(httpHeaders)
				.content(CustomerTestMass.buildCustomerRequestInvalidZipCodeAsString()))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentTypeCompatibleWith(contentType))
			;
		//@formatter:on

		Mockito.verifyNoMoreInteractions(service);
	}

	@Test
	public void shouldUpdateCustomer() throws Exception {
		Mockito.when(service.update(Mockito.anyLong(), Mockito.any()))
				.thenReturn(CustomerTestMass.buildCustomerResponse());

		//@formatter:off
		mockMvc.perform(put("/api/v1/customer/1").headers(httpHeaders)
				.content(CustomerTestMass.buildCustomerRequestAsString()))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(contentType))
			.andExpect(jsonPath("$", Matchers.notNullValue()))
			.andExpect(jsonPath("$.*", Matchers.hasSize(6)))
			.andExpect(jsonPath("$.documentId", Matchers.notNullValue()))
			.andExpect(jsonPath("$.name", Matchers.notNullValue()))
			.andExpect(jsonPath("$.age", Matchers.notNullValue()))
			.andExpect(jsonPath("$.registrationDate", Matchers.notNullValue()))
			.andExpect(jsonPath("$.lastUpdatedDate", Matchers.notNullValue()))
			.andExpect(jsonPath("$.addresses", Matchers.notNullValue()))
			.andExpect(jsonPath("$.addresses", Matchers.hasSize(1)))
			.andExpect(jsonPath("$.addresses[0].*", Matchers.hasSize(2)))
			.andExpect(jsonPath("$.addresses[0].zipCode", Matchers.notNullValue()))
			.andExpect(jsonPath("$.addresses[0].number", Matchers.notNullValue()))
			;
		//@formatter:on

		Mockito.verify(service).update(Mockito.eq(1L), Mockito.any());
		Mockito.verifyNoMoreInteractions(service);
	}

	@Test
	public void shouldDeleteCustomer() throws Exception {

		//@formatter:off
		mockMvc.perform(delete("/api/v1/customer/1").headers(httpHeaders))
			.andExpect(status().isNoContent())
			;
		//@formatter:on

		Mockito.verify(service).delete(Mockito.eq(1L));
		Mockito.verifyNoMoreInteractions(service);
	}

	@Test
	public void shouldDeleteAddressFromCustomer() throws Exception {
		//@formatter:off
		mockMvc.perform(delete("/api/v1/customer/1/address").headers(httpHeaders)
				.content(AddressTestMass.buildAddressRequestAsString()))
			.andExpect(status().isNoContent())
			;
		//@formatter:on

		Mockito.verify(service).deleteAddress(Mockito.eq(1L), Mockito.any());
		Mockito.verifyNoMoreInteractions(service);
	}
	
	@Test
	public void shouldThrowExceptionWhenDeleteAddressFromCustomerInvalidZipCode() throws Exception {
		//@formatter:off
		mockMvc.perform(delete("/api/v1/customer/1/address").headers(httpHeaders)
				.content(AddressTestMass.buildAddressRequestInvalidZipCodeAsString()))
			.andExpect(status().isBadRequest())
			;
		//@formatter:on

		Mockito.verifyNoMoreInteractions(service);
	}
}
