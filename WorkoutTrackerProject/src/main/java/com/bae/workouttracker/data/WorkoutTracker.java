package com.bae.workouttracker.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // tells Spring this class will represent a table in the DB
public class WorkoutTracker {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((dayOfWeek == null) ? 0 : dayOfWeek.hashCode());
		result = prime * result + hoursOfExercise;
		result = prime * result + ((typeOfExercise == null) ? 0 : typeOfExercise.hashCode());
		result = prime * result + ((goal == null) ? 0 : goal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		WorkoutTracker other = (WorkoutTracker) obj;
		if (hoursOfExercise != other.hoursOfExercise)
			return false;
		if (dayOfWeek == null) {
			if (other.dayOfWeek != null)
				return false;
		} else if (!dayOfWeek.equals(other.dayOfWeek))
			return false;
		if (goal == null) {
			if (other.goal != null)
				return false;
		} else if (!goal.equals(other.goal))
			return false;
		if (id != other.id)
			return false;
		if (typeOfExercise == null) {
			if (other.typeOfExercise != null)
				return false;
		} else if (!typeOfExercise.equals(other.typeOfExercise))
			return false;
		return true;
	}

	public WorkoutTracker() { // Empty constructor

	}

	@Id // tells Spring that this is the primary key ID column
	@GeneratedValue(strategy = GenerationType.IDENTITY) // tells Spring to auto increment each ID
	private int id;

	private String dayOfWeek;

	private int hoursOfExercise;

	private String typeOfExercise;

	private String goal;

	public WorkoutTracker(int id, String dayOfWeek, int hoursOfExercise, String typeOfExercise, String goal) {
		super();
		this.id = id;
		this.dayOfWeek = dayOfWeek;
		this.hoursOfExercise = hoursOfExercise;
		this.typeOfExercise = typeOfExercise;
		this.goal = goal;
	}

	public WorkoutTracker(String dayOfWeek, int hoursOfExercise, String typeOfExercise, String goal) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.hoursOfExercise = hoursOfExercise;
		this.typeOfExercise = typeOfExercise;
		this.goal = goal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getHoursOfExercise() {
		return hoursOfExercise;
	}

	public void setHoursOfExercise(int hoursOfExercise) {
		this.hoursOfExercise = hoursOfExercise;
	}

	public String getTypeOfExercise() {
		return typeOfExercise;
	}

	public void setTypeOfExercise(String typeOfExercise) {
		this.typeOfExercise = typeOfExercise;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	@Override
	public String toString() {
		return "WorkoutTracker [dayOfWeek=" + dayOfWeek + ", hoursOfExercise=" + hoursOfExercise + ", typeOfExercise="
				+ typeOfExercise + ", goal=" + goal + "]";
	}

}
