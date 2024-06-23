package theBeginning;

import java.util.ArrayList;
import java.util.List;

// Класс Engine представляет двигатель автомобиля с типом двигателя
class Engine {
    private String type;

    public Engine(String type) {
        this.type = type; // Инициализация типа двигателя
    }

    public String getType() {
        return type; // Возвращает тип двигателя
    }
}

// Класс Car1 содержит объект Engine как компонент
class Car1 {
    private Engine engine;

    public Car1(String engineType) {
        this.engine = new Engine(engineType); // Создает новый Engine при создании Car1
    }

    public Engine getEngine() {
        return engine; // Возвращает ссылку на встроенный объект Engine
    }
}

// Класс Professor представляет преподавателя с именем
class Professor {
    private String name;

    public Professor(String name) {
        this.name = name; // Инициализация имени преподавателя
    }

    public String getName() {
        return name; // Возвращает имя преподавателя
    }
}

// Класс Department агрегирует список профессоров
class Department {
    private List<Professor> professors;

    public Department(List<Professor> professors) {
        this.professors = new ArrayList<>(professors); // Инициализация списка профессоров
    }

    public List<Professor> getProfessors() {
        return new ArrayList<>(professors); // Возвращает копию списка профессоров для защиты инкапсуляции
    }
}

// Главный класс для демонстрации работы с классами Car1 и Department
public class University {
    public static void main(String[] args) {
        // Создание и использование объекта Car1
        Car1 car = new Car1("V6");
        System.out.println("Car has an engine of type: " + car.getEngine().getType());

        // Создание и использование объекта Department с профессорами
        List<Professor> professors = List.of(new Professor("Dr. Smith"), new Professor("Dr. Johnson"));
        Department department = new Department(professors);
        System.out.println("Department has professors:");
        department.getProfessors().forEach(professor -> System.out.println(professor.getName()));
    }
}

// Агрегация и композиция — это два термина, используемых в объектно-ориентированном программировании для
// описания отношений "имеет-а" (has-a) между объектами. Оба эти термина описывают связь, когда один класс
// содержит или использует объекты другого класса, но отличаются степенью зависимости между этими объектами.
//
// Композиция — это более строгий вид отношения между классами, где один класс содержит другой класс, и
// жизненный цикл содержащего объекта управляет жизненным циклом вложенного объекта. Если "родительский"
// объект уничтожается, то уничтожаются и все "дочерние" объекты. Композиция используется, когда один объект
// является частью другого объекта.
//
// Агрегация — это более слабая форма отношения, где класс содержит или использует другие классы, но без
// строгого владения. Это означает, что жизненный цикл вложенных объектов не управляется жизненным циклом
// контейнерного объекта. Объекты могут быть созданы и уничтожены независимо друг от друга.
