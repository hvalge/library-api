package com.library.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateBookLoanDueDateDTO {

    @NotNull
    private LocalDate newDueDate;
}
