package org.launchcode.givemeaquest.persistent.controllers;

import org.launchcode.givemeaquest.persistent.models.Quest;
import org.launchcode.givemeaquest.persistent.models.QuestData;
import org.launchcode.givemeaquest.persistent.models.data.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.launchcode.givemeaquest.persistent.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private QuestRepository questRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Quest> quests;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            quests = questRepository.findAll();
        } else {
            quests = QuestData.findByColumnAndValue(searchType, searchTerm, questRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Quests with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("quests", quests);

        return "search";
    }
}
