package com.au5tie.tutorials.reflections.action;

import com.au5tie.tutorials.reflections.action.fault.DisplayFaultType;
import com.au5tie.tutorials.reflections.action.fault.DisplayFaultUtils;
import com.au5tie.tutorials.reflections.action.processor.ActionProcessor;
import com.au5tie.tutorials.reflections.action.request.ActionRequestOutcome;
import com.au5tie.tutorials.reflections.action.request.BaseActionRequest;
import com.au5tie.tutorials.reflections.action.request.BaseActionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
@Slf4j
public class ActionRouter {

    private final List<ActionProcessor> actionProcessors;
    private Map<ActionType, ActionProcessor> actionProcessorMap;

    /**
     * Prepares the map of ActionType to ActionProcessor. This generates a map which allows routing of incoming action
     * types right to the processor which services them.
     *
     * @author Austin Pilz
     */
    @PostConstruct
    void prepareActionProcessorMap() {
        actionProcessorMap = new HashMap<>();
        actionProcessors.forEach(processor -> actionProcessorMap.put(processor.getRequestType(), processor));
    }

    /**
     * Process the Action Request. This will route the request down to the processor, process it, and then provide the
     * response.
     *
     * @param request Action Request.
     * @param authentication Authentication.
     * @return Action Response.
     * @author Austin Pilz
     */
    public BaseActionResponse processAction(BaseActionRequest request, Authentication authentication) {
        request.setProcessingStart(LocalDateTime.now());

        // Perform pre-population of the request.
        prePopulateRequest(request, authentication);

        // Route the request to the servicing processor and process the request.
        BaseActionResponse response = actionProcessorMap.get(request.getActionType()).processRequest(request);

        // Response Post-Processing.
        performResponsePostProcessing(response);

        // Total Time.
        request.setProcessingEnd(LocalDateTime.now());
        response.completeProcessing(request);

        return response;
    }

    /**
     * Performs request population. This will populate the request with basic and general components to be used by the
     * processors.
     *
     * @param request Request.
     * @param authentication Authentication.
     * @author Austin Pilz
     */
    private void prePopulateRequest(BaseActionRequest request, Authentication authentication) {
        // Nothing to see here, folks.
    }

    /**
     * Performs post action processing.
     *
     * @param response Base Action Response.
     * @author Austin Pilz
     */
    private void performResponsePostProcessing(BaseActionResponse response) {
        // Outcome.
        populateResponseOutcome(response);
    }

    /**
     * Determines and populates the request outcome onto the response.
     *
     * @param response Response.
     * @author Austin Pilz
     */
    private void populateResponseOutcome(BaseActionResponse response) {
        response.setOutcome(determineResponseOutcome(response));
    }

    /**
     * Determines the overall outcome of the request on the response.
     *
     * @param response Response.
     * @author Austin Pilz
     */
    private ActionRequestOutcome determineResponseOutcome(BaseActionResponse response) {

        if (DisplayFaultUtils.getFaultsOfType(response.getFaults(), DisplayFaultType.ERROR).size() > 0 || DisplayFaultUtils.getFaultsOfType(response.getFaults(), DisplayFaultType.SECURITY).size() > 0) {
            return ActionRequestOutcome.ERROR;
        }

        if (DisplayFaultUtils.getFaultsOfType(response.getFaults(), DisplayFaultType.VALIDATION).size() > 0) {
            return ActionRequestOutcome.VALIDATION_ERRORS;
        }

        if (DisplayFaultUtils.getFaultsOfType(response.getFaults(), DisplayFaultType.WARNING).size() > 0) {
            return ActionRequestOutcome.SUCCESS_WITH_WARNINGS;
        }

        return ActionRequestOutcome.SUCCESS;
    }
}
