package oct13;
import java.io.*;
import java.net.*;

public class GameClient {
    public static void main(String[] args) throws IOException {


        Socket socket = new Socket("localhost", 9090);

        // Создаем поток для получения сообщений от сервера
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // Создаем поток для отправки данных на сервер
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        // Создаем поток для чтения ввода с консоли
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        String serverMessage;

        // Читаем сообщения от сервера и отправляем свои догадки
        while ((serverMessage = in.readLine()) != null) {  // Пока сервер отправляет данные
            System.out.println(serverMessage);  // Выводим сообщение от сервера на экран
            if (serverMessage.contains("Введите число:")) {  // Если сервер запрашивает ввод числа
                String guess = console.readLine();  // Читаем число с консоли
                out.println(guess);  // Отправляем введенное число на сервер
            }
            if (serverMessage.contains("Поздравляем")) {  // Если сервер сообщил о победе
                break;  // Завершаем игру
            }
        }

        // Закрываем соединение с сервером
        socket.close();
    }
}
