package br.com.leandro.volvo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.leandro.volvo.entity.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository repo;

	@Before
	public void setup() {
		repo.deleteAll();
	}

	@Test
	public void shouldSaveCustomer() {
		Customer c = new Customer(1L,"mock",30, new Date(), new Date(), null);
		
		Customer result = repo.save(c);

		assertNotNull(result);
		assertNotNull(c.getDocumentId());
		assertEquals("mock", c.getName());
		assertEquals(30, c.getAge());
	}

	@Test
	public void shouldGetDepartment() {
		Customer c = new Customer(1L,"mock",30, new Date(), new Date(), null);
		Customer saved = repo.save(c);
		
		Customer result = repo.findById(saved.getDocumentId()).orElse(null);
		
		assertNotNull(result);
		assertNotNull(c.getDocumentId());
		assertEquals("mock", c.getName());
		assertEquals(30, c.getAge());
	}

}
