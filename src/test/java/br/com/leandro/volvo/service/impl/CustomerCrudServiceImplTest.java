package br.com.leandro.volvo.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import br.com.leandro.volvo.config.AddressTestMass;
import br.com.leandro.volvo.config.CustomerTestMass;
import br.com.leandro.volvo.dto.CustomerResponse;
import br.com.leandro.volvo.exception.AddressNotFoundException;
import br.com.leandro.volvo.exception.AtLeastOneAddressException;
import br.com.leandro.volvo.exception.CustomerNotFoundException;
import br.com.leandro.volvo.repository.AddressRepository;
import br.com.leandro.volvo.repository.CustomerRepository;
import br.com.leandro.volvo.service.CustomerCrudService;

public class CustomerCrudServiceImplTest {

	private AutoCloseable closeable;

	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private AddressRepository addressRepository;

	private CustomerCrudService service;

	@Before
	public void setup() {
		closeable = MockitoAnnotations.openMocks(this);

		service = new CustomerCrudServceImpl(customerRepository, addressRepository);
	}

	@After
	public void close() throws Exception {
		closeable.close();
	}

	@Test
	public void shouldGetCustomerWithOneAddress() {
		Mockito.when(customerRepository.findById(Mockito.anyLong()))
				.thenReturn(Optional.of(CustomerTestMass.buildCustomer()));

		CustomerResponse response = service.findById(1L);

		assertNotNull(response);
		assertEquals(1L, response.getDocumentId().longValue());
		assertEquals("mock", response.getName());
		assertNotNull(response.getAddresses());
		assertEquals(1, response.getAddresses().size());

		Mockito.verify(customerRepository).findById(Mockito.eq(1L));
		Mockito.verifyNoMoreInteractions(customerRepository, addressRepository);
	}

	@Test
	public void shouldGetAllCustomers() {
		Mockito.when(customerRepository.findAll()).thenReturn(CustomerTestMass.buildListCustomer());

		List<CustomerResponse> list = service.findAll();

		assertNotNull(list);
		assertEquals(2, list.size());

		Mockito.verify(customerRepository).findAll();
		Mockito.verifyNoMoreInteractions(customerRepository, addressRepository);
	}

	@Test
	public void shouldCreateCustomer() {
		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(CustomerTestMass.buildCustomer());

		CustomerResponse response = service.create(CustomerTestMass.buildCustomerRequest());

		assertNotNull(response);
		assertEquals(1L, response.getDocumentId().longValue());
		assertEquals("mock", response.getName());
		assertNotNull(response.getAddresses());
		assertEquals(1, response.getAddresses().size());
		assertEquals("00000-000", response.getAddresses().get(0).getZipCode());

		Mockito.verify(addressRepository).findById(Mockito.any());
		Mockito.verify(customerRepository).save(Mockito.any());
		Mockito.verifyNoMoreInteractions(customerRepository, addressRepository);
	}

	@Test
	public void shouldUpdateCustomer() {
		Mockito.when(customerRepository.findById(Mockito.anyLong()))
				.thenReturn(Optional.of(CustomerTestMass.buildCustomer()));
		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(CustomerTestMass.buildCustomer());

		CustomerResponse response = service.update(1L, CustomerTestMass.buildCustomerRequest());

		assertNotNull(response);
		assertEquals(1L, response.getDocumentId().longValue());
		assertEquals("mock", response.getName());
		assertNotNull(response.getAddresses());
		assertEquals(1, response.getAddresses().size());
		assertEquals("00000-000", response.getAddresses().get(0).getZipCode());

		Mockito.verify(customerRepository).findById(Mockito.eq(1L));
		Mockito.verify(addressRepository).findById(Mockito.any());
		Mockito.verify(customerRepository).save(Mockito.any());
		Mockito.verifyNoMoreInteractions(customerRepository, addressRepository);
	}

	@Test
	public void shouldThrowExceptionWhenUpdateCustomerNotFound() {
		try {
			service.update(1L, CustomerTestMass.buildCustomerRequest());
			fail("Exception wanted");
		} catch (Exception e) {
			assertEquals(CustomerNotFoundException.class, e.getClass());
		}

		Mockito.verify(customerRepository).findById(Mockito.eq(1L));
		Mockito.verifyNoMoreInteractions(customerRepository, addressRepository);
	}

	@Test
	public void shouldDeleteCustomer() {
		service.delete(1L);

		Mockito.verify(customerRepository).deleteById(Mockito.eq(1L));
		Mockito.verifyNoMoreInteractions(customerRepository, addressRepository);
	}

	@Test
	public void shouldThrowExceptionWhenDeleteInvalidCustomer() {
		Mockito.doThrow(new EmptyResultDataAccessException(0)).when(customerRepository).deleteById(Mockito.anyLong());

		try {
			service.delete(1L);
			fail("Exception wanted");
		} catch (Exception e) {
			assertEquals(CustomerNotFoundException.class, e.getClass());
		}

		Mockito.verify(customerRepository).deleteById(Mockito.eq(1L));
		Mockito.verifyNoMoreInteractions(customerRepository, addressRepository);
	}

	@Test
	public void shouldDeleteAddressFromCustomer() {
		Mockito.when(customerRepository.findById(Mockito.anyLong()))
				.thenReturn(Optional.of(CustomerTestMass.buildCustomer2Address()));

		service.deleteAddress(1L, AddressTestMass.buildAddressRequest("00000-000"));

		Mockito.verify(customerRepository).findById(Mockito.eq(1L));
		Mockito.verify(customerRepository).save(Mockito.any());
		Mockito.verifyNoMoreInteractions(customerRepository, addressRepository);
	}

	@Test
	public void shouldThrowExceptionWhenDeleteAddressFromCustomerNotFound() {
		try {
			service.deleteAddress(1L, AddressTestMass.buildAddressRequest("00000-000"));
			fail("Exception wanted");
		} catch (Exception e) {
			assertEquals(CustomerNotFoundException.class, e.getClass());
		}

		Mockito.verify(customerRepository).findById(Mockito.eq(1L));
		Mockito.verifyNoMoreInteractions(customerRepository, addressRepository);
	}

	@Test
	public void shouldThrowExceptionWhenDeleteAddressFromCustomerWithOnlyOneAddress() {
		Mockito.when(customerRepository.findById(Mockito.anyLong()))
				.thenReturn(Optional.of(CustomerTestMass.buildCustomer()));

		try {
			service.deleteAddress(1L, AddressTestMass.buildAddressRequest("00000-000"));
			fail("Exception wanted");
		} catch (Exception e) {
			assertEquals(AtLeastOneAddressException.class, e.getClass());
		}

		Mockito.verify(customerRepository).findById(Mockito.eq(1L));
		Mockito.verifyNoMoreInteractions(customerRepository, addressRepository);
	}

	@Test
	public void shouldThrowExceptionWhenDeleteInvalidAddressFromCustomer() {
		Mockito.when(customerRepository.findById(Mockito.anyLong()))
				.thenReturn(Optional.of(CustomerTestMass.buildCustomer()));

		try {
			service.deleteAddress(1L, AddressTestMass.buildAddressRequest("00000-001"));
			fail("Exception wanted");
		} catch (Exception e) {
			assertEquals(AddressNotFoundException.class, e.getClass());
		}

		Mockito.verify(customerRepository).findById(Mockito.eq(1L));
		Mockito.verifyNoMoreInteractions(customerRepository, addressRepository);
	}
}
