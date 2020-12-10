package br.com.leandro.volvo.exception;

public class CustomerNotFoundException extends BaseException {

	private static final long serialVersionUID = -5139435003116752231L;

	private static final ReturnCodes code = ReturnCodes.CUSTOMER_NOT_FOUND;

	public CustomerNotFoundException(Long departmentId) {
		super(code.getCode(), code.getMessage(), departmentId.toString(), code.getHttpCode());
	}

	public CustomerNotFoundException(Long departmentId, Throwable ex) {
		super(code.getCode(), code.getMessage(), departmentId.toString(), code.getHttpCode(), ex);
	}

}
