package com.league.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.league.bean.Venue;
import com.league.util.DBUtil;

public class VenueDAO {
    public Venue findVenue(int venueID) {

        Venue venue = null;

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM VENUE_TBL WHERE VENUE_ID=?");

            ps.setInt(1, venueID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                venue = new Venue();
                venue.setVenueID(rs.getInt("VENUE_ID"));
                venue.setVenueName(rs.getString("VENUE_NAME"));
                venue.setCity(rs.getString("CITY"));
                venue.setCapacity(rs.getInt("CAPACITY"));
                venue.setStatus(rs.getString("STATUS"));
            }

            con.close();
        } catch (Exception e) {
        }

        return venue;
    }
    public List<Venue> viewAllVenues() {

        List<Venue> venueList = new ArrayList<>();

        try {
            Connection con = DBUtil.getDBConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM VENUE_TBL");

            while (rs.next()) {
                Venue venue = new Venue();
                venue.setVenueID(rs.getInt("VENUE_ID"));
                venue.setVenueName(rs.getString("VENUE_NAME"));
                venue.setCity(rs.getString("CITY"));
                venue.setCapacity(rs.getInt("CAPACITY"));
                venue.setStatus(rs.getString("STATUS"));

                venueList.add(venue);
            }

            con.close();
        } catch (Exception e) {
        }

        return venueList;
    }

    public boolean insertVenue(Venue venue) {

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement("INSERT INTO VENUE_TBL VALUES (?,?,?,?,?)");

            ps.setInt(1, venue.getVenueID());
            ps.setString(2, venue.getVenueName());
            ps.setString(3, venue.getCity());
            ps.setInt(4, venue.getCapacity());
            ps.setString(5, venue.getStatus());

            int rows = ps.executeUpdate();
            con.close();

            return rows > 0;

        } catch (Exception e) {
           
            return false;
        }
    }
    public boolean updateVenueStatus(int venueID, String status) {

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement(
                    "UPDATE VENUE_TBL SET STATUS=? WHERE VENUE_ID=?");

            ps.setString(1, status);
            ps.setInt(2, venueID);

            int rows = ps.executeUpdate();
            con.close();

            return rows > 0;

        } catch (Exception e) {
            return false;
        }
    }
    public boolean deleteVenue(int venueID) {

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement("DELETE FROM VENUE_TBL WHERE VENUE_ID=?");

            ps.setInt(1, venueID);
            int rows = ps.executeUpdate();
            con.close();

            return rows > 0;

        } catch (Exception e) {
            return false;
        }
    }
}
