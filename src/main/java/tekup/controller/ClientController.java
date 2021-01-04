package tekup.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tekup.DTO.ClientDTO;
import tekup.service.ClientInter;
@RestController 
@Data
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {
	private ClientInter service;

	@PostMapping ("/ajouter")
	public ClientDTO ajouterclient(@Valid @RequestBody ClientDTO clientDTO  ) {
		//System.out.println(service);
		return service.ajouter(clientDTO);
		
	}
}
