package ru.itis.models;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    private String id;

    private LocalDate date;

    private String name;

}
