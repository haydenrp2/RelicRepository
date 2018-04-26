package com.vcu.groupr.relicrepository;

import java.io.Serializable;
import java.util.Objects;

//organizer, date, time, location (integrate map feature), notes
public class Event implements Serializable {
    private String organizer;
    private String date;
    private String time;
    private String location;
    private String notes;
    public Event(){

    }
    public Event(String organizer, String date, String time, String location, String notes) {
        this.organizer = organizer;
        this.date = date;
        this.time = time;
        this.location = location;
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(organizer, event.organizer) &&
                Objects.equals(date, event.date) &&
                Objects.equals(time, event.time) &&
                Objects.equals(location, event.location) &&
                Objects.equals(notes, event.notes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(organizer, date, time, location, notes);
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
