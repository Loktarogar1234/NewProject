package avg08;
// ObjectMapper — это основной класс в библиотеке Jackson, которая является популярным инструментом для работы с JSON
// в Java. ObjectMapper предоставляет множество методов для преобразования объектов Java в JSON и обратно.
//Основные возможности ObjectMapper:
//1. Преобразование объектов Java в JSON (сериализация):
//ObjectMapper позволяет легко преобразовать объект Java в строку JSON или записать его в файл.
//2. Преобразование JSON в объекты Java (десериализация):
//ObjectMapper также может принимать строку JSON (или другой источник JSON) и преобразовывать её в объект Java.
//3. Поддержка сложных типов данных:
//ObjectMapper поддерживает сериализацию и десериализацию сложных типов данных, включая коллекции, карты (Map),
// вложенные объекты и даже объекты с полями, отмеченными аннотациями.
// В Jackson есть возможность игнорировать поля со значением null при сериализации. Это полезно для уменьшения размера
// JSON или избежания включения пустых полей.

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperExample {

    public static void main(String[] args) {
        try {
            PersonTwo person = new PersonTwo("John Doe", null); // Поле age будет null
            person.setAddress(""); // Пустая строка

            // Создаем ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Настраиваем ObjectMapper для игнорирования null значений и других пустых данных
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

            // Преобразуем объект в строку JSON
            String jsonString = objectMapper.writeValueAsString(person);
            System.out.println("Serialized JSON with NON_EMPTY: " + jsonString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class PersonTwo {
    private String name;
    private Integer age;
    private String address;

    // Конструктор по умолчанию
    public PersonTwo() {}

    // Конструктор
    public PersonTwo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}