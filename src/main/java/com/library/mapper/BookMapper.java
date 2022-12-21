package com.library.mapper;

import com.library.dto.out.StatusBookBusinessDTO;
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

    public StatusBookBusinessDTO mapToBusinessDto(StatusBookDataDTO dataDto) {
        var businessDto = new StatusBookBusinessDTO();
        businessDto.setTitle(dataDto.getTitle());
        businessDto.setCopiesAvailable(dataDto.getCopiesAvailable());
        businessDto.setLocation(dataDto.getLocation());
        businessDto.setDueDate(loanDurationCalculatorService.calculateLoanDueDate(
                        LocalDate.now(), dataDto.getAgeSincePublication(), dataDto.getCopiesAvailable()));
        return businessDto;
    }
}
