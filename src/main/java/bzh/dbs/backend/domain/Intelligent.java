package bzh.dbs.backend.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
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
  private String nom;
  private long heureParJour;
  private int jourParan;
  private long watt;

  /**
   * Constructeur 1.
   */
  public Intelligent() {
  }

  /**
   * Constructeur 2. Avec paramètres.
   * @param nom : nom de l'appareil.
   * @param heureParJour : heures de fonctionnement de l'appareil par jour.
   * @param jourParan : jours de fonctionnement de l'appareil par année.
   * @param watt : puissance de l'appareil en watt.
   */
  public Intelligent(String nom, long heureParJour, int jourParan, long watt){
    super();
    this.nom = nom;
    this.heureParJour = heureParJour;
    this.jourParan = jourParan;
    this.watt = watt;
  }

  /**
   * Fonction qui retourne la moyenne de consommation d'un appareil.
   * @return average : moyenne de consommation.
   */
  public Long calculMoyen() {
    return this.heureParJour * this.jourParan * (this.watt / 1000);
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
   * Fonction qui retourne le nom de l'appareil.
   * @return nom : nom de l'appareil.
   */
  public String getNom() {
    return nom;
  }

  /**
   * Procédure qui permet de modifier le nom de l'appareil.
   * @param nom : nom de l'appareil.
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Fonction qui retourne les heures par jours d'un appareil.
   * @return heureParJour : heure par jour.
   */
  @Column(name = "heureParJour")
  @NotNull
  public long getHeureParJour() {
    return this.heureParJour;
  }

  /**
   * Procédure qui permet de modifier les Heure par jour d'un appareil en fonctionnement.
   * @param heureParJour : heure par jour.
   */
  public void setHeureParJour(long heureParJour) {
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
  public long getWatt() {
    return this.watt;
  }

  /**
   * Procédure qui permet de modifier la puissance en watt d'un appareil.
   * @param watt : puissance en watt.
   */
  public void setWatt(long watt) {
    this.watt = watt;
  }
}
