package com.leadway.ps.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.OneToMany;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 *
 * @author Babatope Festus
 */
@Entity
@Table(name = "th_statement")
@JsonPropertyOrder({
  "employerCode",
  "firstname",
  "fundCode",
  "middlename",
  "quarterId",
  "referenceId",
  "rsapin",
  "rsaBalance",
  "surname",
  "tpfacode",
  "ttlGainOrLoss",
  "ttlNoOfUnits",
  "unitPrice"
})
public class Statement  implements java.io.Serializable{
  @Id
  @Column(name="rsa_pin", nullable = false, length = 22)
  @JsonProperty("rsapin")
  private String pin;

  @Column(name = "fund_id")
  @JsonProperty("fundCode")
  private String fundId;

  @Column(name = "surname", nullable = false, length = 22)
  @JsonProperty("surname")
  private String surname;

  @Column(name = "first_name", nullable = false, length = 22)
  @JsonProperty("firstname")
  private String firstName;

  @Column(name = "middle_name", nullable = false, length = 22)
  @JsonProperty("middlename")
  private String middleName;

  @Column(name="querter", nullable = false, length = 22)
  @JsonProperty("quarterId")
  private String quarter;

  @Column(nullable = false, length = 22)
  @JsonProperty("employerCode")
  private String employer;

  @Column(name="ref_id", nullable = false, length = 22)
  @JsonProperty("referenceId")
  private String reference;

  @Column(nullable = false, length = 22)
  @JsonProperty("tpfacode")
  private String code;

  @Column(nullable = false, precision = 22, scale = 4)
  @JsonProperty("unitPrice")
  private String price;

  @Column(nullable = false, precision = 22, scale = 2)
  @JsonProperty("ttlNoOfUnits")
  private BigDecimal units;

  @Column(nullable = false, precision = 22, scale = 2)
  @JsonProperty("rsaBalance")
  private BigDecimal balance;

  @Column(nullable = false, precision = 22, scale = 2)
  @JsonProperty("ttlGainOrLoss")
  private BigDecimal earning;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "statement")
  @JsonIgnore
  private List<Record> records;

  @Column(nullable = false, length = 22)
  @JsonIgnore
  private String status;

  @Column(nullable = false, length = 22)
  @JsonIgnore
  private String requester;

  @ElementCollection
  @CollectionTable(name = "th_comments", joinColumns = @JoinColumn(name = "rsa_pin"))
  @Column(name = "comments")
  @JsonIgnore
  private List<String> comments;

  public Statement(){
	quarter = new java.text.SimpleDateFormat("MM-yyyy").format(new java.util.Date());
    RoundingMode RAND = RoundingMode.HALF_UP;
    records = new ArrayList<>();
    balance = BigDecimal.ZERO.setScale(2, RAND);
    comments = new ArrayList<>();
    price ="0.00";
    units = BigDecimal.ZERO.setScale(2, RAND);
    earning = BigDecimal.ZERO.setScale(2, RAND);
    code = "023";
    fundId = "FUND4";
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

  public String getFundId() {
    return this.fundId;
  }

  public void setFundId(String fundId) {
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
  public String getPrice() {
    return price;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(String price) {
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

  public void add(Record record) {
    if (record == null) return;
    records.add(record);
    record.setStatement(this);
  }
}
