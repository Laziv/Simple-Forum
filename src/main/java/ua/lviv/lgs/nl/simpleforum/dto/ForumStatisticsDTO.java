package ua.lviv.lgs.nl.simpleforum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ForumStatisticsDTO {
    private Long numberOfUsers;
    private Long numberOfTopics;
    private Long numberOfSections;
    private Long numberOfPosts;
    private String newestUser;
}
