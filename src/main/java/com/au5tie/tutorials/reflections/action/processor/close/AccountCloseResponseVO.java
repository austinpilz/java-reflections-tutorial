package com.au5tie.tutorials.reflections.action.processor.close;

import com.au5tie.tutorials.reflections.action.request.BaseActionResponse;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountCloseResponseVO extends BaseActionResponse {

    private boolean wasClosed;
    private String lockReleasedTimestamp;

    public AccountCloseResponseVO(AccountCloseResponse response) {
        super(response);

        // Map fields from the original response.
        this.wasClosed = response.isWasClosed();

        // Do UI specific conversions.
        this.lockReleasedTimestamp = "UL@" + response.getLockReleasedTimestamp().toString();
    }
}
