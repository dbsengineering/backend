package bzh.dbs.backend.controllers;

import bzh.dbs.backend.dao.ChauffageDao;
import bzh.dbs.backend.domain.Chauffage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Classe PersonneCtrl.
 * Controleur de la classe Personne.
 * @author Jérémy Cavron
 * @version 1.0
 */
@Controller
public class ChauffageCtrl {

  // --- Déclaration des propriétés ---
  @Autowired
  private ChauffageDao chauffageDao;

  /**
   * Fonction qui retourne tous les chauffages.
   * @return chauffages : liste de chauffages.
   */
  @RequestMapping(
          value = "/allChauffage",
          method = RequestMethod.GET)
  @ResponseBody
  public List<Chauffage> getAll() {
    return this.chauffageDao.getAll();
  }

  /**
   * Fcontion qui permet de créer un chauffage et retourne une réponse de confirmation.
   * @param nom : nom du chauffage.
   * @param heureParJour : heure de fonctionnement du chauffage par jour.
   * @param jourParAn : jour de fonctionnement du chauffage par année.
   * @param watt : puissance du chauffage en watt.
   */
  @RequestMapping(
          value = "/createChauffage",
          method = RequestMethod.POST,
          params = {"nom", "heureParJour", "jourParAn", "watt"})
  @ResponseBody
  public String create(String nom, double heureParJour, int jourParAn, double watt) {
    try {
      Chauffage chauffage = new Chauffage(nom, heureParJour, jourParAn, watt);
      chauffageDao.create(chauffage);
    } catch (Exception exceptCreatChau) {
      return "controllers/ChauffageCtrl/createChauffage : Erreur de création du chauffage : "
              + exceptCreatChau.toString();
    }
    return "Création du chauffage réussie !";
  }

  /**
   * Fonction qui permet de supprimer un chauffage et retourne un message de confirmation.
   * @param id : id du chauffage à supprimer.
   * @return String : message de confirmation
   */
  @RequestMapping(
          value = "/deleteChauffage",
          method = RequestMethod.DELETE,
          params = {"id"})
  @ResponseBody
  public String delete(long id) {
    try {
      Chauffage chauffage = chauffageDao.getById(id);

      chauffageDao.delete(chauffage);
    } catch (Exception exceptDeleteChauf) {
      return "controllers/ChauffageCtrl/delete : Erreur de suppresion du chauffage : "
              + exceptDeleteChauf.toString();
    }
    return "Suppression du chauffage réussie !";
  }

  /**
   * Focntion qui met à jours le nom du chauffage dont l'id est passé
   * en paramètre.
   * @param id : id dchauffage.
   * @param nom : nouveau nom du chauffage.
   * @return String : Message de confirmation.
   */
  @RequestMapping(
          value = "/updateChauffName",
          method = RequestMethod.PUT,
          params = {"id", "nom"})
  @ResponseBody
  public String updateName(long id, String nom) {
    try {
      Chauffage chauffage = chauffageDao.getById(id);
      chauffage.setNom(nom);
     chauffageDao.update(chauffage);
    } catch (Exception exceptUpdName) {
      return "controllers/ChauffageCtrl/updateName : Erreur de mise à jours du nom : "
              + exceptUpdName.toString();
    }
    return "Mise à jours du nom réussite !";
  }
}
