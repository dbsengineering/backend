package bzh.dbs.backend.dao;

import bzh.dbs.backend.domain.Chauffage;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;


/**
 * Classe ChauffageDao
 * Cette classe liste les commandes accessibles
 * pour lire, modifier, supprimer un chauffage.
 *
 * @author Jérémy Cavron
 * @version 1.0
 */
@Repository
@Transactional
public class ChauffageDao {

  // --- Déclaration des propriétés ---
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Fonction qui retourne une liste de toutes les chauffages.
   *
   * @return Residence : ensemble de chauffages
   */
  @SuppressWarnings("unchecked")
  public List<Chauffage> getAll() {
    return entityManager.createQuery("from Chauffage").getResultList();
  }

  /**
   * Fonction qui crée un nouveau chauffage et retourne ce
   * dernier.
   * @param chauffage : résidence à créer.
   * @return
   */
  public void create(Chauffage chauffage) {
    entityManager.persist(chauffage);
    return;
  }

  /**
   * Fonction qui retourne le chauffage qui a l'id
   * passé en paramètre.
   *
   * @param id : id du chauffage.
   * @return chauffage : chauffage qui a l'id.
   */
  public Chauffage getById(long id) {
    return entityManager.find(Chauffage.class, id);
  }

  /**
   * Procédure qui permet de supprimer un chauffage.
   * @param chauffage : chauffage à supprimer.
   * @return
   */
  public void delete(Chauffage chauffage) {
    if (entityManager.contains(chauffage)) {
      entityManager.remove(chauffage);
    } else {
      entityManager.remove(entityManager.merge(chauffage));
    }
    return;
  }

  /**
   * Procédure de mise à jours d'un chauffage.
   *
   * @param chauffage : chauffage mise à jours.
   */
  public void update(Chauffage chauffage) {
    entityManager.merge(chauffage);
    return;
  }
}
