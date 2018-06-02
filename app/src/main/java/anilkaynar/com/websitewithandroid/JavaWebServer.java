package anilkaynar.com.websitewithandroid;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
class connectionAc extends Thread{
    public connectionAc(ServerSocket socket) {
        this.socket = socket;
    }

    ServerSocket socket;
    private  void HandleRequest(Socket s)
    {
        BufferedReader in;
        PrintWriter out;
        String request;

        try
        {
            String webServerAddress = s.getInetAddress().toString();
            System.out.println("New Connection:" + webServerAddress);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            request = in.readLine();
            System.out.println("--- Client request: " + request);

            out = new PrintWriter(s.getOutputStream(), true);
            out.println("HTTP/1.0 200");
            out.println("Content-type: text/html");
            out.println("Server-name: myserver");
            String response = "<html>"
                    + "<head>"
                    + "<title>My Web Server</title></head>"
                    + "<h1>Ekle Ekleyebildiğin kadar</h1>"
                    +"<p>Sorulacak sorunlar </p>"
                    + "</html>n";
            out.println("Content-length: " + response.length());
            out.println("");
            out.println(response);
            out.flush();
            out.close();
            s.close();
        }
        catch (IOException e)
        {
            System.out.println("Failed respond to client request: " + e.getMessage());
        }
        finally
        {
            if (s != null)
            {
                try
                {
                    s.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return;
    }
    @Override
    public void run() {
        try {
            while(true) {
                final Socket connection = socket.accept();
                HandleRequest(connection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
public class JavaWebServer
{

    private static final int fNumberOfThreads = 15;
    private static final Executor fThreadPool = Executors.newFixedThreadPool(fNumberOfThreads);

    public  void main2(String[] args) throws IOException
    {
        ServerSocket socket = new ServerSocket(1812);



            fThreadPool.execute(new connectionAc(socket));

    }

    private  void HandleRequest(Socket s)
    {
        BufferedReader in;
        PrintWriter out;
        String request;

        try
        {
            String webServerAddress = s.getInetAddress().toString();
            System.out.println("New Connection:" + webServerAddress);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            request = in.readLine();
            System.out.println("--- Client request: " + request);

            out = new PrintWriter(s.getOutputStream(), true);
            out.println("HTTP/1.0 200");
            out.println("Content-type: text/html");
            out.println("Server-name: myserver");
            String response = "<html>"
                    + "<head>"
                    + "<title>My Web Server</title></head>"
                    + "<h1>Ekle Ekleyebildiğin kadar</h1>"
                    +"<p>Sorulacak sorunlar </p>"
                    + "</html>n";
            out.println("Content-length: " + response.length());
            out.println("");
            out.println(response);
            out.flush();
            out.close();
            s.close();
        }
        catch (IOException e)
        {
            System.out.println("Failed respond to client request: " + e.getMessage());
        }
        finally
        {
            if (s != null)
            {
                try
                {
                    s.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return;
    }
   public void ip444(){


       try {
           Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();  // gets All networkInterfaces of your device
           while (networkInterfaces.hasMoreElements()) {
               NetworkInterface inet = (NetworkInterface) networkInterfaces.nextElement();
               Enumeration address = inet.getInetAddresses();
               while (address.hasMoreElements()) {
                   InetAddress inetAddress = (InetAddress) address.nextElement();
                   if (inetAddress.isSiteLocalAddress()) {
                      Log.e("Kadar","Your ip: " + inetAddress.getHostAddress());
                   }
               }
           }
       } catch (Exception e) {
           // Handle Exception
       }
   }
}
