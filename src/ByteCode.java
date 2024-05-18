public class ByteCode {
    public static void main(String[] args) {
        int i = 0;
        i++;
        i--;
        System.out.println(i);
    }
}

/*
// class version 65.0 (65)
// access flags 0x21
public class SomeCode {

  // compiled from: SomeCode.java

  // access flags 0x1
  public <init>()V
   L0
    LINENUMBER 1 L0
    ALOAD 0
    INVOKESPECIAL java/lang/Object.<init> ()V
    RETURN
   L1
    LOCALVARIABLE this LSomeCode; L0 L1 0
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 0x9
  public static main([Ljava/lang/String;)V
 L0
    LINENUMBER 3 L0
    ICONST_0                  // Помещает константу 0 в стек операндов
    ISTORE 1                  // Сохраняет значение из стека в локальной переменной 1 (i)
   L1
    LINENUMBER 4 L1
    IINC 1 1                  // Увеличивает локальную переменную 1 (i) на 1
   L2
    LINENUMBER 5 L2
    IINC 1 -1                 // Уменьшает локальную переменную 1 (i) на 1
   L3
    LINENUMBER 6 L3
    GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
    // Получает статическое поле System.out из класса System
    ILOAD 1                   // Загружает значение переменной i в стек
    INVOKEVIRTUAL java/io/PrintStream.println (I)V // Вызывает метод println для вывода значения i
   L4
    LINENUMBER 7 L4
    RETURN                    // Возвращает управление из метода
   L5
    LOCALVARIABLE args [Ljava/lang/String; L0 L5 0 // Локальная переменная 'args' типа массив String[]
    LOCALVARIABLE i I L1 L5 1  // Локальная переменная 'i' типа int
    MAXSTACK = 2               // Максимальный размер стека для этого метода
    MAXLOCALS = 2              // Максимальное количество локальных переменных
}

 */