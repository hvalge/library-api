package com.library.service;

import com.library.db.repository.ReportOverdueBooksRepository;
import com.library.dto.out.OverdueBookDTO;
import com.library.dto.out.OverdueBookDataDTO;
import com.library.mapper.ReportMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    private final ReportOverdueBooksRepository reportOverdueBooksRepository;
    private final ReportMapper reportMapper;

    public ReportService(ReportOverdueBooksRepository reportOverdueBooksRepository, ReportMapper reportMapper) {
        this.reportOverdueBooksRepository = reportOverdueBooksRepository;
        this.reportMapper = reportMapper;
    }

    public List<OverdueBookDTO> getReport() {
        List<OverdueBookDataDTO> overdueBooksData = reportOverdueBooksRepository.getOverdueBooks();
        List<OverdueBookDTO> report = new ArrayList<>();

        overdueBooksData.forEach(overdueBookData -> {
            if (isBookOverdueByAtLeastDay(overdueBookData)) {
                report.add(reportMapper.mapOverdueBookDataToOverdueBook(overdueBookData));
            }
        });

        return report;
    }

    private boolean isBookOverdueByAtLeastDay(OverdueBookDataDTO overdueBookData) {
        long daysOverdue;
        if (overdueBookData.getIsReturned()) {
            daysOverdue = overdueBookData.getReturnedAt().toEpochDay() - overdueBookData.getDueDate().toEpochDay();
        } else {
            daysOverdue = LocalDate.now().toEpochDay() - overdueBookData.getDueDate().toEpochDay();
        }

        return daysOverdue > 0;
    }

}
