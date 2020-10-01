package com.leadway.ps.model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 *
 * @author Babatope Festus
 */
@JsonPropertyOrder({
  "thSummary",
  "detailRecords"
})
public class Report  implements java.io.Serializable{

  @JsonProperty("thSummary")
  private Statement statement;

  @JsonProperty("detailRecords")
  private List<Record> records;

  public Report(){
  }

  public Report(Statement statement, List<Record> records){
	  this.statement = statement;
	  this.records = records;
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
}
