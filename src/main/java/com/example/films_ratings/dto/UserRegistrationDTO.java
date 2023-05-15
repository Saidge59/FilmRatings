package com.example.films_ratings.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegistrationDTO {

    @Size(min=2, message = " Username must be min 2 symbols")
    private String name;

    @NotBlank(message = " Email is required field")
    @Email(message = " Not correct your format email")
    private String email;

    @Size(min=4, message = " Password must be min 4 symbols")
    private String password;

    private boolean roleUser;
    private boolean roleAdmin;
}
