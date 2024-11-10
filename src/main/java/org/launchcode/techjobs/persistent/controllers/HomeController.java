package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
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

    // Step 1: Add employerRepository and skillRepository fields
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	model.addAttribute("title", "Add Job");

        List<Employer> employers = (List<Employer>) employerRepository.findAll();  // Fetch all employers
        model.addAttribute("employers", employers);  // Add employers to the model

        model.addAttribute(new Job());  // Add a new Job object to the form
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId) {

        // Step 3: Handle employer selection
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            List<Employer> employers = (List<Employer>) employerRepository.findAll();
            model.addAttribute("employers", employers);  // Adding employers again if there are errors
            return "add";
        }

        // Find the selected employer by ID
        Optional<Employer> employer = employerRepository.findById(employerId);
        if (employer.isPresent()) {
            newJob.setEmployer(employer.get());
        }

        // Save the new job object (you will need a jobRepository, assumed to be added later)
        // jobRepository.save(newJob);

        return "redirect:/";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

            return "view";
    }

}
