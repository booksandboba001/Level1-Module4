package _02_jeopardy;


/*
 *    Copyright (c) The League of Amazing Programmers 2013-2019
 *    Level 1
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game_tools.Sound;


/* Check out the Jeopardy Handout to see what the end result should look like: http://bit.ly/1bvnvd4 */

public class Jeopardy implements ActionListener {
	private JButton firstButton;
	private JButton secondButton;
	private JButton thirdButton, fourthButton;
	private JPanel quizPanel;
	private int score = 0;
	private JLabel scoreBox = new JLabel("0");
	private int buttonCount = 0;
	private Sound jeopardyThemeClip;



	public void run() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		quizPanel = new JPanel();
		frame.setLayout(new BorderLayout());
		
		frame.setTitle("JEOPARDY");
		JPanel panel = createHeader("JEOPARDY");
		
		quizPanel.add(panel);
		firstButton = createButton("200");
		quizPanel.add(firstButton);
		firstButton.addActionListener(this);
		secondButton = createButton("400");
		secondButton.addActionListener(this);
		quizPanel.add(secondButton);
		thirdButton = createButton("600");
		thirdButton.addActionListener(this);
		quizPanel.add(thirdButton);
		fourthButton = createButton("800");
		fourthButton.addActionListener(this);
		quizPanel.add(fourthButton);
		frame.add(quizPanel);
		
		// 1. Make the frame show up

		// 2. Give your frame a title

		// 3. Create a JPanel variable to hold the header using the createHeader method

		// 4. Add the header component to the quizPanel

		// 5. Add the quizPanel to the frame

		// 6. Use the createButton method to set the value of firstButton

		// 7. Add the firstButton to the quizPanel

		// 8. Write the code to complete the createButton() method below. Check that your
		// game looks like Figure 1 in the Jeopardy Handout - http://bit.ly/1bvnvd4.

		// 9. Use the secondButton variable to hold a button using the createButton
		// method

		// 10. Add the secondButton to the quizPanel

		// 11. Add action listeners to the buttons (2 lines of code)

		// 12. Write the code to complete the actionPerformed() method below

		// 13. Add buttons so that you have $200, $400, $600, $800 and $1000 questions
		
		 /*
		 * [optional] Use the showImage or playSound methods when the user answers a
		 * question
		 */
		
		frame.pack();
		frame.show();
		quizPanel.setLayout(new GridLayout(buttonCount + 1, 3));
		frame.add(makeScorePanel(), BorderLayout.NORTH);
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().height,
				Toolkit.getDefaultToolkit().getScreenSize().width);
	}


	private JButton createButton(String dollarAmount) {
		JButton button = new JButton();
		button.setSize(200,100);
		button.setText(dollarAmount);
		buttonCount++;
		// Create a new JButton

		// Set the text of the button to the dollarAmount

		// Increment the buttonCount (this should make the layout vertical)

		// Return your new button instead of the temporary button

		return button;
	}

	@Override
    public void actionPerformed(ActionEvent e) {
		
		JButton buttonPressed = (JButton) e.getSource();
		if (buttonPressed==firstButton) {
			askQuestion("What song did Deep Cut collaborate on with the Squid Sisters?", "Calamari Inkcantation 3MIX", 200);
		}
		buttonPressed.setText("");
		if (buttonPressed==secondButton) {
			askQuestion("What song did Deep Cut collaborate on with Fire v. Ice?", "Suffer No Fools", 400);
		}
		if (buttonPressed==thirdButton) {
			askQuestion("What is the real name of the song that goes BANG BING?", "Seep and Destroy", 600);
		}
		if (buttonPressed==fourthButton) {
			askQuestion("Is agent 38 canon?", "Yes", 800);
		}
		// If the buttonPressed was the firstButton

			// Call the askQuestion() method
 
		// Complete the code in the askQuestion() method. When you play the game, the score should change.

		// If the buttonPressed was the secondButton

			// Call the askQuestion() method with a harder question

		// Clear the text on the button that was pressed (set the button text to nothing)

	}

	private void askQuestion(String question, String correctAnswer, int prizeMoney) {
		
		// Use the playJeopardyTheme() method to play music while the use thinks of an answer
		
		// Remove this temporary message and replace it with a pop-up that asks the user the question
		playJeopardyTheme();
		String ques = JOptionPane.showInputDialog(null, question);
		stopJeopardyTheme();
		if(ques.equalsIgnoreCase(correctAnswer)) {
			JOptionPane.showMessageDialog(null, "Correct!");
			score+=prizeMoney;
		}else{
			JOptionPane.showMessageDialog(null, "Incorrect!");
			score-=prizeMoney;
		}
		updateScore();
		// Stop the theme music when they have entered their response.
		
		// If the answer is correct

			// Increase the score by the prizeMoney

			// Pop up a message to tell the user they were correct

		// Otherwise

			// Decrement the score by the prizeMoney

			// Pop up a message to tell the user they were wrong and give them the correct answer

		// Call the updateScore() method

	}

	public void playJeopardyTheme() {
		String fileName = "_02_jeopardy/jeopardy.wav";
		jeopardyThemeClip = new Sound(fileName);
		jeopardyThemeClip.play();
	}

	public void stopJeopardyTheme() {
		jeopardyThemeClip.stop();
	}

	private Component makeScorePanel() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("score:"));
		panel.add(scoreBox);
		panel.setBackground(Color.CYAN);
		return panel;
	}

	private void updateScore() {
		scoreBox.setText("" + score);
	}

	private JPanel createHeader(String topicName) {
		JPanel panelj = new JPanel();
		panelj.setLayout(new BoxLayout(panelj, BoxLayout.PAGE_AXIS));
		JLabel l1 = new JLabel(topicName);
		l1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelj.add(l1);
		return panelj;
	}

	void showCorrectImage() {
		showImage("correct.jpg");
	}

	void showIncorrectImage() {
		showImage("incorrect.jpg");
	}

	private void showImage(String fileName) {
		JFrame frame = new JFrame();
		URL imageURL = getClass().getResource(fileName);
		Icon icon = new ImageIcon(imageURL);
		JLabel image = new JLabel(icon);
		frame.add(image);
		frame.setVisible(true);
		frame.pack();
	}
}
