package tekup.module;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class table {
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY )
private Integer numero ;
@Column
private Integer nbCouvert;
private String type;
private Double supplement;
@OneToMany(mappedBy = "table")
private List<Ticket> tickets;

}
