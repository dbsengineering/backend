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
  private Set<Intelligent> intelligents;

  /**
   * Constructeur 1. Défaut.
   */
  public Residence() {
    super();
    this.intelligents = new HashSet<Intelligent>();
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
    this.intelligents = new HashSet<Intelligent>();
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
   * Fonction qui retourne les appareils intelligents de la résidence.
   *
   * @return intelligents : liste d'appareil intelligents de la résidence.
   */
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "residence_intelligent",
          joinColumns = {@JoinColumn(name = "residence_id")},
          inverseJoinColumns = {@JoinColumn(name = "intelligent_id")})
  public Set<Intelligent> getIntelligents() {
    return this.intelligents;
  }

  /**
   * Procédure qui modifie la liste des appareils intelligents de la résidence.
   *
   * @param intelligents : liste d'appareils intelligents de la résidence.
   */
  public void setIntelligents(Set<Intelligent> intelligents) {
    this.intelligents = intelligents;
  }

  /**
   * Procédure qui ajoute un appareil intelligent à la résidence
   *
   * @param intelligent : appareil intelligent à ajouter
   */
  public void addIntelligent(Intelligent intelligent) {
    this.intelligents.add(intelligent);
  }

  /**
   * Procédure qui permet de supprimer un appareil intelligent.
   * @param intelligent : appareil intelligent.
   */
  public void deleteIntelligent(Intelligent intelligent){
    this.intelligents.remove(intelligent);
  }
}
