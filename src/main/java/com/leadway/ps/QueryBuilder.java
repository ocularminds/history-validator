package com.leadway.ps;

public class QueryBuilder {

  public static String search() {
    StringBuilder sb = new StringBuilder();
    sb.append("select e.firstname,e.lastname,e.MiddleName,e.rsapin,e.fundid, ");
    sb.append("(select top 1 u.unitprice  from unitprice u  ");
    sb.append("where e.fundid = u.fundid  order by unitpriceid desc");
    sb.append(") as fundunitprice, ");
    sb.append("select isnull((select total from [dbo].[udfGetBalanceBySource](employeeid,fundid,");
    sb.append("(select max(valuedate) from UnitPrice where FundID = fundid))),0) as Balance,");
    sb.append("er.employercode, fd.FundName from employee e join employer er ");
    sb.append("on e.employerid = er.employerid join  funddefinition fd ");
    sb.append("on fd.funddefinitionid = e.fundid and e.rsapin = ?");
    return sb.toString();
  }

  public static String build() {
    StringBuilder sb = new StringBuilder();
    sb.append("select e.rsapin,c.Employeeid, Valuedate [PAY_RECEIVE_DATE],  ");
    sb.append("StartPeriod [RELATED_MONTH_START], ");
    sb.append("EndPeriod [RELATED_MONTH_END],  ");
    sb.append("[TRANSACTION_TYPE]=case ");
    sb.append("when cast(ContributionTypeID as varchar)  ");
    sb.append("in (11,12,13,14,66) then 'AR001'  ");
    sb.append("when cast(ContributionTypeID as varchar) in (3) then 'VLC01'  ");
    sb.append(
      "when cast(ContributionTypeID as varchar) in (17,18,32) then 'OST01'  "
    );
    sb.append("when cast(ContributionTypeID as varchar) in (71) then	'NR001' ");
    sb.append("when cast(ContributionTypeID as varchar) in  ");
    sb.append(
      "(1,10,29,41,43,44,46,49,51,53,54,55,56,57,60,62,63,67,68,69,70,72,74,39,59,75) then	'MC001' "
    );
    sb.append("when cast(ContributionTypeID as varchar) in (31) then	'NST01'  ");
    sb.append(
      "when cast(ContributionTypeID as varchar) in (2,34) then	'LEG01' "
    );
    sb.append(
      "when cast(ContributionTypeID as varchar) in (73,15,19,20) then 'RFD01'  "
    );
    sb.append(
      "when (cast(ContributionTypeID as varchar) in (9) AND c.Fundid=9) then 'TFD01'  "
    );
    sb.append(
      "when (cast(ContributionTypeID as varchar) in (37) AND c.Fundid=9) then 'TFD01' "
    );
    sb.append(
      "when (cast(ContributionTypeID as varchar) in (9) AND c.Fundid=1) then 'TFD02'  "
    );
    sb.append(
      "when (cast(ContributionTypeID as varchar) in (37) AND c.Fundid=1) then 'TFD02'  "
    );
    sb.append(
      "when (cast(ContributionTypeID as varchar) in (9) AND c.Fundid=10) then 'TFD03'  "
    );
    sb.append(
      "when (cast(ContributionTypeID as varchar) in (37) AND c.Fundid=10) then 'TFD03'  "
    );
    sb.append(
      "when (cast(ContributionTypeID as varchar) in (6,7,9,36,45) AND c.Fundid=2) then 'TFD04' "
    );
    sb.append(
      "when (cast(ContributionTypeID as varchar) in (9) AND c.Fundid=13) then 'TFD05' "
    );
    sb.append(
      "when (cast(ContributionTypeID as varchar) in (8) AND c.Fundid=9) then 'TFD07' "
    );
    sb.append(
      "when (cast(ContributionTypeID as varchar) in (8) AND c.Fundid=1) then 'TFD08' "
    );
    sb.append(
      "when (cast(ContributionTypeID as varchar) in (8) AND c.Fundid=10) then 'TFD09' "
    );
    sb.append(
      "when (cast(ContributionTypeID as varchar) in (8) AND c.Fundid=2) then 'TFD10' "
    );
    sb.append(
      "when (cast(ContributionTypeID as varchar) in (8) AND c.Fundid=13) then 'TFD11' "
    );
    sb.append(" else cast(ContributionTypeID as varchar) END ");
    sb.append(
      ",case when cast(ContributionTypeID as varchar) in (11,12,13,14,66)  "
    );
    sb.append("then 0.00 else EmployerAmount end [EMPLOYER_CONTRIBUTION], ");
    sb.append(
      "case when cast(ContributionTypeID as varchar) in (11,12,13,14,66)  "
    );
    sb.append("then 0.00 else EmployeeAmount end [EMPLOYEE_CONTRIBUTION],  ");
    sb.append(
      "AVCAmount [VOLUNTARY_CONTINGENT],  0.00 [VOLUNTARY_RETIREMENT],  "
    );
    sb.append(
      "[OTHER_INFLOWS]=case when cast(ContributionTypeID as varchar) in (11,12,13,14,66)  "
    );
    sb.append("then EmployerAmount+EmployeeAmount  ");
    sb.append(
      "else EmployeeAccumAmount+EmployeeLegacyAmount+EmployerAccumAmount+EmployerLegacyAmount "
    );
    sb.append(
      "end, GrossAmount [TOTAL_CONTRIBUTIONS], UnitValue [NUMBER_OF_UNITS], "
    );
    sb.append("AdminFee [FEES],0.00 [OTHER_WITHDRAWALS], ");
    sb.append(
      "NET_CONTRIBUTIONS = ISNULL(GrossAmount,0.00)- 0.00, '0023' [RELATED_PFA_CODE] "
    );
    sb.append(
      "from contributions c join employee e on e.employeeid = c.EmployeeID "
    );
    sb.append(
      "where isnull(c.isConfirmed,0) = 1 and isnull(c.isarchived,0)=0 "
    );
    sb.append("and e.rsapin in (?) ");
    sb.append("UNION ");
    sb.append("select rsapin, p.Employeeid, ValueDate [PAY_RECEIVE_DATE], ");
    sb.append(
      "StartPeriod [RELATED_MONTH_START], EndPeriod [RELATED_MONTH_END], "
    );
    sb.append(
      " [TRANSACTION_TYPE]=case when cast(PaymentTypeID as varchar) in (1,22,78) then 'LS001'  "
    );
    sb.append(
      "when cast(PaymentTypeID as varchar) in (3,8,17,33,34,35,36) then 'PW001'  "
    );
    sb.append("when cast(PaymentTypeID as varchar) in (7) then 'WT001' ");
    sb.append("when cast(PaymentTypeID as varchar) in (9) then 'PT025'  ");
    sb.append("when cast(PaymentTypeID as varchar) in (10) then 'NST01'  ");
    sb.append("when cast(PaymentTypeID as varchar) in (13,19) then 'LEG01' ");
    sb.append(
      "when (cast(PaymentTypeID as varchar) in (14) AND p.Fundid=9) then 'TFD01' "
    );
    sb.append(
      "when (cast(PaymentTypeID as varchar) in (14) AND p.Fundid=1) then 'TFD02'  "
    );
    sb.append(
      "when (cast(PaymentTypeID as varchar) in (14) AND p.Fundid=10) then 'TFD03' "
    );
    sb.append(
      "when (cast(PaymentTypeID as varchar) in (14,87) AND p.Fundid=2) then 'TFD04' "
    );
    sb.append(
      "when (cast(PaymentTypeID as varchar) in (14) AND p.Fundid=13) then 'TFD05' "
    );
    sb.append(
      "when cast(PaymentTypeID as varchar) in (15,50,69) then 'EXC01'  "
    );
    sb.append(
      "when cast(PaymentTypeID as varchar) in (20,48,66) then 'CREF1'  "
    );
    sb.append("when cast(PaymentTypeID as varchar) in (29,49) then 'RFD01' ");
    sb.append(
      "when cast(PaymentTypeID as varchar) in (26,38,40,41,42,57,85) then 'TRF02'  "
    );
    sb.append("when cast(PaymentTypeID as varchar) in  ");
    sb.append("(12,25, 43,44,46,52,53,54,55,58,61,63,68,70,79) then 'REV01'  ");
    sb.append("when cast(PaymentTypeID as varchar) in (86) then 'MPP02' ");
    sb.append("when cast(PaymentTypeID as varchar) in (67,88) then 'EXMP1' ");
    sb.append("else cast(PaymentTypeID as varchar) end, ");
    sb.append("0.00, 0.00, 0.00, 0.00, 0.00, 0.00, ");
    sb.append("UnitValue [NUMBER_OF_UNITS], Fee [FEES] , ");
    sb.append("ISNULL(Gross,0.00) [OTHER_WITHDRAWALS],");
    sb.append("NET_CONTRIBUTIONS = 0.00 - ISNULL(Gross,0.00) , ");
    sb.append("'0023' [RELATED_PFA_CODE]  ");
    sb.append("From payments p join employee e ");
    sb.append("on p.employeeid = e.employeeid ");
    sb.append("where isnull(isConfirmed,0) = 1 and isnull(isarchived,0)=0 ");
    sb.append("and e.rsapin in (?) ");
    sb.append("order by 1, 2, 3 ");
    return sb.toString();
  }
}
