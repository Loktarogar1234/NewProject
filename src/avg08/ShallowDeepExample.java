package avg08;

import java.io.*;

// Поверхностная сериализация (Shallow Serialization) При поверхностной сериализации сохраняется состояние только самого
// объекта и ссылки на другие объекты, которые он содержит. Однако содержимое этих связанных объектов не сериализуется.
// Это значит, что при десериализации такие связанные объекты будут восстановлены только как ссылки, но не их содержимое.
// Если связанные объекты изменятся после сериализации, эти изменения будут отражены при десериализации.
// Как это работает: Если объект A содержит ссылку на объект B, при поверхностной сериализации сохраняется только объект
// A и ссылка на объект B, но не сам объект B.
// Проблемы: Это может привести к неожиданным результатам, если объект, на который ссылаются, изменяется после сериализации,
// так как десериализованный объект будет ссылаться на измененную версию.

// Глубокая сериализация (Deep Serialization): Глубокая сериализация включает сериализацию как самого объекта, так и всех
// объектов, на которые он ссылается. Это означает, что все объекты, находящиеся внутри сериализуемого объекта, также будут
// сериализованы, и при десериализации они будут восстановлены в том же состоянии, что и на момент сериализации.
//Как это работает: Если объект A содержит ссылку на объект B, то при глубокой сериализации сохраняется как объект A,
// так и объект B. В результате, при десериализации будет восстановлен как объект A, так и объект B в том виде, в каком
// они были на момент сериализации.
//Преимущества: Полная копия объекта и всех его зависимостей. Это позволяет избежать проблем с изменением связанных
// объектов после сериализации.
//Недостатки: Может быть более сложной и ресурсозатратной, так как требует сериализации всех объектов, связанных с изначальным.

// Класс для демонстрации поверхностной и глубокой сериализации
class Address implements Serializable {
    String city;
    public Address(String city) {
        this.city = city;
    }
    @Override
    public String toString() {
        return city;
    }
}

class Person implements Serializable {
    String name;
    Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return name + " lives in " + address;
    }
}

public class ShallowDeepExample {

    public static void main(String[] args) {
        Address address = new Address("New York");
        PersonOne person = new PersonOne("John", address);

        // Сериализация (можно считать это поверхностной)
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.ser"))) {
            oos.writeObject(person);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Изменим исходный объект Address
        address.city = "Los Angeles";

        // Десериализация
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.ser"))) {
            PersonOne deserializedPerson = (PersonOne) ois.readObject();
            System.out.println("After Deserialization: " + deserializedPerson);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
