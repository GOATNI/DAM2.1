import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
public class CubeMultiSvrThread implements Runnable {
    // socket que atendará al cliente
    private Socket socket;
    private boolean[] stop;
    public CubeMultiSvrThread(Socket socket, boolean[] stop) {
        super();
        this.socket = socket;
        this.stop = stop;
    }
    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner in = new Scanner(socket.getInputStream());) {
// leemos el número del canal de entrada
            if (in.hasNextDouble()) {
                double num = in.nextDouble();
                in.nextLine();
// calculamos el cubo
                double cube = num * num * num;
// formateamos el número ya que Scanner
// trabaja con la configuración regional de la máquina
                NumberFormat nf = NumberFormat.getInstance(new Locale("es", "ES"));
// escribimos el resultado en el canal de salida
                out.println(nf.format(cube));
            } else {
                String s = in.nextLine();
                if (s.equals(CubeMultiSvr.STOP)) {
                    this.stop[0] = true;
                } else {
                    System.out.println("Bad input received. "
                            + socket.getInetAddress());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
// cerrar el socket
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}