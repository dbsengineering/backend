package bzh.dbs.backend.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Classe Intelligent.
 * Objet Intelligent.
 *
 * @author Jérémy cavron
 * @version 1.0
 */
@Entity
@Table(name = "intelligent")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id")
public class Intelligent {

  // --- Déclaration des propriétées ---
  private Long id;
  private double heureParJour;
  private int jourParan;
  private double watt;

  /**
   * Constructeur.
   */
  public Intelligent() {
  }

  /**
   * Fonction qui retourne l'id de la classe.
   *
   * @return id : id de la classe.
   */
  @Id
  @GeneratedValue
  @Column(name = "id")
  public Long getId() {
    return this.id;
  }

  /**
   * Procédure qui modifie l'id de la classe.
   *
   * @param id : id de la classe.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Fonction qui retourne les heures par jours d'un appareil.
   * @return heureParJour : heure par jour.
   */
  @Column(name = "heureParJour")
  @NotNull
  public double getHeureParJour() {
    return this.heureParJour;
  }

  /**
   * Procédure qui permet de modifier les Heure par jour d'un appareil en fonctionnement.
   * @param heureParJour : heure par jour.
   */
  public void setHeureParJour(double heureParJour) {
    this.heureParJour = heureParJour;
  }

  /**
   * Fonction qui retourne les jour par an d'un appareil.
   * @return jourParan : jour par an.
   */
  @Column(name = "jourParAn")
  @NotNull
  public int getJourParan() {
    return this.jourParan;
  }

  /**
   * Procédure qui permet de modifier les jour par année de fonctionnement d'un appareil.
   * @param jourParan : jourParAn.
   */
  public void setJourParan(int jourParan) {
    this.jourParan = jourParan;
  }

  /**
   * Fonction qui retourne la puissance en watt d'un appareil.
   * @return watt : puissance en watt de l'appareil.
   */
  @Column(name = "watt")
  @NotNull
  public double getWatt() {
    return this.watt;
  }

  /**
   * Procédure qui permet de modifier la puissance en watt d'un appareil.
   * @param watt : puissance en watt.
   */
  public void setWatt(double watt) {
    this.watt = watt;
  }
}
