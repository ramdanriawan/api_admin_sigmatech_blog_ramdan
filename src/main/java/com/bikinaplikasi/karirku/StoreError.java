package com.bikinaplikasi.karirku;

import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StoreError {
    private List<ObjectError> objectErrors;
    private List<StoreErrorMessage> storeErrorMessage = new ArrayList<>();

    public StoreError(List<ObjectError> objectErrors) {
        this.objectErrors = objectErrors;

        objectErrors.parallelStream().forEach((item) -> {
            String[] fieldSplit = Objects.requireNonNull(item.getCodes())[0].split("\\.");

            String field = fieldSplit[fieldSplit.length - 1];

           storeErrorMessage.add(new StoreErrorMessage(field + ": " + item.getDefaultMessage(), field));
        });
    }

    public List<ObjectError> getObjectErrors() {
        return objectErrors;
    }

    public void setObjectErrors(List<ObjectError> objectErrors) {
        this.objectErrors = objectErrors;
    }

    public List<StoreErrorMessage> getStoreErrorMessage() {
        return storeErrorMessage;
    }

    public void setStoreErrorMessage(List<StoreErrorMessage> storeErrorMessage) {
        this.storeErrorMessage = storeErrorMessage;
    }

}

class StoreErrorMessage {
    private String defaultMessage;
    private String field;

    public StoreErrorMessage(String defaultMessage, String field) {
        this.defaultMessage = defaultMessage;
        this.field = field;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
