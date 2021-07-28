package com.bae.workouttracker.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.workouttracker.data.WorkoutTracker;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:WorkoutTracker-data.sql",
		"classpath:WorkoutTracker-schema.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class WTControllerIntegrationTest {

	@Autowired // tells Spring to inject the MockMVC object into this class
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper; // takes the class Spring uses to convert Java to JSON

	@Test
	void testCreate() throws Exception {
		WorkoutTracker testWorkout = new WorkoutTracker(2, "Monday", 2, "Weights upper body",
				"To improve upper body strength");
		String testWorkoutASJSON = this.mapper.writeValueAsString(testWorkout); // converts to JSON

		System.out.println(testWorkout);
		System.out.println(testWorkoutASJSON);

		RequestBuilder request = post("/createWorkout").contentType(MediaType.APPLICATION_JSON)
				.content(testWorkoutASJSON);
		ResultMatcher checkStatus = status().is(201);

		WorkoutTracker testCreatedWorkout = new WorkoutTracker(2, "Monday", 2, "Weights upper body",
				"To improve upper body strength");
		testCreatedWorkout.setId(2);
		String testCreatedAsJSON = this.mapper.writeValueAsString(testCreatedWorkout);

		ResultMatcher checkBody = content().json(testCreatedAsJSON);
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testGet() throws Exception {

		RequestBuilder request = get("/getWorkout/1");
		ResultMatcher checkStatus = status().is(200);

		WorkoutTracker testGetWorkout = new WorkoutTracker("Tuesday", 1, "Weights lower body",
				"to improve lower body strength");
		testGetWorkout.setId(1);
		String testCreatedAsJSON = this.mapper.writeValueAsString(testGetWorkout);

		ResultMatcher checkBody = content().json(testCreatedAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testReplace() throws Exception {

		WorkoutTracker testUpdate = new WorkoutTracker("Wednesday", 2, "Speed and agility training",
				"to improve agility");
		String testUpdateAsJSON = this.mapper.writeValueAsString(testUpdate);

		RequestBuilder request = put("/replaceWorkout/1").contentType(MediaType.APPLICATION_JSON)
				.content(testUpdateAsJSON);
		ResultMatcher checkStatus = status().is(202);

		WorkoutTracker testReplaceWorkout = new WorkoutTracker("Wednesday", 2, "Speed and agility training",
				"to improve agility");
		testReplaceWorkout.setId(1);
		String testCreateAsJSON = this.mapper.writeValueAsString(testReplaceWorkout);

		ResultMatcher checkBody = content().json(testCreateAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDelete() throws Exception {
		RequestBuilder request = delete("/deleteWorkout/1");

		ResultMatcher checkStatus = status().is(204);
		ResultMatcher checkBody = content().string("Deleted Workout: 1");

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetByDayOfWeek() throws Exception {

		ResultMatcher checkStatus = status().is(202);

		System.out.println("step 1");

		List<WorkoutTracker> testWorkouts = List
				.of(new WorkoutTracker("Monday", 1, "cardio", "To improve my endurance"));

		System.out.println("step 2");
		String testWorkoutsAsJSON = this.mapper.writeValueAsString(testWorkouts);

		System.out.println("step 3");
		ResultMatcher checkBody = content().json(testWorkoutsAsJSON);
		RequestBuilder request = get("/getByDay/Monday");

		System.out.println("step 4");
		this.mockMVC.perform(request).andExpect(checkBody).andExpect(checkStatus);

	}

	@Test
	void testGetAllWorkouts() throws Exception {
		RequestBuilder request = get("/getAllWorkouts");

		ResultMatcher checkStatus = status().is(200);

		List<WorkoutTracker> workouts = new ArrayList<>();
		WorkoutTracker testGetAll = new WorkoutTracker("Monday", 1, "cardio", "To improve my endurance");
		testGetAll.setId(1);
		workouts.add(testGetAll);
		String testCreatedAsJSON = this.mapper.writeValueAsString(workouts);

		ResultMatcher checkBody = content().json(testCreatedAsJSON);
		this.mockMVC.perform(request).andExpect(checkBody).andExpect(checkStatus);

	}
}
