package com.leadway.ps.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dev.io
 */
public class Criteria {

    private int fund;
    private String pin;
    private String from;
    private String to;
    static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");

    public Criteria() {
        this(0, null);
    }

    public Criteria(int fund, String pin) {
        this.fund = fund;
        this.pin = pin;
        Date d = new Date();
        this.from = SDF.format(new Date(d.getTime() - 360 * 24 * 60 * 60 * 1000));
        this.to = SDF.format(d);
    }

    /**
     * @return the pin
     */
    public String getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the fund
     */
    public int getFund() {
        return fund;
    }

    /**
     * @param fund the fund to set
     */
    public void setFund(int fund) {
        this.fund = fund;
    }
}
