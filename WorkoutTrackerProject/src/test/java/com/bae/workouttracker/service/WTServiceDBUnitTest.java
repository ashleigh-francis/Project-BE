package com.bae.workouttracker.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.bae.workouttracker.data.WTRepo;
import com.bae.workouttracker.data.WorkoutTracker;

@SpringBootTest
@ActiveProfiles("test")
public class WTServiceDBUnitTest {

	@Autowired // wiring links a bean to something that uses it - in this case auto wiring is
				// injecting the service for us

	private WTServiceDB service;

	@MockBean // Spring will make a new decoy repo that we can use to put in a fake return so
				// that we can test

	private WTRepo Repo;

	@Test
	void testCreate() {
		int id = 1;
		WorkoutTracker testWorkout = new WorkoutTracker(id, "Monday", 3, "Yoga",
				"To rest and recover and improve posture");

		Mockito.when(
				this.Repo.save(new WorkoutTracker(id, "Monday", 3, "Yoga", "To rest and recover and improve posture")))
				.thenReturn(testWorkout);

		WorkoutTracker actual = this.service.createWorkout(testWorkout);

		assertThat(actual).isEqualTo(testWorkout);

		Mockito.verify(this.Repo, Mockito.times(2))
				.save(new WorkoutTracker(id, "Monday", 3, "Yoga", "To rest and recover and improve posture"));
	}

	@Test
	void testGet() {
		int id = 1;
		WorkoutTracker testWorkout = new WorkoutTracker(id, "Tuesday", 1, "Upper body",
				"To strengthen upper body and core");
		Mockito.when(this.Repo.findById(id)).thenReturn(Optional.of(testWorkout));

		WorkoutTracker actual = this.service.getWorkout(id);

		assertThat(actual).isEqualTo(testWorkout);
	}

	@Test
	void testUpdate() {
		int id = 1;
		WorkoutTracker testWorkout = new WorkoutTracker(id, "Wednesday", 3, "Cardio", "To improve endurance");

		WorkoutTracker testNewWorkout = new WorkoutTracker(id, "Thursday", 3, "Lower body", "To strengthen lower body");

		Mockito.when(this.Repo.findById(id)).thenReturn(Optional.of(testWorkout));
		Mockito.when(this.Repo.save(new WorkoutTracker(id, "Thursday", 3, "Lower body", "To strengthen lower body")))
				.thenReturn(testNewWorkout);

		WorkoutTracker actual = this.service.replaceWorkout(id, testNewWorkout);

		assertThat(actual).isEqualTo(testNewWorkout);

		Mockito.verify(this.Repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.Repo, Mockito.times(1))
				.save(new WorkoutTracker(id, "Thursday", 3, "Lower body", "To strengthen lower body"));

	}

	@Test
	void testDelete() {
		int id = 1;
		assertThat(this.service.deleteWorkout(id)).isEqualTo("Deleted: " + id);
	}

	@Test
	void testGetByDayOfWeek() {
		int id = 1;
		WorkoutTracker testWorkout = new WorkoutTracker(id, "Friday", 2, "Core", "To strengthen abdominal muscles");
		WorkoutTracker testWorkout1 = new WorkoutTracker(id, "Saturday", 1, "Swimming",
				"To improve endurance and vary cardio");

		List<WorkoutTracker> workouts = new ArrayList<>();
		workouts.add(testWorkout);
		workouts.add(testWorkout1);

		Mockito.when(this.Repo.findByDayOfWeekIgnoreCase("Friday")).thenReturn(workouts);

		List<WorkoutTracker> actual = this.service.getByDayOfWeek(testWorkout.getDayOfWeek());
		assertThat(actual).isEqualTo(workouts);

	}

	@Test
	void testGetAllWorkouts() {
		List<WorkoutTracker> testWorkouts = List
				.of(new WorkoutTracker(1, "Sunday", 1, "Pilates", "To improve balance"));

		Mockito.when(this.Repo.findAll()).thenReturn(testWorkouts);

		assertThat(this.service.getAllWorkouts()).isEqualTo(testWorkouts);

		Mockito.verify(this.Repo, Mockito.times(1)).findAll();
	}

}
