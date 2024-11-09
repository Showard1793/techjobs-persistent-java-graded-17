package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Employer extends AbstractEntity {

    @NotNull(message = "Location is required")
    @Size(max = 255, message = "Location must not be longer than 255 characters")
    private String location;

    // No-arg constructor
    public Employer() {
    }

    // Getter - Location
    public String getLocation() {
        return location;
    }

    // Setter - Location
    public void setLocation(String location) {
        this.location = location;
    }
}