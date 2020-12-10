package br.com.leandro.volvo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.leandro.volvo.config.AddressTestMass;
import br.com.leandro.volvo.entity.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressRepositoryTest {

	@Autowired
	private AddressRepository repo;

	@Before
	public void setup() {
		repo.deleteAll();
	}

	@Test
	public void shouldSaveCustomer() {

		Address result = repo.save(AddressTestMass.buildAddress("00000-000"));

		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals("00000-000", result.getId().getZipCode());
		assertEquals(10, result.getId().getNumber());
	}

	@Test
	@Transactional
	public void shouldGetAddressByZipCode() {
		repo.save(AddressTestMass.buildAddress("00000-000"));
		repo.save(AddressTestMass.buildAddress("00000-001"));

		List<Address> list = repo.findByIdZipCode("00000-000");

		assertNotNull(list);
		assertEquals(1, list.size());
	}

}
