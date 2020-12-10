package br.com.leandro.volvo.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import br.com.leandro.volvo.dto.CustomerAddressDto;
import br.com.leandro.volvo.dto.CustomerRequest;
import br.com.leandro.volvo.dto.CustomerResponse;
import br.com.leandro.volvo.entity.Address;
import br.com.leandro.volvo.entity.AddressId;
import br.com.leandro.volvo.entity.Customer;
import br.com.leandro.volvo.exception.AddressNotFoundException;
import br.com.leandro.volvo.exception.AtLeastOneAddressException;
import br.com.leandro.volvo.exception.CustomerNotFoundException;
import br.com.leandro.volvo.repository.AddressRepository;
import br.com.leandro.volvo.repository.CustomerRepository;
import br.com.leandro.volvo.service.CustomerCrudService;

@Service
public class CustomerCrudServiceImpl implements CustomerCrudService {

	private CustomerRepository customerRepository;
	private AddressRepository addressRepository;

	@Autowired
	public CustomerCrudServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository) {
		this.customerRepository = customerRepository;
		this.addressRepository = addressRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CustomerResponse> findAll() {
		return customerRepository.findAll().stream().map(this::toCustomerResponse).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public CustomerResponse findById(long id) {
		return customerRepository.findById(id).map(this::toCustomerResponse).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public CustomerResponse create(CustomerRequest request) {
		AddressId addressId = new AddressId(request.getZipCode(), request.getNumber());

		Address address = addressRepository.findById(addressId).orElse(new Address(addressId));

		Set<Address> addressList = new HashSet<>();
		addressList.add(address);

		Customer c = toCustomer(request, addressList);

		Customer save = customerRepository.save(c);

		return toCustomerResponse(save);
	}

	@Override
	@Transactional(readOnly = false)
	public CustomerResponse update(long id, CustomerRequest request) {
		Customer c = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

		if (!ObjectUtils.isEmpty(request.getName())) {
			c.setName(request.getName());
		}
		if (request.getAge() != null) {
			c.setAge(request.getAge());
		}
		if (!ObjectUtils.isEmpty(request.getZipCode()) && !ObjectUtils.isEmpty(request.getNumber())) {
			AddressId addressId = new AddressId(request.getZipCode(), request.getNumber());

			Address address = addressRepository.findById(addressId).orElse(new Address(addressId));

			c.getAddress().add(address);
		}
		c.setLastUpdatedDate(new Date());

		Customer save = customerRepository.save(c);

		return toCustomerResponse(save);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(long id) {
		try {
			customerRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new CustomerNotFoundException(id, ex);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteAddress(long id, CustomerAddressDto request) {
		Customer c = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

		Address address = c.getAddress().stream()
				.filter(a -> a.getId().getZipCode().equals(request.getZipCode())
						&& a.getId().getNumber() == request.getNumber())
				.findFirst().orElseThrow(() -> new AddressNotFoundException(request.getZipCode(), request.getNumber(),
						HttpStatus.UNPROCESSABLE_ENTITY));

		c.getAddress().remove(address);

		if (c.getAddress().size() == 0) {
			throw new AtLeastOneAddressException();
		}

		customerRepository.save(c);
	}

	private Customer toCustomer(CustomerRequest request, Set<Address> addressList) {
		Customer c = new Customer();
		c.setName(request.getName());
		c.setAge(request.getAge());
		c.setAddress(addressList);
		c.setRegistrationDate(new Date());
		c.setLastUpdatedDate(new Date());

		return c;
	}

	private CustomerResponse toCustomerResponse(Customer c) {
		CustomerResponse response = new CustomerResponse();
		response.setDocumentId(c.getDocumentId());
		response.setName(c.getName());
		response.setAge(c.getAge());
		response.setRegistrationDate(c.getRegistrationDate());
		response.setLastUpdatedDate(c.getLastUpdatedDate());
		response.setAddresses(
				c.getAddress().stream().map(a -> new CustomerAddressDto(a.getId().getZipCode(), a.getId().getNumber()))
						.collect(Collectors.toList()));
		return response;
	}
}
