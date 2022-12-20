package com.library.service;

import com.library.db.entity.Loaner;
import com.library.db.repository.LoanerRepository;
import com.library.dto.in.LoanerDTO;
import org.springframework.stereotype.Service;

@Service
public class LoanerService {

    private final LoanerRepository loanerRepository;

    public LoanerService(LoanerRepository loanerRepository) {
        this.loanerRepository = loanerRepository;
    }

    public Loaner createLoaner(LoanerDTO loanerDTO) {
        Loaner loaner = new Loaner();
        loaner.setFirstName(loanerDTO.getFirstName());
        loaner.setLastName(loanerDTO.getLastName());

        return loanerRepository.save(loaner);
    }
}
