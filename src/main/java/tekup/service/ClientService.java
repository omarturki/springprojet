package tekup.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tekup.DTO.ClientDTO;
import tekup.Repository.ClientRepo;
import tekup.module.Client;
@Service
@Data
@AllArgsConstructor

public class ClientService implements ClientInter {
	private ModelMapper m;
	private ClientRepo ClientRepo;
	
	
	public ClientDTO ajouter(ClientDTO clientDTO ) {
		//System.out.println("mapper   = "+m);
		
		Client client=m.map(clientDTO, Client.class);
		client = ClientRepo.save(client);
		clientDTO = m.map(client, ClientDTO.class);
		return clientDTO; 
	}

}
