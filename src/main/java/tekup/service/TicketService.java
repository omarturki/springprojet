package tekup.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import tekup.DTO.TicketDTO;
import tekup.Repository.TableRepo;
import tekup.Repository.TicketRepo;
import tekup.exception.TableNotFoundException;
import tekup.exception.TicketNotFoundException;
import tekup.module.Client;
import tekup.module.Met;
import tekup.module.Ticket;
import tekup.module.table;


@Service
@Data
@AllArgsConstructor
 public class TicketService implements TicketInter {

	
	private ModelMapper mapper;
	private TicketRepo ticketRepository;
	private ClientInter ClientService;
	private TableRepo tableRepository;
	private MetInt iMetService;

	public TicketDTO ajouterTicket(TicketDTO ticketDto) {
		//System.out.println(ticketDto.getClient().getId());
		Integer t=ticketDto.getClient().getId();
		System.out.println(ClientService);

		Client client =ClientService.chercher(t);		
		table table=tableRepository
				.findById(ticketDto.getTableresto().getNumero())
				.orElseThrow(()-> new TableNotFoundException());
		
		Ticket ticket=mapper.map(ticketDto, Ticket.class)	;
		ticket.setClient(client);
		ticket.setTableresto(table);
		ticket =ticketRepository.save(ticket);
		return mapper.map(ticket, TicketDTO.class);
	}



	@Override
	public Ticket supprimerTicket(TicketDTO ticketDto) {
		Ticket t=chercherTicket(ticketDto);
		ticketRepository.deleteById(ticketDto.getNumero());
		return t;
	}

	@Override
	public Ticket chercherTicket(TicketDTO ticketDto) {
		Optional<Ticket> opt= ticketRepository.findById(ticketDto.getNumero());
		return opt.orElseThrow(()-> new TicketNotFoundException());
	}

	@Override
	public List<Ticket> listerTicket(LocalDate date) {
		return ticketRepository
				.findAll()
				.stream()
				.filter(t->t.getDate().toLocalDate().equals(date))
				.collect(Collectors.toList());
	}


	public double getAdd(TicketDTO ticket) {
		return chercherTicket(ticket).getaddition();
	}

	@Override
	public double getRevenue(LocalDate d, String s) {
		Period p;
		if (s.equalsIgnoreCase("jour"))
			p = Period.ofDays(1);
		else if (s.equalsIgnoreCase("semaine"))
			p = Period.ofWeeks(1);
		else if (s.equalsIgnoreCase("mois"))
			p = Period.ofMonths(1);
		else
			p = Period.ZERO;
		double revenue = ticketRepository.findAll().stream()
				.filter(t -> (t.getDate().toLocalDate().isAfter(d) || t.getDate().toLocalDate().isEqual(d))
						&& t.getDate().toLocalDate().isBefore(d.plus(p)))
				.mapToDouble(t -> t.getaddition()).sum();
		return revenue;
	}

	@Override
	public double getRevenuePourPeriode(LocalDate d1, LocalDate d2) {

		double revenue = ticketRepository.findAll().stream()
				.filter(t -> (t.getDate().toLocalDate().isAfter(d1) || t.getDate().toLocalDate().isEqual(d1))
						&& t.getDate().toLocalDate().isBefore(d2))
				.mapToDouble(t -> t.getaddition()).sum();
		return revenue;
	}

}
	
	


