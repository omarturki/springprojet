package tekup.controller;

import java.time.DayOfWeek;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tekup.DTO.ClientDTO;
import tekup.Repository.ClientRepo;
import tekup.exception.ClientNotFoundException;
import tekup.service.ClientInter;
@AllArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {
//	private ClientInter service;
//
//	@PostMapping ("/ajouter")
//	public ClientDTO ajouterclient(@Valid @RequestBody ClientDTO clientDTO  ) {
//		//System.out.println(service);
//		return service.ajouter(clientDTO);
//		
//	}
//	@PutMapping ("/modifier")
//	public ClientDTO modifierclient(@Valid @RequestBody ClientDTO clientDTO   ) {
//		//System.out.println(service);
//		return service.modifier(clientDTO);
//		
//	}
//	@DeleteMapping ("/delete")
//	public void deleteclient(@Valid @RequestBody ClientDTO clientDTO  ) {
//		//System.out.println(service);
//		service.supprimer(clientDTO);
//		System.out.println("vous avez supprimer");
//		
//	}
//	@GetMapping ("/list")
//	public List<ClientDTO> listclient( ) {
//		//System.out.println(service);
//		return service.listclient();
//		
//	}
//	@ExceptionHandler(ClientNotFoundException.class)
//	public ResponseEntity<String> handleNoSuchElementException(ClientNotFoundException e) {
//		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
//	}
	private ClientRepo clientRepository;
	private ClientInter iClientService;	
	
	
	@PostMapping("/ajouter") 
	public ResponseEntity< ClientDTO> ajouter( @Valid @RequestBody ClientDTO clientDto) {
			 return new ResponseEntity<ClientDTO>(iClientService.ajouter(clientDto), HttpStatus.CREATED) ;
			 }
	
	@DeleteMapping("/supprimer")
	public ResponseEntity<String> supprimer(@RequestBody ClientDTO clientDto){
		System.out.println("id  *****    ="+ clientDto.getId());
		iClientService.supprimer(clientDto);
		return new ResponseEntity<String>("Client supprimé avec succes !", HttpStatus.OK);
		
	}
	
	@GetMapping("/listerClients")
	public List<ClientDTO> ListerClients()
	{
		return iClientService.listerClients();
	}
	
	@PutMapping("/modifier")
	public ClientDTO modifier( @Valid @RequestBody ClientDTO clientDto) {
		return iClientService.modifier(clientDto);
	}

	
	@GetMapping("/plusFidele")
	public ClientDTO plusFidele() {
		return iClientService.clientPlusFidele();
	}
	@GetMapping("/jourPlusReserve")
	public DayOfWeek getJourPlusReserve(@RequestBody ClientDTO clientDto) {
		return iClientService.getJourPlusReserve(clientDto);
	}
	
	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<String> handleNoSuchElementException(ClientNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
