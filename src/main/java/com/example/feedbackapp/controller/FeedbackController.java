package com.example.feedbackapp.controller;

import com.example.feedbackapp.Repository.FeedbackRepository;
import com.example.feedbackapp.entity.Feedback;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FeedbackController {

    private final FeedbackRepository repository;

    public FeedbackController(FeedbackRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/feedback")
    public String showForm() {
        return "feedback";
    }

    @PostMapping("/saveFeedback")
    public String saveFeedback(
            @RequestParam String name,
            @RequestParam String bookName,
            @RequestParam String feedback) {

        Feedback f = new Feedback();

        f.setName(name);
        f.setBookName(bookName);
        f.setFeedback(feedback);

        repository.save(f);

        return "redirect:/feedback";
    }

    @GetMapping("/feedbacks")
    @ResponseBody
    public List<Feedback> getAllFeedback() {
        return repository.findAll();
    }
}