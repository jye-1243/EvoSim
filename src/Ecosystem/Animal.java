package Ecosystem;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

public class Animal {

	//fields

	protected int health, size, speed, lifespan, hunger, thirst;
	public Image appearance = null;

	protected boolean controlled;

	protected String gender, imageName;

	protected ArrayList<Disease> disease;
	public ArrayList<String> moveList;
	
	protected static int maxStat = 100;
	
	// methods

	// constructor
	public Animal (String imageName, int size, int speed, int lifespan, String gender) {
		this.health = maxStat;
		this.hunger = maxStat;
		this.thirst = maxStat;
		this.imageName = imageName;
		this.size = size;
		this.speed = speed;
		this.lifespan = lifespan;
		
		this.gender = gender;

		try {
			appearance = ImageIO.read(new File(imageName));
		}
		catch (Exception ex) {}
		
		disease = new ArrayList<Disease>();
		moveList = new ArrayList<String>();
	}
	
	public Animal(Animal animal)
	{
		this.health = maxStat;
		this.hunger = maxStat;
		this.thirst = maxStat;
		this.imageName = animal.imageName;
		this.size = animal.size;
		this.speed = animal.speed;
		this.lifespan = animal.lifespan;
		
		this.gender = animal.gender;

		try {
			appearance = ImageIO.read(new File(imageName));
		}
		catch (Exception ex) {}
		
		disease = animal.disease;
		moveList = animal.moveList;
	}
	
	public void updateImage(String imageName) {
		try {
			appearance = ImageIO.read(new File(imageName));
		}
		catch (Exception ex) {}
	}

	protected void drink(){
		thirst += Math.random() * 5 + 8;
		thirst = Math.min(thirst, maxStat);
	}

	public void mate(Animal mate){
		if (mate.gender() != this.gender) {
			return;
		}
	}

	public Animal birth() {
		return null;
		
	}

	public void flee (Animal predator) {
		
	}

	public void seek (Animal animal){
		
	}
	
	public void injured (Integer damage) {
		health -= damage;
	}
	
	public void updateAppetite () {
		if (Math.random() < .5)
		{
			thirst -= 3;
			hunger -= 2;
		}
		
		if (thirst < 35)
		{
			health -= 5;
		}
		
		if (hunger < 35)
		{
			health -= 5;
		}
		
		System.out.println(hunger + ", " + thirst + ", " + health);
	}
	
	// accessors
	public int health() {
		return this.health;
	}
	
	public int size() {
		return this.size;
	}

	public int speed() {
		return this.health;
	}

	public String gender() {
		return this.gender;
	}


}

interface Herbivore {
	public void feed (String food);
}

interface Carnivore {
	public void feed (Animal prey);
	public void chase (Animal prey);
}
