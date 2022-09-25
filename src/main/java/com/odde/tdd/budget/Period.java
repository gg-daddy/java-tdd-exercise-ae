package com.odde.tdd.budget;

import java.time.LocalDate;

public class Period {
  private final LocalDate begin;
  private final LocalDate end;

  public Period(LocalDate begin, LocalDate end) {
    if (begin == null || end == null) {
      throw new IllegalArgumentException("beginDate or endDate can't be null!");
    }
    if (begin.isAfter(end)) {
      throw new IllegalArgumentException("beginDate can't be after endDate!");
    }

    this.begin = begin;
    this.end = end;
  }

  public LocalDate getBegin() {
    return begin;
  }

  public LocalDate getEnd() {
    return end;
  }
}
