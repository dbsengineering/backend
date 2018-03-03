package bzh.dbs.backend.dao;

import bzh.dbs.backend.domain.EquipementElec;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;


/**
 * Classe EquipementElecDao
 * Cette classe liste les commandes accessibles
 * pour lire, modifier, supprimer un équipement électrique.
 *
 * @author Jérémy Cavron
 * @version 1.0
 */
@Repository
@Transactional
public class EquipementElecDao {

  // --- Déclaration des propriétés ---
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Fonction qui retourne une liste de toutes les équipements électriques.
   *
   * @return Residence : ensemble d'équipements électriques.
   */
  @SuppressWarnings("unchecked")
  public List<EquipementElec> getAll() {
    return entityManager.createQuery("from EquipementElec").getResultList();
  }

  /**
   * Fonction qui crée un nouvel équipement électrique et retourne ce
   * dernier.
   * @param equipementElec : équipement électrique à créer.
   * @return
   */
  public void create(EquipementElec equipementElec) {
    entityManager.persist(equipementElec);
    return;
  }

  /**
   * Fonction qui retourne l'équipement électrique qui a l'id
   * passé en paramètre.
   *
   * @param id : id de l'équipement électrique.
   * @return equipementElec : équipement électrique qui a l'id.
   */
  public EquipementElec getById(long id) {
    return entityManager.find(EquipementElec.class, id);
  }

  /**
   * Procédure qui permet de supprimer un équipement électrique.
   * @param equipementElec : équipement électrique à supprimer.
   * @return
   */
  public void delete(EquipementElec equipementElec) {
    if (entityManager.contains(equipementElec)) {
      entityManager.remove(equipementElec);
    } else {
      entityManager.remove(entityManager.merge(equipementElec));
    }
    return;
  }

  /**
   * Procédure de mise à jours d'un équiepement électrique.
   *
   * @param equipementElec : équipement électrique mise à jours.
   */
  public void update(EquipementElec equipementElec) {
    entityManager.merge(equipementElec);
    return;
  }
}
