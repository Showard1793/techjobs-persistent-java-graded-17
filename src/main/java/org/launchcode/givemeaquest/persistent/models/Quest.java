package org.launchcode.givemeaquest.persistent.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Quest extends AbstractEntity {

    @NotNull(message = "FlavorText is required")
    @Size(max = 255, message = "FlavorText must not be longer than 255 characters")
    private String flavorText;

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }


    // Update questTags to many-to-many relationship and join quest and questTag tables by their IDs
    @ManyToMany
    @JoinTable(
            name = "quest_questTag",
            joinColumns = @JoinColumn(name = "quest_id"),
            inverseJoinColumns = @JoinColumn(name = "questTag_id")
    )
    @NotNull(message = "QuestTags are required")
    private List<QuestTag> questTags = new ArrayList<>();

    // no arg constructor
    public Quest() {
    }

    public Quest(String flavorText, List<QuestTag> questTags) {
        this.flavorText = flavorText;
        this.questTags = questTags;
    }

    // Getters and setters


    public List<QuestTag> getQuestTags() {
        return questTags;
    }

    public void setQuestTags(List<QuestTag> questTags) {
        this.questTags = questTags;
    }

}
