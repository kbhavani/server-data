package org.lauchcode.models.forms;

import org.lauchcode.models.ServerFieldType;

/**
 * Created by karumuri on 5/8/2017.
 */
public class SearchForm {

    // The search options
    private ServerFieldType[] fields = ServerFieldType.values();

    // The selected search options
    private ServerFieldType searchField = ServerFieldType.ALL;

    // The search string
    private String keyword;

    public ServerFieldType getSearchField() {
        return searchField;
    }

    public void setSearchField(ServerFieldType searchField) {
        this.searchField = searchField;
    }

    public ServerFieldType[] getFields() {
        return fields;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


}
