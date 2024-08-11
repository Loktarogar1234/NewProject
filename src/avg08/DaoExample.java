package avg08;
// Паттерн DAO (Data Access Object) — это шаблон проектирования, который используется для абстрагирования доступа к данным
// в приложении. Этот паттерн помогает отделить логику работы с базой данных от бизнес-логики, предоставляя интерфейс
// для взаимодействия с источниками данных (например, с базами данных, файлами, веб-сервисами). Основная идея заключается
// в создании объекта, который отвечает за все операции с данными для конкретной сущности.
//
//Основные компоненты паттерна DAO:
//1. Интерфейс DAO (DAO Interface):
//- Интерфейс, который определяет методы для доступа к данным. Этот интерфейс описывает контракт, который конкретный DAO-класс должен реализовать.
//- Методы обычно включают CRUD-операции: Create, Read, Update, Delete.
//2. Реализация DAO (DAO Implementation):
//-Класс, который реализует интерфейс DAO. Этот класс содержит логику взаимодействия с источником данных (например, с базой данных с использованием SQL).
//- Этот класс скрывает детали работы с данными от остальной части приложения.
//3. Модель данных (Data Model) - Класс, представляющий сущность данных, например, User, Product, Order, и т.д. Это объекты, которые DAO-класс будет обрабатывать.
//4. Клиент (Service Layer) - Это уровень приложения, который использует DAO для выполнения операций над данными. Клиент вызывает методы DAO, не заботясь о том, как именно данные сохраняются или извлекаются.

//Преимущества паттерна DAO:
//- Разделение ответственности. Бизнес-логика отделена от логики работы с данными. Это делает код более модульным и упрощает его поддержку.
//- Удобство тестирования. Благодаря интерфейсам и абстракции, можно легко тестировать бизнес-логику, подставляя mock-объекты вместо реальных DAO.
//- Универсальность. DAO можно использовать для работы с разными источниками данных, например, базами данных, файлами, веб-сервисами, без изменения бизнес-логики.
//- Повторное использование. Код для работы с данными для конкретной сущности сосредоточен в одном месте, что облегчает его повторное использование и поддержку.

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Модель данных (Data Model)
class User {
    private int id;
    private String name;
    private String email;

    // Конструктор по умолчанию
    public User() {}

    // Пользовательский конструктор
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

// Интерфейс DAO
interface UserDAO {
    void addUser(User user); // Создание пользователя
    User getUser(int id); // Получение пользователя по ID
    List<User> getAllUsers(); // Получение всех пользователей
    void updateUser(User user); // Обновление данных пользователя
    void deleteUser(int id); // Удаление пользователя по ID
}

// Реализация DAO
class UserDAOImpl implements UserDAO {

    private Connection connection;
    // Это поле класса UserDAOImpl, которое хранит объект типа Connection. Connection — это объект из библиотеки JDBC,
    // который представляет собой соединение с базой данных.

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Основной класс для использования DAO
public class DaoExample {
    public static void main(String[] args) {
        // Настройка соединения с базой данных
        try (Connection connection = DriverManager.getConnection("jdbc:your_database_url", "username", "password")) {
            UserDAO userDAO = new UserDAOImpl(connection);

            // Создание нового пользователя
            User newUser = new User(0, "John Doe", "john.doe@example.com");
            userDAO.addUser(newUser);

            // Получение пользователя по ID
            User user = userDAO.getUser(1);
            if (user != null) {
                System.out.println("User: " + user.getName() + ", Email: " + user.getEmail());
            }

            // Обновление данных пользователя
            user.setEmail("john.newemail@example.com");
            userDAO.updateUser(user);

            // Получение всех пользователей
            List<User> users = userDAO.getAllUsers();
            for (User u : users) {
                System.out.println(u.getName() + " - " + u.getEmail());
            }

            // Удаление пользователя по ID
            userDAO.deleteUser(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
