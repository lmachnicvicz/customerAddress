package br.com.leandro.volvo.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.leandro.volvo.config.AddressTestMass;
import br.com.leandro.volvo.dto.AddressResponse;
import br.com.leandro.volvo.repository.AddressRepository;
import br.com.leandro.volvo.service.AddressService;

public class AddressServiceImplTest {

	private AutoCloseable closeable;

	@Mock
	private AddressRepository repo;

	private AddressService service;

	@Before
	public void setup() {
		closeable = MockitoAnnotations.openMocks(this);

		service = new AddressServiceImpl(repo);
	}

	@After
	public void close() throws Exception {
		closeable.close();
	}

	@Test
	public void shouldGetAllAddress() {
		Mockito.when(repo.findAll()).thenReturn(AddressTestMass.buildListAddress());

		List<AddressResponse> list = service.findAll();

		assertNotNull(list);
		assertEquals(2, list.size());

		Mockito.verify(repo).findAll();
		Mockito.verifyNoMoreInteractions(repo);
	}

	@Test
	public void shouldGetAddressByZipCode() {
		Mockito.when(repo.findByIdZipCode(Mockito.anyString())).thenReturn(AddressTestMass.buildListAddress());

		List<AddressResponse> list = service.findByZipCode("00000-000");

		assertNotNull(list);
		assertEquals(2, list.size());

		Mockito.verify(repo).findByIdZipCode(Mockito.eq("00000-000"));
		Mockito.verifyNoMoreInteractions(repo);
	}

}
