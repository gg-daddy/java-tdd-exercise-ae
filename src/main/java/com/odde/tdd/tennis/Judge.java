package com.odde.tdd.tennis;

public class Judge {

  private static final String[] SCORE_DISPLAY = new String[] {"Love", "fifteen"};

  public String tell(TennisPlayer player1, TennisPlayer player2) {
    if (player1.getScore() == player2.getScore()) {
      return SCORE_DISPLAY[player1.getScore()] + " All";
    }
    return SCORE_DISPLAY[player1.getScore()] + " " + SCORE_DISPLAY[player2.getScore()];
  }
}
