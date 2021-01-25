package tekup.DTO;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;


import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
import tekup.module.Client;
import tekup.module.Met;
import tekup.module.table;
@Getter
@Setter
public class TicketDTO {
	@NotNull 
	private Integer numero;
	@PastOrPresent 
	private LocalDateTime date;
	@Min(1) @Max(6)
	private Integer nbCouvert;
	private Double addition;
	private Client client;
	private table tableresto;
	private List<Met> mets;


}
