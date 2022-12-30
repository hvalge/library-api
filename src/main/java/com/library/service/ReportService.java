package com.library.service;

import com.library.db.repository.ReportRepository;
import com.library.dto.out.OverdueBookDto;
import com.library.dto.out.OverdueBookDataDto;
import com.library.mapper.ReportMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    public ReportService(ReportRepository reportRepository, ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
    }

    public List<OverdueBookDto> getReport() {
        List<OverdueBookDataDto> overdueBooksData = reportRepository.getOverdueBooks();
        List<OverdueBookDto> report = new ArrayList<>();

        overdueBooksData.forEach(overdueBookData -> {
            if (isBookOverdueByAtLeastDay(overdueBookData)) {
                report.add(reportMapper.mapOverdueBookDataToOverdueBook(overdueBookData));
            }
        });

        return report;
    }

    private boolean isBookOverdueByAtLeastDay(OverdueBookDataDto overdueBookData) {
        long daysOverdue;
        if (overdueBookData.getIsReturned()) {
            daysOverdue = overdueBookData.getReturnedAt().toEpochDay() - overdueBookData.getDueDate().toEpochDay();
        } else {
            daysOverdue = LocalDate.now().toEpochDay() - overdueBookData.getDueDate().toEpochDay();
        }

        return daysOverdue > 0;
    }

}
