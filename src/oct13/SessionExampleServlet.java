package oct13;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

// HTTP-сессия (или просто сессия) — это механизм, который позволяет сохранять состояние между запросами в протоколе
// HTTP, который является статeless (безсостояние). Поскольку HTTP не сохраняет информацию о предыдущих запросах,
// сессии используются для отслеживания взаимодействия пользователя с веб-приложением

public class SessionExampleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получение текущей сессии или создание новой, если она не существует
        HttpSession session = request.getSession();

        // Установка атрибута сессии
        session.setAttribute("username", "JohnDoe");

        // Получение атрибута сессии
        String username = (String) session.getAttribute("username");

        // Отправка ответа
        response.getWriter().println("Hello, " + username);
    }
}
