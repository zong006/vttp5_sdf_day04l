import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {

    public static void main(String[] args) throws UnknownHostException, IOException{


        // create socket 
        Socket sock = new Socket("localhost", 5001);

        Console con = System.console();
        String msg = con.readLine(">>> ");
        msg += "\n"; // <--- need to concat with new line else server side cannot read 

        OutputStream os = sock.getOutputStream();
        Writer wr = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(wr);
        // BufferedOutputStream bos = new BufferedOutputStream(os);

        InputStream is = sock.getInputStream();
        Reader read = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(read);


        bw.write(msg);
        bw.flush(); // <-- flush it into the  network
        bw.close();

        sock.close();


        // public class 



    }

}