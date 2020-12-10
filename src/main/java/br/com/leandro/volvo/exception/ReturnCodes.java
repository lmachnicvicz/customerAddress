package br.com.leandro.volvo.exception;

import org.springframework.http.HttpStatus;

public enum ReturnCodes {
	//@formatter:off
	CUSTOMER_NOT_FOUND(-1001, "Customer not found", HttpStatus.NOT_FOUND),
	ADDRESS_NOT_FOUND(-1002, "Address not found", HttpStatus.NOT_FOUND),
	AT_LEAST_ONE_ADDRESS(-1003, "At least one address is required", HttpStatus.UNPROCESSABLE_ENTITY);
	//@formatter:on

	private Integer code;
	private String message;
	private HttpStatus httpCode;

	private ReturnCodes(Integer code, String message, HttpStatus httpCode) {
		this.code = code;
		this.message = message;
		this.httpCode = httpCode;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpCode() {
		return httpCode;
	}

}
