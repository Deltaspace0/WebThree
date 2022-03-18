package com.deltaspace.webthree;

import com.deltaspace.webthree.history.HistoryEntry;
import com.deltaspace.webthree.history.HistoryManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Named("areaCheck")
@ApplicationScoped
public class AreaCheckBean implements Serializable {
    private double x;
    private double y;
    private double graphX;
    private double graphY;
    private boolean r1;
    private boolean r2;
    private boolean r3;
    private boolean r4;
    private boolean r5;
    private List<HistoryEntry> history;
    private final HistoryManager historyManager = new HistoryManager();

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getGraphX() {
        return graphX;
    }

    public void setGraphX(double graphX) {
        this.graphX = graphX;
    }

    public double getGraphY() {
        return graphY;
    }

    public void setGraphY(double graphY) {
        this.graphY = graphY;
    }

    public boolean isR1() {
        return r1;
    }

    public void setR1(boolean r1) {
        this.r1 = r1;
    }

    public boolean isR2() {
        return r2;
    }

    public void setR2(boolean r2) {
        this.r2 = r2;
    }

    public boolean isR3() {
        return r3;
    }

    public void setR3(boolean r3) {
        this.r3 = r3;
    }

    public boolean isR4() {
        return r4;
    }

    public void setR4(boolean r4) {
        this.r4 = r4;
    }

    public boolean isR5() {
        return r5;
    }

    public void setR5(boolean r5) {
        this.r5 = r5;
    }

    public List<HistoryEntry> getHistory() {
        history = historyManager.findHistory();
        return history;
    }

    public String checkHit() {
        if (r1) {
            checkArgumentHit(x, y, 1);
        }
        if (r2) {
            checkArgumentHit(x, y, 2);
        }
        if (r3) {
            checkArgumentHit(x, y, 3);
        }
        if (r4) {
            checkArgumentHit(x, y, 4);
        }
        if (r5) {
            checkArgumentHit(x, y, 5);
        }
        return "success";
    }

    public String checkGraphHit() {
        if (r1) {
            checkArgumentHit(graphX, graphY, 1);
        }
        if (r2) {
            checkArgumentHit(graphX, graphY, 2);
        }
        if (r3) {
            checkArgumentHit(graphX, graphY, 3);
        }
        if (r4) {
            checkArgumentHit(graphX, graphY, 4);
        }
        if (r5) {
            checkArgumentHit(graphX, graphY, 5);
        }
        return "success";
    }

    private boolean checkTriangle(double x, double y, int r) {
        return x >= 0 && y >= 0 && y <= r-x;
    }

    private boolean checkRectangle(double x, double y, int r) {
        return x <= 0 && y >= 0 && x >= -r && y <= r;
    }

    private boolean checkCircle(double x, double y, int r) {
        return x <= 0 && y <= 0 && x*x+y*y <= r*r/4;
    }

    private void checkArgumentHit(double x, double y, int r) {
        long start = System.nanoTime();
        boolean hit = checkTriangle(x, y, r) || checkRectangle(x, y, r) || checkCircle(x, y, r);
        HistoryEntry entry = new HistoryEntry();
        entry.setX(x);
        entry.setY(y);
        entry.setR(r);
        entry.setHit(hit);
        entry.setDuration(System.nanoTime()-start);
        entry.setTime(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Europe/Moscow")));
        historyManager.addNew(entry);
    }

    public String removeAllEntries() {
        historyManager.eraseHistory();
        return "success";
    }
}
