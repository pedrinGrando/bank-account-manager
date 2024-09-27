package com.pedro.accountsservice.repository;

import com.pedro.accountsservice.model.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<Adress, Long> {
}
