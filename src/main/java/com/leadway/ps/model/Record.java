package com.leadway.ps.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Dev.io
 */
@Entity
@Table(name = "th_history")
@JsonPropertyOrder(
  {
    "emplContribution",
    "employeeContribution",
    "fees",
    "netContribution",
    "numberOfUnits",
    "others",
    "paymentDate",
    "quarterId",
    "referenceId",
    "relatedMnthEnd",
    "relatedMnthStart",
    "relatedPfaCode",
    "serialNo",
    "totalContribution",
    "transactionType",
    "voluntaryContingent",
    "voluntaryRetirement",
    "withdrawal",
  }
)
public class Record  implements Comparable<Record>{
  @Id
  @Column(name = "hid", nullable = false, length = 22)
  @JsonIgnore
  private String id; //pin+pfa+recordnum

  @Column(name="serial", nullable = false)
  @JsonProperty("serialNo")
  private int serial;

  @Column(name="querter", nullable = false, length = 22)
  @JsonProperty("quarterId")
  private String quarter;

  @Column(name="ref_id", nullable = false, length = 22)
  @JsonProperty("referenceId")
  private String reference;

  @Column(name = "date_received")
  @JsonProperty("paymentDate")
  @JsonFormat(pattern = "dd-MMM-yyyy")
  private Date dateReceived;

  @Column(name = "month_start")
  @JsonProperty("relatedMnthStart")
  @JsonFormat(pattern = "dd-MMM-yyyy")
  private Date monthStart;

  @Column(name = "month_end")
  @JsonProperty("relatedMnthEnd")
  @JsonFormat(pattern = "dd-MMM-yyyy")
  private Date monthEnd;

  @Column(name = "typ", nullable = false, precision = 22, scale = 2)
  @JsonProperty("transactionType")
  private String type;

  @Column(name = "contribution", nullable = false, precision = 22, scale = 2)
  @JsonProperty("employeeContribution")
  private BigDecimal contribution;

  @Column(name = "employer", nullable = false, precision = 22, scale = 2)
  @JsonProperty("emplContribution")
  private BigDecimal employer;

  @Column(name = "contigent", nullable = false, precision = 22, scale = 2)
  @JsonProperty("voluntaryContingent")
  private BigDecimal voluntaryContigent;

  @Column(name = "retirement", nullable = false, length = 22)
  @JsonProperty("voluntaryRetirement")
  private BigDecimal voluntaryRetirement;

  @Column(name = "inflows", nullable = false, precision = 22, scale = 2)
  @JsonProperty("others")
  private BigDecimal otherInflows;

  @Column(nullable = false, precision = 22, scale = 2)
  @JsonProperty("totalContribution")
  private BigDecimal total;

  @Column(nullable = false, precision = 22, scale = 2)
  @JsonProperty("numberOfUnits")
  private BigDecimal units;

  @Column(nullable = false, precision = 22, scale = 2)
  @JsonProperty("fees")
  private BigDecimal fees;

  @Column(nullable = false, precision = 22, scale = 2)
  @JsonProperty("withdrawal")
  private BigDecimal withdrawals;

  @Column(nullable = false, precision = 22, scale = 2)
  @JsonProperty("netContribution")
  private BigDecimal net;

  @Column(nullable = false)
  @JsonProperty("relatedPfaCode")
  private String pfa;

  @ManyToOne
  @JoinColumn(name = "rsa_pin")
  @JsonIgnore
  private Statement statement;

  public Record(){
	  quarter = "3-2020";
	  pfa = "023";
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param pfa the pfa to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the serial
   */
  public int getSerial() {
    return serial;
  }

  /**
   * @param serial the serial to set
   */
  public void setSerial(int serial) {
    this.serial = serial;
  }

  /**
   * @return the ref
   */
  public String getReference() {
    return reference;
  }

  /**
   * @param middleName the middleName to set
   */
  public void setReference(String reference) {
    this.reference = reference;
  }

  /**
   * @return the quarter
   */
  public String getQuarter() {
    return quarter;
  }

  /**
   * @param quarter the quarter to set
   */
  public void setQuarter(String quarter) {
    this.quarter = quarter;
  }

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

  /**
   * @return the statement
   */
  public Statement getStatement() {
    return statement;
  }

  /**
   * @param statement the request to set
   */
  public void setStatement(Statement request) {
    this.statement = request;
  }

    @Override
    public int compareTo(Record e) {
        return Integer.toString(this.getSerial()).compareTo(Integer.toString(e.getSerial()));
    }
}
