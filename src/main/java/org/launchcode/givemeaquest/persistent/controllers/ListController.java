package org.launchcode.givemeaquest.persistent.controllers;

import org.launchcode.givemeaquest.persistent.models.Quest;
import org.launchcode.givemeaquest.persistent.models.QuestTag;
import org.launchcode.givemeaquest.persistent.models.data.QuestRepository;
import org.launchcode.givemeaquest.persistent.models.data.QuestTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.launchcode.givemeaquest.persistent.models.QuestData;

import java.util.HashMap;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    private QuestRepository questRepository;


    @Autowired
    private QuestTagRepository questTagRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("all", "All");
        columnChoices.put("questTag", "QuestTag");

    }

    @RequestMapping("")
    public String list(Model model) {

        // Fetch all employers and questTags
        List<QuestTag> questTags = (List<QuestTag>) questTagRepository.findAll();

        // Add them to the model
        model.addAttribute("questTags", questTags);
        model.addAttribute("columnChoices", columnChoices);

        return "list";
    }

    @RequestMapping(value = "quests")
    public String listQuestsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Quest> quests;
        if (column.toLowerCase().equals("all")){
            quests = questRepository.findAll();
            model.addAttribute("title", "All Quests");
        } else {
            quests = QuestData.findByColumnAndValue(column, value, questRepository.findAll());
            model.addAttribute("title", "Quests with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("quests", quests);

        return "list-quests";
    }
}
