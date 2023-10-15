package com.au5tie.tutorials.reflections.action.processor.close;

import com.au5tie.tutorials.reflections.action.ActionType;
import com.au5tie.tutorials.reflections.action.fault.DisplayFault;
import com.au5tie.tutorials.reflections.action.fault.DisplayFaultType;
import com.au5tie.tutorials.reflections.action.processor.ActionProcessor;
import com.au5tie.tutorials.reflections.action.request.BaseActionRequest;
import com.au5tie.tutorials.reflections.action.request.BaseActionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class AccountCloseActionProcessor implements ActionProcessor {

    @Override
    public ActionType getRequestType() {
        return ActionType.ACCOUNT_CLOSE;
    }

    @Override
    public BaseActionResponse processRequest(BaseActionRequest request) {
        // Open the account. We know the request is of AccountCloseRequest, so we can cast it.
        return closeAccount((AccountCloseRequest) request);
    }

    private AccountCloseResponse closeAccount(AccountCloseRequest request) {
        // Log our performance.
        request.setProcessingStart(LocalDateTime.now());

        // Create our response object which will contain the close results.
        AccountCloseResponse response = new AccountCloseResponse();

        // Build a simple response letting the user know if we closed their account.
        response.addFault(DisplayFault.builder()
                .type(DisplayFaultType.INFO)
                .title("Account Closed")
                .message("Account " + request.getAccountNumber() + " closed!")
                .build());

        // Populate response object specific fields.
        response.setWasClosed(true);
        response.setLockReleasedTimestamp(LocalDateTime.now());

        return response;
    }
}
