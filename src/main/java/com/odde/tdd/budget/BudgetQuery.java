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
      Budget matchedBudget = getWantedBudget(beginYearMonth, allBudgets);
      if (matchedBudget != null) {
        return matchedBudget.getPartialAmount(countedDaysForBudget(begin, end));
      } else {
        return NO_BUDGET_RESULT;
      }
    } else {
      long result = 0L;
      for (Budget current : allBudgets) {
        if (current.getMonth().isBefore(beginYearMonth)
            || current.getMonth().isAfter(endYearMonth)) {
          continue;
        } else if (current.isWantedBudget(beginYearMonth)) {
          int countedDays = beginYearMonth.lengthOfMonth() - begin.getDayOfMonth() + 1;
          result += current.getPartialAmount(countedDays);
        } else if (current.isWantedBudget(endYearMonth)) {
          int countedDays = end.getDayOfMonth();
          result += current.getPartialAmount(countedDays);
        } else {
          result += current.getAmount();
        }
      }
      return result;
    }
  }

  private long countedDaysForBudget(LocalDate begin, LocalDate end) {
    return begin.until(end, ChronoUnit.DAYS) + 1;
  }

  private Budget getWantedBudget(YearMonth watched, List<Budget> budgets) {
    return budgets.stream().filter(budget -> budget.isWantedBudget(watched)).findAny().orElse(null);
  }
}
