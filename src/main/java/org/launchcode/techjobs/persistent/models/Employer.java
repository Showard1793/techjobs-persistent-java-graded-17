package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotNull(message = "Location is required")
    @Size(max = 255, message = "Location must not be longer than 255 characters")
    private String location;

    // Add a jobs field with @OneToMany and @JoinColumn
    @OneToMany
    @JoinColumn(name = "employer_id")
    private List<Job> jobs = new ArrayList<>();

    // No-arg constructor
    public Employer() {
    }

    //LOCATION

    // Getter
    public String getLocation() {
        return location;
    }

    // Setter
    public void setLocation(String location) {
        this.location = location;
    }

    // JOBS
    //Make Getter and Setter for jobs

    // Getter
    public List<Job> getJobs() {
        return jobs;
    }

    // Setter
    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}