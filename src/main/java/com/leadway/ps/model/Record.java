package com.leadway.ps.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Dev.io
 */
public class Record {

    private Date dateReceived;
    private Date monthStart;
    private Date monthEnd;
    private String type;
    private BigDecimal contribution;
    private BigDecimal employer;
    private BigDecimal voluntaryContigent;
    private BigDecimal voluntaryRetirement;
    private BigDecimal otherInflows;
    private BigDecimal total;
    private BigDecimal units;
    private BigDecimal fees;
    private BigDecimal withdrawals;
    private BigDecimal net;
    private String pfa;

    /**
     * @return the dateReceived
     */
    public Date getDateReceived() {
        return dateReceived;
    }

    /**
     * @param dateReceived the dateReceived to set
     */
    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    /**
     * @return the monthStart
     */
    public Date getMonthStart() {
        return monthStart;
    }

    /**
     * @param monthStart the monthStart to set
     */
    public void setMonthStart(Date monthStart) {
        this.monthStart = monthStart;
    }

    /**
     * @return the monthEnd
     */
    public Date getMonthEnd() {
        return monthEnd;
    }

    /**
     * @param monthEnd the monthEnd to set
     */
    public void setMonthEnd(Date monthEnd) {
        this.monthEnd = monthEnd;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the contribution
     */
    public BigDecimal getContribution() {
        return contribution;
    }

    /**
     * @param contribution the contribution to set
     */
    public void setContribution(BigDecimal contribution) {
        this.contribution = contribution;
    }

    /**
     * @return the employer
     */
    public BigDecimal getEmployer() {
        return employer;
    }

    /**
     * @param employer the employer to set
     */
    public void setEmployer(BigDecimal employer) {
        this.employer = employer;
    }

    /**
     * @return the voluntaryContigent
     */
    public BigDecimal getVoluntaryContigent() {
        return voluntaryContigent;
    }

    /**
     * @param voluntaryContigent the voluntaryContigent to set
     */
    public void setVoluntaryContigent(BigDecimal voluntaryContigent) {
        this.voluntaryContigent = voluntaryContigent;
    }

    /**
     * @return the voluntaryRetirement
     */
    public BigDecimal getVoluntaryRetirement() {
        return voluntaryRetirement;
    }

    /**
     * @param voluntaryRetirement the voluntaryRetirement to set
     */
    public void setVoluntaryRetirement(BigDecimal voluntaryRetirement) {
        this.voluntaryRetirement = voluntaryRetirement;
    }

    /**
     * @return the otherInflows
     */
    public BigDecimal getOtherInflows() {
        return otherInflows;
    }

    /**
     * @param otherInflows the otherInflows to set
     */
    public void setOtherInflows(BigDecimal otherInflows) {
        this.otherInflows = otherInflows;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
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
     * @return the fees
     */
    public BigDecimal getFees() {
        return fees;
    }

    /**
     * @param fees the fees to set
     */
    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    /**
     * @return the withdrawals
     */
    public BigDecimal getWithdrawals() {
        return withdrawals;
    }

    /**
     * @param withdrawals the withdrawals to set
     */
    public void setWithdrawals(BigDecimal withdrawals) {
        this.withdrawals = withdrawals;
    }

    /**
     * @return the net
     */
    public BigDecimal getNet() {
        return net;
    }

    /**
     * @param net the net to set
     */
    public void setNet(BigDecimal net) {
        this.net = net;
    }

    /**
     * @return the pfa
     */
    public String getPfa() {
        return pfa;
    }

    /**
     * @param pfa the pfa to set
     */
    public void setPfa(String pfa) {
        this.pfa = pfa;
    }

}
