package com.library.controller;

import com.library.db.entity.Loaner;
import com.library.dto.in.LoanerDto;
import com.library.service.LoanerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loaners")
public class LoanerController {

    private final LoanerService loanerService;

    public LoanerController(LoanerService loanerService) {
        this.loanerService = loanerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Loaner createLoaner(@RequestBody @NotNull @Valid LoanerDto loanerDTO) {
        return loanerService.createLoaner(loanerDTO);
    }

    @GetMapping("/search")
    public List<Loaner> searchLoaners(@RequestParam String name) {
        return loanerService.searchLoaners(name);
    }
}
