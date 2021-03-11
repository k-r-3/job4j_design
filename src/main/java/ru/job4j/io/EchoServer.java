package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    public static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Queue<String> query = new ArrayDeque<>();
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (query.isEmpty()) {
                            query.offer(str);
                        }
                    }
                    if (query.peek().matches(".*Hello\\s.*")) {
                        out.write(new EchoServer()
                                .resp("Hello, dear friend")
                                .getBytes());
                    } else if ((query.peek().matches(".*Exit\\s.*"))) {
                        out.write(new EchoServer()
                                .resp("Bye!")
                                .getBytes());
                        server.close();
                    } else if ((query.peek().matches("(?!.*Exit\\s.*|.*Hello\\s.*).*"))) {
                        out.write(new EchoServer()
                                .resp("What?")
                                .getBytes());
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Exception  in server block : ", e);
        }
    }

    private String resp(String msg) {
        return String.format("%s %s",
                "HTTP/1.1 200 OK\r\n\r\n", msg);
    }
}

