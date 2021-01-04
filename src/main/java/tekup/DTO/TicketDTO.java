package tekup.DTO;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tekup.module.Client;
import tekup.module.Met;
import tekup.module.table;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
	@NotNull 
	private Integer numero;
	@PastOrPresent 
	private LocalDateTime date;
	@Min(1) @Max(6)
	private Integer nbCouvert;
	private Double addition;
	@NotBlank (message = "champs obligatoire")
	private Client client;
	@NotBlank (message = "champs obligatoire")
	private table table;
	@NotNull 
	@JsonIgnore
	private List<Met> mets;


}
