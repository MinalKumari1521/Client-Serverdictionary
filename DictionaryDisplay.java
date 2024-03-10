 
// Name: Minal Kumari
 
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.HashMap;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DictionaryDisplay extends JFrame {

	private JPanel contentPane;
	public static JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DictionaryDisplay frame = new DictionaryDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DictionaryDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(1, 1, 511, 302);
		contentPane.add(textArea);
		
		JLabel lblNewLabel = new JLabel("DICTIONARY");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(197, 22, 128, 20);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 53, 513, 395);
		contentPane.add(scrollPane);
	}
	public static void dislay(HashMap<String, String[]> map) {
		textArea.setText("");
	    for(String key: map.keySet()) {
	    	textArea.append("Word: "+key+'\n');
	    	String[] meaning = map.get(key);
	    	textArea.append("Meaning:"+"\n");
	    	
	    	for (int i=0; i<meaning.length;i++) {
	    		textArea.append(Integer.toString(i+1)+") "+meaning[i]+"\n");
	    	}
	    	textArea.append("\n");
	    }
	}

}
