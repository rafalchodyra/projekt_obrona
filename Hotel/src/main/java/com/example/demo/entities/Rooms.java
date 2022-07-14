package com.example.demo.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="ROOM")
public class Rooms {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int rId;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_no;
	private String checkinDate;
	private String checkoutDate;
	private int price;
	private String category;

	public int getrId() {
		return rId;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}
	public String getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Rooms [rId=" + rId + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", phone_no=" + phone_no + ", checkinDate=" + checkinDate + ", checkoutDate=" + checkoutDate
				+ ", price=" + price  + ", category=" + category + "]";
	}
	
	
	
	
	
	
}
