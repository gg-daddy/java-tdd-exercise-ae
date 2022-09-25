package com.odde.tdd.budget;

import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDate;
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
    return allBudgets.stream().mapToLong(budget -> budget.getCountedAmount(begin, end)).sum();
  }
}
