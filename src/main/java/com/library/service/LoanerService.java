package com.library.service;

import com.library.db.entity.Loaner;
import com.library.db.repository.LoanerRepository;
import com.library.dto.in.LoanerDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanerService {

    private final LoanerRepository loanerRepository;

    public LoanerService(LoanerRepository loanerRepository) {
        this.loanerRepository = loanerRepository;
    }

    @Transactional
    public Loaner createLoaner(LoanerDto loanerDTO) {
        Loaner loaner = new Loaner();
        loaner.setFirstName(loanerDTO.getFirstName());
        loaner.setLastName(loanerDTO.getLastName());

        return loanerRepository.save(loaner);
    }

    public List<Loaner> searchLoaners(String name) {
        return loanerRepository.findPartialMatchForLoaners(name);
    }
}
