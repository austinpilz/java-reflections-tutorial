package com.au5tie.tutorials.reflections.action.fault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@ToString
@Slf4j
public class DisplayFault {

    private DisplayFaultType type;
    private String title;
    private String message;
    private String exception;
    private String referenceId;
    private LocalDateTime timestamp;

    public DisplayFault(DisplayFaultType type, String title, String message) {
        this.type = type;
        this.title = title;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
