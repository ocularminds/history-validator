package com.leadway.ps.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.leadway.ps.common.HttpIO;
import com.leadway.ps.common.JsonParser;
import com.leadway.ps.model.Statement;
import com.leadway.ps.model.Report;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;

@Service
public class PencomService {
  String endpoint = "http://rts.pencom.gov.ng";
  String key = "";
  String subkey = "Zadok@.01";
  String user = "PPHILIPS";
  String token = "37059739";
  final TypeReference<HashMap<String, String>> typ = new TypeReference<HashMap<String, String>>() {};

  public void submit() {}

  public Object[] submit(Statement req) {
	Report report = new Report(req,req.getRecords());
    System.out.println(JsonParser.toJson(report));
    return new Object[] {
      Integer.toString(HttpStatus.OK.value()),
      "Success",
      token,
    };
    /*
    System.out.println(JsonParser.toJson(d));
    Map<String, String> data = new HashMap();
    data.put("providerCallbackHost", endpoint + "/apiuser");
    HttpIO io = new HttpIO(endpoint + "/submitth");
    io.addBasicAuthorization(user, key);
    io.addHeader("Ocp-Apim-Subscription-Key", subkey);
    try {
      Map<String, String> res = io.post(JsonParser.toJson(data));
      HashMap<String, String> response = JsonParser.parse(typ, res.get("data"));
      token = response.get("access_token");
      return new Object[] {
        Integer.toString(HttpStatus.OK.value()),
        "Success",
        token,
      };
    } catch (Exception ex) {
      return new Object[] {
        Integer.toString(HttpStatus.UNAUTHORIZED.value()),
        "Unauthorized.",
      };
    }*/
  }
}
