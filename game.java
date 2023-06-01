import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class game implements ActionListener{
	
    JButton[] TicTacToeButtons = new JButton[9];
    boolean playerXTurn;
    int turn = 1;
	JFrame frame = new JFrame("Assignment 2");
	JPanel gameDisplay = new JPanel();
	private JTextArea scoreBoard = new JTextArea("");
	private JTextField playerturn = new JTextField("");
	int win = 0;
	int draw = 0;
	int lose = 0;
	JButton resetButton = new JButton("Reset");
	int reset = -1;
	public boolean VsBot; // true vsPlayer, false vsBot
	JButton Return = new JButton("Return");
	long startTime = System.currentTimeMillis();
	long endTime = System.currentTimeMillis();
	
	public game(boolean VsBot) {
		
		this.VsBot = VsBot;
		playerXTurn = Math.random() < 0.5;
		
		//frame.setResizable(false);
		frame.setBounds(100, 100, 550 , 650 );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        scoreBoard.setEditable(false);
        
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(playerturn,BorderLayout.NORTH);
        playerturn.setEditable(false);
        rightPanel.add(scoreBoard,BorderLayout.CENTER);
        frame.add(rightPanel,BorderLayout.EAST);

		gameDisplay.setVisible(true);
		gameDisplay.setLayout(new GridLayout(3,3));
		
		for (int i = 0; i < 9; i++) {
			TicTacToeButtons[i] = new JButton();
            gameDisplay.add(TicTacToeButtons[i]);

            TicTacToeButtons[i].setBackground(Color.white);
            TicTacToeButtons[i].setFont(new Font("Ink Free", Font.BOLD, 120));
            TicTacToeButtons[i].setFocusable(false);
            TicTacToeButtons[i].addActionListener(this);
        }
		
		
		frame.add(gameDisplay,BorderLayout.CENTER);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		
		resetButton.setBackground(Color.DARK_GRAY);
		resetButton.setForeground(Color.WHITE);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		leftPanel.add(resetButton,BorderLayout.CENTER);
		
		JLabel turnLabel = new JLabel("Turn: 1");
		leftPanel.add(turnLabel,BorderLayout.SOUTH);
		
		Return.setBackground(Color.GRAY);
		Return.setForeground(Color.WHITE);
		Return.setFocusable(false);
		Return.addActionListener(this);
		leftPanel.add(Return,BorderLayout.NORTH);
		
		frame.add(leftPanel,BorderLayout.WEST);
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

        		if(VsBot && !playerXTurn)
        			botMove(playerXTurn);

        		turnLabel.setText("Turn: " + turn);
        		endTime = System.currentTimeMillis();
        		
        		if(VsBot)
        			scoreBoard.setText("\n\nWin:\t"+win+"\nDraw:\t"+draw+"\nLose:\t"+lose+"\n"+"Time Played:"+((endTime - startTime)/1000));
        		else
        			scoreBoard.setText("\n\nX Win:\t"+win+"\nO Win:\t"+lose+"\n"+"Draw:\t"+draw+"\n"+"Time Played:"+((endTime - startTime)/1000));
	        		
        		if(playerXTurn)
    				playerturn.setText("X Turn\n\n");
        		else
        			playerturn.setText("O Turn\n\n");
			}
		};

		Timer displayTimer = new Timer(1, listener);
		displayTimer.start();
		
		frame.setVisible(true);
	}
	
	private boolean checkWinner() {
		
		//Rows
		for(int i = 0 ; i < 9; i = i+3)
			if (TicTacToeButtons[i].getText() == TicTacToeButtons[i+1].getText() && TicTacToeButtons[i+1].getText() == TicTacToeButtons[i+2].getText() && TicTacToeButtons[i].getText() != "") {
				for (i = 0; i < 9; i++)
					TicTacToeButtons[i].setEnabled(false);
				return true;
			}
		
		//Columns
		for(int i = 0 ; i < 3; i++)
			if (TicTacToeButtons[i].getText() == TicTacToeButtons[i+3].getText() && TicTacToeButtons[i+3].getText() == TicTacToeButtons[i+6].getText() && TicTacToeButtons[i].getText() != "") {
				for (i = 0; i < 9; i++)
					TicTacToeButtons[i].setEnabled(false);
				return true;
			}
		
		//Diagonals
		if (TicTacToeButtons[0].getText() == TicTacToeButtons[4].getText() && TicTacToeButtons[4].getText() == TicTacToeButtons[8].getText() && TicTacToeButtons[0].getText() != "") {
			for (int i = 0; i < 9; i++)
				TicTacToeButtons[i].setEnabled(false);
			return true;
		}
		if (TicTacToeButtons[2].getText() == TicTacToeButtons[4].getText() && TicTacToeButtons[4].getText() == TicTacToeButtons[6].getText() && TicTacToeButtons[2].getText() != "") {
			for (int i = 0; i < 9; i++)
				TicTacToeButtons[i].setEnabled(false);
			return true;
		}
		
		return false;
	}
	
	private void resetGame() {
		playerXTurn = Math.random() < 0.5;
		reset = -1;
		turn = 1;
		for (int i = 0; i < 9; i++) {
			TicTacToeButtons[i].setEnabled(true);
			TicTacToeButtons[i].setText("");
        }
		startTime = System.currentTimeMillis();
	}
	
	private void botMove(boolean isX) {
		Random rand = new Random();
		int move =  rand.nextInt((8 - 0) + 1) + 0;
		
		while(TicTacToeButtons[move].getText() != "")
			move = (int) (Math.random() * 10)%8;
		
		if(!checkWinner() && turn <= 10)
			if(isX) {
				TicTacToeButtons[move].setText("X");
	    		TicTacToeButtons[move].setEnabled(false);
	    		turn++;
	    		if(checkWinner()) {
	    			System.out.println("X Wins!!");
        			JOptionPane.showMessageDialog(frame,  "Computer X Wins!!!");
        			lose++;
	    		}
			} else {
				TicTacToeButtons[move].setText("O");
	    		TicTacToeButtons[move].setEnabled(false);
	    		turn++;
	    		if(checkWinner()) {
	    			System.out.println("O Wins!!");
        			JOptionPane.showMessageDialog(frame,  "Computer O Wins!!!");
        			lose++;
	    		}
			}

		if(!checkWinner() && turn == 10) {
			playerXTurn = true;
    		JOptionPane.showMessageDialog(frame, "Its a Draw!");
    		draw++;
     	}
		
		playerXTurn = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == Return) {
			new MyApp();
            frame.dispose();
		}
		
		for (int i = 0; i < 9; i++) {
            if (e.getSource() == TicTacToeButtons[i]) {
            	if(playerXTurn) {
            		TicTacToeButtons[i].setText("X");
            		TicTacToeButtons[i].setEnabled(false);
            		playerXTurn = !playerXTurn;
                	turn++;
            		if(checkWinner()) {
    					playerXTurn = true;
            			System.out.println("X Wins!!");
            			JOptionPane.showMessageDialog(frame, "Player X Wins!!!");
            			win++;
            		}
            	}
            	else {
            		TicTacToeButtons[i].setText("O");
            		TicTacToeButtons[i].setEnabled(false);
            		playerXTurn = !playerXTurn;
                	turn++;
            		if(checkWinner()) {
    					playerXTurn = true;
            			System.out.println("O Wins!!");
            			JOptionPane.showMessageDialog(frame,  "Player O Wins!!!");
            			lose++;
            		}
            	}

        		if(!checkWinner() && turn == 10) {
        			playerXTurn = true;
            		JOptionPane.showMessageDialog(frame, "Its a Draw!");
            		draw++;
             	}
        		
            }
		}
		
		
    	if(e.getSource() == resetButton)
    	{
    		System.out.println("Game Reset");
    		resetGame();
    	}
	}
}