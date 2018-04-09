
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
public class LaunchGame {
	private JTextField commandTF;
	private JButton commandButton;
	private JTextArea outputTA;

	public String room, textOutput;

	private JFrame f;
	public LaunchGame() throws InterruptedException{
		
		f = new JFrame();
		// f.getContentPane().setPreferredSize(new Dimension(600, 600));
		f.setContentPane(new JLabel(new ImageIcon("pictures/foyer.jpg")));
		room = "foyer";
		f.setTitle("Beauty and the Beast!");
        f.setLayout (null);
        f.pack();
	    f.setResizable(false);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	    f.setLocationRelativeTo(null);
	 
	 	commandTF = new JTextField(10);
	 	commandButton = new JButton("Send Command");

	 	outputTA = new JTextArea();
        outputTA.setEditable(false);
        textOutput = "";
        outputTA.setText(textOutput);

        f.add(outputTA);
	 	f.add(commandTF);
	 	f.add(commandButton);
	 	commandTF.setBounds (20, 540, 380, 40);
	 	commandButton.setBounds (420, 540, 160, 40);
	 	outputTA.setBounds (20, 400, 380, 120);

	 	commandButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String test = commandTF.getText();
            	command(test, f);
            }
        });

   		f.setVisible(true);
	    // mf = new MainFrame(f);
	    // ms = new MenuScreen();
	    // f.add(mf);
	    // f.revalidate();
	}

	public void command(String result, JFrame f){
		result = result.toLowerCase();
		switch (result) {
            case "go to foyer":
                room = "foyer";
                break;
            case "go to ballroom":
                room = "ballroom";
                break;
            case "go to bedroom":
                room = "bedroom";
                break;
            case "go to fireplace":
                room = "fireplace";
                break;
            case "go to dining room":
                room = "diningroom";
                break;
            case "go to forest":
                room = "forest";
                break;
            case "go to garden":
                room = "garden";
                break;
            case "go to balcony":
                room = "flower";
                break;
            case "go to kitchen":
                room = "kitchen";
                break;
            case "go to library":
                room = "library";
                break;
            default:
            	break;
        }
        textOutput = textOutput + "Went to " + room + "\n";
        outputTA.setText(textOutput);
		f.setContentPane(new JLabel(new ImageIcon("pictures/" + room + ".jpg")));
		addTextBox(f);
		f.pack();
	}
	public static void main (String args[]) throws InterruptedException {
		LaunchGame game = new LaunchGame();
		
	}	

	public void addTextBox(JFrame f){
		f.add(outputTA);
	 	f.add(commandTF);
	 	f.add(commandButton);
	 	commandTF.setBounds (20, 540, 380, 40);
	 	commandButton.setBounds (420, 540, 160, 40);
	 	outputTA.setBounds (20, 400, 380, 120);

	}
}
