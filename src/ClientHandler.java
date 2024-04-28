import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // Handle client requests and send responses
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String request;
            while ((request = in.readLine()) != null) {
                // Process the request and generate response
                String response = processRequest(request);

                // Send back the response
                out.println(response);
            }

            // Close the connection
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processRequest(String request) {
        // Split the request into parts
        String[] parts = request.split("#");
        
        // Extract sender, receiver, data, and timestamp
        String sender = parts[0];
        String receiver = parts[1];
        String data = parts[2];
        String timestamp = getCurrentTimestamp();

        // Generate the response
        return "Sender: " + sender + "\nReceiver: " + receiver + "\nData: " + data + "\nTimestamp: " + timestamp;
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
