/**
 * Clase para guardar datos de uso del servidor EchoServer
 */
public class EchoData {
    private int totalMessages;
    private int totalCharacters;
    private long startTime;
    private long endTime;

    public EchoData() {
        this.totalMessages = 0;
        this.totalCharacters = 0;
        this.startTime = System.currentTimeMillis();
    }

    // Método sincronizado para contar mensajes correctamente en entorno multi-thread
    public synchronized void addMessage(String message) {
        if (message != null) {
            this.totalMessages++;
            this.totalCharacters += message.length();
        }
    }

    public synchronized void incrementMessages() {
        this.totalMessages++;
    }

    public synchronized void addCharacters(int count) {
        this.totalCharacters += count;
    }

    public void setEndTime() {
        this.endTime = System.currentTimeMillis();
    }

    public int getTotalMessages() {
        return totalMessages;
    }

    public int getTotalCharacters() {
        return totalCharacters;
    }

    public long getExecutionTime() {
        return endTime - startTime;
    }

    @Override
    public String toString() {
        return String.format("Mensajes procesados: %d | Caracteres totales: %d | Tiempo: %d ms",
                totalMessages, totalCharacters, getExecutionTime());
    }
}