package br.com.leandro.volvo.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.leandro.volvo.dto.CustomerAddressDto;
import br.com.leandro.volvo.dto.CustomerRequest;
import br.com.leandro.volvo.dto.CustomerResponse;
import br.com.leandro.volvo.service.CustomerCrudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/customer")
@Api(value = "Endpoint containing Crud operations for Customer entity")
public class CustomerCrudController {

	@Autowired
	private CustomerCrudService service;

	@GetMapping()
	@ApiOperation(value = "Endpoint to find all Customers")
	public ResponseEntity<List<CustomerResponse>> findAll() {
		List<CustomerResponse> list = service.findAll();

		return ResponseEntity.ok(list);
	}

	@GetMapping("{id}")
	@ApiOperation(value = "Endpoint to search for a Customer")
	public ResponseEntity<CustomerResponse> getById(
			@ApiParam(name = "id", value = "Customer Document Id", example = "1") @PathVariable(name = "id", required = true) long id) {
		CustomerResponse result = service.findById(id);

		if (result != null) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping()
	@ApiOperation(value = "Endpoint to create a Customer")
	public ResponseEntity<CustomerResponse> create(@RequestBody @Valid CustomerRequest request) {
		CustomerResponse response = service.create(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("{id}")
	@ApiOperation(value = "Endpoint to update a Customer")
	public ResponseEntity<CustomerResponse> update(
			@ApiParam(name = "id", value = "Customer Document Id", example = "1") @PathVariable(name = "id", required = true) long id,
			@RequestBody @Valid CustomerRequest request) {
		CustomerResponse response = service.update(id, request);

		if (response != null) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "Endpoint to delete a Customer")
	public ResponseEntity<Object> delete(
			@ApiParam(name = "id", value = "Customer Document Id", example = "1") @PathVariable(name = "id", required = true) long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("{id}/address")
	@ApiOperation(value = "Endpoint to delete a Customer")
	public ResponseEntity<Object> deleteAddress(
			@ApiParam(name = "id", value = "Customer Document Id", example = "1") @PathVariable(name = "id", required = true) long id,
			@RequestBody @Valid CustomerAddressDto request) {
		service.deleteAddress(id, request);

		return ResponseEntity.noContent().build();
	}
}
