package com.library.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OverdueBookDTO {

    private String loanerFullName;
    private String bookTitle;
    private int overdueDurationInDays;
    private boolean isReturned;

}
