package com.workshop.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.Entity.Booking;
import com.workshop.Repo.BookingRepo;

import jakarta.transaction.Transactional;

@Service
public class BookingService {
	
	@Autowired
	BookingRepo repo;
	
	
	public void saveBooking(Booking booking ) {
		repo.save(booking);
	}
	
	 public String getLastUsedBookingId() {
	        // Fetch the maximum booking ID from the database
	        String lastUsedId = repo.findMaxBookingId();
	        
	        // If lastUsedId is null, return 0
	        if (lastUsedId == null) {
	            return "";
	        }
	        
	        return lastUsedId;
	    }
	 
	 public List<Booking> getBooking(String userid){
		 return repo.findByUserId(userid);
	 }
	 
	 public List<Booking> getAll(){
		 return repo.findAll();
	 }
	 
	 public Booking findBookingbyId(int id) {
		 return repo.findById(id).get();
		 
	 }

	 public List<Booking> getAllBookings() {
        return repo.findAllByOrderByIdDesc();
    }

	 
	 public void deleteBooking(Booking booking) {
		 repo.delete(booking);
	 }

	//  public void deleteBooking1(int id) {
	// 	repo.delete(id);
		
	// }
      
    @Transactional
		public String deleteBookingByBookingId(String bookingId) {
        // Check if the booking exists before attempting to delete
		//System.out.println(bookingId);
        if (repo.existsByBookingId(bookingId)) {
            repo.deleteByBookingId(bookingId); // Use the custom delete method
            return "Booking with ID " + bookingId + " has been deleted successfully.";
        } else {
            return "Booking with ID " + bookingId + " not found.";
        }
    }


}
