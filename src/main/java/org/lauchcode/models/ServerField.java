package org.lauchcode.models;

/**
 * Created by karumuri on 5/9/2017.
 */
public class ServerField {
    private String value;
    private int id;
    private static int nextId = 1;

    public ServerField() {
        id = nextId;
        nextId++;
    }

    public ServerField(String aValue) {
        this();
        value = aValue;
    }

    public boolean contains(String value) {
        return this.value.toLowerCase().contains(value.toLowerCase());
    }

    public String getValue() {
        return value;
    }

    public void setValue(String aValue) {
        value = aValue;
    }

    public String toString() {
        return value;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerField serverField = (ServerField) o;

        return id == serverField.getId();
    }

    @Override
    public int hashCode() {
        return id;
    }

}
