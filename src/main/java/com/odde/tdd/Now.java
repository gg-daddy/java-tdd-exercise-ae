package com.odde.tdd;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Now {

    public String getString() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
                .format(new Date());
    }

}
