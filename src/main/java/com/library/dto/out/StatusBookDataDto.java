package com.library.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusBookDataDto {

    private String title;
    private int copiesAvailable;
    private String location;
    private LocalDate ageSincePublication;

}
