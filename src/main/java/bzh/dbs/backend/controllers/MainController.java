package bzh.dbs.backend.controllers;

import bzh.dbs.backend.dao.ChauffageDao;
import bzh.dbs.backend.dao.IntelligentDao;
import bzh.dbs.backend.dao.PersonneDao;
import bzh.dbs.backend.dao.ResidenceDao;
import org.springframework.beans.factory.annotation.Autowired;
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

  // --- Déclaration des propriétés ---
  @Autowired
  private PersonneDao personneDao;
  @Autowired
  private ResidenceDao residenceDao;
  @Autowired
  private IntelligentDao intelligentDao;
  @Autowired
  private ChauffageDao chauffageDao;

  /**
   * fonction qui permet de créer des personnes, résidences, appareils
   * intelligents fictifs dans la base de données.
   * @return string : Message de confirmation.
   */
  @RequestMapping("/")
  @ResponseBody
  public String index() {




    return "Création réussite";
  }


}
