package bzh.dbs.backend.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.*;


/**
 * Classe Personne.
 * Objet Personne.
 *
 * @author Jérémy cavron
 * @version 1.0
 */
@Entity
@Table(name = "personne")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id")
public class Personne {

  // --- Déclaration des propriétés ---
  private long id;
  private String nom;
  private String prenom;
  private String mail;
  //private Set<Residence> residences;
  private Set<Personne> amis;

  /**
   * Constructeur 1. Défaut.
   */
  public Personne() {
    super();
    this.amis = new HashSet<Personne>();
    //this.residences = new HashSet<Residence>();
  }

  /**
   * Constructeur 2. Avec id
   *
   * @param id : id de la personne.
   */
  public Personne(long id) {
    this.id = id;
  }

  /**
   * Constructeur 3. Avec paramètres.
   *
   * @param nom    : nom de la personne.
   * @param prenom : prénom de la personne.
   * @param mail   : mail de la personne.
   */
  public Personne(String nom, String prenom, String mail) {
    this.nom = nom;
    this.prenom = prenom;
    this.mail = mail;
    this.amis = new HashSet<Personne>();
    //this.residences = new HashSet<Residence>();
  }

  /**
   * Fonction qui retourne l'id d'une personne.
   *
   * @return Id : id de la personne.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public long getId() {
    return this.id;
  }

  /**
   * Procédure qui permet de modifier l'id de la personne.
   *
   * @param id : id de la personne.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Fonction qui retourne le nom de la personne.
   *
   * @return nom : nom de la personne.
   */
  @Column(name = "nom")
  @NotNull
  public String getNom() {
    return this.nom;
  }

  /**
   * Procédure qui permet de modifier le nom de la personne.
   *
   * @param nom : nom de la peronne.
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Fonction qui retourne le prénom de la peronne.
   *
   * @return prenom : prénom de la personne.
   */
  @Column(name = "prenom")
  @NotNull
  public String getPrenom() {
    return this.prenom;
  }

  /**
   * Procédure qui permet de modifier le prénom de la personne.
   *
   * @param prenom : prénom de la personne.
   */
  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  /**
   * Fonction qui retourne le mail de la personne.
   *
   * @return mail : mail de la personne.
   */
  @Column(name = "mail")
  @NotNull
  public String getMail() {
    return this.mail;
  }

  /**
   * Procédure qui permet de modifier le mail de la personne.
   *
   * @param mail : mail de la personne.
   */
  public void setMail(String mail) {
    this.mail = mail;
  }

  /**
   * Fonction qui retourne l'ensemble de résidences de la personne.
   *
   * @return residences : ensemble de résidences de la personne.
   */
  /*@OneToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "personne_residence",
          joinColumns = {@JoinColumn(name = "personne_id")},
          inverseJoinColumns = {@JoinColumn(name = "residence_id")})
  public Set<Residence> getResidences() {
    return this.residences;
  }*/

  /**
   * Procédure qui permet de modifier l'ensemble des résidences de la personne.
   *
   * @param residences : nouvel ensemble de résidences de la personne.
   */
  /*public void setResidences(Set<Residence> residences) {
    this.residences = residences;
  }*/

  /**
   * Fonction qui retourne l'ensemble d'amis de la personne.
   *
   * @return amis : les amis de la personne.
   */
  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "personne_amis",
          joinColumns = {@JoinColumn(name = "personne_id")},
          inverseJoinColumns = {@JoinColumn(name = "amis_id")})
  public Set<Personne> getAmis() {
    return this.amis;
  }

  /**
   * Procédure qui permet de modifier la liste d'amis de la personne.
   *
   * @param amis : nouvelle liste d'amis de la personne.
   */
  public void setAmis(Set<Personne> amis) {
    this.amis = amis;
  }

  /**
   * Fonction qui retourne un ami par son id s'il le trouve,
   * sinon null.
   *
   * @param id : id de l'ami.
   * @return Personne : ami de this.
   */
  public Personne getAmi(long id) {
    Personne personne;
    Iterator<Personne> it = this.getAmis().iterator();
    while (it.hasNext()) {
      personne = it.next();
      if (personne.getId() == id) {
        return personne;
      }
    }
    return null;
  }

  /**
   * Procédure qui permet d'ajouter un ami pour la personne.
   * @param ami : futur ami de la peronne.
   */
  public void addAmi(Personne ami){
    this.amis.add(ami);
  }

  /**
   * Procédure qui permet de supprimer un ami pour la personne.
   * @param ami : ancien ami de la peronne.
   */
  public void deleteAmi(Personne ami){
    this.amis.remove(ami);
  }
}
