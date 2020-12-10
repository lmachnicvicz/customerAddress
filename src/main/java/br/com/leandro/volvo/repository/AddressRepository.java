package br.com.leandro.volvo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.leandro.volvo.entity.Address;
import br.com.leandro.volvo.entity.AddressId;

public interface AddressRepository extends JpaRepository<Address, AddressId> {

}
