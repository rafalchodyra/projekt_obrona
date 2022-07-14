package com.example.demo.controller;



import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dao.RoomRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Rooms;
import com.example.demo.entities.User;
import com.example.demo.helper.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
	
@Autowired
private BCryptPasswordEncoder passwordEncoder;
	
@Autowired
private UserRepository userRepository;	
	
@Autowired
private RoomRepository roomRepository;	


@RequestMapping(value={"/","home"},method=RequestMethod.POST)
public String start() {
	return "hotel";
}

@RequestMapping(value= {"/signup"})
public String signup(Model model) {

	System.out.println("signup");
	model.addAttribute("user",new User());
	return "signup";
}

@RequestMapping(value= {"/hotel"},method=RequestMethod.GET)
public String hotel() {
	System.out.println("hotel");
	return "hotel";
}


@RequestMapping(value={"/room_reservation"})
public String room_reservation(Model model){
	System.out.println("room_reservation");
	model.addAttribute("rooms",new Rooms());
	return "room_reservation";

}

@RequestMapping(value={"/restaurant"})
public String restaurant(){
	return "restaurant";
}



	
	


//@RequestMapping(value={"/logout"})
//public String logout(){
//	return "logout";
//}

@RequestMapping(value={"/admin"},method=RequestMethod.GET)
public String admin(Model model){

	System.out.println("admin");
	List<User> listAdmins=userRepository.getUserByRole("ROLE_ADMIN");
	List<User> listUsers=userRepository.getUserByRole("ROLE_USER");
	 model.addAttribute("listAdmins",listAdmins);
	model.addAttribute("listUsers", listUsers);
	return "admin";
}

@RequestMapping(value= {"/do_register"},method=RequestMethod.POST)
public String registerUser(@ModelAttribute("user") User user,Model model,HttpSession session) {

	if(this.userRepository.getUserByUserName(user.getName())!=null)
	{
		model.addAttribute("nameExist", true);
		return "signup";
	}
	if(this.userRepository.getUserByEmail(user.getEmail())!=null)
	{
		model.addAttribute("emailExist", true);
		return "signup";
	}

	user.setPassword(passwordEncoder.encode(user.getPassword()));
	user.setPassword(user.getPassword());
	System.out.println("USER "+user);
	
	User result= this.userRepository.save(user);
	
	model.addAttribute("user", new User());
	session.setAttribute("message", new Message("Successfully Registered","alert-success"));
	return "signup";
}

@RequestMapping(value= {"/process_reservation"},method=RequestMethod.POST)
public String reserveRoom(@ModelAttribute("rooms") Rooms rooms,Model model,HttpSession session) {

	System.out.println(rooms.getCheckinDate());
	System.out.println(rooms.getCheckoutDate());
	if(rooms.getCheckoutDate().equals(rooms.getCheckoutDate()))
	{
		model.addAttribute("toSmallBooked", true);
		return "room_reservation";
	}
	List<Rooms> roomReservations=this.roomRepository.getRooms();
	for(int i=0;i<roomReservations.size();i+=1)
	{
		Rooms room_tmp=roomReservations.get(i);
		if(room_tmp.getCategory()==rooms.getCategory())
		{

		}

	}
	if(rooms.getCategory().equals("Apartament")) {
		rooms.setPrice(600);
	}
	else if(rooms.getCategory().equals("Apartament Classic")) {
		rooms.setPrice(650);
	}
	else if(rooms.getCategory().equals("Apartament Premium")) {
		rooms.setPrice(680);
	}else {
		rooms.setPrice(0);
	}

	Rooms reservation=this.roomRepository.save(rooms);
	  
	
	model.addAttribute("rooms", new Rooms());
	session.setAttribute("message", new Message("Successfully Registered","alert-success"));
	return "room_reservation";
}


//handler for deleting user data from admin login
@RequestMapping(value= {"/admin/delete/{cid}"})
public String deleteUser(@PathVariable("cid")Integer cId,Model model,HttpSession session) {

	Optional<User> userOptional=this.userRepository.findById(cId);
	User user=userOptional.get();
	
	this.userRepository.delete(user);
	
	session.setAttribute("message", new Message("Contact deleted Successfully...","success"));
	
	return "redirect:/admin";
}

//handler for opening update form for admin
@RequestMapping(value= {"/admin/update/{cid}"})
public String updateUser(@PathVariable("cid")Integer cId,Model model,HttpSession session) {

	Optional<User> userOptional=this.userRepository.findById(cId);
	User user=userOptional.get();

	model.addAttribute("user", user);
	model.addAttribute("userRooms",user.getRooms());
	return "admin_userinfo";
}

//handler for processing update form using admin login
@RequestMapping(value= {"/admin/do_update"},method = RequestMethod.POST)
public String processUpdate(@ModelAttribute User user,HttpSession session) {


	this.userRepository.save(user);
	
	session.setAttribute("message", new Message("Contact updated Successfully...","success"));
	return "admin_userinfo";
}

//handler for billing page
@RequestMapping(value= {"/billing"})
public String userBillinginfo(@ModelAttribute("rooms") Rooms rooms,Model model,HttpSession session,Principal principal) {

	List<Rooms> roomReservations=this.roomRepository.getRooms();
	model.addAttribute("userRooms", roomReservations);
	return "billing";
}
	@RequestMapping(value= {"/billing/delete/{cid}"})
	public String deleteReservation(@PathVariable("cid")Integer cId,Model model,HttpSession session) {

		Optional<Rooms> rooms=this.roomRepository.findById(cId);
		Rooms room=rooms.get();

		this.roomRepository.delete(room);

		session.setAttribute("message", new Message("Room deleted Successfully...","success"));

		return "redirect:/billing";
	}


}
  
