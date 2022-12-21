package com.library.dto.out;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StatusBookBusinessDTO {
        private String title;
        private int copiesAvailable;
        private LocalDate dueDate;
        private String location;
}
