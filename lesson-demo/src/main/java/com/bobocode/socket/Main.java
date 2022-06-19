package com.bobocode.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8090);) {
            var pool = Executors.newFixedThreadPool(50);
            while (true) {
                Socket socket = serverSocket.accept();
                pool.submit(() -> processRequest(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void processRequest(Socket socket) {
        try {
            socket.setSoTimeout(2_000);
            var inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String value = bufferedReader.readLine();

            String paramValue = null;

            while(value != null && !value.isBlank()) {
                if (value.contains("GET ")){
                    String url = value.split(" ")[1];
                    if(url.contains("?")){
                        String queryParam = url.split("\\?")[1];
                        paramValue = queryParam.split("=")[1];
                    }
                }

                System.out.println(value);
                value = bufferedReader.readLine();
            }
            sendResponse(socket, paramValue);

            bufferedReader.close();
        } catch (Exception e) {
            System.err.println("Socket exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket");
            }
        }
    }

    private static void sendResponse(Socket socket, String paramValue) throws IOException {
        var outputStream = socket.getOutputStream();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            bufferedWriter.write("HTTP/1.1 200 OK\r\n");
            bufferedWriter.write("Date: Sun, 19 Jun 2022 01:17:00 GMT\r\n");
            bufferedWriter.write("Server: Andrii Tsepukh and Co\r\n");
            bufferedWriter.write("Content-Type: text/html\r\n");
            bufferedWriter.write("\r\n");
            if (paramValue != null) {
                bufferedWriter.write("Hello, " + paramValue + "\r\n");
            } else {
                bufferedWriter.write("Hello\r\n");
            }
            bufferedWriter.flush();
            System.out.println("------------------ Response was sent ------------------");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
