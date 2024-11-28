package org.launchcode.givemeaquest.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class QuestTag extends AbstractEntity {

    // Add a quests field (ArrayList type)
    @ManyToMany(mappedBy = "questTags")
    private List<Quest> quests = new ArrayList<>();

    // No-arg Constructor
    public QuestTag() {
    }

    // Getter and Setter - quests
    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }
}