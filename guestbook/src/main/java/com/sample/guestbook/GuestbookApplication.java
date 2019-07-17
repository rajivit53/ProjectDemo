package com.sample.guestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class to bootstrap and launch a Spring application from a Java main method
 */
@SpringBootApplication
public class GuestbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestbookApplication.class, args);
	}

}
