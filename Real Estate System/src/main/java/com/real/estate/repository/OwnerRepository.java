package com.real.estate.repository;

import com.real.estate.model.OwnerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerModel, Long>
{
    Optional<OwnerModel> findById(long id);
}
