package bzh.dbs.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Classe MainController.
 * Controleur principal.
 * @author Jérémy Cavron
 * @version 1.0
 */
@Controller
@RequestMapping(method = RequestMethod.GET)
public class MainController {

  @RequestMapping("/")
  @ResponseBody
  public String index() {
    return "OPOWER API";
  }

}
