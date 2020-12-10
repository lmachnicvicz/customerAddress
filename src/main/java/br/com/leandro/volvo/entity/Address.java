package br.com.leandro.volvo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Address {
	@NotNull
	@EmbeddedId
	private AddressId id;
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "address")
	private Set<Customer> customer = new HashSet<>();

	public Address() {

	}

	public Address(@NotNull AddressId id) {
		this.id = id;
	}

	public Address(@NotNull AddressId id, Set<Customer> customer) {
		this.id = id;
		this.customer = customer;
	}

	public AddressId getId() {
		return id;
	}

	public void setId(AddressId id) {
		this.id = id;
	}

	public Set<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(Set<Customer> customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", customer=" + customer + "]";
	}

}
