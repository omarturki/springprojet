package tekup.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import lombok.Data;
import tekup.Repository.TableRepo;
import tekup.Repository.TicketRepo;
import tekup.exception.TableNotFoundException;
import tekup.module.table;
@Service
@AllArgsConstructor
public class tableService implements tableInter{
	private ModelMapper m;
	private TableRepo tableRepo;
	private TicketRepo ticketRepo;
	
	@Override
	public table ajouter(table t ) {		
		
		return tableRepo.save(t);
		 
	}
	@Override
	public table chercher(table tableResto) {
		
		return tableRepo.findById(tableResto.getNumero()).orElseThrow( () ->new TableNotFoundException() ) ;
		
		
	}
	@Override
	public table modifierTable(table tableResto) {
		// TODO Auto-generated method stub	
		table tableRestoInBase = chercher(tableResto);
		tableRestoInBase.setNbCouvert(tableResto.getNbCouvert());
		tableRestoInBase.setSupplement(tableResto.getSupplement());
		tableRestoInBase.setTickets(tableResto.getTickets());
		tableRestoInBase.setType(tableResto.getType());
		return tableRepo.save(tableRestoInBase);

	}
	@Override
	public table supprimerTable(table tablere) {
		table t = chercher(tablere);
		tableRepo.deleteById(tablere.getNumero());
		return t;
	}
	@Override
	public table plusReserve() {
		List<table> listOfTables = ticketRepo.findAll().stream().map(t -> t.getTableresto())
				.collect(Collectors.toList());
		List<table> listOfTablesDistinct = listOfTables.stream().distinct().collect(Collectors.toList());

		HashMap<table, Integer> hm = new HashMap<table, Integer>();
		int max = 0;
		table plusReservee = null;
		for (table t : listOfTablesDistinct) {
			int x = Collections.frequency(listOfTables, t);
			hm.put(t, x);
			if (max < x) {
				max = x;
				plusReservee = t;
			}
		}

		return plusReservee;
	}
}
