package com.gjdev.hugo.gjant.data.event;

/**
 * Created by Hugo on 19/01/2017.
 */

public class ValidOrderForm {
    private int typePosition;
    private String description;

    public ValidOrderForm(int typePosition, String description) {
        this.typePosition = typePosition;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getTypePosition() {
        return typePosition;
    }
}
