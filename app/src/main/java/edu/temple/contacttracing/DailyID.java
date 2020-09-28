package edu.temple.contacttracing;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.UUID;
import java.time.LocalDate;

public class DailyID {
    HashMap<LocalDate, String> twoWeeks;
    String dailyID;
    LocalDate now;
    boolean checkedIN;

    public void recordUUID(LocalDate now, String dailyID) {
        if (twoWeeks.containsKey(now))
            checkedIN = true;
        else {
            twoWeeks.put(now, dailyID);
            if(twoWeeks.containsKey(now.minus(14, ChronoUnit.DAYS)));
            twoWeeks.remove(now.minus(14, ChronoUnit.DAYS));
        }
    }

    public String getDailyID(){
        return dailyID;
    }

    public LocalDate getNow(){
        return now;
    }

    public void setDailyID(String dailyID) {
        this.dailyID = UUID.randomUUID().toString();
    }

    public void setNow(){
        this.now = LocalDate.now();
    }

    public void newID(){
        dailyID = UUID.randomUUID().toString();
        twoWeeks.remove(now);
        twoWeeks.put(now, dailyID);
    }
}
