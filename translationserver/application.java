import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;  

public class application {
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String res = "";
		
		ServerSocket ss = new ServerSocket(4228);
		ServerSocket ss1= new ServerSocket(4229);
		String[] malay = {"Selamat pagi","Selamat malam","Apa khabar?","Terima kasih","Selamat tinggal","Ada apa?"};
		String[] arab = {"صباح الخير\", \"طاب مساؤك\", \"كيف حالك؟\", \"شكرا لك\", \"مع السلامة\", \"ما أخبارك؟"};
		String[] korean = {"좋은 아침\", \"안녕히 주무세요\", \"어떻게 지내세요?\", \"감사합니다\", \"안녕\", \"뭐야?"};
		int pointer = 0;
        while (true) {
    		Socket socket = ss.accept();
    		
    		InputStream inputStream = socket.getInputStream();
    		
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            
            String message = dataInputStream.readUTF();
            System.out.println("The message sent from the socket was: " + message);
            System.out.println("Closing sockets.");
       
            
            
            Socket socket1 = ss1.accept();
    		
    		InputStream inputStream1 = socket1.getInputStream();
    		
            DataInputStream dataInputStream1 = new DataInputStream(inputStream1);
            
            String message1 = dataInputStream1.readUTF();
            System.out.println("The message sent from the socket was: " + message1);
            System.out.println("Closing sockets.");
            
            socket1.close();
            
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
    		
            pointer = 0;
            if (message.equals("Good morning"))
            	pointer = 0;
            else if (message.equals("Good night"))
            	pointer = 1;
            else if (message.equals("How are you?"))
            	pointer = 2;
            else if (message.equals("Thank you"))
            	pointer = 3;
            else if (message.equals("Goodbye"))
            	pointer = 4;
            else if (message.equals("Whatâ€™s up?"))
            	pointer = 5;
    
            	
            if (message1.equals("Korean"))
            	res = korean[pointer];
            else if (message1.equals("Arab"))
            	res = arab[pointer];
            else if (message1.equals("Malay"))
            	res = malay[pointer];
            
            System.out.println(res);
    		outputStream.writeUTF(res); 
            
            socket.close();
        }
	}
}