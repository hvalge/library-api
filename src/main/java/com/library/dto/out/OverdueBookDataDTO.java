package com.library.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OverdueBookDataDTO {

    private String loanerFullName;
    private String bookTitle;
    private LocalDate dueDate;
    private Boolean isReturned;
    private LocalDate returnedAt;

}
