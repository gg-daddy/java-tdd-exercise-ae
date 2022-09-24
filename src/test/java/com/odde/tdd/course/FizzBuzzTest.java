package com.odde.tdd.course;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

public class FizzBuzzTest {

  private static final Map<Integer, String> FIZZ_BUZZ_TABLE = new HashMap<>();

  public static final String FIZZ = "FIZZ";
  public static final String BUZZ = "BUZZ";
  public static final String FIZZBUZZ = FIZZ + BUZZ;

  static {
    FIZZ_BUZZ_TABLE.put(3, FIZZ);
    FIZZ_BUZZ_TABLE.put(5, BUZZ);
  }

  @ParameterizedTest
  @MethodSource("useGenerator")
  public void shouldWork(int num, String result) {
    assert result.equals(tellMe(num));
  }

  static Stream<Arguments> useGenerator() {
    return Stream.of(
        Arguments.of(1, "1"),
        Arguments.of(2, "2"),
        Arguments.of(3, FIZZ),
        Arguments.of(5, BUZZ),
        Arguments.of(13, FIZZ),
        Arguments.of(15, FIZZBUZZ),
        Arguments.of(51, FIZZBUZZ),
        Arguments.of(53, FIZZBUZZ),
        Arguments.of(151, BUZZ));
  }

  private String tellMe(int num) {
    StringBuffer result = new StringBuffer();
    Iterator<Map.Entry<Integer, String>> it = FIZZ_BUZZ_TABLE.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<Integer, String> each = it.next();
      if (isHit(num, each.getKey())) {
        result.append(each.getValue());
      }
    }
    if (result.length() == 0) {
      return String.valueOf(num);
    } else {
      return result.toString();
    }
  }

  private boolean isHit(int num, int target) {
    return num % target == 0 || String.valueOf(num).contains(String.valueOf(target));
  }
}
