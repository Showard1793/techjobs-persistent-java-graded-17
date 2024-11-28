package org.launchcode.givemeaquest.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.givemeaquest.persistent.models.QuestTag;
import org.launchcode.givemeaquest.persistent.models.data.QuestTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("questTags")
public class QuestTagController {

    // Add a private field for QuestTagRepository with @Autowired
    @Autowired
    private QuestTagRepository questTagRepository;

    // Add index method to list all questTags
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("questTags", questTagRepository.findAll());
        return "questTags/index";
    }

    @GetMapping("add")
    public String displayAddQuestTagForm(Model model) {
        model.addAttribute(new QuestTag());
        return "questTags/add";
    }

    // Update to save a valid QuestTag
    @PostMapping("add")
    public String processAddQuestTagForm(@ModelAttribute @Valid QuestTag newQuestTag,
                                      Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "questTags/add";
        }

        questTagRepository.save(newQuestTag); //saves new questTag
        return "redirect:";
    }

    // Update to fetch a questTag by ID
    @GetMapping("view/{questTagId}")
    public String displayViewQuestTag(Model model, @PathVariable int questTagId) {
        Optional<QuestTag> optQuestTag = questTagRepository.findById(questTagId);
        if (optQuestTag.isPresent()) {
            QuestTag questTag = optQuestTag.get();
            model.addAttribute("questTag", questTag);
            return "questTags/view";
        } else {
            return "redirect:../";
        }
    }
}
