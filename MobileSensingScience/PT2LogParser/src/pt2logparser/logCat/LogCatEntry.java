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
    private long timeSpan;
    
    private double cpuConsumption;
    private double lcdConsumption;
    private double wifiConsumption;
    private double threeGConsumption;
    
    private double cpuCurrentConsumption;
    private double lcdCurrentConsumption;
    private double wifiCurrentConsumption;
    private double threeGCurrentConsumption;

    public LogCatEntry(AppEvent event, long time, String data) {
        this.event = event;
        this.time = time;
        this.data = data;
    }

    public long getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(long timeSpan) {
        this.timeSpan = timeSpan;
    }

    
    public double getCpuCurrentConsumption() {
        return cpuCurrentConsumption;
    }

    public void setCpuCurrentConsumption(double cpuCurrentConsumption) {
        this.cpuCurrentConsumption = cpuCurrentConsumption;
    }

    public double getLcdCurrentConsumption() {
        return lcdCurrentConsumption;
    }

    public void setLcdCurrentConsumption(double lcdCurrentConsumption) {
        this.lcdCurrentConsumption = lcdCurrentConsumption;
    }

    public double getWifiCurrentConsumption() {
        return wifiCurrentConsumption;
    }

    public void setWifiCurrentConsumption(double wifiCurrentConsumption) {
        this.wifiCurrentConsumption = wifiCurrentConsumption;
    }

    public double getThreeGCurrentConsumption() {
        return threeGCurrentConsumption;
    }

    public void setThreeGCurrentConsumption(double threeGCurrentConsumption) {
        this.threeGCurrentConsumption = threeGCurrentConsumption;
    }

    
    public double getCpuConsumption() {
        return cpuConsumption;
    }

    public void setCpuConsumption(double cpuConsumption) {
        this.cpuConsumption = cpuConsumption;
    }

    public double getLcdConsumption() {
        return lcdConsumption;
    }

    public void setLcdConsumption(double lcdConsumption) {
        this.lcdConsumption = lcdConsumption;
    }

    public double getWifiConsumption() {
        return wifiConsumption;
    }

    public void setWifiConsumption(double wifiConsumption) {
        this.wifiConsumption = wifiConsumption;
    }

    public double getThreeGConsumption() {
        return threeGConsumption;
    }

    public void setThreeGConsumption(double threeGConsumption) {
        this.threeGConsumption = threeGConsumption;
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
