package Utils;

import android.location.Location;

import java.util.Date;

/**
 * Created by whq on 17/6/26 0026.
 * 用于存储每一次地震的详细信息
 */

public class Quake {
    private Date date;
    private String details;
    private Location location;
    private double magnitude;
    private String link;

    public Quake(Date date, String details, Location location, double magnitude, String link) {
        this.date = date;
        this.details = details;
        this.location = location;
        this.magnitude = magnitude;
        this.link = link;
    }

    @Override
    public String toString() {
        return "Quake{" +
                "date=" + date +
                ", details='" + details + '\'' +
                ", location=" + location +
                ", magnitude=" + magnitude +
                ", link='" + link + '\'' +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public Location getLocation() {
        return location;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLink() {
        return link;
    }
}
