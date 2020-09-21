package com.leadway.ps.model;

/**
 *
 * @author Dev.io
 */
public class Approval {
  private String requestId;
  private ApprovalType approval;
  private String comment;

  public enum ApprovalType {
    SEARCH,
    REVIEW,
    APPROVE,
  }

  public Approval() {}

  public Approval(String id, ApprovalType type) {
    this.requestId = id;
    this.approval = type;
  }

  /**
   * @return the requestId
   */
  public String getRequestId() {
    return requestId;
  }

  /**
   * @param requestId the requestId to set
   */
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  /**
   * @return the approval
   */
  public ApprovalType getApproval() {
    return approval;
  }

  /**
   * @param approval the approval to set
   */
  public void setApproval(ApprovalType approval) {
    this.approval = approval;
  }

  /**
   * @return the comment
   */
  public String getComment() {
    return comment;
  }

  /**
   * @param comment the comment to set
   */
  public void setComment(String comment) {
    this.comment = comment;
  }
}
