package com.library.mapper;

import com.library.dto.out.StatusBookDTO;
import com.library.dto.out.StatusBookDataDTO;
import com.library.service.LoanDurationCalculatorService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BookMapper {

    private final LoanDurationCalculatorService loanDurationCalculatorService;

    public BookMapper(LoanDurationCalculatorService loanDurationCalculatorService) {
        this.loanDurationCalculatorService = loanDurationCalculatorService;
    }

    public StatusBookDTO statusBookDataToStatusBook(StatusBookDataDTO dataDto) {
        var businessDto = new StatusBookDTO();
        businessDto.setTitle(dataDto.getTitle());
        businessDto.setCopiesAvailable(dataDto.getCopiesAvailable());
        businessDto.setLocation(dataDto.getLocation());
        businessDto.setDueDate(loanDurationCalculatorService.calculateLoanDueDate(
                LocalDate.now(), dataDto.getAgeSincePublication(), dataDto.getCopiesAvailable()));
        return businessDto;
    }
}
