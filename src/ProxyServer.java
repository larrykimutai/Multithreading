import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class ProxyServer{
public static List<String> clientUrl = new ArrayList<>();
  public ProxyServer(int i) throws Exception {
    Server(i);
  }

  public ProxyServer() {

  }

  public void Server(int port) throws Exception{
    //starting server and accepting client
    ServerSocket serverSocket = new ServerSocket(port);
    System.out.println("waiting....");
    Socket socket = serverSocket.accept();

    Scanner scanner = new Scanner(socket.getInputStream());
    //String clientUrl = scanner.nextLine();

    //List<String> clientUrl = new ArrayList<>();
    int in = 0;
    do{
      clientUrl.add(scanner.nextLine());
      //System.out.println(clientUrl.get(in));
      in++;
    }while(scanner.hasNextLine());

    Scanner fromClient = new Scanner(socket.getInputStream());
    System.out.println(fromClient);

    int s = clientUrl.size();
    for(int i = 0; i < s; i++){
      MultiThreading mt1 = new MultiThreading(clientUrl.get(i));
      mt1.start();
    }




  }

  public void httpRequest(String hostname) throws Exception{
    //create a connection to given URL
    URL url = new URL(hostname);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");


    InputStreamReader isr = new InputStreamReader(connection.getInputStream());
    BufferedReader br = new BufferedReader(isr);

    //store all string data line by line
    StringBuilder sb = new StringBuilder();
    String message = "";
    while((message = br.readLine()) !=null){
      sb.append(message);
      System.out.println(message);
    }
    br.close();
    //System.out.println(sb.toString());
  }

  public static void main(String[] args) throws Exception {
    new ProxyServer(8080);
  }



}

class MultiThreading extends Thread{
  String host = "";
  public MultiThreading(String h){
    host = h;
  }
  @Override
  public void run(){
    System.out.println(host);
    //create a connection to given URL
    URL url = null;
    try {
      url = new URL(host);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    HttpURLConnection connection = null;
    try {
      connection = (HttpURLConnection) url.openConnection();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      connection.setRequestMethod("GET");
    } catch (ProtocolException e) {
      e.printStackTrace();
    }


    InputStreamReader isr = null;
    try {
      isr = new InputStreamReader(connection.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
    BufferedReader br = new BufferedReader(isr);

    //store all string data line by line
    StringBuilder sb = new StringBuilder();
    String message = "";
    while(true){
      try {
        if (!((message = br.readLine()) !=null)) break;
      } catch (IOException e) {
        e.printStackTrace();
      }
      sb.append(message);
      System.out.println(message);
    }
    try {
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    //System.out.println(sb.toString());
  }
}

//class multThread extends Thread{
//  ProxyServer ps = new ProxyServer();
//  @Override
//  public void run(){
//    ps.httpRequest();
//  }
//}


//class MyThread extends Thread {
//  @Override
//  public void run() {
//    ProxyServer ps = null;
//    try {
//      ps = new ProxyServer();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    try {
//      ps.httpRequest("www.google.com");
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//}
