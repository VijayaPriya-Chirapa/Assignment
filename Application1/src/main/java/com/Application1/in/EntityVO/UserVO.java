package com.Application1.in.EntityVO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="user_table")
public class UserVO {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // This will auto-generate the ID
	    private Long id;

	    @NotBlank(message = "Name is mandatory")
	    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	    private String name;

	    @NotBlank(message = "Email is mandatory")
	    @Email(message = "Email should be valid")
	    private String email;

	    @NotBlank(message = "Password is mandatory")
	    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
	    private String password;
	    

    public UserVO() {}

    public UserVO(long id,String name, String email, String password) {
    	this.id=id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
