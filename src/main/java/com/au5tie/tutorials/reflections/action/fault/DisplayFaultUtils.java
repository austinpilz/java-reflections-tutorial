package com.au5tie.tutorials.reflections.action.fault;

import com.au5tie.tutorials.reflections.action.request.BaseActionResponse;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayFaultUtils {

    /**
     * Generates {@link DisplayFault} and adds it to the {@link BaseActionResponse}.
     *
     * @param response Base Action Response.
     * @param type Display Fault Type.
     * @param title Fault Title.
     * @param message Fault Message.
     * @author Austin Pilz
     */
    public static void generateAndAddFault(BaseActionResponse response, DisplayFaultType type, String title, String message) {

        DisplayFault fault = DisplayFault.builder()
                .type(type)
                .title(title)
                .message(message)
                .build();

        response.addFault(fault);
    }

    /**
     * Returns all faults of the provided type.
     *
     * @param faults Display Faults.
     * @param type Fault Type.
     * @return Matching faults of type.
     * @author Austin Pilz
     */
    public static List<DisplayFault> getFaultsOfType(List<DisplayFault> faults, DisplayFaultType type) {

        if (CollectionUtils.isNotEmpty(faults)) {
            return faults.stream()
                    .filter(fault -> fault.getType().equals(type))
                    .collect(Collectors.toList());
        } else {
            // The fault list was empty.
            return new ArrayList<>();
        }
    }
}
