package com.odde.tdd.budget;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class BudgetQuery {

  private static final long NO_BUDGET_RESULT = 0L;
  private BudgetRepo repo;

  public BudgetQuery(BudgetRepo repo) {
    this.repo = repo;
  }

  public long query(Period period) {
    List<Budget> allBudgets = repo.findAll();
    if (CollectionUtils.isEmpty(allBudgets)) {
      return NO_BUDGET_RESULT;
    }
    return allBudgets.stream().mapToLong(budget -> budget.getCountedAmount(period)).sum();
  }
}
