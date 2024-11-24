package oct13;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

// HttpServletRequest и HttpServletResponse — это два ключевых интерфейса в Java, которые используются для обработки
// HTTP-запросов и формирования HTTP-ответов в веб-приложениях, основанных на сервлетах. Они предоставляют методы для
// взаимодействия с клиентом и сервером

// HttpServletRequest представляет собой объект, который содержит информацию о запросе, поступившем от клиента (веб-браузера).
// Он предоставляет методы для получения данных, связанных с запросом.
// Основные методы HttpServletRequest:
// Получение параметров запроса:
// - String getParameter(String name): Получает значение параметра запроса по его имени.
// - String[] getParameterValues(String name): Получает массив значений параметра запроса (если он имеет несколько значений).
// Получение информации о заголовках:
// - String getHeader(String name): Получает значение заголовка запроса по его имени.
// - Enumeration<String> getHeaderNames(): Возвращает перечисление всех имен заголовков запроса.
// Получение информации о клиенте:
// - String getRemoteAddr(): Получает IP-адрес клиента.
// - String getRemoteHost(): Получает имя хоста клиента.
// Получение информации о сессии:
// - HttpSession getSession(): Получает текущую сессию, связанную с запросом, или создает новую, если она не существует.
// Получение URI и метода запроса:
// - String getRequestURI(): Получает URI запроса.
// - String getMethod(): Получает HTTP-метод (например, GET, POST) запроса.

// HttpServletResponse представляет собой объект, который используется для формирования и отправки ответа клиенту. Он предоставляет методы для установки различных аспектов ответа.
// Основные методы HttpServletResponse:
// Установка статуса ответа:
// - void setStatus(int sc): Устанавливает статус-код ответа (например, 200 для успешного запроса, 404 для "Не найдено").
// Установка заголовков ответа:
// - void setHeader(String name, String value): Устанавливает заголовок ответа.
// - void addHeader(String name, String value): Добавляет заголовок ответа (можно использовать для установки нескольких значений одного заголовка).
// Отправка содержимого ответа:
// - PrintWriter getWriter(): Получает объект PrintWriter, который можно использовать для записи текста в ответ.
// - OutputStream getOutputStream(): Получает объект OutputStream, который можно использовать для записи бинарных данных в ответ.
// Перенаправление:
// - void sendRedirect(String location): Перенаправляет клиента на указанный URL.

public class MyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Установка типа контента
        resp.setContentType("text/html");

        // Получение параметра запроса
        String name = req.getParameter("name");

        // Получение объекта PrintWriter для отправки ответа
        PrintWriter out = resp.getWriter();

        // Формирование ответа
        out.println("<html><body>");
        out.println("<h1>Hello, " + (name != null ? name : "Guest") + "!</h1>");
        out.println("</body></html>");
    }
}
