package com.example.jobapp.ComapanyApi.repository;


import com.example.jobapp.ComapanyApi.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
