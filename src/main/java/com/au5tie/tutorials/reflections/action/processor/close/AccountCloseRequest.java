package com.au5tie.tutorials.reflections.action.processor.close;

import com.au5tie.tutorials.reflections.action.request.BaseActionRequest;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AccountCloseRequest extends BaseActionRequest {

    private int accountNumber;
}
