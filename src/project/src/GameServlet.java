package project.src;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet("/game") //Аннотация сервлета, обработка запросов по URL /game
public class GameServlet extends HttpServlet {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;

    @Override
    // Обработка начального запроса
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); //Создание/возвращение сессии
        Integer secretNumber = (Integer) session.getAttribute("secretNumber");
        Integer attempts = (Integer) session.getAttribute("attempts");
        //Извлекам сохранённые значения загаданный номер (secretNumber) и количество попыток (attempts).

        // Инициализация игры при первом заходе, генерация
        if (secretNumber == null || attempts == null) {
            Random random = new Random();
            secretNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
            attempts = 0;
            session.setAttribute("secretNumber", secretNumber);
            session.setAttribute("attempts", attempts);
        }

        response.setContentType("text/html"); //Сообщает браузеру, что сервер отправляет HTML-код
        PrintWriter out = response.getWriter(); //Создаёт объект для записи текста HTML в ответ

        out.println("<html>");
        out.println("<head><title>Угадай число</title></head>");
        out.println("<body>");
        out.println("<h1>Игра: Угадай число</h1>");
        out.println("<p>Попробуйте угадать число от " + MIN_NUMBER + " до " + MAX_NUMBER + ".</p>");
        out.println("<form method='post'>");
        out.println("Ваше предположение: <input type='number' name='guess' required>");
        out.println("<button type='submit'>Отправить</button>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        // Генерация HTML-страницы с заголовком "Угадай число" и формой для ввода числа (<form method='post'>) с
        // полем <input> для числа и кнопкой <button>.
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer secretNumber = (Integer) session.getAttribute("secretNumber");
        Integer attempts = (Integer) session.getAttribute("attempts");
        // Получаем текущую сессию, извлекаем отправленные через форму загаданный номер и количество попыток

        if (secretNumber == null || attempts == null) {
            response.sendRedirect("game");
            return;
            // Если данные из сессии не найдены (например, из-за сброса сервера), перенаправляем пользователя обратно
            // на начальную страницу (GET /game).
        }

        int guess;
        try {
            guess = Integer.parseInt(request.getParameter("guess")); //Получаем число, введённое пользователем
        } catch (NumberFormatException e) {
            response.sendRedirect("game");
            //Если введены некорректные данные (не число), перенаправляем на начальную страницу (GET /game)
            return;
        }

        attempts++;
        session.setAttribute("attempts", attempts);

        response.setContentType("text/html"); //Готовит HTML-ответ, как в doGet
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Угадай число</title></head>");
        out.println("<body>");
        out.println("<h1>Игра: Угадай число</h1>");

        if (guess == secretNumber) {
            out.println("<p>Поздравляем! Вы угадали число " + secretNumber + " за " + attempts + " попыток.</p>");
            session.invalidate(); // Сброс сессии
            out.println("<a href='game'>Сыграть снова</a>");
        } else if (guess < secretNumber) {
            out.println("<p>Ваше число меньше загаданного. Попробуйте снова.</p>");
            out.println("<a href='game'>Назад</a>");
        } else {
            out.println("<p>Ваше число больше загаданного. Попробуйте снова.</p>");
            out.println("<a href='game'>Назад</a>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}