package tekup.controller;


import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import tekup.exception.ClientNotFoundException;
import tekup.module.table;
import tekup.service.tableInter;
@RestController 
@Data
@AllArgsConstructor
@RequestMapping("/table")
public class TableController {

		private tableInter service;

		@PostMapping ("/ajouter")
		public table ajouterclient(@Valid @RequestBody table table  ) {
			//System.out.println(service);
			return service.ajouter(table);
			
		}
		@PutMapping("/modifier")
		public table modifierTable(@Valid @RequestBody table tableResto) {
			return service.modifierTable(tableResto);
		}
		
		@DeleteMapping("/supprimer")
		public table supprimerTable(@Valid @RequestBody  table tableResto) {
			return service.supprimerTable(tableResto);
		}
		
		@GetMapping("/chercher")
		public table getTable(@Valid @RequestBody table tableResto) {
			return service.chercher(tableResto);
		}
		
		@GetMapping("/plusReservee")
		public table plusReserve() {
			return service.plusReserve();
		}
		
		@ExceptionHandler(ClientNotFoundException.class)
		public ResponseEntity<String> handleNoSuchElementException(ClientNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
}
