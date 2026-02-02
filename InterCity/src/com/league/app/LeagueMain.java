package com.league.app;

import com.league.bean.Team;
import com.league.bean.Venue;
import com.league.service.LeagueService;
import com.league.util.ValidationException;
import com.league.util.VenueDoubleBookingException;

public class LeagueMain {

    private static LeagueService leagueService;
    public static void main(String[] args) {

        leagueService = new LeagueService();

        System.out.println("--- Inter-City Sports League Console ---");
        try {
            Team t = new Team();
            t.setTeamCode("HYDR");
            t.setTeamName("Hyderabad Hurricanes");
            t.setCity("Hyderabad");
            t.setCoachName("Vikram Rao");
            t.setStatus("ACTIVE");

            boolean ok = leagueService.registerNewTeam(t);
            System.out.println(ok ? "TEAM REGISTERED" : "TEAM REGISTRATION FAILED");

        } catch (ValidationException e) {
            System.out.println("Validation Error: " + e.toString());
        } catch (Exception e) {
            System.out.println("System Error: " + e.getMessage());
        }
        try {
            Venue v = new Venue();
            v.setVenueID(3005);
            v.setVenueName("Hyderabad Sports Ground");
            v.setCity("Hyderabad");
            v.setCapacity(27000);
            v.setStatus("AVAILABLE");

            boolean ok = leagueService.registerNewVenue(v);
            System.out.println(ok ? "VENUE REGISTERED" : "VENUE REGISTRATION FAILED");

        } catch (ValidationException e) {
            System.out.println("Validation Error: " + e.toString());
        } catch (Exception e) {
            System.out.println("System Error: " + e.getMessage());
        }
        try {
            java.sql.Date matchDate =
                new java.sql.Date(System.currentTimeMillis());

            boolean ok = leagueService.scheduleMatch(
                "HYDR",   
                "TCHN",  
                3005,    
                matchDate,
                "18:30"
            );

            System.out.println(ok ? "MATCH SCHEDULED" : "MATCH SCHEDULING FAILED");

        } catch (VenueDoubleBookingException e) {
            System.out.println("Scheduling Error: " + e.toString());
        } catch (ValidationException e) {
            System.out.println("Validation Error: " + e.toString());
        } catch (Exception e) {
            System.out.println("System Error: " + e.getMessage());
        }
    }
}
