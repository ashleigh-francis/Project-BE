package com.bae.workouttracker.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.workouttracker.data.WorkoutTracker;
import com.bae.workouttracker.service.WTService;

@RestController
@CrossOrigin
public class WorkoutTrackerController {

	private WTService service;

	public WorkoutTrackerController(WTService service) {
		super();
		this.service = service;

	}

	@GetMapping("/getAllWorkouts")
	public List<WorkoutTracker> getAllWorkouts() {
		return this.service.getAllWorkouts();
	}

	@GetMapping("/getByDay/{dayOfWeek}")
	public ResponseEntity<List<WorkoutTracker>> getByDayOfWeek(@PathVariable String dayOfWeek) {
		return new ResponseEntity<>(this.service.getByDayOfWeek(dayOfWeek), HttpStatus.ACCEPTED);
	}

	@PostMapping("/createWorkout")
	public ResponseEntity<WorkoutTracker> createWorkout(@RequestBody WorkoutTracker workout) {
		WorkoutTracker created = this.service.createWorkout(workout);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@GetMapping("/getWorkout/{id}")
	public ResponseEntity<WorkoutTracker> getWorkout(@PathVariable int id) {
		return new ResponseEntity<>(this.service.getWorkout(id), HttpStatus.OK);

	}

	@DeleteMapping("/deleteWorkout/{id}")
	public ResponseEntity<String> deleteWorkout(@PathVariable int id) {
		return new ResponseEntity<>(this.service.deleteWorkout(id), HttpStatus.NO_CONTENT);
	}

	@PutMapping("/replaceWorkout/{id}")
	public ResponseEntity<WorkoutTracker> replaceWorkout(@PathVariable int id, @RequestBody WorkoutTracker workout) {
		return new ResponseEntity<>(this.service.replaceWorkout(id, workout), HttpStatus.ACCEPTED);
	}
}
