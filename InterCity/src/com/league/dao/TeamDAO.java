package com.league.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.league.bean.Team;
import com.league.util.DBUtil;
public class TeamDAO {
    public Team findTeam(String teamCode) {

        Team team = null;
        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM TEAM_TBL WHERE TEAM_CODE=?");

            ps.setString(1, teamCode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                team = new Team();
                team.setTeamCode(rs.getString("TEAM_CODE"));
                team.setTeamName(rs.getString("TEAM_NAME"));
                team.setCity(rs.getString("CITY"));
                team.setCoachName(rs.getString("COACH_NAME"));
                team.setStatus(rs.getString("STATUS"));
            }

            con.close();
        } catch (Exception e) {
        }

        return team;
    }
    public List<Team> viewAllTeams() {

        List<Team> teamList = new ArrayList<>();

        try {
            Connection con = DBUtil.getDBConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM TEAM_TBL");

            while (rs.next()) {
                Team team = new Team();
                team.setTeamCode(rs.getString("TEAM_CODE"));
                team.setTeamName(rs.getString("TEAM_NAME"));
                team.setCity(rs.getString("CITY"));
                team.setCoachName(rs.getString("COACH_NAME"));
                team.setStatus(rs.getString("STATUS"));

                teamList.add(team);
            }

            con.close();
        } catch (Exception e) {
        }

        return teamList;
    }
    public boolean insertTeam(Team team) {

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement("INSERT INTO TEAM_TBL VALUES (?,?,?,?,?)");

            ps.setString(1, team.getTeamCode());
            ps.setString(2, team.getTeamName());
            ps.setString(3, team.getCity());
            ps.setString(4, team.getCoachName());
            ps.setString(5, team.getStatus());

            int rows = ps.executeUpdate();
            con.close();

            return rows > 0;

        } catch (Exception e) {
            return false;
        }
    }
    public boolean updateTeamStatus(String teamCode, String status) {

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement(
                    "UPDATE TEAM_TBL SET STATUS=? WHERE TEAM_CODE=?");

            ps.setString(1, status);
            ps.setString(2, teamCode);

            int rows = ps.executeUpdate();
            con.close();

            return rows > 0;

        } catch (Exception e) {
            return false;
        }
    }
    public boolean deleteTeam(String teamCode) {

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement("DELETE FROM TEAM_TBL WHERE TEAM_CODE=?");

            ps.setString(1, teamCode);
            int rows = ps.executeUpdate();
            con.close();

            return rows > 0;

        } catch (Exception e) {
            return false;
        }
    }
}
