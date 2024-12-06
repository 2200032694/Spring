package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
@Repository
public interface UserRepository  extends JpaRepository<User, String>//String - PrimaryKey Datatype
{
	@Query("select count(u) from User u where u.username=:uname and u.password=:pwd")
	public String validate(@Param("uname") String uname, @Param("pwd") String pwd);
	/*
	@Query("select u from User u where u.username=:uname")
	public List<User> fetchAllById(@Param("uname") String uname);*/
}