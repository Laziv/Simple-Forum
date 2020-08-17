package ua.lviv.lgs.nl.simpleforum.dto;

import lombok.*;
import ua.lviv.lgs.nl.simpleforum.validation.UniqueEmail;
import ua.lviv.lgs.nl.simpleforum.validation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    private Long id;

    @UniqueUsername
    @Size(min = 3, max = 20)
    private String username;

    @UniqueEmail
    @NotBlank
    @Email(message = "Email is not correct")
    private String email;

    @Size(min = 5, max = 32)
    private String password;

    @Size(min = 5, max = 32)
    private String passwordConfirm;

    @NotBlank
    private String birthday;

    private Integer numberOfPosts;
    private String creationDate;
}
