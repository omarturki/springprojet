package tekup.service;

import java.time.LocalDate;
import java.util.List;


import tekup.DTO.TicketDTO;
import tekup.module.Ticket;

public interface TicketInter {
	TicketDTO ajouterTicket(TicketDTO ticketDTO);
	Ticket supprimerTicket(TicketDTO ticketDTO);
	Ticket chercherTicket(TicketDTO ticketDto);
	List<Ticket> listerTicket(LocalDate date);
	
public double getAdd(TicketDTO ticket) ;
	
	public double getRevenue(LocalDate d,String s);
	
 public double	getRevenuePourPeriode(LocalDate d1,LocalDate d2);
}
