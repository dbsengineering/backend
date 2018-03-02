package bzh.dbs.backend.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe EquipementElec.
 * Objet EquipementElec.
 *
 * @author Jérémy cavron
 * @version 1.0
 */
@Entity
@Table(name = "equipementElec")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id")
public class EquipementElec extends Intelligent {


  /**
   * Constructeur 1. Défaut
   */
  public EquipementElec(){
    super();
  }

  /**
   * Constructeur 2. Avec paramètres.
   * @param nom : nom de l'appareil électrique.
   * @param heureParJour : heure de fonctionnement de l'appareil électrique par jour.
   * @param jourParan : jour de fonctionnement de l'appareil électrique par année.
   * @param watt : puissance de l'appareil électrique en watt.
   */
  public EquipementElec(String nom, double heureParJour, int jourParan, double watt){
    super(nom, heureParJour, jourParan, watt);
  }

}
