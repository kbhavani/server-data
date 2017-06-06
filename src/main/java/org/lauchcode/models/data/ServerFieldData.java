package org.lauchcode.models.data;

import org.lauchcode.models.ServerField;

import java.util.ArrayList;

/**
 * Created by karumuri on 5/8/2017.
 */
public class ServerFieldData<T extends ServerField> {

    private ArrayList<T> allFields = new ArrayList<>();

    public ArrayList<T> findAll() {
        return allFields;
    }

    public T findById(int id) {
        for (T item : allFields) {
            if (item.getId() == id)
                return item;
        }

        return null;
    }

    public void add(T item) {
        allFields.add(item);
    }

    T findByValue(String value) {
        for (T item : allFields) {
            if (item.contains(value))
                return item;
        }

        return null;
    }

}
