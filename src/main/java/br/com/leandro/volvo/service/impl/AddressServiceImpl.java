package br.com.leandro.volvo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.leandro.volvo.dto.AddressCustomerDto;
import br.com.leandro.volvo.dto.AddressResponse;
import br.com.leandro.volvo.entity.Address;
import br.com.leandro.volvo.entity.Customer;
import br.com.leandro.volvo.repository.AddressRepository;
import br.com.leandro.volvo.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	private AddressRepository repo;

	public AddressServiceImpl(AddressRepository repo) {
		this.repo = repo;
	}

	@Override
	@Transactional
	public List<AddressResponse> findAll() {
		return repo.findAll().stream().map(this::toAddressResponse).collect(Collectors.toList());
	}

	@Override
	public List<AddressResponse> findByZipCode(String zipCode) {
		return repo.findByIdZipCode(zipCode).stream().map(this::toAddressResponse).collect(Collectors.toList());
	}

	private AddressResponse toAddressResponse(Address address) {
		return new AddressResponse(address.getId().getZipCode(), address.getId().getNumber(),
				address.getCustomer().stream().map(this::toAddressCustomerDto).collect(Collectors.toList()));
	}

	private AddressCustomerDto toAddressCustomerDto(Customer customer) {
		return new AddressCustomerDto(customer.getDocumentId(), customer.getName());
	}
}
