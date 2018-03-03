package bzh.dbs.backend.controllers;

import bzh.dbs.backend.dao.*;
import bzh.dbs.backend.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
  @Autowired
  private EquipementElecDao equipementElecDao;
  private List<Personne> lstPersons;
  private List<Residence> lstResidences;
  private List<Chauffage> lstChauffages;
  private List<EquipementElec> lstEquips;

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
    this.lstChauffages.add(new Chauffage("Salon", 8, 200, 1000));
    this.lstChauffages.add(new Chauffage("Salon", 7, 245, 900));
    this.lstChauffages.add(new Chauffage("Salon", 8, 246, 1000));
    this.lstChauffages.add(new Chauffage("Salon", 7, 245, 900));
    this.lstChauffages.add(new Chauffage("Salon", 8, 200, 1000));
    this.lstChauffages.add(new Chauffage("Salon", 7, 245, 900));
    this.lstChauffages.add(new Chauffage("Salon", 8, 212, 1000));
    this.lstChauffages.add(new Chauffage("Salon", 7, 245, 900));
    this.lstChauffages.add(new Chauffage("Salon", 8, 200, 1000));
    this.lstChauffages.add(new Chauffage("Salon", 7, 245, 900));

    this.lstChauffages.add(new Chauffage("Salle de bain", 3, 200, 750));
    this.lstChauffages.add(new Chauffage("Salle de bain", 4, 245, 900));
    this.lstChauffages.add(new Chauffage("Salle de bain", 5, 200, 400));
    this.lstChauffages.add(new Chauffage("Salle de bain", 2, 245, 900));
    this.lstChauffages.add(new Chauffage("Salle de bain", 6, 200, 1000));
    this.lstChauffages.add(new Chauffage("Salle de bain", 8, 245, 900));
    this.lstChauffages.add(new Chauffage("Salle de bain", 1, 200, 1200));
    this.lstChauffages.add(new Chauffage("Salle de bain", 7, 245, 900));
    this.lstChauffages.add(new Chauffage("Salle de bain", 10, 245, 900));

    this.lstChauffages.add(new Chauffage("Séjour", 15, 200, 750));
    this.lstChauffages.add(new Chauffage("Séjour", 14, 245, 900));
    this.lstChauffages.add(new Chauffage("Séjour", 13, 200, 400));
    this.lstChauffages.add(new Chauffage("Séjour", 18, 245, 900));
    this.lstChauffages.add(new Chauffage("Séjour", 16, 200, 1000));
    this.lstChauffages.add(new Chauffage("Séjour", 20, 245, 900));
    this.lstChauffages.add(new Chauffage("Séjour", 18, 200, 1200));
    this.lstChauffages.add(new Chauffage("Séjour", 17, 245, 900));
    this.lstChauffages.add(new Chauffage("Séjour", 16, 245, 900));

    this.lstEquips = new ArrayList<EquipementElec>();
    this.lstEquips.add(new EquipementElec("Serveur", 24, 365, 1300));
    this.lstEquips.add(new EquipementElec("Ordinateur", 3, 300, 1000));
    this.lstEquips.add(new EquipementElec("Télé", 2, 325, 200));
    this.lstEquips.add(new EquipementElec("GoogleHome", 24, 365, 5));
    this.lstEquips.add(new EquipementElec("Lampe salon", 8, 245, 75));
    this.lstEquips.add(new EquipementElec("Lampe séjour", 8, 278, 45));
    this.lstEquips.add(new EquipementElec("Aquarium", 24, 365, 20));
    this.lstEquips.add(new EquipementElec("Alarme", 24, 365, 20));
    this.lstEquips.add(new EquipementElec("Serveur", 24, 365, 1300));
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
      for (Chauffage chauffage: this.lstChauffages) {
        chauffageDao.create(chauffage);
      }
      for (EquipementElec equipementElec: this.lstEquips) {
        equipementElecDao.create(equipementElec);
      }
      for (Residence residence: this.lstResidences) {
        residenceDao.create(residence);
      }
      for (Personne person : this.lstPersons) {
        personneDao.create(person);
      }

      //Add Friend
      Personne pers = this.personneDao.getById(1);
      Personne ami = this.personneDao.getById(2);
      ami.addAmi(pers);
      pers.addAmi(ami);
      personneDao.update(ami);
      ami = this.personneDao.getById(4);
      ami.addAmi(pers);
      pers.addAmi(ami);
      personneDao.update(ami);
      ami = this.personneDao.getById(5);
      ami.addAmi(pers);
      pers.addAmi(ami);
      personneDao.update(ami);
      ami = this.personneDao.getById(6);
      ami.addAmi(pers);
      pers.addAmi(ami);
      personneDao.update(ami);
      personneDao.update(pers);

      pers = this.personneDao.getById(2);
      ami = this.personneDao.getById(3);
      ami.addAmi(pers);
      pers.addAmi(ami);
      personneDao.update(ami);
      ami = this.personneDao.getById(4);
      ami.addAmi(pers);
      pers.addAmi(ami);
      personneDao.update(ami);
      personneDao.update(pers);

      //Add Residence
      pers = this.personneDao.getById(1);
      Residence resid = this.residenceDao.getById(1);
      pers.addResidence(resid);
      personneDao.update(pers);

      //Add intelligent
      Intelligent inte = intelligentDao.getById(1);
      resid.addIntelligent(inte);
      intelligentDao.update(inte);


    } catch (Exception exceptCreate){
      return "Erreur : controllers/MainController/createFictif : " + exceptCreate;
    }
    return "Création réussite";
  }
}
