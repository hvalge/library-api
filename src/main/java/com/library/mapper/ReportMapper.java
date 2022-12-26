package com.library.mapper;

import com.library.dto.out.OverdueBookDTO;
import com.library.dto.out.OverdueBookDataDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class ReportMapper {

    public OverdueBookDTO mapOverdueBookDataToOverdueBook(OverdueBookDataDTO overdueBookData) {
        OverdueBookDTO overdueBook = new OverdueBookDTO();
        overdueBook.setLoanerFullName(overdueBookData.getLoanerFullName());
        overdueBook.setBookTitle(overdueBookData.getBookTitle());

        LocalDate startDate = overdueBookData.getDueDate();
        LocalDate endDate = overdueBookData.getIsReturned() ? overdueBookData.getReturnedAt() : LocalDate.now();
        Period duration = Period.between(startDate, endDate);
        overdueBook.setOverdueDurationInDays(duration.getDays());

        overdueBook.setReturned(overdueBookData.getIsReturned());
        return overdueBook;
    }
}
