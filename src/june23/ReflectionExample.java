package june23;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// Reflection API позволяет динамически исследовать и изменять структуру и поведение классов, интерфейсов и объектов
// во время выполнения программы. Это мощный инструмент для создания гибких и динамических программ, но его следует
// использовать с осторожностью из-за потенциальных проблем с производительностью и безопасностью.

public class ReflectionExample {
    public static void main(String[] args) {
        try {
            // Получение информации о классе ExampleClass
            Class<?> clazz = ExampleClass.class;

            // Создание экземпляра класса ExampleClass
            Object instance = clazz.getDeclaredConstructor().newInstance();

            // Вывод всех полей класса, включая приватные
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // Позволяет получить доступ к приватным полям
                Object value = field.get(instance);
                System.out.println("Field: " + field.getName() + ", Value: " + value);
            }

            // Вывод всех методов класса, включая приватные и методы с аннотациями
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                method.setAccessible(true); // Позволяет получить доступ к приватным методам
                System.out.println("Method: " + method.getName());
                if (method.isAnnotationPresent(MyAnnotation.class)) {
                    System.out.println("Method " + method.getName() + " is annotated with @MyAnnotation");
                }
            }

            // Вызов приватного метода
            Method privateMethod = clazz.getDeclaredMethod("privateMethod");
            privateMethod.setAccessible(true); // Позволяет вызвать приватный метод
            privateMethod.invoke(instance);

            // Вызов публичного метода с аннотацией
            Method publicAnnotatedMethod = clazz.getDeclaredMethod("publicAnnotatedMethod");
            publicAnnotatedMethod.invoke(instance);

            // Вывод всех конструкторов класса
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println("Constructor: " + constructor.getName());
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}

// Класс для Reflection API
class ExampleClass {
    private String privateField = "Private Field Value";

    //Аннотация к приватному методу
    @MyAnnotation(author = "John Doe", date = "01.02.2020")
    private void privateMethod() {
        System.out.println("Private Method Called");
    }

    //Аннотация к методу
    @MyAnnotation(author = "John Smith", date = "03.04.2024")
    public void publicAnnotatedMethod() {
        System.out.println("Public Annotated Method Called");
    }

    public ExampleClass() {
    }
}
// Аннотация - добавление метаданных к коду (классы, методы, поля, параметры и конструкторы). Они не изменяют само
// поведение кода, но могут быть использованы различными инструментами и фреймворками для генерации кода, проверки
// ошибок, выполнения времени выполнения и других задач.
// Пример аннотации метода
@interface MyAnnotation {
    String author();
    String date();
    int version() default 1;
}