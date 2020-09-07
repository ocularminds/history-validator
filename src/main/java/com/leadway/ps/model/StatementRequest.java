package com.leadway.ps.model;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Dev.io
 */
public class StatementRequest {
    private int fundId;
    private String surname;
    private String firstName;
    private String middleName;
    private String pin;
    private String employer;
    private String code;
    private BigDecimal price;
    private BigDecimal units;
    private BigDecimal balance;
    private BigDecimal earning;
    private List<Record> records;
    private String status;
    private String requester;
    private List<String> comments;

   public int getFundId(){
return this.fundId;}
public void setFundId(int fundId){
this.fundId = fundId;
}
    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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
     * @return the employer
     */
    public String getEmployer() {
        return employer;
    }

    /**
     * @param employer the employer to set
     */
    public void setEmployer(String employer) {
        this.employer = employer;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the units
     */
    public BigDecimal getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(BigDecimal units) {
        this.units = units;
    }

    /**
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * @return the earning
     */
    public BigDecimal getEarning() {
        return earning;
    }

    /**
     * @param earning the earning to set
     */
    public void setEarning(BigDecimal earning) {
        this.earning = earning;
    }

    /**
     * @return the records
     */
    public List<Record> getRecords() {
        return records;
    }

    /**
     * @param records the records to set
     */
    public void setRecords(List<Record> records) {
        this.records = records;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the requester
     */
    public String getRequester() {
        return requester;
    }

    /**
     * @param requester the requester to set
     */
    public void setRequester(String requester) {
        this.requester = requester;
    }

    /**
     * @return the comments
     */
    public List<String> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
