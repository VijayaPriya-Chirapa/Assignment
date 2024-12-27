package com.Application1.in.Thymleaf;

import com.Application1.in.BO.UserBO;
import com.Application1.in.DTO.UserDTO;
import com.Application1.in.EntityVO.UserVO;
import com.Application1.in.Exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/api/Users")
public class ThymleafController {
	
	@Autowired
	private UserBO userbo;

    private final String API_URL = "http://localhost:9090/api/users"; // Assuming your API runs on port 8080

 //login 
    @GetMapping("/login")
    public String loginUser() {
        return "loginPage";  // Redirect to dashboard after login
    }
    
    @PostMapping("/loginUser")
    public String loginUser(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password, Model model) {
        String message = userbo.loginUser(email, password); // Get the message from the service layer
        model.addAttribute("message", message); // Pass the message to the Thymeleaf template
        return "loginSuccess"; // Return the name of the view (Thymeleaf template)
    }
    
    
 //dashboard of all 
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";  // Redirect to dashboard after login
    }
    
    
 //Adding user
    @GetMapping("/addUser")
    public String showAddUser(Model model) {
        model.addAttribute("userDTO", new UserDTO());  // Empty DTO for the form
        return "addUser";  // Returns addUser.html
    }
    
    @PostMapping("/addU")
    public String addUser(@ModelAttribute("userDTO") UserDTO userDTO, Model model) {
        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Send POST request to the external API to add the user
            ResponseEntity<UserVO> response = restTemplate.postForEntity(API_URL + "/add", userDTO, UserVO.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("successMessage", "User added successfully!");
            } else {
                model.addAttribute("errorMessage", "Failed to add user.");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while adding the user: " + e.getMessage());
        }

        return "Success";  // Return to the same addUser.html page
    }

    
    
//get all users
    @GetMapping("/getAllUsers")
    public String showGetAllUsers(Model model) {
        // Fetch all users from the API
        RestTemplate restTemplate = new RestTemplate();
        List<UserVO> userList = restTemplate.getForObject(API_URL + "/all", List.class);
        model.addAttribute("users", userList);  // Add users to the model
        return "getAllUsers";  // Returns getAllUsers.html
    }
    
    
    
    
 //get user by id   
    @GetMapping("/getUserByIdForm")
    public String showGetUserById() {
        return "getUserByIdForm";  // name of the HTML template for login
    }

    @GetMapping("/getUserByIdDetails")
    public String showGetUserById(@RequestParam("id") Long id, Model model) {
       try 
       { // Fetch a user by ID from the API
        RestTemplate restTemplate = new RestTemplate();
        UserVO user = restTemplate.getForObject(API_URL + "/id/" + id, UserVO.class);
        model.addAttribute("user", user);  // Add user to the model
        return "getUserByIdDetails";}  // Returns getUserById.html
       
       catch(UserNotFoundException e) {
    	   model.addAttribute("error",e.getMessage());
    	   return "getUserByIdDetails";}
       }
    
}
