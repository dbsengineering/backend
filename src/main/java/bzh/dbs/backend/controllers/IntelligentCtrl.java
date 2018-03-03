package bzh.dbs.backend.controllers;

import bzh.dbs.backend.dao.IntelligentDao;
import bzh.dbs.backend.domain.Intelligent;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Classe PersonneCtrl.
 * Controleur de la classe Intelligent.
 * @author Jérémy Cavron
 * @version 1.0
 */
@Controller
public class IntelligentCtrl {

  // --- Déclaration des propriétés ---
  @Autowired
  private IntelligentDao intelligentDao;

  /**
   * Fonction qui retourne tous les appareils intelligents.
   * @return Intelligents : liste d'appareils intelligents.
   */
  @RequestMapping(
          value = "/allIntell",
          method = RequestMethod.GET)
  @ResponseBody
  public List<Intelligent> getAll() {
    return this.intelligentDao.getAll();
  }

  /**
   * Fonction qui permet de supprimer un Intelligent et retourne un message de confirmation.
   * @param id : id du Intelligent à supprimer.
   * @return String : message de confirmation
   */
  @RequestMapping(
          value = "/deleteIntell",
          method = RequestMethod.DELETE,
          params = {"id"})
  @ResponseBody
  public String delete(long id) {
    try {
      Intelligent Intelligent = intelligentDao.getById(id);

      intelligentDao.delete(Intelligent);
    } catch (Exception exceptDeleteChauf) {
      return "controllers/IntelligentCtrl/delete : Erreur de suppresion du Intelligent : "
              + exceptDeleteChauf.toString();
    }
    return "Suppression du Intelligent réussie !";
  }

  /**
   * Focntion qui met à jours le nom du Intelligent dont l'id est passé
   * en paramètre.
   * @param id : id d'appareil Intelligent.
   * @return String : Message de confirmation.
   */
  @RequestMapping(
          value = "/averageIntell",
          method = RequestMethod.GET,
          params = {"id"})
  @ResponseBody
  public Double updateName(long id) {
    double result;
    try {
      Intelligent intelligent = intelligentDao.getById(id);
      result = intelligent.calculMoyen();
    } catch (Exception exceptUpdName) {
      return null;
    }
    return result;
  }
}
