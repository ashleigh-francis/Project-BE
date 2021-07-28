package com.bae.workouttracker.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WTRepo extends JpaRepository<WorkoutTracker, Integer> {

	List<WorkoutTracker> findByDayOfWeekIgnoreCase(String dayOfWeek);
}
