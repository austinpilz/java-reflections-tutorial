package com.au5tie.tutorials.reflections.action;

import com.au5tie.tutorials.reflections.action.processor.close.AccountCloseRequest;
import com.au5tie.tutorials.reflections.action.processor.close.AccountCloseResponse;
import com.au5tie.tutorials.reflections.action.processor.close.AccountCloseResponseVO;
import com.au5tie.tutorials.reflections.action.processor.open.AccountOpenRequest;
import com.au5tie.tutorials.reflections.action.processor.open.AccountOpenResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

/**
 * The ActionType refers to a unique type of action which can be requested to be performed. The Request and Response
 * class references are used for casting of the respective response/request into the appropriate class.
 *
 * @author Austin Pilz
 */
@Getter
@AllArgsConstructor
public enum ActionType {

    ACCOUNT_OPEN(AccountOpenRequest.class, AccountOpenResponse.class, null, "gitHubAustinPilz", LocalDate.parse("2021-04-30"),"Account Open"),
    ACCOUNT_CLOSE(AccountCloseRequest.class, AccountCloseResponse.class, AccountCloseResponseVO.class, "gitHubAustinPilz", LocalDate.parse("2021-04-30"),"Account Close");

    private final Class<?> requestClass;
    private final Class<?> responseClass;
    private final Class<?> responseViewClass; // Optional.
    private final String addedBy;
    private final LocalDate addedOn;
    private final String description;
}
