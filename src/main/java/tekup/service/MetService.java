package tekup.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;



import lombok.AllArgsConstructor;
import tekup.DTO.MetDto;
import tekup.Repository.DessertRepo;
import tekup.Repository.EnntreRepo;
import tekup.Repository.MetRepo;
import tekup.Repository.PlatRepo;
import tekup.Repository.TicketRepo;
import tekup.exception.MetNotFoundException;
import tekup.module.Desser;
import tekup.module.Entree;
import tekup.module.Met;
import tekup.module.Plat;
import tekup.module.Ticket;

@Service
@AllArgsConstructor
public class MetService implements MetInt {

	private MetRepo metRepository;
	private PlatRepo platRepository;
	private EnntreRepo entreeRepository;
	private DessertRepo dessertRepository;
	private TicketRepo ticketRepository;
	private ModelMapper mapper;
	@Override
	public Plat ajouterPlat(Plat plat) {
		return platRepository.save(plat);
	}

	@Override
	
	public Entree ajouterEntree(Entree entree) {
		return entreeRepository.save(entree);
	}

	
	@Override
	public Desser ajouterDessert(Desser dessert) {
		return dessertRepository.save(dessert);
	}

	@Override
	
	public Plat supprimerPlat(Plat plat) {
		Met m = this.chercherMet(plat);
		metRepository.deleteById(plat.getId());
		return (Plat) m;
	}

	@Override
	
	public Entree supprimerEntree(Entree entree) {
		Met e = this.chercherMet(entree);
		metRepository.deleteById(entree.getId());
		return (Entree) e;
	}

	@Override
	public Desser supprimerDessert(Desser dessert) {
		Met d = this.chercherMet(dessert);
		metRepository.deleteById(dessert.getId());
		return (Desser) d;
	}

	@Override
	
	public Met chercherMet(Met met) {
		if(met.getId()==null)
			throw new MetNotFoundException();
		
		Met m = metRepository.findById(met.getId()).orElseThrow(() -> new MetNotFoundException());
		return m;
	}

	@Override
	
	public Plat modifierPlat(Plat plat) {
		System.out.println("plat ======="+plat);
		Plat p=(Plat)this.chercherMet(plat);
		p.setNom(plat.getNom());
		p.setPrix(plat.getPrix());
		p.setTickets(plat.getTickets());
		return platRepository.save(p);
	}

	@Override
	
	public Entree modifierEntree(Entree entree) {
		Entree e=(Entree)this.chercherMet(entree);
		e.setNom(entree.getNom());
		e.setPrix(entree.getPrix());
		e.setTickets(entree.getTickets());
		return entreeRepository.save(e);
	}

	@Override
	
	public Desser modifierDessert(Desser dessert) {
		Desser d=(Desser)this.chercherMet(dessert);
		d.setNom(dessert.getNom());
		d.setPrix(dessert.getPrix());
		d.setTickets(dessert.getTickets());
		return dessertRepository.save(d);
	}

	
	@Override
	public List<Met> listerMets() {
		return metRepository.findAll();
	}
	@Override
	public MetDto platPlusAcheter(LocalDate d1, LocalDate d2) {
		LocalDateTime date1 = LocalDateTime.of(d1, LocalTime.of(0, 0));
		LocalDateTime date2 = LocalDateTime.of(d2, LocalTime.of(23, 59));
		List<Ticket> listeOfTickets = ticketRepository.findAll().stream()
				.filter(t -> (t.getDate().isAfter(date1)) && (t.getDate().isBefore(date2)))
				.collect(Collectors.toList());
		List<List<Met>> listOfMets = listeOfTickets.stream().map(t -> t.getMets()).collect(Collectors.toList());
		Plat p = null;
		Integer max = 0;
		HashMap<Plat, Integer> plats = new HashMap<Plat, Integer>();
		for (List<Met> list : listOfMets)
			for (Met m : list) {
				if (m instanceof Plat) {
					if (plats.containsKey((Plat) m)) {
						plats.put((Plat) m, plats.get((Plat) m) + 1);
						if (plats.get((Plat) m) > max) {
							max = plats.get((Plat) m);
							p = (Plat) m;
						}
					} else
						plats.put((Plat) m, 1);
				}
			}
		return mapper.map(p, MetDto.class);
	}

}
