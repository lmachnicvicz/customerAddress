package br.com.leandro.volvo.dto;

import java.util.Date;
import java.util.List;

public class CustomerResponse {
	private Long documentId;
	private String name;
	private int age;
	private Date registrationDate;
	private Date lastUpdatedDate;
	private List<AddressRequest> addresses;

	public CustomerResponse() {
	}

	public CustomerResponse(Long documentId, String name, int age, Date registrationDate, Date lastUpdatedDate,
			List<AddressRequest> addresses) {
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

	public List<AddressRequest> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressRequest> addresses) {
		this.addresses = addresses;
	}

}
