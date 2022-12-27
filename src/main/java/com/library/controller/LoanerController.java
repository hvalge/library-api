package com.library.controller;

import com.library.db.entity.Loaner;
import com.library.dto.in.LoanerDTO;
import com.library.service.LoanerService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loaners")
public class LoanerController {

    private final LoanerService loanerService;

    public LoanerController(LoanerService loanerService) {
        this.loanerService = loanerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Loaner createLoaner(@RequestBody @NotNull LoanerDTO loanerDTO) {
        return loanerService.createLoaner(loanerDTO);
    }
}
