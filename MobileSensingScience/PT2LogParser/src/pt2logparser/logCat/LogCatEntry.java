/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt2logparser.logCat;

/**
 *
 * @author henny
 */
public class LogCatEntry implements Comparable<LogCatEntry> {

    private AppEvent event;
    private long time;
    private String data;

    public LogCatEntry(AppEvent event, long time, String data) {
        this.event = event;
        this.time = time;
        this.data = data;
    }

    public AppEvent getEvent() {
        return event;
    }

    public void setEvent(AppEvent event) {
        this.event = event;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int compareTo(LogCatEntry o) {
        if (o != null) {
            if (this.getTime() < o.getTime()) {
                return -1;
            }
            if (this.getTime() > o.getTime()) {
                return 1;
            } else {
                return 0;
            }
        } else {
            throw new IllegalArgumentException("param is null");
        }
    }
    
    @Override
    public String toString(){
        return this.getTime()+" Event: "+this.getEvent().toString()+" Data: "+this.getData();
    }

}
