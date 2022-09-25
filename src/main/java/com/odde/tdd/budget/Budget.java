package com.odde.tdd.budget;

import java.time.LocalDate;
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

  public long getPartialAmount(long days) {
    return Double.valueOf(getPerDayAmount() * days).longValue();
  }

  public long getCountedAmount(LocalDate begin, LocalDate end) {
    if (belongToCurrentBudget(begin)) {
      int countedDays = begin.lengthOfMonth() - begin.getDayOfMonth() + 1;
      return getPartialAmount(countedDays);
    } else if (belongToCurrentBudget(end)) {
      int countedDays = end.getDayOfMonth();
      return getPartialAmount(countedDays);
    } else {
      return this.amount;
    }
  }

  private double getPerDayAmount() {
    return (double) amount / month.lengthOfMonth();
  }

  public boolean belongToCurrentBudget(LocalDate date) {
    return YearMonth.from(date).equals(month);
  }
}
