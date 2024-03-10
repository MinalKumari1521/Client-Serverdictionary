
//  Name: Minal Kumari
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Color;

public class ClientGUI {

	private JFrame frame;
	private JTextField textFieldword;
	private JTextField textFieldPort;
	private JTextField textFieldmeaning;
	public static JLabel lblconnection;
	private DictionaryClient dictclient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI window = new ClientGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 645, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldword = new JTextField();
		textFieldword.setBounds(130, 114, 114, 20);
		frame.getContentPane().add(textFieldword);
		textFieldword.setColumns(10);
		
		frame.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	try {
		    		super.windowClosing(e);
		    		dictclient.connectionclose();
		    		frame.dispose();
		    		System.exit(0);
		    	}
		    	catch (Exception event) {
		    		System.exit(1);
		    	}
		    }
		});
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String port = textFieldPort.getText();
				if (port.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter the port");
				}
				else {
					dictclient = new DictionaryClient(Integer.parseInt(port));
				}
				
				
			}
		});
		btnConnect.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConnect.setBounds(368, 62, 89, 23);
		frame.getContentPane().add(btnConnect);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(77, 211, 445, 219);
		frame.getContentPane().add(textArea);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = textFieldword.getText();
				String[] result = dictclient.readword(word);
				if (result[0].equals("success")) {
					textArea.append("\n Word found! Meanings of the word are ");
					textArea.append("\n Word: "+word+" \nMeanings:\n");
					for (int i=1; i<result.length;i++) {
						textArea.append(Integer.toString(i)+") "+result[i]+"\n");
					}
				}
				else {
					textArea.append("\n Read operation failed ");
					textArea.append("\n Failure Message: "+result[1]+"\n");
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(79, 163, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		JButton btnWrite = new JButton("Write");
		btnWrite.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = textFieldword.getText();
				if (word.equals("")) {
					JOptionPane.showMessageDialog(null, "Please write the word first");
				}
				else {
					String meaning = textFieldmeaning.getText();
					if (meaning.equals("")) {
						JOptionPane.showMessageDialog(null, "Please write the meaning of the word first");
					}
					else {
						String[] result = dictclient.writeword(word, meaning);
						if (result[0].equals("success")) {
							textArea.append("\n Following word and its meaning have been successfully written in the dictionary ");
							textArea.append("\n Word and their meanings are  ");
							textArea.append("\n Word: "+word+" \nMeanings:\n");
							for (int i=1; i<result.length;i++) {
								textArea.append(Integer.toString(i)+") "+result[i]+"\n");
							}
						}
						else {
							textArea.append("\n Write operation failed ");
							textArea.append("\n Failure Message: "+result[1]+"\n");
						}
					}
				}
			}
		});
		btnWrite.setBounds(193, 163, 89, 23);
		frame.getContentPane().add(btnWrite);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = textFieldword.getText();
				if (word.equals("")) {
					JOptionPane.showMessageDialog(null, "Please write the word first");
				}
				else {
					String meaning = textFieldmeaning.getText();
					if (meaning.equals("")) {
						JOptionPane.showMessageDialog(null, "Please write the meaning of the word first");
					}
					else {
						String[] result = dictclient.updateword(word, meaning);
						if (result[0].equals("success")) {
							textArea.append("\n Following word and its meaning have been successfully updated in the dictionary ");
							textArea.append("\n Word: "+word+" \nMeaning: "+meaning+"\n");
						}
						else {
							textArea.append("\n Update operation failed ");
							textArea.append("\n Failure Message: "+result[1]+"\n");
						}
					}
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.setBounds(306, 163, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = textFieldword.getText();
				String[] result = dictclient.removeword(word);
				if (result[0].equals("success")) {
					textArea.append("\n Following word has been successfully deleted from the dictionary ");
					textArea.append("\n Word: "+word+"\n");
				}
				else {
					textArea.append("\n Delete operation failed ");
					textArea.append("\n Failure Message: "+result[1]+"\n");
				}
			}
		});
		btnDelete.setBounds(416, 163, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		textFieldPort = new JTextField();
		textFieldPort.setBounds(238, 63, 86, 20);
		frame.getContentPane().add(textFieldPort);
		textFieldPort.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Word");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel.setBounds(58, 117, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Server Port");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1.setBounds(141, 66, 69, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		textFieldmeaning = new JTextField();
		textFieldmeaning.setBounds(399, 96, 207, 56);
		frame.getContentPane().add(textFieldmeaning);
		textFieldmeaning.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Meaning");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_2.setBounds(306, 117, 66, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Dictionary Client");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_3.setBounds(238, 21, 157, 31);
		frame.getContentPane().add(lblNewLabel_3);
		
		
		JLabel lblstatus = new JLabel("status:");
		lblstatus.setBounds(10, 435, 46, 14);
		frame.getContentPane().add(lblstatus);
		
		lblconnection = new JLabel("Not Connected");
		lblconnection.setForeground(Color.RED);
		lblconnection.setBounds(58, 435, 94, 14);
		frame.getContentPane().add(lblconnection);
		
		JScrollPane scrollPane_1 = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setBounds(77, 211, 445, 219);
		frame.getContentPane().add(scrollPane_1);
	}
}
