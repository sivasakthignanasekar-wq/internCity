package com.league.util;

public class ActiveFixturesExistException extends Exception {
    public String toString() {
        return "Active fixtures exist. Cannot delete";
    }
}
