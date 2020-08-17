package ua.lviv.lgs.nl.simpleforum.dto;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SectionDTO {
    private Long id;

    @Size(min = 3, max = 20)
    private String name;

    private String description;
    private Integer numberOfTopics;
    private Integer numberOfPosts;
    private PostDTO lastPost;
}
