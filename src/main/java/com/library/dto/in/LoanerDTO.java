package com.library.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoanerDTO {

    @NotNull
    @Length(min = 1, max = 255)
    private String firstName;

    @NotNull
    @Length(min = 1, max = 255)
    private String lastName;

}
