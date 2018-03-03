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
    this.lstPersons.add(new Personne("Cavron", "Jérémy", "jeremy.cav@gmail.com"));
    this.lstPersons.add(new Personne("Dupont", "Olivier", "olivier.dupont@gmail.com"));
    this.lstPersons.add(new Personne("Hadock", "Tintin", "tintin.hadock@gmail.com"));
    this.lstPersons.add(new Personne("Skywalker", "Luc", "luc.skywalker@gmail.com"));
    this.lstPersons.add(new Personne("Skywalker", "Luc", "luc.skywalker@gmail.com"));
    this.lstPersons.add(new Personne("Lewis", "Karl", "karl.lewis@gmail.com"));
    this.lstPersons.add(new Personne("Capitaine", "Flamme", "capitaine.flamme@gmail.com"));
    this.lstPersons.add(new Personne("Tom", "Sawer", "tom.sawer@gmail.com"));
    this.lstPersons.add(new Personne("Mini", "Pouce", "mini.pouce@gmail.com"));

    this.lstResidences = new ArrayList<Residence>();
    this.lstResidences.add(new Residence(1000, 10));
    this.lstResidences.add(new Residence(200, 7));
    this.lstResidences.add(new Residence(450, 6));
    this.lstResidences.add(new Residence(600, 9));
    this.lstResidences.add(new Residence(546, 5));
    this.lstResidences.add(new Residence(352, 3));
    this.lstResidences.add(new Residence(845, 10));
    this.lstResidences.add(new Residence(954, 7));
    this.lstResidences.add(new Residence(1, 300));

    this.lstChauffages = new ArrayList<Chauffage>();

    this.lstEquips = new ArrayList<EquipementElec>();



  }

  /**
   * fonction qui permet de créer des personnes, résidences, appareils
   * intelligents fictifs dans la base de données.
   * @return string : Message de confirmation.
   */
  @RequestMapping(
          value = "/",
          method = RequestMethod.POST)
  @ResponseBody
  public String createFictif() {

    try {
      for (Personne person : this.lstPersons) {
        personneDao.create(person);
      }
      for (Residence residence: this.lstResidences) {
        residenceDao.create(residence);
      }
    } catch (Exception exceptCreate){
      return "Erreur : controllers/MainController/createFictif : " + exceptCreate;
    }
    return "Création réussite";
  }
}
