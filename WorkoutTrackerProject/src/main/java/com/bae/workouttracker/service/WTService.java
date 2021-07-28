package com.bae.workouttracker.service;

import java.util.List;

import com.bae.workouttracker.data.WorkoutTracker;

public interface WTService {

	public WorkoutTracker createWorkout(WorkoutTracker workout);

	public List<WorkoutTracker> getAllWorkouts();

	public WorkoutTracker getWorkout(int id);

	public WorkoutTracker replaceWorkout(int id, WorkoutTracker newWorkout);

	public String deleteWorkout(int id);

	public List<WorkoutTracker> getByDayOfWeek(String dayOfWeek);
}
