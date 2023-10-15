package com.au5tie.tutorials.reflections.action.processor.open;

import com.au5tie.tutorials.reflections.action.ActionType;
import com.au5tie.tutorials.reflections.action.fault.DisplayFault;
import com.au5tie.tutorials.reflections.action.fault.DisplayFaultType;
import com.au5tie.tutorials.reflections.action.processor.ActionProcessor;
import com.au5tie.tutorials.reflections.action.request.BaseActionRequest;
import com.au5tie.tutorials.reflections.action.request.BaseActionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class AccountOpenActionProcessor implements ActionProcessor {

    @Override
    public ActionType getRequestType() {
        return ActionType.ACCOUNT_OPEN;
    }

    @Override
    public BaseActionResponse processRequest(BaseActionRequest request) {
        // Open the account. We know the request is of AccountOpenRequest, so we can cast it.
        return openAccount((AccountOpenRequest) request);
    }

    private AccountOpenResponse openAccount(AccountOpenRequest request) {
        // Log our performance.
        request.setProcessingStart(LocalDateTime.now());

        // Create our response object which will contain the open results.
        AccountOpenResponse response = new AccountOpenResponse();

        if (StringUtils.isNotBlank(request.getAccountNumber())) {
            // Build a simple response letting the user know if we opened their account.
            response.addFault(DisplayFault.builder()
                    .type(DisplayFaultType.INFO)
                    .title("Account Open")
                    .message("Account " + request.getAccountNumber() + " opened with lock = " + request.isShouldLock())
                    .build());

            response.setWasOpened(true);
        } else {
            // No account number was provided.
            response.addFault(DisplayFault.builder()
                    .type(DisplayFaultType.ERROR)
                    .title("Account Open")
                    .message("No account number was provided!")
                    .build());

            response.setWasOpened(false);
        }

        return response;
    }
}
