package theBeginning;

public class Hooks {
    public static void main(String[] args) {
        /*
        Hook method обычно относится к методу, который предоставляется с целью быть переопределённым в
        производных классах для изменения поведения компонента или программы без изменения его общей
        структуры. Это один из основных механизмов, который используется в шаблоне проектирования
        "шаблонный метод" (Template Method).
        */
        }
}

class Game {
    // Шаблонный метод
    void playOneGame(int playersCount) {
        initializeGame();
        playGame();
        if (addNewCharacter(playersCount)) {
            addCharacter();
        }
        endOfGame(); // Hook метод
    }

    void initializeGame() {
        // инициализация игры
    }

    void playGame() {
        // основной игровой процесс
    }

    boolean addNewCharacter(int playersCount) {
        return playersCount > 4; // условие для добавления нового персонажа
    }

    void addCharacter() {
        // добавление нового персонажа
    }

    // Hook метод, который может быть переопределён
    void endOfGame() {
        System.out.println("The game has ended.");
    }
}

class Chess extends Game {
    // Переопределение hook метода
    @Override
    void endOfGame() {
        System.out.println("Chess game has ended. Checkmate!");
    }
}


class ShutdownHookExample {
    public ShutdownHookExample(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("JVM is shutting down!");
        }));
        System.out.println("Application is running");
    }
}