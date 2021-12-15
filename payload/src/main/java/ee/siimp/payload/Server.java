package ee.siimp.payload;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executors;

public class Server {

    private static final int DEFAULT_PORT = 8081;

    public static void main(String[] args) throws IOException {
        int port = args.length == 0 ? DEFAULT_PORT : Integer.parseInt(args[0]);
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", port), 0);
        server.createContext("/", new PayloadHttpHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        System.out.println("Starting server at port " + port);
        server.start();
    }

    static class PayloadHttpHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            byte[] payloadClassFile = Files.readAllBytes(Paths.get(
                    ".", "src", "main", "java", "Payload.class"));
            OutputStream outputStream = exchange.getResponseBody();
            exchange.sendResponseHeaders(200, payloadClassFile.length);
            outputStream.write(payloadClassFile);
            outputStream.flush();
            outputStream.close();
        }
    }

}
