package ua.lviv.lgs.nl.simpleforum.dto;

import lombok.*;
import ua.lviv.lgs.nl.simpleforum.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TopicDTO {
    private  Long id;

    @Size(min = 3, max = 30)
    private String title;

    @NotBlank
    private String content;

    private Long numberOfViews;
    private Integer numberOfReplies;
    private String creationDate;
    private UserDTO user;
    private PostDTO lastPost;
    private Long sectionId;

}
