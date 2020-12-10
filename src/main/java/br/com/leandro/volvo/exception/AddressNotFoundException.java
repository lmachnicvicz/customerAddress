package br.com.leandro.volvo.exception;

import org.springframework.http.HttpStatus;

public class AddressNotFoundException extends BaseException {

	private static final long serialVersionUID = -7219132816779985274L;

	private static final ReturnCodes code = ReturnCodes.ADDRESS_NOT_FOUND;

	public AddressNotFoundException(Long userId) {
		super(code.getCode(), code.getMessage(), userId.toString(), code.getHttpCode());
	}

	public AddressNotFoundException(String zipCode, int number, HttpStatus status) {
		super(code.getCode(), code.getMessage(), String.format("ZipCode: %s Number: %s", zipCode, number), status);
	}

	public AddressNotFoundException(Long userId, Throwable ex) {
		super(code.getCode(), code.getMessage(), userId.toString(), code.getHttpCode(), ex);
	}

}
