package ua.lviv.lgs.nl.simpleforum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.lviv.lgs.nl.simpleforum.dto.ForumStatisticsDTO;
import ua.lviv.lgs.nl.simpleforum.dto.SectionDTO;
import ua.lviv.lgs.nl.simpleforum.service.ForumStatisticsService;
import ua.lviv.lgs.nl.simpleforum.service.SectionService;

import java.util.List;

@Controller
public class HomeController {
    private final SectionService sectionService;
    private final ForumStatisticsService forumStatisticsService;

    @Autowired
    public HomeController(SectionService sectionService, ForumStatisticsService forumStatisticsService) {
        this.sectionService = sectionService;
        this.forumStatisticsService = forumStatisticsService;
    }

    @GetMapping({"/"})
    public String index(Model model) {
        model.addAttribute("sections", sectionService.findAll());
        model.addAttribute("statistics", forumStatisticsService.getForumStatistics());
        return "index";
    }

}
