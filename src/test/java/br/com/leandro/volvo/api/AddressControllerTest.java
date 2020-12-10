package br.com.leandro.volvo.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import br.com.leandro.volvo.exception.handler.BaseExceptionHandler;
import br.com.leandro.volvo.service.AddressService;

public class AddressControllerTest {
	private AutoCloseable autoCloseable;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());

	protected MockMvc mockMvc;
	protected HttpHeaders httpHeaders;

	@InjectMocks
	private AddressController controller;

	@Mock
	private AddressService service;

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
	public void shouldGetAllAddress() throws Exception {
		Mockito.when(service.findAll()).thenReturn(AddressTestMass.buildAddressResponseList());

		//@formatter:off
		mockMvc.perform(get("/api/v1/address").headers(httpHeaders))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(contentType))
			.andExpect(jsonPath("$", Matchers.notNullValue()))
			.andExpect(jsonPath("$.*", Matchers.hasSize(1)))
			.andExpect(jsonPath("$[0].*", Matchers.hasSize(3)))
			.andExpect(jsonPath("$[0].zipCode", Matchers.notNullValue()))
			.andExpect(jsonPath("$[0].number", Matchers.notNullValue()))
			.andExpect(jsonPath("$[0].customers", Matchers.notNullValue()))
			.andExpect(jsonPath("$[0].customers.*", Matchers.hasSize(1)))
			.andExpect(jsonPath("$[0].customers[0].*", Matchers.hasSize(2)))
			.andExpect(jsonPath("$[0].customers[0].documentId", Matchers.notNullValue()))
			.andExpect(jsonPath("$[0].customers[0].name", Matchers.notNullValue()))
			;
		//@formatter:on

		Mockito.verify(service).findAll();
		Mockito.verifyNoMoreInteractions(service);
	}

	@Test
	public void shouldGetAddressByZipCode() throws Exception {
		Mockito.when(service.findByZipCode(Mockito.anyString())).thenReturn(AddressTestMass.buildAddressResponseList());

		//@formatter:off
		mockMvc.perform(get("/api/v1/address?zipCode=00000-000").headers(httpHeaders))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(contentType))
			.andExpect(jsonPath("$", Matchers.notNullValue()))
			.andExpect(jsonPath("$.*", Matchers.hasSize(1)))
			.andExpect(jsonPath("$[0].*", Matchers.hasSize(3)))
			.andExpect(jsonPath("$[0].zipCode", Matchers.notNullValue()))
			.andExpect(jsonPath("$[0].number", Matchers.notNullValue()))
			.andExpect(jsonPath("$[0].customers", Matchers.notNullValue()))
			.andExpect(jsonPath("$[0].customers.*", Matchers.hasSize(1)))
			.andExpect(jsonPath("$[0].customers[0].*", Matchers.hasSize(2)))
			.andExpect(jsonPath("$[0].customers[0].documentId", Matchers.notNullValue()))
			.andExpect(jsonPath("$[0].customers[0].name", Matchers.notNullValue()))
			;
		//@formatter:on

		Mockito.verify(service).findByZipCode("00000-000");
		Mockito.verifyNoMoreInteractions(service);
	}

}
