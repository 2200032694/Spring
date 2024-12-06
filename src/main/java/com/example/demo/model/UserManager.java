package com.example.demo.model;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class UserManager 
{
	//Dependency Injection
	@Autowired
	UserRepository ur;
	//User Login Validation
	public String userValidate(User u)
	{
		if(ur.validate(u.username, u.password).compareTo("1")==0) 
		{
			return "Authorised User";
		} 
		else 
		{
			return "Unauthorised User";
		}
	}
	//Get all the user details
	//Load User
	public List<String> getUser()
	{
		List<String> list = new ArrayList<String>();
		for(User u : ur.findAll())
		{
			list.add(toJsonString(u));
		}
		return list;
	}
	//Read a record from User table based on username
	public List<String> readDetails(String uname)
	{
	    List<String> list = new ArrayList<>();
	    User tmp = ur.findById(uname).orElse(null);
	    if (tmp != null) {
	        list.add(toJsonString(tmp));
	    } else {
	        list.add("{\"message\": \"User not found\"}");
	    }
	    return list;
	}
	// Save or insert a new user record
    public String saveUser(User user) {
        try {
            ur.save(user);
            return "User saved successfully.";
        } catch (Exception e) {
            return "{\"message\": \"Error saving user: " + e.getMessage() + "\"}";
        }
    }

    // Update an existing user record
    public String updateUser(User user) {
        if (ur.existsById(user.username)) {
            try {
                ur.save(user); // Save will update if user exists
                return "User updated successfully.";
            } catch (Exception e) {
                return "{\"message\": \"Error updating user: " + e.getMessage() + "\"}";
            }
        } else {
            return "{\"message\": \"User not found. Cannot update.\"}";
        }
    }

    // Delete a user by username
    public String deleteUser(String username) {
        if (ur.existsById(username)) {
            try {
                ur.deleteById(username);
                return "User deleted successfully.";
            } catch (Exception e) {
                return "{\"message\": \"Error deleting user: " + e.getMessage() + "\"}";
            }
        } else {
            return "{\"message\": \"User not found. Cannot delete.\"}";
        }
    }

	
	//Method for converting Java Object to JSON String
	public String toJsonString(Object obj)
	{
		GsonBuilder builder = new GsonBuilder();
		Gson G = builder.create();
		return G.toJson(obj);
	}
}
