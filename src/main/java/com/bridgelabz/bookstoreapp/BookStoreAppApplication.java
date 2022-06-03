package com.bridgelabz.bookstoreapp;

import com.bridgelabz.bookstoreapp.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class BookStoreAppApplication {
	@Autowired
	private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(BookStoreAppApplication.class, args);
		System.out.println("Welcome to AddressBook App");
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void sendMail(){
//		senderService.sendEmail("kumbharshardul384@gmail.com",
//				"This is Subject",
//				"This is body of the email lets see");
//	}

}
