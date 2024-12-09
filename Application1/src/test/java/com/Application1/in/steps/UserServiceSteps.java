package com.Application1.in.steps;

import com.Application1.in.service.UserServiceImp;
import com.Application1.in.DTO.UserDTO;
import com.Application1.in.EntityVO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceSteps {

    @Autowired
    private UserServiceImp userService;

    private UserDTO userDTO;
    private UserVO savedUser;

    @Given("I have a new user")
    public void givenIHaveANewUser() {
        // Create a new user DTO
        userDTO = new UserDTO();
        userDTO.setName("John");
        userDTO.setEmail("john.doe@example.com");
        userDTO.setPassword("jobhcsjn$%");
    }

    @When("I add the user")
    public void whenIAddTheUser() {
        // Call the UserService to add the user
        savedUser = userService.addUser(userDTO);
    }

    @Then("the user should be added successfully")
    public void thenTheUserShouldBeAddedSuccessfully() {
        // Assert that the user was added successfully
        assertNotNull(savedUser);
        assertEquals(userDTO.getName(), savedUser.getName());
        assertEquals(userDTO.getEmail(), savedUser.getEmail());
    }
}
