package bzh.dbs.backend.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe Chauffage.
 * Objet Chauffage.
 *
 * @author Jérémy cavron
 * @version 1.0
 */
@Entity
@Table(name = "chauffage")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id")
public class Chauffage extends Intelligent {

  /**
   * Constructeur 1. Défaut
   */
  public Chauffage(){
    super();
  }

  /**
   * Constructeur 2. Avec paramètres.
   * @param nom : nom du chauffage.
   * @param heureParJour : heure de fonctionnement du chauffage par jour.
   * @param jourParan : jour de fonctionnement du chauffage par année.
   * @param watt : puissance du chauffage en watt.
   */
  public Chauffage(String nom, long heureParJour, int jourParan, long watt){
    super(nom, heureParJour, jourParan, watt);
  }
}
