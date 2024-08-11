package july14;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class SomeTestTable implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int tagID;
    private final String name;
    private final int counter;

    public SomeTestTable(int tagID, String name, int counter) { // конструктор
        this.tagID = tagID;
        this.name = name;
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "SomeTestTable{tagID=" + tagID + ", name='" + name + "', counter=" + counter + '}';
    }
}

public class JDBCDemo {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456Qq";

    public static void main(String[] args) {
        List<SomeTestTable> objects = new ArrayList<>(); // Создание списка для хранения объектов

        // Загрузка драйвера базы данных
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Подключение к базе данных
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Добавление нового объекта в таблицу
            String insertSQL = "INSERT INTO SomeTestTable (tagID, name, counter) VALUES (3, 'troll', 5)";
            statement.executeUpdate(insertSQL);

            // Извлечение всех объектов из таблицы
            String selectSQL = "SELECT tagID, name, counter FROM SomeTestTable";
            ResultSet resultSet = statement.executeQuery(selectSQL);

            while (resultSet.next()) { // Обработка результатов выборки
                int tagID = resultSet.getInt("tagID"); // Получение значения столбца tagID
                String name = resultSet.getString("name"); // Получение значения столбца name
                int counter = resultSet.getInt("counter"); // Получение значения столбца counter
                objects.add(new SomeTestTable(tagID, name, counter)); // Добавление объекта в список
            }
            resultSet.close(); // Закрытие объекта ResultSet

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Сериализация объектов в файл
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("objects.ser"))) {
            // Создание потока для сериализации объектов в файл
            out.writeObject(objects); // Сериализация списка объектов
            System.out.println("Objects serialized successfully"); // Сообщение об успешной сериализации
        } catch (IOException e) {
            e.printStackTrace(); // Обработка исключений ввода-вывода
        }

        // Десериализация объектов из файла
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("objects.ser"))) {
            // Создание потока для десериализации объектов из файла
            List<SomeTestTable> deserializedObjects = (List<SomeTestTable>) in.readObject();
            // Десериализация списка объектов
            System.out.println("Objects deserialized successfully"); // Сообщение об успешной десериализации
            for (SomeTestTable obj : deserializedObjects) { // Вывод десериализованных объектов
                System.out.println(obj); // Печать объекта
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Обработка исключений ввода-вывода и класса
        }
    }
}
