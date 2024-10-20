package oct13;

import java.io.*;
import java.net.*;

public class GameClient2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9090);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        String serverMessage;

        while ((serverMessage = in.readLine()) != null) {
            System.out.println(serverMessage);
            //
            if (serverMessage.contains("Введите число:")) {
                String guess = console.readLine();
                out.println(guess);
                out.flush();
            }
            if (serverMessage.contains("Поздравляем")) {
                break;
            }
        }

        socket.close();
    }
}
