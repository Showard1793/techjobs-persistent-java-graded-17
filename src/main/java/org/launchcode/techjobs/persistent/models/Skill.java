package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

    @Size(max = 500, message = "Description must not be longer than 500 characters")
    private String description;

    // Add a jobs field (ArrayList type)
    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs = new ArrayList<>();

    // No-arg Constructor
    public Skill() {
    }

    // Getter and Setter - description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter - jobs
    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}