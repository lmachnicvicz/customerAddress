package br.com.leandro.volvo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.leandro.volvo.dto.AddressResponse;
import br.com.leandro.volvo.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/address")
@Api(value = "Endpoint containing query operations for Address entity")
public class AddressController {

	@Autowired
	private AddressService service;

	@GetMapping()
	@ApiOperation(value = "Endpoint to find all Addresses")
	public ResponseEntity<List<AddressResponse>> findAll(
			@ApiParam(name = "zipCode", value = "Zip Code", example = "00000-000", required = false) @RequestParam(name = "zipCode", required = false) String zipCode) {
		List<AddressResponse> list;
		if (ObjectUtils.isEmpty(zipCode)) {
			list = service.findAll();
		} else {
			list = service.findByZipCode(zipCode);
		}

		return ResponseEntity.ok(list);
	}

}
