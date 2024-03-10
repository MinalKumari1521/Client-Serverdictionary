
// Name: Minal Kumari
import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class DictionaryClient {
	private static Socket connection;
	public DictionaryClient(int port) {
		try {
			connection = new Socket("localhost",port);
			ClientGUI.lblconnection.setText("connected");
			ClientGUI.lblconnection.setForeground(Color.GREEN);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Server is down");
			e.printStackTrace();
		} catch (ConnectException e) {
			ClientGUI.lblconnection.setText("Not Connected");
			ClientGUI.lblconnection.setForeground(Color.RED);
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	private boolean senddata(String word) {
		OutputStream s1Out;
		try {
			s1Out = connection.getOutputStream();
			DataOutputStream wordout = new DataOutputStream(s1Out);
			wordout.writeUTF(word);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
			return false;
		}
		
	}
	private String receivedata() {
		InputStream s1In;
		try {
			s1In = connection.getInputStream();
			DataInputStream wordin = new DataInputStream(s1In);
			String word = wordin.readUTF();
			return word;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			return "Error Occurred";
		}
		
	}
	public String[] readword(String word) {
		String datatosent = "read";
		datatosent=datatosent.concat("@").concat(word);
		boolean success = senddata(datatosent);
		String datareceived = receivedata();
		String[] result= datareceived.split("@"); 
		return result;
	}
	public String[] writeword(String word, String meaning) {
		String datatosent = "write";
		datatosent=datatosent.concat("@").concat(word).concat("@").concat(meaning);
		boolean success = senddata(datatosent);
		String datareceived = receivedata();
		String[] result= datareceived.split("@"); 
		return result;
	}
	public String[] updateword(String word, String meaning) {
		String datatosent = "update";
		datatosent=datatosent.concat("@").concat(word).concat("@").concat(meaning);
		boolean success = senddata(datatosent);
		String datareceived = receivedata();
		String[] result= datareceived.split("@"); 
		return result;
	}
	public String[] removeword(String word) {
		String datatosent = "delete";
		datatosent=datatosent.concat("@").concat(word);
		boolean success = senddata(datatosent);
		String datareceived = receivedata();
		String[] result= datareceived.split("@"); 
		return result;
	}
	public void connectionclose() {
		String datatosent = "disconnect";
		boolean success = senddata(datatosent);
	}
}

