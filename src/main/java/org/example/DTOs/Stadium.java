package org.example.DTOs;

import org.example.LocationGPS;

public class Stadium {
    private LocationGPS location;
    private int maxNumOfAudience;

    public Stadium(double latitude, double longitude, int maxNumOfAudience) {
        this.location = new LocationGPS(latitude, longitude);
        this.maxNumOfAudience = maxNumOfAudience;
    }

    public LocationGPS getLocation() {
        return location;
    }

    public int getMaxNumOfAudience() {
        return maxNumOfAudience;
    }

    public void setLocation(LocationGPS location) {
        this.location = location;
    }

    public void setMaxNumOfAudience(int maxNumOfAudience) {
        this.maxNumOfAudience = maxNumOfAudience;
    }

    @Override
    public String toString() {
        return "Stadium{" +
                "location=" + location +
                ", maxNumOfAudience=" + maxNumOfAudience +
                '}';
    }
}
