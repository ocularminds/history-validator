package com.leadway.ps.service;

import com.leadway.ps.model.StatementRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.leadway.ps.common.HttpIO;
import com.leadway.ps.common.JsonParser;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PencomService {
  String endpoint = "http://rts.pencom.gov.ng";
  String key = "";
  String subkey = "";
  String user = "";
  String token = "";
  final TypeReference<HashMap<String, String>> typ = new TypeReference<HashMap<String, String>>() {};

  public Object[] submit(StatementRequest req) {
    Map<String, StatementRequest> d = new HashMap<>();
    d.put("thSummary", req);
    System.out.println(JsonParser.toJson(d));
    if (d != null) {
      return new Object[] {
        Integer.toString(HttpStatus.OK.value()),
        "Success",
        token,
      };
    }
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
    }
  }
}
