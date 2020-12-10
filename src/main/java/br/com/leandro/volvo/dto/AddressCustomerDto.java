package br.com.leandro.volvo.dto;

import io.swagger.annotations.ApiModelProperty;

public class AddressCustomerDto {
	@ApiModelProperty(value = "Document Id", example = "1")
	private long documentId;
	@ApiModelProperty(value = "Name", example = "Joao")
	private String name;

	public AddressCustomerDto() {
	}

	public AddressCustomerDto(long documentId, String name) {
		this.documentId = documentId;
		this.name = name;
	}

	public long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(long documentId) {
		this.documentId = documentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AddressCustomerDto [documentId=" + documentId + ", name=" + name + "]";
	}

}
