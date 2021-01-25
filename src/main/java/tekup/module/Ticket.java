package tekup.module;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
private Integer numero;
private LocalDateTime date;
private Integer nbCouvert;
private double addition;
@ManyToOne
private Client client;
@ManyToOne
private table tableresto;
@ManyToMany
@JoinTable(name = "Tickets_Mets",   
joinColumns = {@JoinColumn (name = "Ticket_id")}, 
    inverseJoinColumns = {@JoinColumn(name = "Met_id")}
)
private List<Met> mets;
public double getaddition() {
	
	//return mets.stream().mapToDouble(x->x.getPrix()).sum();
	return addition;
}
}


