package com.library.controller;

import com.library.dto.out.OverdueBookDTO;
import com.library.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/overdue-books")
    public List<OverdueBookDTO> getReport() {
        return reportService.getReport();
    }

}
