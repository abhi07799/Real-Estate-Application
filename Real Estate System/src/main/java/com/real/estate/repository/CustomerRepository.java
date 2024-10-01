package com.real.estate.repository;

import com.real.estate.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long>
{
    Optional<CustomerModel> findById(long id);
}
