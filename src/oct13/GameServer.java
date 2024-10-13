package oct13;

import java.io.*;
import java.net.*;
import java.util.Random;

public class GameServer {
    private static int secretNumber;
    private static boolean gameOver = false;

    public static void main(String[] args) throws IOException {
        // Создаем серверный сокет, который слушает порт 9090
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("Сервер запущен. Ожидание игроков...");

        Random random = new Random();
        secretNumber = random.nextInt(100) + 1;
        System.out.println("Загадано число от 1 до 100");

        // Ожидание подключения двух игроков
        // serverSocket.accept(): Этот метод приостанавливает выполнение программы и ожидает подключения клиента к
        // серверу на указанном порте (в данном случае, сервер прослушивает порт 8080). Как только клиент подключается,
        // метод возвращает объект типа Socket, который представляет это подключение.
        // player1Socket: Это объект типа Socket, который создается после того, как клиент успешно подключился. Этот
        // объект используется для общения с подключенным клиентом, предоставляя методы для чтения и записи данных.
        Socket player1Socket = serverSocket.accept();
        System.out.println("Игрок 1 подключен!");

        // Создаем потоки для общения с игроками, т.е. чтения отправленных клиентом данных и отправки данных клиенту.
        // player1Socket.getInputStream(): Этот метод возвращает поток данных (InputStream), который сервер может
        // использовать для чтения входящих данных от клиента через сокет.
        // new InputStreamReader(...): Этот объект преобразует поток байтов (InputStream) в поток символов, который
        // можно читать в виде строк.
        // new BufferedReader(...): Объект BufferedReader добавляется для того, чтобы можно было более удобно и
        // эффективно читать текстовые данные, полученные от клиента построчно (например, с помощью метода readLine()).
        // В итоге, in1 — это объект, через который сервер читает сообщения от клиента (Игрока 1) в виде строк.
        // in1.readLine(): Этот метод читает одну строку текста, отправленную клиентом через сокет
        // Integer.parseInt(...): Этот метод преобразует строку в целое число. Если клиент отправил строку "42", то она
        // будет преобразована в целое число 42.
        BufferedReader in1 = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
        PrintWriter out1 = new PrintWriter(player1Socket.getOutputStream(), true);

        // Играем, пока кто-то не угадает число
        while (!gameOver) {
            // Ход Игрока 1
            out1.println("Ваш ход, Игрок 1! Введите число:");
            // Читаем догадку клиента (строку), парсим её в целое число, и сохраняем в переменную guess1
            int guess1 = Integer.parseInt(in1.readLine());
            if (checkGuess(guess1, out1, "Игрок 1")) {
                break;
            }
        }

        // Закрытие сокетов после завершения игры
        player1Socket.close();
        serverSocket.close();
    }

    private static boolean checkGuess(int guess, PrintWriter out, String playerName) {
        if (guess == secretNumber) {
            out.println("Поздравляем, " + playerName + "! Вы угадали число: " + secretNumber);
            gameOver = true;
            return true;
        } else if (guess < secretNumber) {
            out.println("Ваше число меньше загаданного.");
        } else {
            out.println("Ваше число больше загаданного.");
        }
        return false;
    }
}
