package com.au5tie.tutorials.reflections.action.request;

/**
 * This needs to be kept in sync with the request-outcome-type.ts in the UI in order for the UI portion to understand
 * these enums.
 *
 * @author Austin Pilz
 */
public enum ActionRequestOutcome {
    SUCCESS,
    SUCCESS_WITH_WARNINGS,
    VALIDATION_ERRORS,
    ERROR
}
