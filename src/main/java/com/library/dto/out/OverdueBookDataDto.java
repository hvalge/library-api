package com.library.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OverdueBookDataDto {

    private String loanerName;
    private String bookTitle;
    private LocalDate dueDate;
    private Boolean isReturned;
    private LocalDate returnedAt;

}
