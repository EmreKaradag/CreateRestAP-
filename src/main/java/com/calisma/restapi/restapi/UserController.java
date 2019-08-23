package com.calisma.restapi.restapi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
  @RequestMapping(value = "/user", method = RequestMethod.GET)
  @ResponseBody
  public String user(Model model){
    return "Hoşgeldin <b>Kullanıcı</b>";
  }
}
