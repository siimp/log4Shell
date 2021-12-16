package ee.siimp.rce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RceServer {

    private static final int DEFAULT_PORT = 8082;

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = args.length == 0 ? DEFAULT_PORT : Integer.parseInt(args[0]);
        ServerSocket serverSocket = new ServerSocket(port);

        Socket socket = serverSocket.accept();

        try (
                BufferedReader cmdIn = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            new Thread(() -> {
                in.lines().forEach(System.out::println);
            }).start();

            while (!socket.isClosed()) {
                System.out.print("rce>");
                String command = cmdIn.readLine();
                out.println(command);
                Thread.sleep(100);
            }
        }
    }
}
