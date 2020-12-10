package br.com.leandro.volvo.service;

import java.util.List;

import br.com.leandro.volvo.dto.CustomerAddressDto;
import br.com.leandro.volvo.dto.CustomerRequest;
import br.com.leandro.volvo.dto.CustomerResponse;

public interface CustomerCrudService {

	CustomerResponse create(CustomerRequest request);

	List<CustomerResponse> findAll();

	CustomerResponse findById(long id);

	CustomerResponse update(long id, CustomerRequest request);

	void delete(long id);

	void deleteAddress(long id, CustomerAddressDto request);

}
