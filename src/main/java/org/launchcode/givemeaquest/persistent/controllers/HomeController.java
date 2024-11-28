package org.launchcode.givemeaquest.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.givemeaquest.persistent.models.Quest;
import org.launchcode.givemeaquest.persistent.models.QuestTag;
import org.launchcode.givemeaquest.persistent.models.data.QuestRepository;
import org.launchcode.givemeaquest.persistent.models.data.QuestTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    // Add questRepository, and questTagRepository fields

    @Autowired
    private QuestTagRepository questTagRepository;

    @Autowired
    private QuestRepository questRepository;

    @RequestMapping("/")
    public String index(Model model) {
        // Fetch all quests from the repository
        List<Quest> quests = (List<Quest>) questRepository.findAll();
        model.addAttribute("quests", quests);
        model.addAttribute("title", "MyQuests");

        // Fetch a random quest for the flavor text
        if (!quests.isEmpty()) {
            Quest randomQuest = quests.get((int) (Math.random() * quests.size()));
            model.addAttribute("randomFlavorText", randomQuest.getFlavorText());
        } else {
            model.addAttribute("randomFlavorText", "No quests available.");
        }

        return "index";
    }

    @GetMapping("add")
    public String displayAddQuestForm(Model model) {
        model.addAttribute("title", "Add Quest");

        // Fetch all questTags
        List<QuestTag> questTags = (List<QuestTag>) questTagRepository.findAll();
        model.addAttribute("questTags", questTags);

        // Add a new Quest object to the model
        model.addAttribute(new Quest());

        return "add";
    }

    @PostMapping("add")
    public String processAddQuestForm(@ModelAttribute @Valid Quest newQuest,
                                      Errors errors,
                                      Model model,
                                      @RequestParam(required = false) List<Integer> questTags) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Quest");

            // Repopulate questTags if validation fails
            List<QuestTag> questTagList = (List<QuestTag>) questTagRepository.findAll();
            model.addAttribute("questTags", questTagList);

            return "add";
        }

        // Handle questTags selection
        if (questTags != null && !questTags.isEmpty()) {
            List<QuestTag> questTagObjs = (List<QuestTag>) questTagRepository.findAllById(questTags);
            newQuest.setQuestTags(questTagObjs);
        }

        questRepository.save(newQuest);
        return "redirect:/";
    }

    @GetMapping("view/{questId}")
    public String displayViewQuest(Model model, @PathVariable int questId) {
        // Retrieve the quest from the repository
        Optional<Quest> quest = questRepository.findById(questId);

        if (quest.isPresent()) {
            model.addAttribute("quest", quest.get());
            return "view";
        } else {
            // redirect to home page if quest is not present
            return "redirect:/";
        }
    }

    //DELETE JOB FUNCTION
    @GetMapping("delete")
    public String displayDeletePage(Model model) {
        // Fetch all quests, questTags from the repository
        List<Quest> quests = (List<Quest>) questRepository.findAll();
        List<QuestTag> questTags = (List<QuestTag>) questTagRepository.findAll();

        // Add these to the model so they can be used in the view
        model.addAttribute("quests", quests);
        model.addAttribute("questTags", questTags);
        model.addAttribute("title", "Delete Items");

        return "delete"; // This will render a 'delete.html' template
    }

    @PostMapping("delete")
    public String processDeleteForm(@RequestParam(required = false) List<Integer> questIds,
                                    @RequestParam(required = false) List<Integer> questTagIds) {

        // Delete selected quests
        if (questIds != null) {
            for (Integer questId : questIds) {
                questRepository.deleteById(questId);
            }
        }

        // Delete selected questTags
        if (questTagIds != null) {
            for (Integer questTagId : questTagIds) {
                questTagRepository.deleteById(questTagId);
            }
        }

        // Redirect back to the home page after deletion
        return "redirect:/";
    }
}
