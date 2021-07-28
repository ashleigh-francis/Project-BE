package com.bae.workouttracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bae.workouttracker.data.WTRepo;
import com.bae.workouttracker.data.WorkoutTracker;

@Service
@Primary
public class WTServiceDB implements WTService {

	private WTRepo repo;

	public WTServiceDB(WTRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public WorkoutTracker createWorkout(WorkoutTracker workout) { // C

		WorkoutTracker created = this.repo.save(workout);

		System.out.println("Workout created: " + created);
		return this.repo.save(workout);

	}

	@Override
	public WorkoutTracker getWorkout(int id) { // R
		Optional<WorkoutTracker> got = this.repo.findById(id);
		System.out.println("Got Workout: " + got);
		return this.repo.findById(id).get();
	}

	@Override
	public WorkoutTracker replaceWorkout(int id, WorkoutTracker newWorkout) { // U
		// 1. pull the existing record out
		WorkoutTracker found = this.repo.findById(id).get();

		System.out.println("Found: " + found);
		// 2. modify the record
		found.setDayOfWeek(newWorkout.getDayOfWeek());
		found.setHoursOfExercise(newWorkout.getHoursOfExercise());
		found.setTypeOfExercise(newWorkout.getTypeOfExercise());
		found.setGoal(newWorkout.getGoal());

		System.out.println("Found After Update: " + found);

		// 3. save it back to overwrite the record

		WorkoutTracker updated = this.repo.save(found);

		System.out.println("Updated: " + updated);
		return updated;
	}

	@Override
	public String deleteWorkout(int id) { // D
		this.repo.deleteById(id);

		if (this.repo.existsById(id)) {
			return "Not Deleted: " + id;
		} else {
			return "Deleted: " + id;
		}

	}

	@Override
	public List<WorkoutTracker> getAllWorkouts() {
		return this.repo.findAll();
	}

	@Override
	public List<WorkoutTracker> getByDayOfWeek(String dayOfWeek) {
		return this.repo.findByDayOfWeekIgnoreCase(dayOfWeek);
	}

}
