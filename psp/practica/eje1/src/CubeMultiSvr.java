import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
public class CubeMultiSvr {
    final static String STOP = ".";
    public static void main(String[] args) {
        try {
            boolean[] stop = {false};

            int port = Integer.parseInt(args[0]);

            try (ServerSocket svr = new ServerSocket(port);) {

                svr.setSoTimeout(1000);
                System.out.println("Server running at " + LocalDateTime.now());
                System.out.println("Server port: " + svr.getLocalPort());

                while (!stop[0]) {

                    try {
                        new Thread(new threadforclients(
                                svr.accept(), stop
                        )).start();

                    } catch (SocketTimeoutException e) {

                    }
                }
            }
            System.out.println("Server stopped");
        } catch (Exception e) {
            System.out.println("Port information missing");
        }
    }
}