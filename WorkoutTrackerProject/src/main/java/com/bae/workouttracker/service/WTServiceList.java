package com.bae.workouttracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.workouttracker.data.WorkoutTracker;

@Service
public class WTServiceList implements WTService {

	private List<WorkoutTracker> workouts = new ArrayList<>();

	@Override
	public WorkoutTracker createWorkout(WorkoutTracker workout) { // C
		System.out.println(workout);
		return this.workouts.get(this.workouts.size() - 1);
	}

	@Override
	public WorkoutTracker getWorkout(int id) { // R
		WorkoutTracker found = this.workouts.get(id);
		return found;
	}

	@Override
	public WorkoutTracker replaceWorkout(int id, WorkoutTracker newWorkout) { // U
		return this.workouts.set(id, newWorkout);
	}

	@Override
	public String deleteWorkout(int id) { // D
		this.workouts.remove(id);

		return "Deleted workout at index " + id;
	}

	@Override
	public List<WorkoutTracker> getAllWorkouts() {
		return this.workouts;
	}

	@Override
	public List<WorkoutTracker> getByDayOfWeek(String dayOfWeek) {

		return null;
	}

}
