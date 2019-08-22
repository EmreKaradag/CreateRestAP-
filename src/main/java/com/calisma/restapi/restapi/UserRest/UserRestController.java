package com.calisma.restapi.restapi.UserRest;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserRestController {
DB db = new DB();
  //all yser Rest -> Json
  //Return Hashmap<String, Object>?

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public HashMap<String,Object> allCustomer(@RequestParam(defaultValue = "") String apiKey){
    HashMap<String, Object> hm = new HashMap<>();
    if(!Util.apiControl(apiKey,db)){
      hm.put("message:", "Lütfen Geçerli APİ Key Giriniz.");
      return hm ;
    }
    System.out.println("apiKey:" + apiKey);

    List<CustomerPro> ls = dataResult(); // burasının bir defa çalışması için yaptık. dataresult bir defa tetiklenir fakat 2 iş yapar.
    if(ls.size()==0){
      hm.put("statu",false);
      hm.put("message", "Kullanıcı listesinde üye yok.");
    }else{
      hm.put("statu",true);
      hm.put("message", "Kullanıcılar başarı ile listelendi.");
      hm.put("count", ls.size());
      hm.put("customers",dataResult());

    }

    return hm;
  }

  public List<CustomerPro> dataResult() {
    List<CustomerPro> ls = new ArrayList<>();

    try {
      String query ="select * from customer";
      PreparedStatement pre = db.connect(query);
      ResultSet rs = pre.executeQuery();
      while(rs.next()){
        CustomerPro cr = new CustomerPro();
        cr.setCustomer_id(rs.getInt("customer_id"));
        cr.setFirst_name(rs.getString("first_name"));
        cr.setLast_name(rs.getString("last_name"));
        cr.setEmail(rs.getString("email"));
        ls.add(cr);
      }
    }catch(Exception e) {
      System.err.println("Data Result Error:" + e);
    }

    return ls;
  }

  }
