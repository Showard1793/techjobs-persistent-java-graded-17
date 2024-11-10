package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity {

    //ID and Name inherited from AbstractEntity

    // Change employer field to be of type Employer and @ManyToOne
    @ManyToOne
    private Employer employer;

    // Update skills to represent a many-to-many relationship with the Skill entity
    @ManyToMany
    @JoinTable(
            name = "job_skills",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @NotNull(message = "Skills are required")
    private List<Skill> skills = new ArrayList<>();

    // no arg constructor
    public Job() {
    }

    // Constructor
    public Job(Employer employer, List<Skill> skills) {
        this.employer = employer;
        this.skills = skills;
    }

    // Getters and setters

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

}
