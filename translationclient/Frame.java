import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Frame extends JFrame{
	private int width = 700;
	private int height = 500;
	
	private static JLabel label;
	
	String[] lang = {"Malay", "Arab", "Korean"};
	
	public Frame() {
		// Default frame setting
		this.setLayout(new BorderLayout());
		this.setTitle("Translate");
		this.setSize(new Dimension(width, height));  
		
		// Must close on X
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Center the frame on the screen
        this.setLocationRelativeTo(null);
        loadComponent();
	
	}
	
	private JPanel translatePanel() {
		JPanel panel = new JPanel();
		
		JComboBox langList = new JComboBox(lang);
		JTextField tf1 = new JTextField("", 40);
		JButton button = new JButton("Translate");
		panel.add(tf1);
		panel.add(langList);
		panel.add(button);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					translate.sendText(tf1.getText(), String.valueOf(langList.getSelectedItem()));
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		return panel;
	}
	
	private JPanel resultPanel() {
		JPanel panel = new JPanel();
		label = new JLabel("-");
		label.setFont(new Font("Serif ", Font.PLAIN, 18));

		panel.add(label);
		
		return panel;
	}
	
	public void loadComponent() {
		JPanel topPanel = this.translatePanel();
		JPanel cPanel = this.resultPanel();

		this.add(topPanel, BorderLayout.NORTH);
		this.add(cPanel, BorderLayout.CENTER);		
	}

	public static void updateResult(String result) throws UnsupportedEncodingException {
		//String res = new String(result.getBytes(), "UTF-16");
		label.setText(result);
	}
}