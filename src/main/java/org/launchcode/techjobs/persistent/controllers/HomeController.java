package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
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

    // Add employerRepository, jobRepository, and skillRepository fields
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("/")
    public String index(Model model) {

        // Fetch all jobs from the repository
        List<Job> jobs = (List<Job>) jobRepository.findAll();

        // Add the list of jobs to the model
        model.addAttribute("jobs", jobs);
        model.addAttribute("title", "MyJobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");

        // Fetch all employers and skills
        List<Employer> employers = (List<Employer>) employerRepository.findAll();
        List<Skill> skills = (List<Skill>) skillRepository.findAll();

        model.addAttribute("employers", employers);
        model.addAttribute("skills", skills);
        model.addAttribute(new Job());

        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors,
                                    Model model,
                                    @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");

            // If there are errors, repopulate employers and skills
            List<Employer> employers = (List<Employer>) employerRepository.findAll();
            List<Skill> skillList = (List<Skill>) skillRepository.findAll();

            model.addAttribute("employers", employers);
            model.addAttribute("skills", skillList);
            return "add";
        }

        // Find the selected employer by ID and set it on the new job
        Optional<Employer> employerOpt = employerRepository.findById(employerId);
        if (employerOpt.isPresent()) {
            newJob.setEmployer(employerOpt.get());
        }

        // Find the selected skills by their IDs and set them on the new job
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);

        // Save the new job object
        jobRepository.save(newJob);

        return "redirect:/";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        // Retrieve the job from the repository
        Optional<Job> job = jobRepository.findById(jobId);

        if (job.isPresent()) {
            model.addAttribute("job", job.get());
            return "view";
        } else {
            // redirect to home page if job is not present
            return "redirect:/";
        }
    }
}
