package july14;
//  Сериализация и десериализация — это процессы преобразования объектов в поток байтов и обратно. Эти процессы
//  используются для сохранения состояния объектов и передачи их по сети или сохранения в файлы

import java.io.*;

class Person implements Serializable { // класс Person реализует интерфейс Serializable
    @Serial
    private static final long serialVersionUID = 1L; // Уникальный идентификатор версии для сериализируемого класса
    private final String name; // приватные поля name и age, которые будут сериализироваться
    private transient int age; // Поле age не будет сериализовано

    public Person(String name, int age) { // конструктор
        this.name = name;
        this.age = age;
    }
    // Пользовательский метод сериализации
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(age); // Явная сериализация поля age
    }
    // Пользовательский метод десериализации
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        age = in.readInt(); // Явная десериализация поля age
    }

    public String toString() { // переопределяет метод toString для удобства
        return "Person{name='" + name + "', age=" + age + '}';
    }
}

public class SerializationExample {
    public static void main(String[] args) {
        Person person = new Person("John", 30);

        // Сериализация объекта.
        // Открываем блок try-with-resources, создавая объект ObjectOutputStream, который оборачивает FileOutputStream.
        // FileOutputStream создает или открывает файл с именем "person.ser" для записи. ObjectOutputStream используется
        // для сериализации объектов в байтовый поток.
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("person.ser"))) {
            out.writeObject(person);
            // Используем метод writeObject объекта ObjectOutputStream для записи (сериализации) объекта person
            // в файл "person.ser".
            System.out.println("Object serialized successfully");
        } catch (IOException e) {
            // Ловим InvalidClassException если версии класса и сериализованного объекта не совпадают
            e.printStackTrace();
        }

        // Десериализация объекта. FileInputStream открывает файл "person.ser" для чтения.
        // ObjectInputStream используется для десериализации объектов из байтового потока.
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("person.ser"))) {
            person = (Person) in.readObject();
            System.out.println("Object deserialized successfully");
            System.out.println("Person: " + person);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

