package com.odde.tdd.tennis;

public class TennisPlayer {

  private final String name;
  private int score = 0;

  public TennisPlayer(String name) {
    this.name = name;
  }

  public int getScore() {
    return score;
  }

  public void win() {
    score++;
  }

  public String getName() {
    return this.name;
  }
}
