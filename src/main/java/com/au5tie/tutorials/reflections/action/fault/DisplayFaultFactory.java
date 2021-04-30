package com.au5tie.tutorials.reflections.action.fault;

public class DisplayFaultFactory {

    /**
     * Generates {@link DisplayFault} for a missing policy.
     *
     * @param referenceKey Policy Reference Key
     * @return Display Fault.
     * @author Austin Pilz
     */
    public static DisplayFault buildPolicyNotFoundFault(String referenceKey) {

        return new DisplayFault(DisplayFaultType.ERROR, "Policy Not Found", "The policy " + referenceKey
                + " could not be found.");
    }
}
