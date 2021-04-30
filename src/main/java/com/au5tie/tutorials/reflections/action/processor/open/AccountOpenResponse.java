package com.au5tie.tutorials.reflections.action.processor.open;

import com.au5tie.tutorials.reflections.action.request.BaseActionResponse;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountOpenResponse extends BaseActionResponse {

    private boolean wasOpened;
    //private boolean wasLocked;
}
