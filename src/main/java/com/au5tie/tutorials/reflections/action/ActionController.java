package com.au5tie.tutorials.reflections.action;

import com.au5tie.tutorials.reflections.action.processor.close.AccountCloseRequest;
import com.au5tie.tutorials.reflections.action.processor.close.AccountCloseResponse;
import com.au5tie.tutorials.reflections.action.processor.open.AccountOpenRequest;
import com.au5tie.tutorials.reflections.action.processor.open.AccountOpenResponse;
import com.au5tie.tutorials.reflections.action.request.BaseActionRequest;
import com.au5tie.tutorials.reflections.action.request.BaseActionResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * This is the main interface for how ActionRequests are processed. This will accept the incoming request as a generic
 * JSON body. This is done because we have to use the ActionType enum (which has the reference to the Request Class) to
 * tell the Object Mapper which specific end (down casted) request to parse. If we didn't do this, Jackson would only
 * parse the BaseActionRequest which does not have all of the JSON elements that each extending request has - thus we
 * actually parse the actual request entity and then UP cast it back to the BaseActionRequest (since it's the superclass).
 *
 * When we get the BaseActionResponse out of the router, we then again use the ActionType enum in order to DOWN cast the
 * BaseActionResponse down into the actual response object which this action uses. This is what allows the outgoing
 * object mapper to have insight into all of the fields and map them back into JSON.
 *
 * @author Austin Pilz
 */
@RestController
@RequestMapping("/api/actions")
@Slf4j
public class ActionController {

    private final ActionRouter router;
    private ObjectMapper objectMapper;

    public ActionController(ActionRouter router) {
        this.router = router;
        this.objectMapper = new ObjectMapper();

        // Configure the object mapper.
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /*
    Examples of endpoints if we didn't do dynamic casting, we'd have to have one endpoint for each and every action.
    Nasty.
     */
    public AccountOpenResponse requestAccountOpen(@RequestBody AccountOpenRequest request) {
        // Invoke the processor.

        // Return the response.

        return null;
    }

    public AccountCloseResponse requestAccountClose(@RequestBody AccountCloseRequest request) {
        // Invoke the processor.

        // Return the response.

        return null;
    }

    /*
    Target implementation.
     */
    @RequestMapping(value = "/{actionType}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object invokeAction(@PathVariable(value = "actionType") ActionType actionType,
                                           @RequestBody String requestBody,
                                           Authentication authentication) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        // Read the response into the specific request class defined by the ActionType.
        BaseActionRequest request = (BaseActionRequest) objectMapper.readValue(requestBody, actionType.getRequestClass());

        // Process the request by invoking the processor for that action.
        BaseActionResponse response = router.processAction(request, authentication);

        // Use the response class that the ActionType calls for and DOWN cast the BaseActionResponse to the specific response entity.
        if (actionType.getResponseViewClass() != null) {
            // This Action Type requests a mapping from it's specific response time to another specific response time VO for the UI.
            return actionType.getResponseViewClass().getDeclaredConstructor(actionType.getResponseClass()).newInstance(actionType.getResponseClass().cast(response));
        } else {
            // No VO mapping required, we can just return the bare response.
            return actionType.getResponseClass().cast(response);
        }
    }
}
