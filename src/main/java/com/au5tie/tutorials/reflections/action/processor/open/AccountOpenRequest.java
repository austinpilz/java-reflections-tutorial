package com.au5tie.tutorials.reflections.action.processor.open;

import com.au5tie.tutorials.reflections.action.request.BaseActionRequest;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AccountOpenRequest extends BaseActionRequest {

    private String accountNumber;
    private boolean shouldLock;
}
