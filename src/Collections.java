import java.util.*;

public class Collections {
    public static void main(String[] args) {

        /*ArrayList - одна из стандартных реализаций интерфейса List (тип коллекций).
        List - стандартный интерфейс из пакета java.util
        Этот интерфейс построен на базе массива элементов, элементы могут дублироваться.
        Выводы:
        1) доступ по индексу к элементам происходит практически мгновенно
        2) добавление элементов в середину массива в теории затратно, поскольку все последующие элементы сдвигаются,
        хотя на практике аррей лист неплохо оптимизирован.
        Кроме того, при нехватке элементов (переполнение массива) будет создан новый массив +10 куда копируются
        (arraycopy) старые элементы - это тоже может быть затратно. Capacity = (Х*1,5 +1) - число, на которое
        увеличивается массив при переполнении*/
        List<String> fruits1 = new ArrayList<>(); // ArrayList - реализация коллекции List
        fruits1.add("apple");
        fruits1.add("pineapple");
        fruits1.add("grape");
        fruits1.add("melon");
        fruits1.add(2, "banana"); //добавляем бананы на 3 позицию, сдвигая список на единицу ниже
        fruits1.add("apple");
        System.out.println("ArrayList " + fruits1);

        /*LinkedList построен на базе связанных друг с другом элементов, каждый элемент имеет ссылку на предыдущий
        и на следущий элементы - получается цепочка элементов.
        Выводы:
        1) обращение по индексу дорогое, поскольку это потребует пройтись по всей цепочке элементов
        2) дешевое добавление элементов в середину массива, т.е. нужно будет поменять ссылки у добавленного элемента
        На практике мало используется
        3)FILO*/
        List<String> fruits2 = new LinkedList<>();
        fruits2.add("apple");
        fruits2.add("pineapple");
        fruits2.add("grape");
        fruits2.add("melon");
        fruits2.add(2, "banana");
        fruits2.add("apple");
        System.out.println("LinkedList " + fruits2);

        /*Set - стандартный тип коллекций, который:
        1) не допускает дублей,
        2) не сохраняет порядка добавлений элементов, т.е. порядок "произвольный"

        HashSet - самый быстрый из всех реализаций Set, не сохраняет в явном виде порядок добавления элементов, он
        хранит в себе мапу и таким образом быстрее всех отвечает на запрос, хранится ли элемент Х в нем или нет,
        в отличие от List. Это связано с особой структурой этой реализации, и при нахождении элемента вся коллекция
        не проверяется, поскольку дублей в ней нет*/
        Set<String> fruits3 = new HashSet<>();
        fruits3.add("apple");
        fruits3.add("pineapple");
        fruits3.add("grape");
        fruits3.add("melon");
        fruits3.add("banana");
        fruits3.add("apple"); //элемент не будет добавлен повторно
        System.out.println("HashSet " + fruits3); //порядок вывода кажется рандомным (порядок зависит от хеша ключа)

        //Реализация коллекции Set с сохранением порядка добавления, из-за чего он медленней, чем HashSet
        Set<String> fruits4 = new LinkedHashSet<>();
        fruits4.add("apple");
        fruits4.add("pineapple");
        fruits4.add("grape");
        fruits4.add("melon");
        fruits4.add("banana");
        fruits4.add("apple"); //элемент не будет добавлен повторно
        System.out.println("LinkedHashSet " + fruits4);

        /*Реализация коллекции Set.
        TreeSet содержит в себе структуру дерева (особая структура данных), позволяет легко сортировать элементы, но с
        сохранением порядка добавления, из-за чего он медленней, чем HashSet - при добавлении элемента происходит
        сортировка. Все элементы всегда отсортированы в порядке возрастания значений. Строки сортируются в алфавитном
        порядке*/
        Set<String> fruits5 = new TreeSet<>();
        fruits5.add("apple");
        fruits5.add("pineapple");
        fruits5.add("grape");
        fruits5.add("melon");
        fruits5.add("banana");
        fruits5.add("apple"); //элемент не будет добавлен повторно
        System.out.println("LinkedHashSet " + fruits5); //порядок вывода в алфавитном порядке по первому символу

        /*Map содержит в себе ключ и значение - похоже на толковый словарь. Обращение по ключу происходит быстро.
        HashMap похож на HashSet. Порядок не сохраняется, ключи идут в "произвольном" порядке.
        !! Entry
        */
        Map<String, Integer> fruits6 = new HashMap<>();
        fruits6.put("apple", 5); // Элемент + ключ
        fruits6.put("pineapple", 9);
        fruits6.put("grape", 5);
        fruits6.put("melon", 5);
        fruits6.put("banana", 6);
        fruits6.put("apple", 4); /*если добавить через put, то элемент не будет добавлен повторно, но его ключ
        будет перезаписан последним значением*/
        fruits6.putIfAbsent("apple", 4); //Элемент яблоко будет добавлен только если его еще нет в коллекции
        System.out.println("HashMap " + fruits6); //порядок вывода не сохраняется (не соответствует порядку добавления)

        /*LinkedHashMap похож на LinkedHashSet, сохраняется порядок добавления*/
        Map<String, Integer> fruits7 = new LinkedHashMap<>();
        fruits7.putIfAbsent("apple", 5); // Элемент + ключ
        fruits7.putIfAbsent("pineapple", 9);
        fruits7.putIfAbsent("grape", 5);
        fruits7.putIfAbsent("melon", 5);
        fruits7.putIfAbsent("banana", 6);
        fruits7.putIfAbsent("apple", 4); //добавление элементы будет проигнорировано
        System.out.println("LinkedHashMap " + fruits7); //порядок вывода - по порядку добавления

        /*TreeMap похож на TreeSet, позволяет легко сортировать элементы, но с сохранением порядка добавления, из-за
        чего он медленней, чем HashSet - при добавлении элемента происходит сортировка. Все элементы всегда
        отсортированы в порядке возрастания значений. Строки сортируются в алфавитном*/
        Map<String, Integer> fruits8 = new TreeMap<>();
        fruits8.putIfAbsent("apple", 5); // Элемент + ключ
        fruits8.putIfAbsent("pineapple", 9);
        fruits8.putIfAbsent("grape", 5);
        fruits8.putIfAbsent("melon", 5);
        fruits8.putIfAbsent("banana", 6);
        System.out.println("TreeMap " + fruits8); //порядок вывода КЛЮЧЕЙ в алфавитном порядке


        /*Queue (Очередь) реализует принцип FIFO (первый вошел - первый вышел). Пример обычная очередь*/
        Queue<Integer> queue = new LinkedList<>();// интерфейс реализцется через LinkedList
        queue.add(1);
        queue.add(2);
        queue.add(3);
        while (!queue.isEmpty()) {
            System.out.println("Queue " + queue.poll());/* для просмотра следующего в очереди элемента использовать
            метод peek, но цикл будет бесконечным*/
        }

        /* Deque может быть и как стек, и как очередь, еализцет принцип FILO (первый вошел - последний вышел).
        Примеры - лифт, полка книг). Двунаправленная очередь, может работать в обе стороны, можно класть и забирать с
        обоих концов, но не из срердины*/
        Deque<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());// вывод обратного порядка, FILO
        }
    }
}
