package tekup.DTO;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
	
	private Integer id;
	@NotBlank (message = "champs obligatoire")
	private String name;
	@NotBlank (message = "champs obligatoire")
	private String prenom;
	@NotNull
	@Past (message = "champs obligatoire")
	private LocalDate dateDeNaissance;
	@Email (message = "champs obligatoire")
	private String courriel;
	@NotBlank (message = "champs obligatoire")
	private String telephone;
}
