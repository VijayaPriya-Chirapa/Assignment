package com.Application1.in.gatling;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class UserControllerGatling extends Simulation {

    private static final String BASE_URL = "http://localhost:9090/api/users"; // Modify according to your setup

    // Define the HTTP protocol
    private final HttpProtocolBuilder httpProtocol = http.baseUrl(BASE_URL)
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    // Define the scenario for adding a user, retrieving all users, etc.
    private final ScenarioBuilder userScenario = scenario("User Controller Scenario")
            // Add a new user
            .exec(http("Add New User")
                    .post("/add")
                    .body(StringBody("{\"name\": \"John Doe\", \"email\": \"johndoe@example.com\", \"password\": \"password123\"}"))
                    .check(status().is(201))
                    .check(status().saveAs("addUserStatus")) // Save the status code of the add user request
            )
            .exec(session -> {
                int addUserStatus = session.getInt("addUserStatus");
                System.out.println("Add User Request Status: " + addUserStatus); // Log the status of adding user
                return session;
            })
            // Get all users
            .exec(http("Get All Users")
                    .get("/all")
                    .check(status().is(200))
                    .check(status().saveAs("getAllStatus")) // Save status code of the Get All request
            )
            .exec(session -> {
                int getAllStatus = session.getInt("getAllStatus");
                System.out.println("Get All Users Status: " + getAllStatus); // Log the status of Get All Users request
                return session;
            })
            // Get user by ID
            .exec(http("Get User by ID")
                    .get("/id/1") // Adjust user ID to match an existing user
                    .check(status().is(200))
                    .check(status().saveAs("getUserByIdStatus")) // Save status code of Get User request
            )
            .exec(session -> {
                int getUserByIdStatus = session.getInt("getUserByIdStatus");
                System.out.println("Get User by ID Status: " + getUserByIdStatus); // Log the status of Get User by ID request
                return session;
            })
            // Health check for valid ID
            .exec(http("Health Check for Valid ID")
                    .get("/health/1") // Use an existing user ID
                    .check(status().is(200))
                    .check(status().saveAs("healthCheckValidStatus")) // Save status code of health check request
            )
            .exec(session -> {
                int healthCheckValidStatus = session.getInt("healthCheckValidStatus");
                System.out.println("Health Check for Valid ID Status: " + healthCheckValidStatus); // Log the health check status
                return session;
            })
            // Health check for invalid ID
            .exec(http("Health Check for Invalid ID")
                    .get("/health/999999") // Use a non-existent user ID to trigger failure
                    .check(status().saveAs("healthCheckInvalidStatus")) // Save status code of invalid health check
            )
            .exec(session -> {
                int healthCheckInvalidStatus = session.getInt("healthCheckInvalidStatus");
                System.out.println("Health Check for Invalid ID Status: " + healthCheckInvalidStatus); // Log the health check invalid status
                return session;
            });

    // Setup the simulation to run
    {
        setUp(
                userScenario.injectOpen(atOnceUsers(10)) // Inject 10 users at once; adjust as needed
        )
        .protocols(httpProtocol);
    }
}
