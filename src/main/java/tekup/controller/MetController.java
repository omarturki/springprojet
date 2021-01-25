package tekup.controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;
import lombok.Data;
import tekup.DTO.MetDto;
import tekup.module.Desser;
import tekup.module.Entree;
import tekup.module.Met;
import tekup.module.Plat;
import tekup.service.MetInt;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("/met")
public class MetController {
	private MetInt metService;
	
	@PostMapping("/ajouter/plat")
	public Plat ajouterPlat( @RequestBody Plat plat) {	
		return metService.ajouterPlat(plat);
	}
	
	@PostMapping("/ajouter/entree")
	public Entree ajouterEntree(@Valid @RequestBody Entree entree) {	
		return metService.ajouterEntree(entree);
	}

	@PostMapping("/ajouter/dessert")
	public Desser ajouterDessert(@Valid @RequestBody Desser dessert) {	
		return metService.ajouterDessert(dessert);
	}
	
	
	
	@DeleteMapping("/supprimer/dessert")
	public Desser supprimerDessert(@Valid @RequestBody Desser dessert) {	
		return metService.supprimerDessert(dessert);
	}
	
	@DeleteMapping("/supprimer/entree")
	public Entree supprimerEntree(@Valid @RequestBody Entree entree) {	
		return metService.supprimerEntree(entree);
	}
	@DeleteMapping("/supprimer/plat")
	public Plat supprimerPlat(@Valid @RequestBody Plat plat) {	
		return metService.supprimerPlat(plat);
	}
	
	@PutMapping("/modifier/plat")
	public Plat modifierPlat(@Valid @RequestBody Plat plat) {
		return metService.modifierPlat(plat);
	}
	
	@PutMapping("/modifier/entree")
	public Entree modifierEntree(@Valid @RequestBody Entree entree) {
		return metService.modifierEntree(entree);
	}
	
	@PutMapping("/modifier/dessert")
	public Desser modifierDessert(@Valid @RequestBody Desser dessert) {
		return metService.modifierDessert(dessert);
	}
	@GetMapping("/listerMets")
	public List<Met> listerMets (){
		return metService.listerMets();
	}
	@GetMapping("/PlatplusAcheter/{date}/{date2}")
	public MetDto platPlusAcheter(@PathVariable("date") String date,@PathVariable("date2") String date2) {
		
		LocalDate d1=LocalDate.parse(date);
		LocalDate d2=LocalDate.parse(date2);
		if(d1.isAfter(d2))
			throw new DateTimeException("Date invalide ");
		
		return metService.platPlusAcheter(d1,d2);
	}
}

