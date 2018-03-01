package bzh.dbs.backend.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
}
