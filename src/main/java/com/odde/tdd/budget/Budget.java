package com.odde.tdd.budget;

import java.time.YearMonth;

public class Budget {
  private final YearMonth month;
  private final long amount;

  public Budget(YearMonth month, long amount) {
    this.month = month;
    this.amount = amount;
  }

  public YearMonth getMonth() {
    return month;
  }

  public long getAmount() {
    return amount;
  }

  public long getPortedAmount(long days) {
    return getPerDayAmount() * days;
  }

  public long getPerDayAmount() {
    return amount / month.lengthOfMonth();
  }

  public boolean matchYearMonth(YearMonth wanted) {
    return wanted.equals(month);
  }
}