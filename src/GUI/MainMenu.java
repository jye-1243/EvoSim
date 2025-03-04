package GUI;

import java.util.*;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.event.*;

/**
 * Defines a screen for the main menu of the game. 
 * <p>
 * The menu includes a large title and four button options for the game aspects on top of a nature background.
 */
public class MainMenu extends JLayeredPane implements MouseListener, ActionListener, KeyListener {

	/**
	 * Game button on the main menu that allow users to click different options.
	 */
	public gameBtn gameMode, simMode, howTo, creds, exit;
	private int btnChoice = -1;
	private int selectedBtn = -1;
	private int w, h;
	private Image bg = null, title = null, instructions = null, credits = null;
	private JLabel howToLbl, credsLbl;
	private JPanel popup;
	
	/**
	 * Constructs a screen with a nature background, title, and buttons centered on the screen.
	 * The buttons are interactive and lead to the other aspects of the game display that are unified in the Display class upon pressing.
	 * 
	 * @param width Size of the menu screen
	 */
	public MainMenu(int width) {
		//set size
		w = width;
		h = w * 5 / 6;
		setSize(w, h);
		
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
		
		//load in images
		try
        {
        	bg = ImageIO.read(new File("Summative Graphics\\MainMenu\\bg.png"));
        	title = ImageIO.read(new File("Summative Graphics\\MainMenu\\title.png"));
        	instructions = ImageIO.read(new File("Summative Graphics\\MainMenu\\howTo.png"));
        	credits = ImageIO.read(new File("Summative Graphics\\MainMenu\\creds.png"));
        }
        catch (IOException e)
        {
        }
		
		//scale down the loaded images to match the window
		bg = bg.getScaledInstance(this.getSize().width, this.getSize().height, Image.SCALE_DEFAULT);
		title = title.getScaledInstance(3*this.getSize().width/4, 2*this.getSize().height/3, Image.SCALE_DEFAULT);
		instructions = instructions.getScaledInstance(15*this.getSize().width/16, 15*this.getSize().height/16-11, Image.SCALE_DEFAULT);
		credits = credits.getScaledInstance(15*this.getSize().width/16, 15*this.getSize().height/16-11, Image.SCALE_DEFAULT);
		
		ImageIcon background = new ImageIcon(bg);
		ImageIcon finalTitle = new ImageIcon(title);
		ImageIcon finalHowTo = new ImageIcon(instructions);
		ImageIcon finalCreds = new ImageIcon(credits);
		
		//create labels for each of the images loaded --> each button
		JLabel pic = new JLabel(background);	 
		JLabel ttl = new JLabel(finalTitle);
		howToLbl = new JLabel(finalHowTo);
		credsLbl = new JLabel(finalCreds);
				
		//create buttons
		gameMode = new gameBtn("Summative Graphics\\MainMenu\\gameModeBtn.png",this.getSize().width/4, this.getSize().height/7);
		simMode = new gameBtn("Summative Graphics\\MainMenu\\simModeBtn.png",this.getSize().width/4, this.getSize().height/7);
		creds = new gameBtn("Summative Graphics\\MainMenu\\credits.png",this.getSize().width/4, this.getSize().height/7);
		howTo = new gameBtn("Summative Graphics\\MainMenu\\howToPlayBtn.png",this.getSize().width/4, this.getSize().height/7);
		exit = new gameBtn ("Summative Graphics\\exitBtn.png",this.getSize().height/7, this.getSize().height/7);
		
		//add actionListeners to elements
		simMode.addActionListener(this);
		gameMode.addActionListener(this);
		howTo.addActionListener(this);
		creds.addActionListener(this);
		exit.addActionListener(this);
		
		//establish appropriate JPanels and add in appropriate elements to them --> for the title and popup elements
		JPanel bg = new JPanel();
		JPanel ttlPanel = new JPanel();
		JPanel popup = new JPanel();
		ttlPanel.add(ttl);
		bg.add(pic);
		popup.add(howToLbl);
		popup.add(credsLbl);
		howToLbl.setVisible(false);
		credsLbl.setVisible(false);
		exit.setVisible(false);
		
		//set sizes for each panel
		bg.setBounds(0, 0, getSize().width, getSize().height);
		ttlPanel.setBounds(1, 1, getSize().width, getSize().height);
		popup.setBounds(getSize().width/32, getSize().height/32, 15*getSize().width/16, 15*getSize().height/16);
		
		//make backgrounds transparent on higher layer panels
		ttlPanel.setOpaque(false);
		popup.setOpaque(false);
		
		//set boundaries of buttons
		gameMode.setBounds(getSize().width/2 + getSize().width/12, 7* getSize().height/12, this.getSize().width/4, this.getSize().height/7);
		simMode.setBounds (getSize().width/2 - getSize().width/3, 7* getSize().height/12, this.getSize().width/4, this.getSize().height/7);
		creds.setBounds(getSize().width/2 + getSize().width/12, 9* getSize().height/12, this.getSize().width/4, this.getSize().height/7);
		howTo.setBounds(getSize().width/2 - getSize().width/3, 9* getSize().height/12, this.getSize().width/4, this.getSize().height/7);
		exit.setBounds(getSize().width/21, getSize().width/21, this.getSize().height/7, this.getSize().height/7);
			
		//place elements on appropriate layers of the panel
		add (bg, new Integer(0));
		add (ttlPanel, new Integer(1));
		add (gameMode, new Integer(2));
		add (simMode, new Integer(2));
		add (howTo, new Integer(2));
		add (creds, new Integer(2));
		add (popup, new Integer(3));
		add (exit, new Integer(4));
	}
	
	protected void paintComponent(Graphics g) {
		
		//draws desired windows based on the selected button from the menu
		if (btnChoice == 3)
		{
			//if how to is pushed, how to and exit are visible
			howToLbl.setVisible(true);
			credsLbl.setVisible(false);
			exit.setVisible(true);
		}
		
		else if (btnChoice == 4)
		{
			//if creds is selected, creds and exit are visible
			howToLbl.setVisible(false);
			credsLbl.setVisible(true);
			exit.setVisible(true);
		}
		
		else if (btnChoice == -1)
		{
			//reset if exit is clicked
			howToLbl.setVisible(false);
			credsLbl.setVisible(false);
			exit.setVisible(false);
		}
		// This call will paint the label and the focus rectangle.
		super.paintComponent(g);
	}
	
	/**
	 * @return An integer representing the button of choice
	 */
	public int buttonChoice() 
	{
		return btnChoice;
	}
	
	public void mouseClicked(MouseEvent e) {		
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//listens for actions
	public void actionPerformed(ActionEvent e) {
		
		//listens for which source enacted the actionListener and then sets btnChoice accordingly
		if (e.getSource().equals(simMode))
        {
            btnChoice = 1;
        }
		
		else if (e.getSource().equals(gameMode))
        {
            btnChoice = 2;
        }
		
		else if (e.getSource().equals(howTo))
        {
            btnChoice = 3;
        }
		
		else if (e.getSource().equals(creds))
        {
            btnChoice = 4;
        }

		else if (e.getSource().equals(exit))
        {
            btnChoice = -1;
        }
		
		repaint();
		
	}

	public void keyTyped(KeyEvent e) {			
		//draws desired windows based on the selected button from the menu
		if (btnChoice == 3)
		{
			//if how to is pushed, how to and exit are visible
			howToLbl.setVisible(true);
			credsLbl.setVisible(false);
			exit.setVisible(true);
		}
		
		else if (btnChoice == 4)
		{
			//if creds is selected, creds and exit are visible
			howToLbl.setVisible(false);
			credsLbl.setVisible(true);
			exit.setVisible(true);
		}
		
		else if (btnChoice == -1)
		{
			//reset if exit is clicked
			howToLbl.setVisible(false);
			credsLbl.setVisible(false);
			exit.setVisible(false);
		}
		
		this.repaint();
	}

	public void keyPressed(KeyEvent e) {
		//gets keyCode of event
		int keyCode = e.getKeyCode();

		//checks if button pressed was up
		if (keyCode == KeyEvent.VK_UP) {						// if yes, select appropriate button
			if (howToLbl.isVisible() || credsLbl.isVisible())
				selectedBtn = 5;
			else if (selectedBtn == -1)
				selectedBtn = 1;
			else if (selectedBtn == 3)
				selectedBtn = 1;
			else if (selectedBtn == 4)
				selectedBtn = 2;
		} 
		//if button was left, highlight appropriate button by changing selected button
		else if(keyCode == KeyEvent.VK_LEFT) {
			if (howToLbl.isVisible() || credsLbl.isVisible())
				selectedBtn = 5;
			else if (selectedBtn == -1)
				selectedBtn = 1;
			else if (selectedBtn == 2)
				selectedBtn = 1;
			else if (selectedBtn == 4)
				selectedBtn = 3;
		}
		//if button was right, select and highlight appropriate button
		else if(keyCode == KeyEvent.VK_RIGHT) {
			if (howToLbl.isVisible() || credsLbl.isVisible())
				selectedBtn = 5;
			else if (selectedBtn == -1)
				selectedBtn = 1;
			else if (selectedBtn == 1)
				selectedBtn = 2;
			else if (selectedBtn == 3)
				selectedBtn = 4;
		}
		//if button was down, select and highlight appropriate button
		else if(keyCode == KeyEvent.VK_DOWN) {
			if (howToLbl.isVisible() || credsLbl.isVisible())
				selectedBtn = 5;
			else if (selectedBtn == -1)
				selectedBtn = 1;
			else if (selectedBtn == 1)
				selectedBtn = 3;
			else if (selectedBtn == 2)
				selectedBtn = 4;
		}
		
		//if button was enter, select and highlight appropriate button
		else if (keyCode == KeyEvent.VK_ENTER) {
			if (selectedBtn == 5)		//turn button choice to -1 if exit selected
				btnChoice = -1;
			else {		//otherwise, button choice is the selected button
				btnChoice = selectedBtn;
			}
				selectedBtn = -1;	//turn selected button to -1
		}
		
		//checks for what number selectedBtn is
		//display appropriate windows and highlight approproate buttons based on selectedBtn
		if (selectedBtn == 1)		//1 corresponds to simMode
		{
			simMode.highlight();
			gameMode.unhighlight();
			howTo.unhighlight();
			creds.unhighlight();
		}
		
		else if (selectedBtn == 2)		//2 relates to gameMode
		{
			simMode.unhighlight();
			gameMode.highlight();
			howTo.unhighlight();
			creds.unhighlight();
		}
		else if (selectedBtn == 3)		//3 relates to howTo
		{
			simMode.unhighlight();
			gameMode.unhighlight();
			howTo.highlight();
			creds.unhighlight();
		}
			
		else if (selectedBtn == 4)		//4 relates to the creds
		{
			simMode.unhighlight();
			gameMode.unhighlight();
			howTo.unhighlight();
			creds.highlight();
		}
		
		else if (selectedBtn == 5)		//5 relates to exit
		{
			exit.highlight();
		}
		
		else if (selectedBtn ==-1)		//-1 relates to no highlight
		{
			simMode.unhighlight();
			gameMode.unhighlight();
			howTo.unhighlight();
			creds.unhighlight();
			exit.unhighlight();
		}
		this.repaint();
		
	}
	
	public void keyReleased(KeyEvent arg0) {

	}
	
//	public static void main(String[] args) {
//		MainMenu menu = new MainMenu(1200);
//		
//		JFrame frame = new JFrame();
//		frame.getContentPane().setLayout(null);
//		frame.getContentPane().add(menu);
//
//		//frame.getContentPane().setLayout(new FlowLayout());
//		frame.setSize(1200, 1000);
//		frame.setVisible(true);
//	}



}