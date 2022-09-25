package com.odde.tdd.budget;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

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

  private long getPartialAmount(long days) {
    return Double.valueOf(getPerDayAmount() * days).longValue();
  }

  private long countedDaysForBudget(LocalDate begin, LocalDate end) {
    return begin.until(end, ChronoUnit.DAYS) + 1;
  }

  public long getCountedAmount(LocalDate begin, LocalDate end) {
    if (month.isBefore(YearMonth.from(begin)) || month.isAfter(YearMonth.from(end))) {
      return 0L;
    } else if (belongToCurrentBudget(begin) && belongToCurrentBudget(end)) {
      return getPartialAmount(countedDaysForBudget(begin, end));
    } else if (belongToCurrentBudget(begin)) {
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
