package com.library.mapper;

import com.library.dto.out.StatusBookDto;
import com.library.dto.out.StatusBookDataDto;
import com.library.service.LoanDurationCalculatorService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BookMapper {

    private final LoanDurationCalculatorService loanDurationCalculatorService;

    public BookMapper(LoanDurationCalculatorService loanDurationCalculatorService) {
        this.loanDurationCalculatorService = loanDurationCalculatorService;
    }

    public StatusBookDto statusBookDataToStatusBook(StatusBookDataDto dataDto) {
        var businessDto = new StatusBookDto();
        businessDto.setTitle(dataDto.getTitle());
        businessDto.setCopiesAvailable(dataDto.getCopiesAvailable());
        businessDto.setLocation(dataDto.getLocation());
        businessDto.setDueDate(loanDurationCalculatorService.calculateLoanDueDate(
                LocalDate.now(), dataDto.getAgeSincePublication(), dataDto.getCopiesAvailable()));
        return businessDto;
    }
}
