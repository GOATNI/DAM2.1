package com.example.jobapp.Jobapi;


import java.util.List;

public interface JobService {
    List<Job> findAll();
    Job getJobById(Long id);
    Job createJob(Job job);
    Job updateJob(Long id, Job job);
    boolean deleteJob(Long id);
}