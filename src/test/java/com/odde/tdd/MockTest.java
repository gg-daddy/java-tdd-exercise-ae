package com.odde.tdd;

import org.junit.jupiter.api.Test;

import java.time.YearMonth;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockTest {
    @Test
    public void mock_repo(){
        BudgetRepo repo = mock(BudgetRepo.class);
        when(repo.findAll()).thenReturn(Arrays.asList(new Budget(YearMonth.of(2018, 11), 1000)));
        assertEquals(1, repo.findAll().size());
    }
}
