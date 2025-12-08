package com.example.jobapp.Jobapi.repository;



import com.example.jobapp.Jobapi.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}