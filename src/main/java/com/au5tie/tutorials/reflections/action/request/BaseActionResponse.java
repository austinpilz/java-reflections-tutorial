package com.au5tie.tutorials.reflections.action.request;

import com.au5tie.tutorials.reflections.action.fault.DisplayFault;
import com.au5tie.tutorials.reflections.common.TimeDifference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
@Slf4j
public class BaseActionResponse {
    private ActionRequestOutcome outcome;
    private List<DisplayFault> faults;
    private String totalTime;

    // Notice how I have no useful fields :)

    public BaseActionResponse() {
        this.faults = new ArrayList<>();
    }

    public BaseActionResponse(BaseActionResponse existingResponse) {
        this();
        this.outcome = existingResponse.getOutcome();
        this.faults = existingResponse.getFaults();
        this.totalTime = existingResponse.totalTime;
    }

    /**
     * Marks completion of the request processing.
     *
     * @param request Base Request.
     * @author Austin Pilz
     */
    public void completeProcessing(BaseActionRequest request) {
        this.totalTime = new TimeDifference(request.getProcessingStart(), request.getProcessingEnd()).toString();
    }

    /**
     * Adds fault to the list of faults.
     *
     * @author Austin Pilz
     */
    public void addFault(DisplayFault fault) {
        this.faults.add(fault);
    }
}
