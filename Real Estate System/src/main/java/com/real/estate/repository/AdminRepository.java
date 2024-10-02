package com.real.estate.repository;

import com.real.estate.model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Long>
{
    Optional<AdminModel> findById(long id);

    boolean existsByEmail(String email);
}
