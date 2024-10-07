import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.File;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileReader;
import java.io.BufferedReader;

public class day04_class_problem_client {

    public static void main(String[] args) throws UnknownHostException, IOException {

        int port = 3000;
        String fileName = "";

        if (args.length >0){
            port = Integer.parseInt(args[0]);
            fileName = args[1];
        }

        Socket socket = new Socket("localhost", port);

        System.out.printf("Connected to port %d \n", port);

        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);
        
        copyFileContent(fileName, dos);

        socket.close();

    }
    
    public static void copyFileContent(String fileName, DataOutputStream dos) throws IOException{
        File file = new File(fileName);
        Long fileSize = file.length();

        System.out.println("sending a message...");
        dos.writeUTF(fileName); // <-- send the filename
        dos.flush();

        dos.writeLong(fileSize); // <-- send file size
        dos.flush();

        // send over file content
        try (FileReader fr = new FileReader(file)) {
            try (BufferedReader br = new BufferedReader(fr)) {
                String newLine = "";
                while((newLine = br.readLine())!=null){
                    dos.writeUTF(newLine);
                    dos.flush();
                }
            }
        } 
        System.out.println("message sent");

    }
}
