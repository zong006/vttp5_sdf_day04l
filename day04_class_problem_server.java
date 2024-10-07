import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileWriter;
import java.io.BufferedWriter;



public class day04_class_problem_server {
    
    public static void main(String[] args) throws IOException{

        int port = 3000;
        if (args.length >0){
            port = Integer.parseInt(args[0]);
        }

        ServerSocket server = new ServerSocket(port);
        Socket socket = server.accept();

        System.out.printf("Connected to port %d \n", port);
        
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        String copyFileDir = "copies";

        copyFileContent(copyFileDir, dis);

        socket.close();
        
    }

    public static void copyFileContent(String copyFileDir, DataInputStream dis) throws IOException{
            System.out.println("Waiting for message...");
            String fileName = dis.readUTF(); // <-- blocks code until a line is read
            Long size = dis.readLong(); // <-- read the file size 

            File dirAsFile = new File(copyFileDir);
            if (dirAsFile.exists()){
                System.out.println("Destination directory exists.");
            }
            else {
                dirAsFile.mkdir();
                System.out.println("Created directory " + copyFileDir);
            }

            String fileDestinationPath = copyFileDir + File.separator + fileName;
            File asFile = new File(fileDestinationPath); 

            String line = "";
            FileWriter fw = new FileWriter(asFile, true);
            BufferedWriter bw = new BufferedWriter(fw);

            while (true){
                try{line = dis.readUTF();
                bw.append(line);
                bw.append(System.lineSeparator());
                bw.flush();}
                catch (Exception e) {
                    break;
                }
                }
                // System.out.println("This line has been read in: " + line);
            }
    }

