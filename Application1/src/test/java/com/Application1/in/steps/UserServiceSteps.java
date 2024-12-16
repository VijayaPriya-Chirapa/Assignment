package com.Application1.in.steps;

import com.Application1.in.DTO.UserDTO;
import com.Application1.in.EntityVO.UserVO;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

public class UserServiceSteps {

    private RestTemplate restTemplate = new RestTemplate();
    private final static String BASE_URL = "http://localhost:9090/api/users"; // The base URL of your app
    private ResponseEntity<UserVO> response;
    private List<UserVO> usersList;

    private static final String ADD_USER_URL = BASE_URL + "/add";
    private static final String GET_ALL_USERS_URL = BASE_URL + "/all";
    private static final String GET_USER_BY_ID_URL = BASE_URL + "/id/";

    @Given("I have a new user with name {string}, email {string}, and password {string}")
    public void givenIHaveANewUser(String name, String email, String password) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);
        response = restTemplate.exchange(ADD_USER_URL, HttpMethod.POST, request, UserVO.class);
    }

    @When("I add the user")
    public void whenIAddTheUser() {
        // The actual user addition is already being handled in the @Given step via the RestTemplate
    }

    @Then("the user should be added successfully with name {string} and email {string}")
    public void thenTheUserShouldBeAddedSuccessfully(String name, String email) {
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        UserVO savedUser = response.getBody();
        assertNotNull(savedUser);
        assertEquals(name, savedUser.getName());
        assertEquals(email, savedUser.getEmail());
    }
    
    
    

    @Given("a user exists with ID {long}")
    public void givenAUserExistsWithID(Long id) {
        response = restTemplate.getForEntity(GET_USER_BY_ID_URL + id, UserVO.class);
    }

    @When("I retrieve the user by ID")
    public void whenIRetrieveTheUserByID() {
        // The actual retrieval logic happens in the @Given step using GET request
    }

    @Then("I should get a user with name {string} and email {string}")
    public void thenIShouldGetAUserWithNameAndEmail(String name, String email) {
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        UserVO retrievedUser = response.getBody();
        assertNotNull(retrievedUser);
        assertEquals(name, retrievedUser.getName());
        assertEquals(email, retrievedUser.getEmail());
    }
    
    
    

    @Given("there are users in the system name {string}, email {string}, and password {string}")
    public void there_are_users_in_the_system_name_email_and_password(String name, String email, String password) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);
        restTemplate.exchange(ADD_USER_URL, HttpMethod.POST, request, UserVO.class);
    }

    @When("I retrieve the list of users")
    public void i_retrieve_the_list_of_users() {
        // Use ParameterizedTypeReference to deserialize the response as a list of UserVO
        ResponseEntity<List<UserVO>> responseEntity = restTemplate.exchange(
                GET_ALL_USERS_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserVO>>() {}
        );
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            usersList = responseEntity.getBody();
        }
    }

    @Then("I should get a list of users with name {string} and email {string}")
    public void i_should_get_a_list_of_users_with_name_and_email(String name, String email) {
        assertTrue(usersList.stream().anyMatch(user -> user.getName().equals(name) && user.getEmail().equals(email)));
    }
}

