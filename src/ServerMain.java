import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main (String[] args) throws IOException{
    
        //  default port number
        int port = 3000;
        if (args.length>0){
            port = Integer.parseInt(args[0]);
        }
        System.out.printf("listening on port %d \n", port);
    
        // create server 
    
        ServerSocket server = new ServerSocket(port);
    
        // wait for connection. returns a socket 
        System.out.println("waiting for connection");
        Socket sock = server.accept();
    
        System.out.println("established connection");

        InputStream is = sock.getInputStream();
        Reader read = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(read);

        OutputStream os = sock.getOutputStream();
        Writer wr = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(wr);

        String msg = br.readLine();

        System.out.println("From Client: ");
        System.out.println(msg);

        sock.close();

        





    
    
    
    }
    
}
