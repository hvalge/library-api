package com.library.mapper;

import com.library.dto.out.OverdueBookDto;
import com.library.dto.out.OverdueBookDataDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class ReportMapper {

    public OverdueBookDto mapOverdueBookDataToOverdueBook(OverdueBookDataDto overdueBookData) {
        OverdueBookDto overdueBook = new OverdueBookDto();
        overdueBook.setLoanerFullName(overdueBookData.getLoanerName());
        overdueBook.setBookTitle(overdueBookData.getBookTitle());

        LocalDate startDate = overdueBookData.getDueDate();
        LocalDate endDate = overdueBookData.getIsReturned() ? overdueBookData.getReturnedAt() : LocalDate.now();
        Period duration = Period.between(startDate, endDate);
        overdueBook.setOverdueDurationInDays(duration.getDays());

        overdueBook.setReturned(overdueBookData.getIsReturned());
        return overdueBook;
    }
}
