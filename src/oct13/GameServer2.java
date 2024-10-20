package oct13;

import java.io.*;
import java.net.*;
import java.util.Random;

public class GameServer2 {
    private static int secretNumber;
    private static boolean gameOver = false;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("Сервер запущен. Ожидание подключения...");

        Random random = new Random();
        secretNumber = random.nextInt(100) + 1;
        System.out.println("Загадано число от 1 до 100");

        Socket player1Socket = serverSocket.accept();
        System.out.println("Игрок 1 подключен!");

        BufferedReader in1 = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
        PrintWriter out1 = new PrintWriter(player1Socket.getOutputStream(), true);
//!
        out1.println("Сколько будет игроков? Введите 1 для соло или 2 для двух игроков:");
        out1.flush();

        String playerCountStr = in1.readLine(); // читаем строку от клиента
        System.out.println("Выбор игрока: " + playerCountStr); // выводим на сервере для проверки
        int playerCount = Integer.parseInt(playerCountStr); // конвертируем строку в число

        if (playerCount == 1) {
            out1.println("Вы выбрали соло режим. Попробуйте угадать число от 1 до 100.");
            out1.flush(); // отправляем клиенту информацию о выборе соло режима
            playSolo(out1, in1); // запускаем соло-режим
        } else if (playerCount == 2) {
            out1.println("Ожидание подключения второго игрока...");
            out1.flush();
            Socket player2Socket = serverSocket.accept();
            System.out.println("Игрок 2 подключен!");

            BufferedReader in2 = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
            PrintWriter out2 = new PrintWriter(player2Socket.getOutputStream(), true);

            out1.println("Игрок 1, начнем игру! Попробуйте угадать число.");
            out1.flush();
            out2.println("Игрок 2, ожидайте своего хода.");
            out2.flush();

            playWithTwoPlayers(out1, in1, out2, in2);

            player2Socket.close();
        } else {
            out1.println("Неверный ввод. Перезапустите игру и выберите количество игроков 1 или 2.");
            out1.flush();
        }

        player1Socket.close();
        serverSocket.close();
    }

    private static void playSolo(PrintWriter out, BufferedReader in) throws IOException {
        while (!gameOver) {
            out.println("Введите число:");
            out.flush();
            int guess = Integer.parseInt(in.readLine());
            if (checkGuess(guess, out, "Вы")) {
                break;
            }
        }
    }

    private static void playWithTwoPlayers(PrintWriter out1, BufferedReader in1, PrintWriter out2, BufferedReader in2) throws IOException {
        while (!gameOver) {
            out1.println("Ваш ход, Игрок 1! Введите число:");
            out1.flush();
            int guess1 = Integer.parseInt(in1.readLine());
            if (checkGuess(guess1, out1, "Игрок 1")) {
                out2.println("Игра завершена. Игрок 1 угадал число!");
                out2.flush();
                break;
            }

            out2.println("Ваш ход, Игрок 2! Введите число:");
            out2.flush();
            int guess2 = Integer.parseInt(in2.readLine());
            if (checkGuess(guess2, out2, "Игрок 2")) {
                out1.println("Игра завершена. Игрок 2 угадал число!");
                out1.flush();
                break;
            }
        }
    }

    private static boolean checkGuess(int guess, PrintWriter out, String playerName) {
        if (guess == secretNumber) {
            out.println("Поздравляем, " + playerName + "! Вы угадали число: " + secretNumber);
            out.flush();
            gameOver = true;
            return true;
        } else if (guess < secretNumber) {
            out.println("Ваше число меньше загаданного.");
            out.flush();
        } else {
            out.println("Ваше число больше загаданного.");
            out.flush();
        }
        return false;
    }
}
