package com.deltaspace.webthree.history;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name="HistoryEntry", schema = "s311731")
public class HistoryEntry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name="id", unique = true)
    private long id;

    @NotNull
    @Column(name="x")
    private double x;

    @NotNull
    @Column(name="y")
    private double y;

    @NotNull
    @Column(name="r")
    private int r;

    @NotNull
    @Column(name="hit")
    private boolean hit;

    @NotNull
    @Column(name="server_time")
    private ZonedDateTime time;

    @NotNull
    @Column(name="server_duration")
    private long duration;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getX() {
        return Math.round(x*1000)/1000.0;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return Math.round(y*1000)/1000.0;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getHitString() {
        return hit ? "Есть" : "Нет";
    }

    public String getTimeString() {
        return time.format(new java.time.format.DateTimeFormatterBuilder()
                .appendPattern("dd.MM.yyyy HH:mm:ss (O)")
                .toFormatter());
    }

    public String getDurationString() {
        return String.valueOf(duration);
    }

    public String getHitStyle() {
        return "fill: var(--graph-" + (hit ? "hit" : "no-hit") + ");";
    }
}