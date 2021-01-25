package tekup.module;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String name;
	private String prenom;
	private LocalDate dateDeNaissance;
	private String courriel;
	private String telephone;
	@OneToMany(mappedBy = "client")
	@JsonIgnore
	private List<Ticket> tickets;

}
