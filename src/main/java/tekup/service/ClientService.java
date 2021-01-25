package tekup.service;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;



import lombok.AllArgsConstructor;
import lombok.Data;
import tekup.DTO.ClientDTO;
import tekup.Repository.ClientRepo;
import tekup.Repository.TicketRepo;
import tekup.exception.ClientNotFoundException;
import tekup.module.Client;
import tekup.module.Ticket;
@Service
@Data
@AllArgsConstructor
public class ClientService implements ClientInter {
	private ModelMapper m;
	private ClientRepo ClientRepo;
	private TicketRepo ticketRepository;
	@Override
	public ClientDTO ajouter(ClientDTO clientDTO ) {
		//System.out.println("mapper   = "+m);
		
		Client client=m.map(clientDTO, Client.class);
		client = ClientRepo.save(client);
		clientDTO = m.map(client, ClientDTO.class);
		return clientDTO; 
	}
	@Override
	public Client chercher(Integer id) {
		Optional<Client> opt =ClientRepo.findById(id);
		
		Client client = opt.orElseThrow( () ->new ClientNotFoundException() ) ;
		
		return client;
	}



	@Override
	public ClientDTO modifier(ClientDTO clientDTO ) {
	
		Client oldclient=this.chercher(clientDTO.getId());
		Client newclient=m.map(clientDTO,Client.class);

			newclient.setTickets(oldclient.getTickets());
		
		newclient= ClientRepo.save(newclient);
		return m.map(newclient,ClientDTO.class); 
	}
	@Override
	public void supprimer(ClientDTO clientDTO ) {
		
		Client client=m.map(clientDTO, Client.class);
		client=this.chercher(client.getId());

				ClientRepo.deleteById((client.getId()));
		
		System.out.println("client bien supprimer"); 
	}
	
	@Override
	public List<ClientDTO> listerClients() {
		
				List<ClientDTO> list=ClientRepo.findAll().stream().map(c->m.map(c, ClientDTO.class)).collect(Collectors.toList()) ;
				
		
return list;	}
	@Override
	public ClientDTO clientPlusFidele() {
		List<Ticket> listOfTickets = ticketRepository.findAll();
		List<Client> listOfClients = listOfTickets.stream().map(t -> t.getClient()).collect(Collectors.toList());
		List<Client> listDist = listOfClients.stream().distinct().collect(Collectors.toList());
		int max = 0;
		Client plusF = null;
		for (Client c : listDist) {
			int frequence = Collections.frequency(listOfClients, c);
			if (max < frequence)
				max = frequence;
			plusF = c;
		}
		return m.map(plusF, ClientDTO.class);
	}

	@Override
	public DayOfWeek getJourPlusReserve(ClientDTO clientDto) {
		Client c = ClientRepo.findById(clientDto.getId()).orElseThrow(() -> new ClientNotFoundException());
		List<Ticket> listOfTickets = ticketRepository.findAll().stream().filter(t->(t.getClient().getId()==c.getId()))
				.collect(Collectors.toList());

		List<DayOfWeek> listOfDays = listOfTickets.stream().map(t -> t.getDate().getDayOfWeek())
				.collect(Collectors.toList());
		int max = 0;
		DayOfWeek day = null;
		for (DayOfWeek d : listOfDays) {

			int nbr = Collections.frequency(listOfDays, d);
			if (max < nbr) {
				max = nbr;
				day = d;
			}
		}

		return day;
	}



	

}
