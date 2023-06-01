import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyApp implements ActionListener {

	JFrame frame = new JFrame("Select Game Mode");
    JButton vsPlayerButton = new JButton("Player vs Player");
    JButton vsBotButton = new JButton("Player vs Bot");
    
    public MyApp() {
    	frame.setBounds(600,400,300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setLayout(new GridLayout(2, 1));
        
        frame.add(vsPlayerButton);
        frame.add(vsBotButton);
        
        vsPlayerButton.addActionListener(this);
        vsBotButton.addActionListener(this);
        
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) { 
        if (e.getSource() == vsPlayerButton) {
            // Open player vs player game
            new game(false);
            frame.dispose();
        } else if (e.getSource() == vsBotButton) {
            // Open player vs bot game
            new game(true);
            frame.dispose();
        }
    }
    
    public static void main(String[] args) {
        new MyApp();
    }
}

