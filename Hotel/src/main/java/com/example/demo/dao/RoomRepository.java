package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Rooms;

public interface RoomRepository extends JpaRepository<Rooms, Integer>{
	@Query("select u from Rooms u")
	List<Rooms> getRooms();
}
