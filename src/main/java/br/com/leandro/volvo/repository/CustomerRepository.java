package br.com.leandro.volvo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.leandro.volvo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByName(String name);
}
