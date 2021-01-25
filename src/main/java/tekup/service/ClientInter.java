package tekup.service;

import java.time.DayOfWeek;
import java.util.List;


import tekup.DTO.ClientDTO;
import tekup.module.Client;

public interface ClientInter {
public ClientDTO ajouter(ClientDTO clientDTO);
public void supprimer(ClientDTO clientDTO);
public ClientDTO modifier(ClientDTO clientDTO);
Client chercher(Integer id);
public List<ClientDTO> listerClients();
ClientDTO clientPlusFidele();

DayOfWeek getJourPlusReserve(ClientDTO clientDto);

}
