package tekup.module;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
@Data
@Entity
public class Client {
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	@Column (length = 255 )
	private String name;
	private String prenom;
	private LocalDate dateDeNaissance;
	private String courriel;
	private String telephone;
	@OneToMany(mappedBy = "client")
	private List<Ticket> tickets;

	

}
