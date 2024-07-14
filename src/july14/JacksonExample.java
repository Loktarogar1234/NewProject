package july14;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;

class User {
    private String name;
    private int age;
    private String email;

    // Конструкторы, геттеры и сеттеры
    public User() {
    }

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}

public class JacksonExample {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        // Сериализация объекта в JSON
        User user = new User("John Doe", 30, "john.doe@example.com");
        try {
            String jsonString = objectMapper.writeValueAsString(user);
            System.out.println("JSON: " + jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Десериализация JSON в объект
        String jsonString = "{\"name\":\"John Doe\",\"age\":30,\"email\":\"john.doe@example.com\"}";
        try {
            User userFromJson = objectMapper.readValue(jsonString, User.class);
            System.out.println("User: " + userFromJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Сериализация списка объектов в JSON
        List<User> users = Arrays.asList(
                new User("John Doe", 30, "john.doe@example.com"),
                new User("Jane Smith", 25, "jane.smith@example.com")
        );
        try {
            String jsonListString = objectMapper.writeValueAsString(users);
            System.out.println("JSON List: " + jsonListString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Десериализация JSON в список объектов
        String jsonListString = "[{\"name\":\"John Doe\",\"age\":30,\"email\":\"john.doe@example.com\"}," +
                "{\"name\":\"Jane Smith\",\"age\":25,\"email\":\"jane.smith@example.com\"}]";
        try {
            List<User> usersFromJson = objectMapper.readValue(jsonListString, objectMapper.getTypeFactory().
                    constructCollectionType(List.class, User.class));
            System.out.println("Users: " + usersFromJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
