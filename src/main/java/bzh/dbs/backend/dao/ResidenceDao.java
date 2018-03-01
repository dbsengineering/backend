package bzh.dbs.backend.dao;

import bzh.dbs.backend.domain.Residence;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;


/**
 * Classe ResidenceDao
 * Cette classe liste les commandes accessibles
 * pour lire, modifier, supprimer une residence.
 *
 * @author Jérémy Cavron
 * @version 1.0
 */
@Repository
@Transactional
public class ResidenceDao {

  // --- Déclaration des propriétés ---
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Fonction qui retourne une liste de toutes les residences.
   *
   * @return Residence : ensemble de residences
   */
  @SuppressWarnings("unchecked")
  public List<Residence> getAll() {
    return entityManager.createQuery("from Residence").getResultList();
  }

  /**
   * Fonction qui crée une nouvelle residence et retourne cette
   * dernière.
   * @param residence : résidence à créer.
   * @return
   */
  public void create(Residence residence) {
    entityManager.persist(residence);
    return;
  }

  /**
   * Fonction qui retourne la residence qui a l'id
   * passé en paramètre.
   *
   * @param id : id de la residence.
   * @return residence : residence qui a l'id.
   */
  public Residence getById(long id) {
    return entityManager.find(Residence.class, id);
  }

  /**
   * Procédure qui permet de supprimer une residence.
   * @param residence : résidence à supprimer.
   * @return
   */
  public void delete(Residence residence) {
    if (entityManager.contains(residence)) {
      entityManager.remove(residence);
    } else {
      entityManager.remove(entityManager.merge(residence));
    }
    return;
  }

  /**
   * Procédure de mise à jours d'une residence.
   *
   * @param residence : residence mise à jours.
   */
  public void update(Residence residence) {
    entityManager.merge(residence);
    return;
  }


}
