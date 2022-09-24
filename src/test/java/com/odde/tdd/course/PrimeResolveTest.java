package com.odde.tdd.course;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.*;

public class PrimeResolveTest {

  private static final Map<Integer, List<Integer>> TEST_DATA = new HashMap<>();

  static {
    TEST_DATA.put(6, Arrays.asList(2, 3));
    TEST_DATA.put(8, Arrays.asList(2, 2, 2));
    TEST_DATA.put(9, Arrays.asList(9));
  }

  @Test
  public void testData() {
    for (Map.Entry<Integer, List<Integer>> testData : TEST_DATA.entrySet()) {
      List<Integer> primeResolvedResult = resolvePrime(testData.getKey());
      assert CollectionUtils.isEqualCollection(primeResolvedResult, testData.getValue());
    }
  }

  private List<Integer> resolvePrime(int number) {
    List<Integer> resolvedResult = new ArrayList<>();
    if (number > 1) {
      int target = number;
      int i = 2;
      while (target % i == 0) {
        resolvedResult.add(i);
        target = target / i;
      }
      if (target != 1) {
        resolvedResult.add(target);
      }
    }
    return resolvedResult;
  }
}
