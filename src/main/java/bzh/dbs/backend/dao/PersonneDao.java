package bzh.dbs.backend.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import bzh.dbs.backend.domain.Personne;
import bzh.dbs.backend.domain.Residence;
import org.springframework.stereotype.Repository;

/**
 * Classe PersonneRes. Repository de Personne.
 * Cette classe liste les commandes accessibles
 * pour lire, modifier, supprimer une personne.
 *
 * @author Jérémy Cavron
 * @version 1.0
 */
@Repository
@Transactional
public class PersonneDao {

  // --- Déclaration des propriétés ---
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Fonction qui retourne une liste de toutes les personnes.
   *
   * @return Personne : ensemble de personnes
   */
  @SuppressWarnings("unchecked")
  public List<Personne> getAll() {
    return entityManager.createQuery("from Personne").getResultList();
  }

  /**
   * Fonction qui crée une nouvelle personne et retourne cette
   * dernière.
   *
   * @return personne : personne ajoutée.
   */
  public void create(Personne personne) {
    entityManager.persist(personne);
    return;
  }

  /**
   * Fonction qui retourne la personne qui a le mail
   * passé en paramètre
   *
   * @param mail : mail de la personne
   * @return personne : personne qui a le mail.
   */
  public Personne getByEmail(String mail) {
    return (Personne) entityManager.createQuery(
            "from Personne where mail = :mail")
            .setParameter("mail", mail)
            .getSingleResult();
  }

  /**
   * Fonction qui retourne la personne qui a l'id
   * passé en paramètre
   *
   * @param id : id de la personne
   * @return personne : personne qui a l'id.
   */
  public Personne getById(long id) {
    return entityManager.find(Personne.class, id);
  }

  /**
   * Procédure qui permet de supprimer une personne.
   *
   * @return personne : personne supprimée
   */
  public void delete(Personne personne) {
    if (entityManager.contains(personne))
      entityManager.remove(personne);
    else
      entityManager.remove(entityManager.merge(personne));
    return;
  }

  /**
   * Procédure de mise à jours d'une personne.
   *
   * @param personne : Personne mise à jours.
   */
  public void update(Personne personne) {
    entityManager.merge(personne);
    return;
  }

}
