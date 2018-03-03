package bzh.dbs.backend.controllers;

import bzh.dbs.backend.dao.EquipementElecDao;
import bzh.dbs.backend.domain.EquipementElec;
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
public class EquipementElecCtrl {

  // --- Déclaration des propriétés ---
  @Autowired
  private EquipementElecDao equipementElecDao;

  /**
   * Fonction qui retourne tous les équipements électriques.
   * @return EquipementElecs : liste de EquipementElecs.
   */
  @RequestMapping(
          value = "/allEquipElec",
          method = RequestMethod.GET)
  @ResponseBody
  public List<EquipementElec> getAll() {
    return this.equipementElecDao.getAll();
  }

  /**
   * Fcontion qui permet de créer un EquipementElec et retourne une réponse de confirmation.
   * @param nom : nom du EquipementElec.
   * @param heureParJour : heure de fonctionnement du EquipementElec par jour.
   * @param jourParAn : jour de fonctionnement du EquipementElec par année.
   * @param watt : puissance du EquipementElec en watt.
   */
  @RequestMapping(
          value = "/createEquipElec",
          method = RequestMethod.POST,
          params = {"nom", "heureParJour", "jourParAn", "watt"})
  @ResponseBody
  public String create(String nom, long heureParJour, int jourParAn, long watt) {
    try {
      EquipementElec EquipementElec = new EquipementElec(nom, heureParJour, jourParAn, watt);
      equipementElecDao.create(EquipementElec);
    } catch (Exception exceptCreatEquipElec) {
      return "controllers/EquipementElecCtrl/createEquipElec : Erreur de création du EquipementElec : "
              + exceptCreatEquipElec.toString();
    }
    return "Création de l'équipement électrique réussie !";
  }

  /**
   * Fonction qui permet de supprimer un EquipementElec et retourne un message de confirmation.
   * @param id : id du EquipementElec à supprimer.
   * @return String : message de confirmation
   */
  @RequestMapping(
          value = "/deleteEquipElec",
          method = RequestMethod.DELETE,
          params = {"id"})
  @ResponseBody
  public String delete(long id) {
    try {
      EquipementElec EquipementElec = equipementElecDao.getById(id);

      equipementElecDao.delete(EquipementElec);
    } catch (Exception exceptDeleteChauf) {
      return "controllers/EquipementElecCtrl/delete : Erreur de suppresion du EquipementElec : "
              + exceptDeleteChauf.toString();
    }
    return "Suppression du EquipementElec réussie !";
  }

  /**
   * Focntion qui met à jours le nom de l'équipement électrique dont l'id est passé
   * en paramètre.
   * @param id : id de l'équipement électrique.
   * @param nom : nouveau nom de l'équipement électrique.
   * @return String : Message de confirmation.
   */
  @RequestMapping(
          value = "/updateEquipElecName",
          method = RequestMethod.PUT,
          params = {"id", "nom"})
  @ResponseBody
  public String updateName(long id, String nom) {
    try {
      EquipementElec EquipementElec = equipementElecDao.getById(id);
      EquipementElec.setNom(nom);
      equipementElecDao.update(EquipementElec);
    } catch (Exception exceptUpdName) {
      return "controllers/EquipementElecCtrl/updateName : Erreur de mise à jours du nom : "
              + exceptUpdName.toString();
    }
    return "Mise à jours du nom réussite !";
  }
}
