package tekup.service;

import java.time.LocalDate;
import java.util.List;

import tekup.DTO.MetDto;
import tekup.module.Desser;
import tekup.module.Entree;
import tekup.module.Met;
import tekup.module.Plat;

public interface MetInt {

	public Plat ajouterPlat(Plat plat);

	Entree ajouterEntree(Entree entree);

	Desser ajouterDessert(Desser dessert);

	Plat supprimerPlat(Plat plat);

	Entree supprimerEntree(Entree entree);

	Desser supprimerDessert(Desser dessert);

	Met chercherMet(Met met);

	Plat modifierPlat(Plat plat);

	Entree modifierEntree(Entree entree);

	Desser modifierDessert(Desser dessert);

	List<Met> listerMets();

	MetDto platPlusAcheter(LocalDate d1, LocalDate d2);

}
