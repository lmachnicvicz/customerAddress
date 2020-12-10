package br.com.leandro.volvo.dto;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class CustomerResponse {
	@ApiModelProperty(value = "Document Id", example = "1")
	private Long documentId;
	@ApiModelProperty(value = "Name", example = "Joao")
	private String name;
	@ApiModelProperty(value = "Age", example = "30")
	private int age;
	@ApiModelProperty(value = "Registration Date")
	private Date registrationDate;
	@ApiModelProperty(value = "Last Updated Date")
	private Date lastUpdatedDate;
	@ApiModelProperty(value = "List of customer addresses")
	private List<CustomerAddressDto> addresses;

	public CustomerResponse() {
	}

	public CustomerResponse(Long documentId, String name, int age, Date registrationDate, Date lastUpdatedDate,
			List<CustomerAddressDto> addresses) {
		this.documentId = documentId;
		this.name = name;
		this.age = age;
		this.registrationDate = registrationDate;
		this.lastUpdatedDate = lastUpdatedDate;
		this.addresses = addresses;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public List<CustomerAddressDto> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<CustomerAddressDto> addresses) {
		this.addresses = addresses;
	}

}
