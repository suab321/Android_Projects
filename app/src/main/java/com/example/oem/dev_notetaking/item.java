package com.example.oem.dev_notetaking;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class item implements Serializable {
    String sub, body, date, filname;


    public item(String sub, String body, String date, String filname) {
        this.sub = sub;
        this.body = body;
        this.date = date;
        this.filname = filname;
    }

    public String getBody() {
        return body;
    }

    public String getSub() {
        return sub;
    }

    public String getFilename() {
        return filname;
    }

    public String getDate() {
        return date;
    }

}
