package com.odde.tdd.budget;

import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BudgetQuery {

  private static final long NO_BUDGET_RESULT = 0L;
  private BudgetRepo repo;

  public BudgetQuery(BudgetRepo repo) {
    this.repo = repo;
  }

  public long query(LocalDate begin, LocalDate end) {
    if (begin == null || end == null) {
      throw new IllegalArgumentException("beginDate or endDate can't be null!");
    }

    if (begin.isAfter(end)) {
      return NO_BUDGET_RESULT;
    }

    List<Budget> allBudgets = repo.findAll();
    if (CollectionUtils.isEmpty(allBudgets)) {
      return NO_BUDGET_RESULT;
    }

    YearMonth beginYearMonth = YearMonth.from(begin);
    YearMonth endYearMonth = YearMonth.from(end);
    if (beginYearMonth.equals(endYearMonth)) {
      Budget matchedBudget = getMatchedBudget(beginYearMonth, allBudgets);
      if (matchedBudget != null) {
        return matchedBudget.getPartialAmount(daysBetween(begin, end));
      } else {
        return NO_BUDGET_RESULT;
      }
    } else {
      long result = 0L;
      for (Budget current : allBudgets) {
        if (current.getMonth().isBefore(beginYearMonth)
            || current.getMonth().isAfter(endYearMonth)) {
          continue;
        } else if (current.matchYearMonth(beginYearMonth)) {
          int countedDays = beginYearMonth.lengthOfMonth() - begin.getDayOfMonth() + 1;
          result += current.getPartialAmount(countedDays);
        } else if (current.matchYearMonth(endYearMonth)) {
          int countedDays = end.getDayOfMonth();
          result += current.getPartialAmount(countedDays);
        } else {
          result += current.getAmount();
        }
      }
      return result;
    }
  }

  private long daysBetween(LocalDate begin, LocalDate end) {
    return begin.until(end, ChronoUnit.DAYS) + 1;
  }

  private Budget getMatchedBudget(YearMonth watched, List<Budget> budgets) {
    Budget matchedBudget = null;
    for (Budget each : budgets) {
      if (each.matchYearMonth(watched)) {
        matchedBudget = each;
        break;
      }
    }
    return matchedBudget;
  }
}
