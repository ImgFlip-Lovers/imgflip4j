package com.github.adriens.imgflip.sdk.imgflip.sdk;

import com.github.adriens.imgflip.sdk.imgflip.sdk.Data;

public class RawData {

    public RawData() {

    }
    private boolean success;
    Data DataObject;

    // Getter Methods 
    public boolean getSuccess() {
        return success;
    }

    public Data getData() {
        return DataObject;
    }

    // Setter Methods 
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(Data dataObject) {
        this.DataObject = dataObject;
    }
}
