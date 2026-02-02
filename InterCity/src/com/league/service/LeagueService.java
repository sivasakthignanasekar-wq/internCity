package com.league.service;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.league.bean.*;
import com.league.dao.*;
import com.league.util.*;
public class LeagueService {

    TeamDAO teamDAO = new TeamDAO();
    VenueDAO venueDAO = new VenueDAO();
    MatchDAO matchDAO = new MatchDAO();
    public Team viewTeamDetails(String teamCode) throws ValidationException {
        if (teamCode == null || teamCode.isEmpty())
            throw new ValidationException();
        return teamDAO.findTeam(teamCode);
    }

    public List<Team> viewAllTeams() {
        return teamDAO.viewAllTeams();
    }
    public boolean registerNewTeam(Team team) throws ValidationException {
        if (team.getTeamCode() == null || team.getTeamCode().isEmpty())
            throw new ValidationException();

        if (teamDAO.findTeam(team.getTeamCode()) != null)
            return false;

        team.setStatus("ACTIVE");
        return teamDAO.insertTeam(team);
    }
    public boolean registerNewVenue(Venue venue) throws ValidationException {
        if (venue.getVenueName() == null || venue.getVenueName().isEmpty())
            throw new ValidationException();

        venue.setStatus("AVAILABLE");
        return venueDAO.insertVenue(venue);
    }
    public boolean scheduleMatch(String home, String away, int venueID,
                                 Date date, String time)
            throws ValidationException, VenueDoubleBookingException {

        if (home.equals(away))
            throw new ValidationException();

        if (!matchDAO.findMatchesByVenueAndSlot(venueID, date, time).isEmpty())
            throw new VenueDoubleBookingException();

        Match m = new Match();
        m.setMatchID(matchDAO.generateMatchID());
        m.setHomeTeamCode(home);
        m.setAwayTeamCode(away);
        m.setVenueID(venueID);
        m.setMatchDate(date);
        m.setStartTime(time);
        m.setResultStatus("SCHEDULED");

        return matchDAO.insertMatch(m);
    }
    public boolean recordMatchResult(int matchID, int homeScore, int awayScore)
            throws ValidationException {

        Match m = matchDAO.findMatch(matchID);
        if (m == null || !"SCHEDULED".equals(m.getResultStatus()))
            return false;

        String winner;
        if (homeScore > awayScore)
            winner = m.getHomeTeamCode();
        else if (awayScore > homeScore)
            winner = m.getAwayTeamCode();
        else
            winner = "DRAW";

        return matchDAO.updateMatchResult(
            matchID, homeScore, awayScore, "COMPLETED", winner);
    }
    public boolean removeTeam(String teamCode)
            throws ValidationException, ActiveFixturesExistException {

        if (!matchDAO.findActiveFixturesForTeam(teamCode).isEmpty())
            throw new ActiveFixturesExistException();

        return teamDAO.deleteTeam(teamCode);
    }
    public boolean removeVenue(int venueID)
            throws ValidationException, ActiveFixturesExistException {

        if (!matchDAO.findActiveFixturesForVenue(venueID).isEmpty())
            throw new ActiveFixturesExistException();

        return venueDAO.deleteVenue(venueID);
    }
}
