package com.odde.tdd.course;

import com.odde.tdd.Now;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class NowTest {

  @Test
  public void test() {
    Date targetDate = new Date();
    Now now = new Now(() -> targetDate);
    Assertions.assertEquals(DateFormatUtils.format(targetDate, Now.DATE_FORMAT), now.getString());
  }
}
