package bzh.dbs.backend.controllers;

import bzh.dbs.backend.dao.PersonneDao;
import bzh.dbs.backend.domain.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Classe PersonneCtrl.
 * Controleur de la classe Personne.
 * @author Jérémy Cavron
 * @version 1.0
 */
@Controller
public class PersonneCtrl {

  // --- Déclaration des propriétés ---
  @Autowired
  private PersonneDao personneDao;

  /**
   * Fonction qui retourne toutes les personnes.
   * @return personnes : liste de personnes.
   */
  @RequestMapping(
          value = "/allPerson",
          method = RequestMethod.GET)
  @ResponseBody
  public List<Personne> getAll(){
    return this.personneDao.getAll();
  }

  /**
   * Fcontion qui permet de créer une personne et retourne une réponse de confirmation.
   * @param nom : nom de la personne.
   * @param prenom : prénom de la personne.
   * @param mail : mail de la personne.
   * @return String : message de confirmation.
   */
  @RequestMapping(
          value = "/createPerson",
          method = RequestMethod.POST,
          params = {"nom", "prenom", "mail"})
  @ResponseBody
  public String create(String nom, String prenom, String mail) {
    try {
      Personne personne = new Personne(nom, prenom, mail);
      personneDao.create(personne);
    } catch (Exception exceptCreatPers) {
      return "controllers/PersonneCtrl/create : Erreur de création de la personne : "
              + exceptCreatPers.toString();
    }
    return "Création de personne réussie !";
  }

  /**
   * Fonction qui permet d'ajouter un ami à une personne par leur id.
   * La fonction retourne un message de confirmation.
   * @param id : id de la personne qui veut un ami.
   * @param idFriend : id de l'ami.
   * @return String : message de confirmation.
   */
  @RequestMapping(
          value = "/addFriend",
          method = RequestMethod.PUT,
          params = {"id", "idFriend"})
  @ResponseBody
  public String addFriend(long id, long idFriend) {
    try {
      Personne personne = this.personneDao.getById(id);
      Personne ami = this.personneDao.getById(idFriend);
      personne.addAmi(ami);
      ami.addAmi(personne);
      personneDao.update(personne);
      personneDao.update(ami);
    } catch (Exception exceptCreatPers) {
      return "controllers/PersonneCtrl/addFriend : Erreur de création de l'ami : "
              + exceptCreatPers.toString();
    }
    return "Création de l'ami réussie !";
  }

  /**
   * Fonction qui permet de supprimer un ami à une personne par leur id.
   * La fonction retourne un message de confirmation.
   * @param id : id de la personne qui veut supprimer un ami.
   * @param idFriend : id de l'ami.
   * @return String : message de confirmation.
   */
  @RequestMapping(
          value = "/deleteFriend",
          method = RequestMethod.PUT,
          params = {"id", "idFriend"})
  @ResponseBody
  public String deleteFriend(long id, long idFriend) {
    try {
      Personne personne = this.personneDao.getById(id);
      Personne ami = this.personneDao.getById(idFriend);
      personne.deleteAmi(ami);
      ami.deleteAmi(personne);
      personneDao.update(personne);
      personneDao.update(ami);
    } catch (Exception exceptCreatPers) {
      return "controllers/PersonneCtrl/deleteFriend : Erreur de création de l'ami : "
              + exceptCreatPers.toString();
    }
    return "Suppression de l'ami réussie !";
  }



  /**
   * Fonction qui permet de supprimer une personne et retourne un message de confirmation.
   * @param id : id de la personne à supprimer.
   * @return String : message de confirmation
   */
  @RequestMapping(
          value = "/deletePerson",
          method = RequestMethod.DELETE,
          params = {"id"})
  @ResponseBody
  public String delete(long id) {
    try {
      Personne personne = new Personne(id);
      personneDao.delete(personne);
    } catch (Exception exceptDeletepers) {
      return "controllers/PersonneCtrl/delete : Erreur de suppresion de la personne : "
              + exceptDeletepers.toString();
    }
    return "Suppression de personne réussie !";
  }

  /**
   * Fonction qui retourne l'id de la personne avec son mail.
   * @param mail : mail de la personne
   * @return id : id de la personne.
   */
  @RequestMapping(
          value = "/getIdByMail",
          method = RequestMethod.GET,
          params = {"mail"})
  @ResponseBody
  public String getIdByEmail(String mail) {
    String id;
    try {
      Personne personne = personneDao.getByEmail(mail);
      id = String.valueOf(personne.getId());
    } catch (Exception exceptGetBMail) {
      return "controllers/PersonneCtrl/delete : Mail de la personne introuvable : "
              + exceptGetBMail.toString();
    }
    return "L'id de la personne du mail est : " + id;
  }

  /**
   * Fonction qui retourne la personne avec son id.
   * @param id : id de la personne
   * @return Personne : personne.
   */
  @RequestMapping(
          value = "/getPersonById",
          method = RequestMethod.GET,
          params = {"id"})
  @ResponseBody
  public Personne getById(long id) {
    Personne personne;
    try {
      personne = personneDao.getById(id);
    } catch (Exception exceptGetBMail) {
      return null;
    }
    return personne;
  }

  /**
   * Focntion qui met à jours le nom d'une personne dont l'id est passé
   * en paramètre.
   * @param id : id de la personne.
   * @param nom : nouveau nom de la personne.
   * @return String : Message de confirmation.
   */
  @RequestMapping(
          value = "/updatePersonName",
          method = RequestMethod.PUT,
          params = {"nom"})
  @ResponseBody
  public String updateName(long id, String nom) {
    try {
      Personne personne = personneDao.getById(id);
      personne.setNom(nom);
      personneDao.update(personne);
    } catch (Exception exceptUpdName) {
      return "controllers/PersonneCtrl/delete : Erreur de mise à jours du nom : "
              + exceptUpdName.toString();
    }
    return "Mise à jours du nom réussite !";
  }


}
