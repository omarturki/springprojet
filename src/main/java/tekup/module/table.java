package tekup.module;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Getter;
import lombok.Setter;
@Entity
@Getter 
@Setter
public class table {
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY )
private Integer numero ;
private Integer nbCouvert;
private String type;
private Double supplement;
@OneToMany(mappedBy = "tableresto")
@JsonIgnore
private List<Ticket> tickets;

}
