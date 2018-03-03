package bzh.dbs.backend.dao;

import bzh.dbs.backend.domain.Intelligent;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 * Classe IntelligenteDao
 * Cette classe liste les commandes accessibles
 * pour lire, modifier, supprimer un appareil intelligent.
 *
 * @author Jérémy Cavron
 * @version 1.0
 */
@Repository
@Transactional
public class IntelligentDao {

  // --- Déclaration des propriétés ---
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Fonction qui retourne une liste de toutes les appareils intelligents.
   *
   * @return Residence : ensemble d'appareils intelligents
   */
  @SuppressWarnings("unchecked")
  public List<Intelligent> getAll() {
    return entityManager.createQuery("from Intelligent").getResultList();
  }

  /**
   * Fonction qui retourne l'appareil intelligent qui a l'id
   * passé en paramètre.
   *
   * @param id : id de l'appareil intelligent.
   * @return chauffage : appareil intelligent qui a l'id.
   */
  public Intelligent getById(long id) {
    return entityManager.find(Intelligent.class, id);
  }

  /**
   * Procédure qui permet de supprimer un appareil intelligent.
   * @param intelligent : appareil intelligent à supprimer.
   * @return
   */
  public void delete(Intelligent intelligent) {
    if (entityManager.contains(intelligent)) {
      entityManager.remove(intelligent);
    } else {
      entityManager.remove(entityManager.merge(intelligent));
    }
    return;
  }

  /**
   * Procédure de mise à jours d'un appareil intelligent.
   *
   * @param intelligent : appareil intelligent mise à jours.
   */
  public void update(Intelligent intelligent) {
    entityManager.merge(intelligent);
    return;
  }
}
