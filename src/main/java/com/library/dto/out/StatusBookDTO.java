package com.library.dto.out;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StatusBookDTO {
        private String title;
        private int copiesAvailable;
        private LocalDate dueDate;
        private String location;
}
