package com.odde.tdd.course.budget;

import com.odde.tdd.budget.Budget;
import com.odde.tdd.budget.BudgetQuery;
import com.odde.tdd.budget.BudgetRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BudgetTest {

  @Test
  void begin_after_end_then_0() {
    // Arrange
    LocalDate beginDate = LocalDate.of(2022, 9, 24);
    LocalDate endDate = LocalDate.of(2022, 9, 23);

    // Act
    long result = getTotalBudget(beginDate, endDate, null);

    // Assert
    Assertions.assertEquals(0L, result);
  }

  @Test
  void no_budgets_then_0() {
    // Arrange
    LocalDate beginDate = LocalDate.of(2022, 9, 24);
    LocalDate endDate = LocalDate.of(2022, 9, 25);

    // Act
    long result = getTotalBudget(beginDate, endDate, Arrays.asList());

    // Assert
    Assertions.assertEquals(0L, result);
  }

  @Test
  void non_matched_budget_for_same_month_then_0() {
    // Arrange
    LocalDate beginDate = LocalDate.of(2022, 9, 24);
    LocalDate endDate = LocalDate.of(2022, 9, 25);
    List<Budget> budgets = Arrays.asList(new Budget(YearMonth.of(2022, 10), 1000));

    // Act
    long result = getTotalBudget(beginDate, endDate, budgets);

    // Assert
    Assertions.assertEquals(0L, result);
  }

  @Test
  void has_budget_for_same_day() {
    // Arrange
    LocalDate beginDate = LocalDate.of(2022, 9, 24);
    LocalDate endDate = LocalDate.of(2022, 9, 24);

    Budget matchedBudget = new Budget(YearMonth.of(2022, 9), 1000);
    List<Budget> budgets = Arrays.asList(matchedBudget);

    // Act
    long result = getTotalBudget(beginDate, endDate, budgets);

    // Assert
    Assertions.assertEquals(1000 / 30, result);
  }

  @Test
  void has_budget_for_same_month() {
    // Arrange
    LocalDate beginDate = LocalDate.of(2022, 9, 7);
    LocalDate endDate = LocalDate.of(2022, 9, 24);

    Budget matchedBudget = new Budget(YearMonth.of(2022, 9), 1000);
    List<Budget> budgets = Arrays.asList(matchedBudget);

    // Act
    long result = getTotalBudget(beginDate, endDate, budgets);

    // Assert
    Assertions.assertEquals((long) (((double) 1000 / 30) * (24 - 7 + 1)), result);
  }

  @Test
  void has_begin_and_end_budgets() {
    // Arrange
    LocalDate beginDate = LocalDate.of(2022, 9, 7);
    LocalDate endDate = LocalDate.of(2022, 12, 24);

    Budget augBudget = new Budget(YearMonth.of(2022, 8), 199);
    Budget sepBudget = new Budget(YearMonth.of(2022, 9), 5);
    Budget octBudget = new Budget(YearMonth.of(2022, 10), 491);
    Budget novBudget = new Budget(YearMonth.of(2022, 11), 821);
    Budget decBudget = new Budget(YearMonth.of(2022, 12), 992);
    List<Budget> budgets = Arrays.asList(augBudget, sepBudget, octBudget, novBudget, decBudget);

    // Act
    long result = getTotalBudget(beginDate, endDate, budgets);

    // Assert
    Assertions.assertEquals(
        (long) (((double) 5 / 30) * (30 - 7 + 1)) + 491 + 821 + 992 / 31 * 24, result);
  }

  @Test
  void non_begin_and_has_end() {
    // Arrange
    LocalDate beginDate = LocalDate.of(2022, 9, 7);
    LocalDate endDate = LocalDate.of(2022, 12, 24);

    Budget augBudget = new Budget(YearMonth.of(2022, 8), 199);
    Budget octBudget = new Budget(YearMonth.of(2022, 10), 491);
    Budget novBudget = new Budget(YearMonth.of(2022, 11), 821);
    Budget decBudget = new Budget(YearMonth.of(2022, 12), 992);
    List<Budget> budgets = Arrays.asList(augBudget, octBudget, novBudget, decBudget);

    // Act
    long result = getTotalBudget(beginDate, endDate, budgets);

    // Assert
    Assertions.assertEquals(491 + 821 + 992 / 31 * 24, result);
  }

  @Test
  void has_begin_and_non_end() {
    // Arrange
    LocalDate beginDate = LocalDate.of(2022, 9, 7);
    LocalDate endDate = LocalDate.of(2022, 12, 24);

    Budget augBudget = new Budget(YearMonth.of(2022, 8), 199);
    Budget sepBudget = new Budget(YearMonth.of(2022, 9), 621);
    Budget octBudget = new Budget(YearMonth.of(2022, 10), 491);
    Budget novBudget = new Budget(YearMonth.of(2022, 11), 821);
    List<Budget> budgets = Arrays.asList(augBudget, sepBudget, octBudget, novBudget);

    // Act
    long result = getTotalBudget(beginDate, endDate, budgets);

    // Assert
    Assertions.assertEquals((long) (((double) 621 / 30) * (30 - 7 + 1)) + 491 + 821, result);
  }

  private long getTotalBudget(LocalDate begin, LocalDate end, List<Budget> budgets) {
    BudgetRepo repo = mock(BudgetRepo.class);
    when(repo.findAll()).thenReturn(budgets);
    return new BudgetQuery(repo).query(begin, end);
  }
}
