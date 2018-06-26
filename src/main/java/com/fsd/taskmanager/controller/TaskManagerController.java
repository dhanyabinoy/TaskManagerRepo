package com.fsd.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fsd.taskmanager.model.TaskDetails;
import com.fsd.taskmanager.service.TaskMangerService;

@RestController
@CrossOrigin("*")
public class TaskManagerController {
	
	@Autowired
	TaskMangerService taskManagerService;
	
	@RequestMapping(value="/saveTaskDetails", method=RequestMethod.POST)
	public ResponseEntity<Void> saveTaskDetails(@RequestBody TaskDetails taskDetails, UriComponentsBuilder builder) {
		taskManagerService.addTask(taskDetails);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/?id={id}").buildAndExpand(taskDetails.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/getTaskDetails")
	public ResponseEntity<List<TaskDetails>> getAllWorkOuts() {
		List<TaskDetails> taskDetails = taskManagerService.getAllTasks();
		return new ResponseEntity<List<TaskDetails>>(taskDetails, HttpStatus.OK);
	}
	
	@PutMapping(value="/updateTaskDetails")
	public ResponseEntity<TaskDetails> updateTaskDetails(@RequestBody TaskDetails workOut) {
		taskManagerService.updateTaskDetails(workOut);
		return new ResponseEntity<TaskDetails>(workOut, HttpStatus.OK);
	}
	


}
