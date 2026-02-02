package com.league.bean;

import java.sql.Date;

public class Match {
    private int matchID;
    private String homeTeamCode;
    private String awayTeamCode;
    private int venueID;
    private Date matchDate;
    private String startTime;
    private Integer homeScore;
    private Integer awayScore;
    private String resultStatus;
    private String winnerTeamCode;

    public int getMatchID() { return matchID; }
    public void setMatchID(int matchID) { this.matchID = matchID; }

    public String getHomeTeamCode() { return homeTeamCode; }
    public void setHomeTeamCode(String homeTeamCode) { this.homeTeamCode = homeTeamCode; }

    public String getAwayTeamCode() { return awayTeamCode; }
    public void setAwayTeamCode(String awayTeamCode) { this.awayTeamCode = awayTeamCode; }

    public int getVenueID() { return venueID; }
    public void setVenueID(int venueID) { this.venueID = venueID; }

    public Date getMatchDate() { return matchDate; }
    public void setMatchDate(Date matchDate) { this.matchDate = matchDate; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public Integer getHomeScore() { return homeScore; }
    public void setHomeScore(Integer homeScore) { this.homeScore = homeScore; }

    public Integer getAwayScore() { return awayScore; }
    public void setAwayScore(Integer awayScore) { this.awayScore = awayScore; }

    public String getResultStatus() { return resultStatus; }
    public void setResultStatus(String resultStatus) { this.resultStatus = resultStatus; }

    public String getWinnerTeamCode() { return winnerTeamCode; }
    public void setWinnerTeamCode(String winnerTeamCode) { this.winnerTeamCode = winnerTeamCode; }
}
