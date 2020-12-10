package br.com.leandro.volvo.service;

import java.util.List;

import br.com.leandro.volvo.dto.AddressResponse;

public interface AddressService {

	List<AddressResponse> findAll();

	List<AddressResponse> findByZipCode(String zipCode);

}
