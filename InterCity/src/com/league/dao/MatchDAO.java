package com.league.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.league.bean.Match;
import com.league.util.DBUtil;
public class MatchDAO {
    public int generateMatchID() {

        int matchID = 0;
try {
            Connection con = DBUtil.getDBConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT NVL(MAX(MATCH_ID),0)+1 FROM MATCH_TBL");

            if (rs.next())
                matchID = rs.getInt(1);

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matchID;
    }
    public boolean insertMatch(Match match) {
 try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement("INSERT INTO MATCH_TBL VALUES (?,?,?,?,?,?,?,?,?,?)");

            ps.setInt(1, match.getMatchID());
            ps.setString(2, match.getHomeTeamCode());
            ps.setString(3, match.getAwayTeamCode());
            ps.setInt(4, match.getVenueID());
            ps.setDate(5, match.getMatchDate());
            ps.setString(6, match.getStartTime());
            ps.setObject(7, match.getHomeScore());
            ps.setObject(8, match.getAwayScore());
            ps.setString(9, match.getResultStatus());
            ps.setString(10, match.getWinnerTeamCode());

            int rows = ps.executeUpdate();
            con.close();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateMatchResult(int matchID, int homeScore, int awayScore,
                                     String resultStatus, String winnerTeamCode) {

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement(
                    "UPDATE MATCH_TBL SET HOME_SCORE=?, AWAY_SCORE=?, RESULT_STATUS=?, WINNER_TEAM_CODE=? WHERE MATCH_ID=?"
                );

            ps.setInt(1, homeScore);
            ps.setInt(2, awayScore);
            ps.setString(3, resultStatus);
            ps.setString(4, winnerTeamCode);
            ps.setInt(5, matchID);

            int rows = ps.executeUpdate();
            con.close();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Match findMatch(int matchID) {

        Match match = null;

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM MATCH_TBL WHERE MATCH_ID=?");

            ps.setInt(1, matchID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                match = new Match();
                match.setMatchID(rs.getInt("MATCH_ID"));
                match.setHomeTeamCode(rs.getString("HOME_TEAM_CODE"));
                match.setAwayTeamCode(rs.getString("AWAY_TEAM_CODE"));
                match.setVenueID(rs.getInt("VENUE_ID"));
                match.setMatchDate(rs.getDate("MATCH_DATE"));
                match.setStartTime(rs.getString("START_TIME"));
                match.setHomeScore((Integer) rs.getObject("HOME_SCORE"));
                match.setAwayScore((Integer) rs.getObject("AWAY_SCORE"));
                match.setResultStatus(rs.getString("RESULT_STATUS"));
                match.setWinnerTeamCode(rs.getString("WINNER_TEAM_CODE"));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return match;
    }
    public List<Match> findMatchesByTeam(String teamCode) {

        List<Match> list = new ArrayList<>();

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement(
                    "SELECT * FROM MATCH_TBL WHERE HOME_TEAM_CODE=? OR AWAY_TEAM_CODE=?"
                );

            ps.setString(1, teamCode);
            ps.setString(2, teamCode);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Match match = new Match();
                match.setMatchID(rs.getInt("MATCH_ID"));
                match.setHomeTeamCode(rs.getString("HOME_TEAM_CODE"));
                match.setAwayTeamCode(rs.getString("AWAY_TEAM_CODE"));
                match.setVenueID(rs.getInt("VENUE_ID"));
                match.setMatchDate(rs.getDate("MATCH_DATE"));
                match.setStartTime(rs.getString("START_TIME"));
                match.setHomeScore((Integer) rs.getObject("HOME_SCORE"));
                match.setAwayScore((Integer) rs.getObject("AWAY_SCORE"));
                match.setResultStatus(rs.getString("RESULT_STATUS"));
                match.setWinnerTeamCode(rs.getString("WINNER_TEAM_CODE"));
                list.add(match);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Match> findMatchesByVenueAndSlot(int venueID, Date matchDate, String startTime) {

        List<Match> list = new ArrayList<>();

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement(
                    "SELECT * FROM MATCH_TBL WHERE VENUE_ID=? AND MATCH_DATE=? AND START_TIME=?"
                );

            ps.setInt(1, venueID);
            ps.setDate(2, matchDate);
            ps.setString(3, startTime);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Match match = new Match();
                match.setMatchID(rs.getInt("MATCH_ID"));
                list.add(match);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Match> findActiveFixturesForTeam(String teamCode) {

        List<Match> list = new ArrayList<>();

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement(
                    "SELECT * FROM MATCH_TBL WHERE (HOME_TEAM_CODE=? OR AWAY_TEAM_CODE=?) AND RESULT_STATUS='SCHEDULED'"
                );

            ps.setString(1, teamCode);
            ps.setString(2, teamCode);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Match match = new Match();
                match.setMatchID(rs.getInt("MATCH_ID"));
                list.add(match);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Match> findActiveFixturesForVenue(int venueID) {

        List<Match> list = new ArrayList<>();

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement(
                    "SELECT * FROM MATCH_TBL WHERE VENUE_ID=? AND RESULT_STATUS='SCHEDULED'"
                );

            ps.setInt(1, venueID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Match match = new Match();
                match.setMatchID(rs.getInt("MATCH_ID"));
                list.add(match);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
