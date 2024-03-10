// Name: Minal Kumari

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ServerGUI {

	private JFrame frmDictionaryServer;
	private JTextField PortTextField;
	public static JTextArea textArea;
	public static JLabel WordsCountLbl;
	public static JLabel connectedclients;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI window = new ServerGUI();
					window.frmDictionaryServer.setVisible(true);
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
	public ServerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmDictionaryServer = new JFrame();
		frmDictionaryServer.setTitle("Dictionary Server");
		frmDictionaryServer.setBounds(100, 100, 636, 432);
		frmDictionaryServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDictionaryServer.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dictionary Server");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBounds(210, 0, 206, 31);
		frmDictionaryServer.getContentPane().add(lblNewLabel);
		
		PortTextField = new JTextField();
		PortTextField.setBounds(193, 36, 178, 31);
		frmDictionaryServer.getContentPane().add(PortTextField);
		PortTextField.setColumns(10);
		
		JLabel PortLabel = new JLabel("Port");
		PortLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		PortLabel.setBounds(107, 43, 46, 14);
		frmDictionaryServer.getContentPane().add(PortLabel);
		
		WordsCountLbl = new JLabel("0");
		WordsCountLbl.setBounds(318, 78, 46, 14);
		frmDictionaryServer.getContentPane().add(WordsCountLbl);
		
		
		
		JLabel NumWordsLabel = new JLabel("Number of words in Dictionary:");
		NumWordsLabel.setBounds(107, 78, 178, 14);
		frmDictionaryServer.getContentPane().add(NumWordsLabel);
		
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 583, 277);
		frmDictionaryServer.getContentPane().add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Number of Clients connected:");
		lblNewLabel_1.setBounds(107, 103, 147, 14);
		frmDictionaryServer.getContentPane().add(lblNewLabel_1);
		
		connectedclients = new JLabel("0");
		connectedclients.setBounds(318, 103, 46, 14);
		frmDictionaryServer.getContentPane().add(connectedclients);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int port;
				try  {
					String porttext=PortTextField.getText();
					if(porttext.equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter the port first");
					}
					else {
						port = Integer.parseInt(porttext);
						DictionaryServer dictionary = new DictionaryServer(port);
					}
					
				}catch (Exception event) {
					JOptionPane.showMessageDialog(null, event.getMessage());
				}
				
				
				
				
			}
		});
		btnConnect.setBounds(435, 40, 89, 23);
		frmDictionaryServer.getContentPane().add(btnConnect);
		
		JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(28, 136, 556, 228);
		frmDictionaryServer.getContentPane().add(scrollPane);
		
		JLabel lblNewLabel_3 = new JLabel("LOGS");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(28, 122, 46, 14);
		frmDictionaryServer.getContentPane().add(lblNewLabel_3);
		
		
	}
}
