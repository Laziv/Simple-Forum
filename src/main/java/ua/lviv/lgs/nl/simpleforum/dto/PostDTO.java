package ua.lviv.lgs.nl.simpleforum.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PostDTO {

    private Long id;

    @NotBlank
    private String content;

    private String creationDate;

    private UserDTO user;

    private Long topicId;

    private Long sectionId;
}
