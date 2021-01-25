package tekup.controller;



import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import lombok.AllArgsConstructor;
import tekup.DTO.TicketDTO;
import tekup.exception.TicketNotFoundException;
import tekup.module.Ticket;
import tekup.service.MetInt;
import tekup.service.TicketInter;
import tekup.service.TicketService;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {
	
	private TicketInter service;
	private MetInt MetService;
	@GetMapping("/lister")
	public List<Ticket> lister( @RequestParam(name = "d")  String d) {
		LocalDate xd=LocalDate.parse(d);
		return service.listerTicket(xd);
	}
	
	@GetMapping("/get")
	public Ticket afficherTicket( @RequestBody  TicketDTO ticketDto) {
		return service.chercherTicket(ticketDto);
	}
	
	@PostMapping("/ajouter")
	public TicketDTO ajouter(@Valid @RequestBody TicketDTO ticketDto) {
		return 	service.ajouterTicket(ticketDto);
	}
	@PutMapping("/modifier/{id}")
	public TicketDTO modifier(@PathVariable("id") Integer id,@Valid @RequestBody TicketDTO ticketDto) {
			ticketDto.setNumero(id);
		return 	service.ajouterTicket(ticketDto);
	}
	
	@DeleteMapping("/supprimer")
	public Ticket supprimerTicket(@RequestBody TicketDTO ticketDto) {
		return service.supprimerTicket(ticketDto);
	}


	
	@ExceptionHandler(TicketNotFoundException.class)
	public ResponseEntity<String> handleNoSuchElementException(TicketNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
