package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.model.UserManager;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	UserManager UM;
	@PostMapping("/validate")
	public String validate(@RequestBody User u)
	{
		return UM.userValidate(u);
	}
	@GetMapping("/getuser")
	public String events()
	{
		return UM.getUser().toString();
	}
	@GetMapping("/getuserbyun/{un}")
	public String searchUser(@PathVariable("un") String un)
	{
		return UM.readDetails(un).toString();
	}
	// Save a new user
    @PostMapping("/save")
    public String saveUser(@RequestBody User user) {
        return UM.saveUser(user);
    }
    // Update existing user
    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        return UM.updateUser(user);
    }
 // Delete a user by username
    @DeleteMapping("/delet/{un}")
    public String deleteUser(@PathVariable("un") String username) {
        return UM.deleteUser(username);
    }
}
