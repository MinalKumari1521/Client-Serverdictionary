
  // Name: Minal Kumari
  
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.net.*;

class Dictionary {
    private HashMap<String, String[]> maindict;
    public int wordcount = 0;
    public Dictionary() {
    	try {
	    		File dictfile = new File ("dictionary.txt");
	    		
	    		if (!dictfile.exists()){
	    			boolean result = dictfile.createNewFile();
	    			String result1;
	    			result1 = writedict("Dummy", "a model or replica of a human being","DEFAULT-DICTIONARY");
	    			logging(result1);
	    			result1 = writedict("New","produced, introduced, or discovered recently or now for the first time; not existing before","DEFAULT-DICTIONARY");
	    			logging(result1);
	    			result1 = writedict("Dictionary", "a book or electronic resource that lists the words of a language (typically in alphabetical order) and gives their meaning, or gives the equivalent words in a different language, often also providing information about pronunciation, origin, and usage","DEFAULT-DICTIONARY");
	    			logging(result1);	    			
	    			ServerGUI.WordsCountLbl.setText(Integer.toString(wordcount));
	    			logging("the wordcount is "+ Integer.toString(wordcount));
	    		}
	    		else if (dictfile.length() == 0 ) {
	    			 wordcount = 0;
	    			 ServerGUI.WordsCountLbl.setText(Integer.toString(wordcount));
	    		}
	    		else {
	                maindict = readfromdictfile();
	                wordcount = maindict.size();
	                ServerGUI.WordsCountLbl.setText(Integer.toString(wordcount));
	    		}
    	} catch (Exception event) {
    		JOptionPane.showMessageDialog(null, event.getMessage());
    		event.printStackTrace();
    		
    	}
    }
    public void logging(String log) {
    	try {
			ServerGUI.textArea.append(log+"\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
    }
    public synchronized String readdict(String word, String user){
    	String status = new String();
        String[] message;
        String result = new String();
    	try {
        	logging(user+": Searching for the word '"+word+"'\n");
        	HashMap<String, String[]> dict = new HashMap<String, String[]>();
        	dict = readfromdictfile();
	        if (dict.containsKey(word)) {
	        	logging(user+": Word Found with the meanings:\n");
	        	String[] tempvalues = dict.get(word);
	        	for (int y=0 ; y<tempvalues.length; y++) {
	        		logging(Integer.toString(y+1)+") "+tempvalues[y]);
	        	}
	        	status = "success";
	        	message = dict.get(word);
	        	String finalresult = String.join("@", message);
	        	result = status.concat("@").concat(finalresult);	
	        	return result;
	        }
	        else {
	        	
	        	logging(user+": Word '"+word+"' not found in the dictionary \n");
	        	status = "failure";
	        	result = status.concat("@").concat("Word not found");
	        	return result;
	        }
        	
        }
        catch (Exception event) {
        	event.printStackTrace();
        	status = "failure";
        	result = status.concat("@").concat("Error Occurred");
        	return result;
        }
    }
    public synchronized String writedict(String word, String meaning, String user) {
    	String status = new String();
        String[] message;
        String result = new String();
    	try {
	    	logging(user+": Writing the word '"+word+"' with the meaning '"+meaning+"' \n");
	        HashMap<String, String[]> dict = new HashMap<String, String[]>();
	        dict = readfromdictfile();
	        
	        if (dict.containsKey(word)) {
	        	boolean search = false;
	        	String[] values= dict.get(word);
	        	for (int i= 0; i<values.length;i++) {
	        		search=values[i].equals(meaning);
	        		if (search==true) {
	        			logging(user+": Writing operation failed, the meaning is already present in the dictionary");
	    	        	status = "failure";
	    	        	result = status.concat("@").concat("Meaning is already present");	
	    	        	return result;
	        		}
	        	}
	        }
	        if (dict.isEmpty() || !dict.containsKey(word)) {
	        	message = new String[] {meaning};
	        }
	        else {
	        	int previousmeaningsize= dict.get(word).length;
	        	message = Arrays.copyOf(dict.get(word), previousmeaningsize+1);
		        message[previousmeaningsize]=meaning;
		        
		        
	        }
	        
	        dict.put(word,message);
	        boolean IOstatus = writetodictfile(dict);
	        if (IOstatus) {
	        	wordcount+=1;
	        	ServerGUI.WordsCountLbl.setText(Integer.toString(wordcount));
	        	logging(user+": Successfully written the word");
	        	status = "success";
	        	String finalresult = String.join("@", message);
	        	result = status.concat("@").concat(finalresult);
	        	DictionaryDisplay.dislay(dict);
	        	return result;
	        }
	        else {
	        	logging(user+": Writing operation failed");
	        	status = "failure";
	        	result = status.concat("@").concat("Could not write the word in the dictionary");	
	        	return result;
	        }
	        
    	}
    	catch (Exception event) {
    		event.printStackTrace();
    		status = "failure";
        	result = status.concat("@").concat("Error Occurred");
        	JOptionPane.showMessageDialog(null, event.getMessage());
        	return result;
    	}
    }
    public synchronized String updatedict(String word, String meaning, String user) {
    	String status = new String();
        String[] message;
        String result = new String();
    	try {
	    	logging(user+": Updating the word '"+word+"' with the meaning '"+meaning+"' \n");
	    	HashMap<String, String[]> dict = new HashMap<String, String[]>();
	    	dict = readfromdictfile();
	    	
	    	if (dict.containsKey(word)) {
	        	boolean search = false;
	        	String[] values= dict.get(word);
	        	for (int i= 0; i<values.length;i++) {
	        		search=values[i].equals(meaning);
	        		if (search==true) {
	        			logging(user+": Writing operation failed, the word is already present in the dictionary");
	    	        	status = "failure";
	    	        	result = status.concat("@").concat("Word is already present");	
	    	        	return result;
	        		}
	        	}
	        	message = new String[] {meaning};
	        	dict.put(word,message);
	        	boolean IOstatus = writetodictfile(dict);
	        	if (IOstatus) {
	        		logging(user+": Successfully updated the word");
	        		status = "success";
		        	result = status.concat("@").concat("Successfully updated the word");
		        	DictionaryDisplay.dislay(dict);
		        	return result;
	        	}	       
	        }
	    	
	        else {
	        	logging(user+": Could not find the word");
	        	status = "failure";
	        	result = status.concat("@").concat("Could not find the word");	
	        	return result;
	        }
    	}
    	catch (Exception event) {
    		event.printStackTrace();
    		status = "failure";
        	result = status.concat("@").concat("Error occurred");
        	JOptionPane.showMessageDialog(null, event.getMessage());
        	return result;
    	}
    	status = "failure";
    	result = status.concat("@").concat("Update operation failed");	
    	return result;
    }
    public synchronized String removedict(String word, String user) {
    	String status = new String();
        String message = new String();
        String result = new String();
    	try {
	        HashMap<String, String[]> dict = new HashMap<String, String[]>();
	        logging(user+": Removing the word '"+word+"'\n");
	        dict = readfromdictfile();
	        if (dict.containsKey(word)) {
	        	dict.remove(word);
	        	wordcount = dict.size();
	        	ServerGUI.WordsCountLbl.setText(Integer.toString(wordcount));
	        	boolean IOstatus = writetodictfile(dict);
	        	if (IOstatus) {
	        		logging(user+": Successfully removed the word");
	        		status = "success";
		        	message = "Sucessfully removed the word";
		        	result = status.concat("@").concat(message);
		        	DictionaryDisplay.dislay(dict);
		        	return result;
	        	}	        	
	        }
	        else {
	        	logging(user+": Could not find the word");
	        	status = "failure";
	        	message = "Could not find the word";
	        	result = status.concat("@").concat(message);	
	        	return result;
	        }
    	}
    	catch (Exception event) {
    		event.printStackTrace();
    		status = "failure";
        	message = "Error Occurred";
        	result = status.concat("@").concat(message);
        	JOptionPane.showMessageDialog(null, event.getMessage());
        	return result;
    	}
    	status = "failure";
    	message = "Delete operation failed";
    	result = status.concat("@").concat(message);	
    	return result;
    }
    
    public synchronized HashMap<String, String[]> readfromdictfile() {
    	HashMap<String, String[]> dict = new HashMap<String, String[]>();
    	File dictfile = new File ("dictionary.txt");
		if (dictfile.exists()) {
			
			if(dictfile.length()==0) {
        		return dict;
        	}
		}
		else {
			Dictionary checking = new Dictionary();
			DictionaryDisplay.dislay(checking.readfromdictfile());
		}
    	try 
    	{
    		FileInputStream fileIn = new FileInputStream("dictionary.txt");
	        ObjectInputStream inputobject = new ObjectInputStream(fileIn);
	        dict = (HashMap<String, String[]>) inputobject.readObject();
	        inputobject.close();
	        fileIn.close();
	        return dict;
    	}
    	catch (EOFException event) {
    		
    		event.printStackTrace();    	
    		return dict;
    	}
    	catch (FileNotFoundException event) {
    		event.printStackTrace();
    		JOptionPane.showMessageDialog(null, event.getMessage());
    	}
    	catch (IOException event) {
    		event.printStackTrace();
    		JOptionPane.showMessageDialog(null, event.getMessage());
    	}
    	catch (ClassNotFoundException event) {
    		event.printStackTrace();
    		JOptionPane.showMessageDialog(null, event.getMessage());
    	}
    	catch (Exception event) {
    		event.printStackTrace();
    		JOptionPane.showMessageDialog(null, event.getMessage());
    	}
    	return dict;
    	
    }
    private synchronized boolean writetodictfile(HashMap<String, String[]> dictwritetofile) {
    	try 
    	{
    		
    		File dictfile = new File ("dictionary.txt");
    		if (!dictfile.exists()) {
    			dictfile.createNewFile();
    		}
    		FileOutputStream fileOut = new FileOutputStream("dictionary.txt");
	        ObjectOutputStream output = new ObjectOutputStream(fileOut);
	        output.writeObject(dictwritetofile);
	        output.close();
	        return (true);
    	}
    	catch (EOFException event) {
    		JOptionPane.showMessageDialog(null, event.getMessage());
    		event.printStackTrace();
    	}
    	catch (FileNotFoundException event) {
    		JOptionPane.showMessageDialog(null, event.getMessage());
    		event.printStackTrace();
    	}
    	catch (IOException event) {
    		JOptionPane.showMessageDialog(null, event.getMessage());
    		event.printStackTrace();
    	}
    	catch (Exception event) {
    		JOptionPane.showMessageDialog(null, event.getMessage());
    		event.printStackTrace();
    	}
    	return false;
    }

}

class dictThreads extends Thread {
    private Socket s1;
    private String operation;
    private String[] word;
    private String user;

    public dictThreads(Socket s1, int mainuser){
        this.s1 = s1;
        int numclients = Integer.parseInt(ServerGUI.connectedclients.getText());
        ServerGUI.connectedclients.setText(Integer.toString(++numclients));
        this.user="USER-"+Integer.toString(mainuser);
    }
    private void dictfunction() {
        Dictionary checking = new Dictionary();
        checking.logging(user+" connected\n");
        OutputStream s1out = null;
        InputStream s1In = null;
        DataOutputStream dos = null;
        DataInputStream wordin = null;
        
        
		try {
			s1out = s1.getOutputStream();
			dos = new DataOutputStream (s1out);
			s1In = s1.getInputStream();
			wordin = new DataInputStream(s1In);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
        
        while(true) {
	        String readword = new String();
			try {
				readword = wordin.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
	        
	        if (readword.equals("disconnect")) {
	        	checking.logging(user+": Disconnected");
	        	try {
					wordin.close();
					dos.close();
					s1In.close();
					s1out.close();
					s1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
	            int numclients = Integer.parseInt(ServerGUI.connectedclients.getText());
	            ServerGUI.connectedclients.setText(Integer.toString(--numclients));
	            break;
	        }
	        
	        this.word = readword.split("@");
	        this.operation = word[0];
	        int sizeofthearray = word.length;
	        
	        if(this.operation.equals("read"))
	        {
	        	checking.logging(user+": going into the read condition");
	            String gettingresults = new String();
	            gettingresults=checking.readdict(word[1],user);
	            
	            try {
					dos.writeUTF(gettingresults);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
	            
	        }
	        else if (this.operation.equals("write"))
	        {
	            String gettingresults = new String();
	
	            gettingresults=checking.writedict(word[1],word[2],user);
	            try {
					dos.writeUTF(gettingresults);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
	        } 
	        else if (this.operation.equals("update"))
	        {
	            String gettingresults = new String();
	
	            gettingresults=checking.updatedict(word[1], word[2], user);
	            try {
					dos.writeUTF(gettingresults);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
	        	
	        }
	        else if (this.operation.equals("delete"))
	        {
	            String gettingresults = new String();
	
	            gettingresults=checking.removedict(word[1], user);
	            try {
					dos.writeUTF(gettingresults);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}            
	        }
	        checking.logging(user+": Results sent to the client");
        }
    }
    public void run() {
        try {
            dictfunction();
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }
}

class DictionaryServerMainThread extends Thread {
	private ServerSocket mainsocket;
	public int user = 0;
	
	public DictionaryServerMainThread(ServerSocket s) {
		this.mainsocket = s;
	}
	private void dictfunction() {
		while(true){
	        Socket s1 =new Socket();
			try {
				s1 = mainsocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        user +=1;
	        dictThreads dictthreads = new dictThreads(s1,user);
	        dictthreads.start();
	    }
	}
	public void run() {
		dictfunction();
		
	}
}

public class DictionaryServer {

	public int wordcount;
	
	public DictionaryServer(int port)
	{
		try  {
			ServerSocket s = new ServerSocket(port);
			DictionaryDisplay dictdisplay = new DictionaryDisplay();
			Dictionary dictionary = new Dictionary();
			HashMap<String, String[]> readdictionary = new HashMap<String, String[]> ();
			dictdisplay.setVisible(true);
			dictdisplay.dislay(dictionary.readfromdictfile());
			wordcount = dictionary.wordcount;
			ServerGUI.WordsCountLbl.setText(Integer.toString(wordcount));
			DictionaryServerMainThread mainthread = new DictionaryServerMainThread(s);
			mainthread.start();
		}
		catch (BindException event) {
			JOptionPane.showMessageDialog(null, event.getMessage());
			ServerGUI.textArea.append(event.getMessage()+"\n");
		}
		catch (Exception event) {
			JOptionPane.showMessageDialog(null, event.getMessage());
			event.printStackTrace();
		}
	}
}

