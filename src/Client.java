import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Client {
  public static void main(String[] args) throws Exception{
    new Client("localhost", 8080);

  }

  public Client(String ip, int port)throws Exception{
    //connect to server and set up input output streams
    Socket socket = new Socket(ip, port);
    System.out.println("Connected");

    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

    //get urls from user
   String url ="";
    int i = 0;
    do{
      url = JOptionPane.showInputDialog("Enter URL");
      if(url != null && !url.equals("")) printWriter.println(url);
      i++;
    }while(url != null && !url.equals(""));


    //send url
    //httpRequest();

  }

//  public void httpRequest() throws Exception{
//    //create a connection to given URL
//    URL url = new URL("https://www.youtube.com/");
//    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//    connection.setRequestMethod("GET");
//
//
//    InputStreamReader isr = new InputStreamReader(connection.getInputStream());
//    BufferedReader br = new BufferedReader(isr);
//
//    //store all string data line by line
//    StringBuilder sb = new StringBuilder();
//    String message = "";
//    while((message = br.readLine()) !=null){
//      sb.append(message);
//      System.out.println(message);
//    }
//    br.close();
//    //System.out.println(sb.toString());
//  }

}
