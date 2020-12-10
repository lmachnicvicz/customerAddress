package br.com.leandro.volvo.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long documentId;
	private String name;
	private int age;
	private Date registrationDate;
	private Date lastUpdatedDate;
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	//@formatter:off
	@JoinTable(name = "customer_address", 
		joinColumns = @JoinColumn(name = "customer_documentId"), 
		inverseJoinColumns = {@JoinColumn(name = "address_zipCode"),
							  @JoinColumn(name = "address_numer")})
	//@formatter:on
	private Set<Address> address = new HashSet<>();

	public Customer() {
	}

	public Customer(long documentId, String name, int age, Date registrationDate, Date lastUpdatedDate,
			Set<Address> address) {
		super();
		this.documentId = documentId;
		this.name = name;
		this.age = age;
		this.registrationDate = registrationDate;
		this.lastUpdatedDate = lastUpdatedDate;
		this.address = address;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [documentId=" + documentId + ", name=" + name + ", age=" + age + ", registrationDate="
				+ registrationDate + ", lastUpdatedDate=" + lastUpdatedDate + ", address=" + address + "]";
	}

}
