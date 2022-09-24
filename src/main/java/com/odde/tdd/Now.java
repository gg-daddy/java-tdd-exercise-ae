package com.odde.tdd;

import com.odde.tdd.now.CurrentTimeProvider;

import java.text.SimpleDateFormat;

public class Now {

  public static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss.SSS";
  private CurrentTimeProvider provider;

  public Now(CurrentTimeProvider provider) {
    this.provider = provider;
  }

  public String getString() {
    return new SimpleDateFormat(DATE_FORMAT).format(provider.getDate());
  }
}
