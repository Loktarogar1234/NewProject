package avg08;
// Сериализация в Java — это процесс преобразования объекта в последовательность байтов с целью сохранения его
// состояния для последующего восстановления (десериализации).
//Типы сериализации:
//1/ Стандартная сериализаци
//2. Пользовательская сериализация (Custom Serialization)
//3. XML сериализация
//4. JSON сериализация
//5. Сериализация с использованием библиотек (например, Protocol Buffers, Avro)
//6. Сериализация с использованием Externalizable


import java.io.*;
// import javax.xml.bind.*; //зависимости через маверн нужно добавить
// import com.google.gson.Gson;

// Стандартная сериализация
//Описание: Это самый простой и распространенный способ сериализации в Java. Для того чтобы класс можно было
//сериализовать, он должен реализовать маркерный интерфейс Serializable (это значит, что он не содержит методов, которые
// необходимо реализовать, его наличие просто сигнализирует JVM о том, что объекты данного класса могут быть сериализованы,
// при этом все поля объекта записываются в поток) в том виде, в каком они присутствуют в объекте, за исключением полей,
// помеченных ключевым словом transient.
// Преимущества: Простота использования. Нет необходимости явно указывать, как сериализовать и десериализовать объекты.
//Недостатки: Может быть медленной и неэффективной с точки зрения использования памяти. Также не поддерживает
//версионность, что может вызвать проблемы при изменении структуры классов.
class StandardSerializationExample implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;

    public StandardSerializationExample(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "StandardSerializationExample{id=" + id + ", name='" + name + "'}";
    }
}

// Пользовательская сериализация.
//Позволяет разработчику контролировать процесс сериализации и десериализации, определяя методы writeObject()
//и readObject() в классе. Это может быть полезно для оптимизации или изменения логики сериализации, например, для
//сериализации только части объекта.
//Преимущества: Гибкость. Возможность контроля за процессом сериализации, возможность добавления логики.
//Недостатки: Увеличение сложности кода, потенциальные ошибки при неправильной реализации.
class CustomSerializationExample implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private transient String name; // `transient` field won't be serialized

    public CustomSerializationExample(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(name.toUpperCase()); // Custom serialization logic
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.name = ((String) ois.readObject()).toLowerCase(); // Custom deserialization logic
    }

    @Override
    public String toString() {
        return "CustomSerializationExample{id=" + id + ", name='" + name + "'}";
    }
}

// XML сериализация с использованием JAXB
//Сериализация объекта в формате XML. Это часто используется в веб-службах и конфигурационных файлах. В Java для этого
//можно использовать такие библиотеки, как JAXB (Java Architecture for XML Binding).
//Преимущества: Человекочитаемый формат, широко используется для интеграции с другими системами.
//Недостатки: Большой размер данных по сравнению с бинарной сериализацией, медленная обработка.

class XMLSerializationExample {
    private int id;
    private String name;

    public XMLSerializationExample() {}

    public XMLSerializationExample(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "XMLSerializationExample{id=" + id + ", name='" + name + "'}";
    }
}

//JSON сериализация с использованием Gson.
//Сериализация объекта в формат JSON. Это легковесный формат, который часто используется для передачи данных
//между сервером и клиентом в веб-приложениях. В Java можно использовать библиотеки, такие как Gson или Jackson, для
//сериализации объектов в JSON и обратно.
//Преимущества: Легковесный и человекочитаемый формат, широко используется в веб-разработке.
//Недостатки: Не сохраняет типы данных, требуется дополнительная обработка при десериализации.
class JSONSerializationExample {
    private int id;
    private String name;

    public JSONSerializationExample(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "JSONSerializationExample{id=" + id + ", name='" + name + "'}";
    }
}

// Сериализация с использованием Externalizable.
// Этот интерфейс предоставляет полный контроль над процессом сериализации и десериализации. Классы, которые
// реализуют интерфейс Externalizable, должны реализовать два метода: writeExternal() и readExternal().
//Преимущества: Полный контроль над процессом сериализации, возможность оптимизации.
//Недостатки: Большая сложность кода, чем в случае Serializable, риск ошибок при неправильной реализации.
class ExternalizableSerializationExample implements Externalizable {
    private int id;
    private String name;

    public ExternalizableSerializationExample() {}

    public ExternalizableSerializationExample(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException { // !1
        out.writeInt(id);
        out.writeObject(name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException { // !2
        this.id = in.readInt();
        this.name = (String) in.readObject();
    }

    @Override
    public String toString() {
        return "ExternalizableSerializationExample{id=" + id + ", name='" + name + "'}";
    }
}

// Основной класс для тестирования всех типов сериализации
public class SerializationTypesExamples {

    public static void main(String[] args) {
        standardSerialization();
        customSerialization();
        //xmlSerialization();
        //jsonSerialization();
        //externalizableSerialization();
    }

    private static void standardSerialization() {
        StandardSerializationExample example = new StandardSerializationExample(1, "John Doe");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("standard.ser"))) {
            oos.writeObject(example);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("standard.ser"))) {
            StandardSerializationExample deserializedExample = (StandardSerializationExample) ois.readObject();
            System.out.println("Standard Serialization: " + deserializedExample);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void customSerialization() {
        CustomSerializationExample example = new CustomSerializationExample(1, "John Doe");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("custom.ser"))) {
            oos.writeObject(example);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("custom.ser"))) {
            CustomSerializationExample deserializedExample = (CustomSerializationExample) ois.readObject();
            System.out.println("Custom Serialization: " + deserializedExample);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
/*
    private static void xmlSerialization() {
        XMLSerializationExample example = new XMLSerializationExample(1, "John Doe");

        try {
            JAXBContext context = JAXBContext.newInstance(XMLSerializationExample.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(example, new File("example.xml"));

            Unmarshaller unmarshaller = context.createUnmarshaller();
            XMLSerializationExample deserializedExample = (XMLSerializationExample) unmarshaller.unmarshal(new File("example.xml"));
            System.out.println("XML Serialization: " + deserializedExample);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void jsonSerialization() {
        JSONSerializationExample example = new JSONSerializationExample(1, "John Doe");

        Gson gson = new Gson();

        String jsonString = gson.toJson(example);
        System.out.println("Serialized JSON: " + jsonString);

        JSONSerializationExample deserializedExample = gson.fromJson(jsonString, JSONSerializationExample.class);
        System.out.println("JSON Serialization: " + deserializedExample);
    }

    private static void externalizableSerialization() {
        ExternalizableSerializationExample example = new ExternalizableSerializationExample(1, "John Doe");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("externalizable.ser"))) {
            oos.writeObject(example);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("externalizable.ser"))) {
            ExternalizableSerializationExample deserializedExample = (ExternalizableSerializationExample) ois.readObject();
            System.out.println("Externalizable Serialization: " + deserializedExample);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

 */
}
