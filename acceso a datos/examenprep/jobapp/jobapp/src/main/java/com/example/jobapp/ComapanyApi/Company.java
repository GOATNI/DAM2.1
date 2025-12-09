package com.example.jobapp.ComapanyApi;


import com.example.jobapp.Jobapi.Job;
import com.example.jobapp.ReviewsApi.Reviws;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Reviws> reviews;
}