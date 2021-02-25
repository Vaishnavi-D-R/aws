package com.mindtree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.entity.PlayerEntity;
import com.mindtree.entity.TeamEntity;
import com.mindtree.exception.serviceException.ServiceException;
import com.mindtree.service.TeamsService;

@RestController
@RequestMapping(path="/team")
public class TeamsController {
	
	@Autowired
	public TeamsService service;
	
	
	@PostMapping("/addteam")
	public ResponseEntity<?> addTeam(@RequestBody TeamEntity team) {
		String string;
		try {
			service.addTeams(team);
			return new ResponseEntity<>("succesfully added team \n" , HttpStatus.CREATED);
		} catch (ServiceException e) {
			System.out.println("Something went wrong" + e);
			string = e + "error";
		}

		return new ResponseEntity<>(string, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/addplayer/{id}")
	public ResponseEntity<?> addPlayer(@RequestBody PlayerEntity player,@PathVariable("id") int id) {
		String string;
		try {
			service.addPlayers(player,id);
			return new ResponseEntity<>("succesfully added Player\n" , HttpStatus.CREATED);
		} catch (ServiceException e) {
			System.out.println("Controller error" + e);
			string = e + "error";
		}

		return new ResponseEntity<>(string, HttpStatus.BAD_REQUEST);
	}

	
	@GetMapping("/getTeam/{name}")
	public ResponseEntity<?> getByTrack(@PathVariable("name") String name) {
		String string;
		try {
		TeamEntity details = service.getByName(name);
			return new ResponseEntity<>(details, HttpStatus.OK);
		} catch (ServiceException e) {
			string = e + "error";
		}

		return new ResponseEntity<>(string, HttpStatus.BAD_REQUEST);
	}
	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
		String string;
		try {
			String details = service.deletePlayer(id);
			return new ResponseEntity<>(details, HttpStatus.OK);
		} catch (ServiceException e) {
			string = e + "error";
		}
		return new ResponseEntity<>(string, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/tupdate/{id}")
	public ResponseEntity<?> updateName(@PathVariable("id") int id, @RequestBody String location) {
		String string;
		try {
			String details = service.updateLocation( id,location);
			return new ResponseEntity<>(details, HttpStatus.OK);
		} catch (ServiceException e) {
			string = e + "error";
		}

		return new ResponseEntity<>(string, HttpStatus.BAD_REQUEST);
	}
}
