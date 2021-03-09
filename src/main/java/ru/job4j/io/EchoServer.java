package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Set<String> resp = new HashSet<>();
                boolean check = true;
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        resp.add(str);
                    }
                    if (resp.toString().matches(".*Hello\\s.*")) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write("Hello, dear friend.".getBytes());
                    } else if ((resp.toString().matches(".*Exit\\s.*"))) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write("Bye!".getBytes());
                        server.close();
                    } else if ((resp.toString().matches("(?!.*Exit\\s.*|.*Hello\\s.*).*"))) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write("What?".getBytes());
                    }
                }
            }
            System.exit(-1);
        }
    }
}
