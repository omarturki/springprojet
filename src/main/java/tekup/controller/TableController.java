package tekup.controller;


import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
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
		
		
		@ExceptionHandler(ClientNotFoundException.class)
		public ResponseEntity<String> handleNoSuchElementException(ClientNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
}
