package bzh.dbs.backend.controllers;

import bzh.dbs.backend.dao.PersonneDao;
import bzh.dbs.backend.dao.ResidenceDao;
import bzh.dbs.backend.domain.Personne;
import bzh.dbs.backend.domain.Residence;
import java.util.List;
import java.util.Set;
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
public class PersonneCtrl {

  // --- Déclaration des propriétés ---
  @Autowired
  private PersonneDao personneDao;
  @Autowired
  private ResidenceDao residenceDao;

  /**
   * Fonction qui retourne toutes les personnes.
   * @return personnes : liste de personnes.
   */
  @RequestMapping(
          value = "/allPerson",
          method = RequestMethod.GET)
  @ResponseBody
  public List<Personne> getAll() {
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
   * Fonction qui permet d'ajouter une résidence à une personne par leur id.
   * La fonction retourne un message de confirmation.
   * @param id : id de la personne qui veut un ami.
   * @param idResid : id de la résidence.
   * @return String : message de confirmation.
   */
  @RequestMapping(
          value = "/addResid",
          method = RequestMethod.POST,
          params = {"id", "idResid"})
  @ResponseBody
  public String addResid(long id, long idResid) {
    try {
      Personne personne = this.personneDao.getById(id);
      Residence residence = residenceDao.getById(idResid);
      personne.addResidence(residence);
      personneDao.update(personne);
    } catch (Exception exceptAddResid) {
      return "controllers/PersonneCtrl/addResid : Erreur d'ajout de la résidence : "
              + exceptAddResid.toString();
    }
    return "Ajout de la résidence réussie !";
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
          method = RequestMethod.POST,
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
    } catch (Exception exceptAddPers) {
      return "controllers/PersonneCtrl/addFriend : Erreur de l'ajout de l'ami : "
              + exceptAddPers.toString();
    }
    return "Ajout de l'ami réussie !";
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
          method = RequestMethod.DELETE,
          params = {"id", "idFriend"})
  @ResponseBody
  public String deleteFriend(long id, long idFriend) {
    try {
      Personne personne = this.personneDao.getById(id);
      Personne ami = this.personneDao.getById(idFriend);
      if(!personne.deleteAmi(ami)){
        return "L'ami ne fait pas parti de vos connaissances !";
      }
      ami.deleteAmi(personne);
      personneDao.update(personne);
      personneDao.update(ami);
    } catch (Exception exceptDelPers) {
      return "controllers/PersonneCtrl/deleteFriend : Erreur de création de l'ami : "
              + exceptDelPers.toString();
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
      Personne personne = personneDao.getById(id);
      Set<Personne> lstAmis = personne.getAmis();
      for (Personne ami:lstAmis) {
        ami.deleteAmi(personne);
        personneDao.update(ami);
      }
      personneDao.delete(personne);
    } catch (Exception exceptDeletepers) {
      return "controllers/PersonneCtrl/delete : Erreur de suppresion de la personne : "
              + exceptDeletepers.toString();
    }
    return "Suppression de personne réussie !";
  }

  /**
   * Fonction qui permet de supprimer une résidence et retourne un message de confirmation.
   * @param id : id de la personne qui possede la résidence.
   * @param idRes : id de la résidence à supprimer de la personne.
   * @return String : message de confirmation
   */
  @RequestMapping(
          value = "/deleteResPers",
          method = RequestMethod.DELETE,
          params = {"id", "idRes"})
  @ResponseBody
  public String deleteResPers(long id, long idRes) {
    try {
      Personne personne = this.personneDao.getById(id);
      Residence residence = residenceDao.getById(idRes);
      if(!personne.deleteResidence(residence)){
        return "la résidence ne fait pas parti de vos résidences !";
      }
      personneDao.update(personne);
    } catch (Exception exceptDelRespers) {
      return "controllers/PersonneCtrl/deleteResPerse : Erreur de suppresion de résidence de la personne : "
              + exceptDelRespers.toString();
    }
    return "Suppression de la résidence de la personne réussie !";
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
      return "controllers/PersonneCtrl/getIdByEmail : Mail de la personne introuvable : "
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
   * Fonction qui retourne la liste de résidences d'une personne
   * avec son id.
   * @param id : id de la personne
   * @return residences : liste de réssidences.
   */
  @RequestMapping(
          value = "/getResidByIdPers",
          method = RequestMethod.GET,
          params = {"id"})
  @ResponseBody
  public Set<Residence> getResidByIdPers(long id) {
    Personne personne;
    try {
      personne = personneDao.getById(id);
    } catch (Exception exceptGetBMail) {
      return null;
    }
    return personne.getResidences();
  }

  /**
   * Fonction qui retourne la liste des amis d'une personne avec son id.
   * @param id : id de la personne
   * @return amis : liste d'amis d'une personne.
   */
  @RequestMapping(
          value = "/getFriendsByIdPers",
          method = RequestMethod.GET,
          params = {"id"})
  @ResponseBody
  public Set<Personne> getFriendsByIdPers(long id) {
    Personne personne;
    try {
      personne = personneDao.getById(id);
    } catch (Exception exceptGetBMail) {
      return null;
    }
    return personne.getAmis();
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
          params = {"id", "nom"})
  @ResponseBody
  public String updateName(long id, String nom) {
    try {
      Personne personne = personneDao.getById(id);
      personne.setNom(nom);
      personneDao.update(personne);
    } catch (Exception exceptUpdName) {
      return "controllers/PersonneCtrl/updateName : Erreur de mise à jours du nom : "
              + exceptUpdName.toString();
    }
    return "Mise à jours du nom réussite !";
  }

  /**
   * Focntion qui met à jours le prénom d'une personne dont l'id est passé
   * en paramètre.
   * @param id : id de la personne.
   * @param prenom : nouveau prenom de la personne.
   * @return String : Message de confirmation.
   */
  @RequestMapping(
          value = "/updatePersonPrenom",
          method = RequestMethod.PUT,
          params = {"id", "prenom"})
  @ResponseBody
  public String updatePrenom(long id, String prenom) {
    try {
      Personne personne = personneDao.getById(id);
      personne.setPrenom(prenom);
      personneDao.update(personne);
    } catch (Exception exceptUpdName) {
      return "controllers/PersonneCtrl/updatePrenom : Erreur de mise à jours du prénom : "
              + exceptUpdName.toString();
    }
    return "Mise à jours du prénom réussite !";
  }

  /**
   * Focntion qui met à jours le mail d'une personne dont l'id est passé
   * en paramètre.
   * @param id : id de la personne.
   * @param mail : nouveau mail de la personne.
   * @return String : Message de confirmation.
   */
  @RequestMapping(
          value = "/updatePersonMail",
          method = RequestMethod.PUT,
          params = {"id", "mail"})
  @ResponseBody
  public String updateMail(long id, String mail) {
    try {
      Personne personne = personneDao.getById(id);
      personne.setMail(mail);
      personneDao.update(personne);
    } catch (Exception exceptUpdName) {
      return "controllers/PersonneCtrl/updateMail : Erreur de mise à jours du mail : "
              + exceptUpdName.toString();
    }
    return "Mise à jours du mail réussite !";
  }

  /**
   * Focntion qui met à jours une personne dont l'id est passé
   * en paramètre.
   * @param id : id de la personne.
   * @param nom : nouveau nom de la personne.
   * @param prenom : nouveau prénom de la personne.
   * @param mail : nouveau mail de la personne.
   * @return String : Message de confirmation.
   */
  @RequestMapping(
          value = "/updatePerson",
          method = RequestMethod.PUT,
          params = {"id", "nom", "prenom", "mail"})
  @ResponseBody
  public String updatePerson(long id, String nom, String prenom, String mail) {
    try {
      Personne personne = personneDao.getById(id);
      personne.setNom(nom);
      personne.setPrenom(prenom);
      personne.setMail(mail);
      personneDao.update(personne);
    } catch (Exception exceptUpdpers) {
      return "controllers/PersonneCtrl/updatePerson : Erreur de mise à jours de la personne : "
              + exceptUpdpers.toString();
    }
    return "Mise à jours de la personne réussite !";
  }
}
