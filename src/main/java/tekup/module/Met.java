package tekup.module;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@DiscriminatorColumn (name = "type")
@JsonSubTypes({ 
	@Type(name = "Desser", value = Desser.class),
	@Type(name = "Entree", value = Entree.class),
	@Type(name = "Plat", value = Plat.class) })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Met {
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
private Integer id;
private String nom;
private Double prix;
@ManyToMany(mappedBy = "mets")
@JsonIgnore
private List<Ticket> tickets;

public Met() {
	
}

public String getType() {
	return this.getClass().getSimpleName();
}
}
