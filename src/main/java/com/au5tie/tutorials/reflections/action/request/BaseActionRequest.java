package com.au5tie.tutorials.reflections.action.request;

import com.au5tie.tutorials.reflections.action.ActionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
public class BaseActionRequest {
    private ActionType actionType;

    private LocalDateTime processingStart;
    private LocalDateTime processingEnd;

    // Notice how I have no useful fields :)

    private Authentication authentication;
}
