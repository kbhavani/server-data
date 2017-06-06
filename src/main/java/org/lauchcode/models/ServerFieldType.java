package org.lauchcode.models;

/**
 * Created by karumuri on 5/9/2017.
 */
public enum ServerFieldType {

    LOCATION ("Location"),
    STATUS ("Status"),
    TEAM ("Team"),
    ALL ("All");

    private final String name;

    ServerFieldType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
