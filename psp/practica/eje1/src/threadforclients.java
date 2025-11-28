import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class threadforclients implements Runnable {
    // socket que atendará al cliente
    private Socket socket;
    private boolean[] stop;
    public threadforclients (Socket socket, boolean[] stop) {
        super();
        this.socket = socket;
        this.stop = stop;
    }
    Serverside serverside = new Serverside();
    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner in = new Scanner(socket.getInputStream());) {
// leemos el número del canal de entrada

            if (in.hasNextDouble()) {

                } else {
                    String s = in.nextLine();
                    if (s.equals(CubeMultiSvr.STOP)) {
                        this.stop[0] = true;
                    } else {
                        System.out.println("Bad input received. "
                                + socket.getInetAddress());

                    }
                }
            } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
// cerrar el socket
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
