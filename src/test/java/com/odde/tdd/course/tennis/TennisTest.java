package com.odde.tdd.course.tennis;

import com.odde.tdd.tennis.Judge;
import com.odde.tdd.tennis.TennisPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TennisTest {

  @Test
  void love_all() {
    TennisPlayer jackson = new TennisPlayer("jackson");
    TennisPlayer philip = new TennisPlayer("philip");
    Judge judge = new Judge();
    Assertions.assertEquals("Love All", judge.tell(jackson, philip));
  }
}
