package oct13;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// Фильтр — это объект, который выполняет предварительную и/или последующую обработку запросов и ответов в веб-приложении.
// Фильтры могут использоваться для различных целей, таких как:
// - Аутентификация и авторизация пользователей.
// - Логирование запросов и ответов.
// - Изменение содержимого запросов и ответов (например, сжатие или шифрование).
// - Управление кэшированием

public class MyFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        // Инициализация фильтра
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // Логика фильтрации (например, логирование)
        System.out.println("Request URI: " + httpRequest.getRequestURI());

        // Продолжение цепочки фильтров
        chain.doFilter(request, response);
    }
// Цепочка фильтров — это механизм, который позволяет фильтрам обрабатывать запросы последовательно. Когда запрос
// поступает на сервер, он проходит через цепочку фильтров, где каждый фильтр может выполнять свою логику и затем
// передавать управление следующему фильтру в цепочке.
//Метод doFilter() фильтра принимает объект FilterChain, который предоставляет метод doFilter(), позволяющий передать
// управление следующему фильтру в цепочке или конечному ресурсу (например, сервлету).

    public void destroy() {
        // Освобождение ресурсов
    }
}
