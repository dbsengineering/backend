package bzh.dbs.backend.controllers;

import bzh.dbs.backend.dao.ChauffageDao;
import bzh.dbs.backend.dao.IntelligentDao;
import bzh.dbs.backend.dao.PersonneDao;
import bzh.dbs.backend.dao.ResidenceDao;
import bzh.dbs.backend.domain.Chauffage;
import bzh.dbs.backend.domain.EquipementElec;
import bzh.dbs.backend.domain.Personne;
import bzh.dbs.backend.domain.Residence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
  private List<Personne> lstPersons;
  private List<Residence> lstResidences;
  private List<Chauffage> lstChauffages;
  private List<EquipementElec> lstEquips;
  private long[] tabFriends;

  /**
   * Constructeur.
   */
  public MainController(){
    //Initialisation des listes
    this.lstPersons = new ArrayList<Personne>();
    this.lstResidences = new ArrayList<Residence>();
    this.lstChauffages = new ArrayList<Chauffage>();
    this.lstEquips = new ArrayList<EquipementElec>();

    this.lstPersons.add(new Personne("Cavron", "Jérémy", "jerem.cav@gmail.com"));
    this.lstPersons.add(new Personne("Dupont", "Olivier", "olivier.dupont@gmail.com"));
    this.lstPersons.add(new Personne("Hadock", "Tintin", "tintin.hadock@gmail.com"));
    this.lstPersons.add(new Personne("Skywalker", "Luc", "luc.skywalker@gmail.com"));
    this.lstPersons.add(new Personne("Skywalker", "Luc", "luc.skywalker@gmail.com"));

  }

  /**
   * fonction qui permet de créer des personnes, résidences, appareils
   * intelligents fictifs dans la base de données.
   * @return string : Message de confirmation.
   */
  @RequestMapping("/")
  @ResponseBody
  public String index() {

    for (Personne person : this.lstPersons) {
      personneDao.create(person);
    }


    return "Création réussite";
  }


}
