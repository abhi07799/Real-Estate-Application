package com.real.estate.repository;

import com.real.estate.model.PropertyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyModel, Long>
{
    Optional<PropertyModel> findById(long propertyId);

    List<PropertyModel> findByPropertyTitleContainingIgnoreCase(String title);
}
