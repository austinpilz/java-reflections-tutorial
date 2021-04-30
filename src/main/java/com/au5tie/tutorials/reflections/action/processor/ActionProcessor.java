package com.au5tie.tutorials.reflections.action.processor;

import com.au5tie.tutorials.reflections.action.ActionType;
import com.au5tie.tutorials.reflections.action.request.BaseActionRequest;
import com.au5tie.tutorials.reflections.action.request.BaseActionResponse;

public interface ActionProcessor {

    /**
     * Returns the type of request that this processor services.
     *
     * @return UI Request Type.
     * @author Austin Pilz
     */
    ActionType getRequestType();

    /**
     * Processes the request.
     *
     * @param request Request.
     * @return Response.
     * @author Austin Pilz
     */
    BaseActionResponse processRequest(BaseActionRequest request);
}
