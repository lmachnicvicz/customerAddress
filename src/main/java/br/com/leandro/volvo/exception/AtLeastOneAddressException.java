package br.com.leandro.volvo.exception;

public class AtLeastOneAddressException extends BaseException {

	private static final long serialVersionUID = -5139435003116752231L;

	private static final ReturnCodes code = ReturnCodes.AT_LEAST_ONE_ADDRESS;

	public AtLeastOneAddressException() {
		super(code.getCode(), code.getMessage(), "", code.getHttpCode());
	}

}
