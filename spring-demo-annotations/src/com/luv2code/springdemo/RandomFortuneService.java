package com.luv2code.springdemo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {

	// create an array of strings
	private String[] data = {
			"Beware of the wolf in sheep's clothing", 
			"Diligence is the mother of good luck", 
			"The journey is the reward"
			};
	
	private Random random = new Random();
	
	@Override
	public String getFrotune() {
		// Pick a random string from the array
		int index = this.random.nextInt(data.length);
		return this.data[index];
	}

}
