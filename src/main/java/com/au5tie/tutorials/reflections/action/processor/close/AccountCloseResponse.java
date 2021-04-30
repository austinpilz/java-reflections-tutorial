package com.au5tie.tutorials.reflections.action.processor.close;

import com.au5tie.tutorials.reflections.action.request.BaseActionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountCloseResponse extends BaseActionResponse {

    private boolean wasClosed;
    private LocalDateTime lockReleasedTimestamp;
}
