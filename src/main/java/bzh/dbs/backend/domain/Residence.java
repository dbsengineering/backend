package bzh.dbs.backend.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe Residence.
 * Objet Résidence.
 *
 * @author Jérémy cavron
 * @version 1.0
 */
@Entity
@Table(name = "residence")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id")
public class Residence {

  // --- Déclaration des Propriétées ---
  private Long id;
  private double taille;
  private int nbPieces;
  private Set<Chauffage> chauffages;
  //private Set<EquipementElec> equipementElecs;

  /**
   * Constructeur 1. Défaut.
   */
  public Residence() {
    super();
    this.chauffages = new HashSet<Chauffage>();
    //this.equipementElecs = new HashSet<EquipementElec>();
  }

  /**
   * Constructeur 2. Id.
   */
  public Residence(long id) {
    super();
    this.id = id;
  }

  /**
   * Constructeur 3 Avec paramètres.
   *
   * @param taille   : taille de la résidence
   * @param nbPieces : nombre de pièces dans la résidence.
   */
  public Residence(double taille, int nbPieces) {
    super();
    this.taille = taille;
    this.nbPieces = nbPieces;
    this.chauffages = new HashSet<Chauffage>();
    //this.equipementElecs = new HashSet<EquipementElec>();
  }

  /**
   * Fonction qui retourne l'id d'une résidence.
   *
   * @return Id : id de la résidence.
   */
  @Id
  @GeneratedValue
  @Column(name = "id")
  public Long getId() {
    return this.id;
  }

  /**
   * Procédure qui permet de modifier l'id de la résidence.
   *
   * @param id : id de la résidence.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Fonction qui retourne la taille de la résidence.
   *
   * @return taille : taille de la résidence.
   */
  @Column(name = "taille")
  @NotNull
  public double getTaille() {
    return this.taille;
  }

  /**
   * Procédure qui permet de modifier la ltaille de la résidence.
   *
   * @param taille : taille de la résidence.
   */
  public void setTaille(double taille) {
    this.taille = taille;
  }

  /**
   * Fonction qui retourne le nombre de pièces de la résidence.
   * @return nbPieces : nombre de pièces de la résidence.
   */
  @NotNull
  @Column(name = "nbpieces")
  public int getNbPieces() {
    return this.nbPieces;
  }

  /**
   * Procédure qui modifie le nombre de pièces de la résidence.
   *
   * @param nbPieces : nombre de pièces de la réseidnce.
   */
  public void setNbPieces(int nbPieces) {
    this.nbPieces = nbPieces;
  }

  /**
   * Fonction qui retourne les chauffages de la résidence.
   *
   * @return chauffages : liste de chauffages de la résidence.
   */
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "residence_chauffage",
          joinColumns = {@JoinColumn(name = "residence_id")},
          inverseJoinColumns = {@JoinColumn(name = "chauffage_id")})
  public Set<Chauffage> getChauffages() {
    return this.chauffages;
  }

  /**
   * Procédure qui modifie la liste des chauffages de la résidence.
   *
   * @param chauffages : liste de chauffages de la résidence.
   */
  public void setChauffages(Set<Chauffage> chauffages) {
    this.chauffages = chauffages;
  }

  /**
   * Procédure qui ajoute un chauffage à la résidence
   *
   * @param chauffage : chauffage à ajouter
   */
  public void addChauffage(Chauffage chauffage) {
    this.chauffages.add(chauffage);
  }

  /**
   * Procédure qui permet de supprimer un chauffage.
   * @param chauffage : chauffage.
   */
  public void deleteChauffage(Chauffage chauffage){
    this.chauffages.remove(chauffage);
  }

  /**
   * Fonction qui retourne la liste d'équipements électriques de la résidence.
   *
   * @return equipementElecs : liste d'équipements électriques.
   */
  /*@OneToMany(targetEntity = EquipementElec.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
  @JoinColumn(name = "id")
  public Set<EquipementElec> getEquipementElecs() {
    return this.equipementElecs;
  }*/

  /**
   * Procédure qui modifie la liste des équipements électriques de la résidence.
   *
   * @param equipementElecs : liste d'équipements électriques.
   */
  /*public void setEquipementElecs(Set<EquipementElec> equipementElecs) {
    this.equipementElecs = equipementElecs;
  }*/

  /**
   * Procédure qui ajoute un équipement électrique.
   * @param equipementElec : équipement électrique à ajouter.
   */
  /*public void addEquipementE(EquipementElec equipementElec){
    this.equipementElecs.add(equipementElec);
  }*/

  /**
   * Procédure qui permet de supprimer un équipement électrique.
   * @param equipementElec : équipement électrique.
   */
  /*public void deleteEquipementElec(EquipementElec equipementElec){
    this.equipementElecs.remove(equipementElec);
  }*/

}
