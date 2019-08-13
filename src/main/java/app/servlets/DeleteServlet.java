package app.servlets;

import app.entities.FailCause;
import app.entities.User;
import app.model.Errors;
import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Predicate;

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/delete.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        Errors error = ErrorCounter(name, password);
        if (error.getCount() == 0) {
            Model model = Model.getInstance();
            model.remove(name, password);
            req.setAttribute("userName", name);
            doGet(req, resp);
        } else {
            req.setAttribute("userName", name);
            req.setAttribute("cause", error.getCauses());
            doGet(req, resp);
        }
    }

    protected Errors ErrorCounter(String name, String password){
        Errors error = Errors.getInstance();
        error.setCount(0);
        error.clearCauses();
        Model model = Model.getInstance();
        if (!Model.getInstance().list().contains(name)) {  //не найдено пользователя
            error.setCount(error.getCount() + 1);
            error.setCauses(FailCause.NO_SUCH_USER);
        } else if (!model.getModel().stream()       //неправильный пароль пользователя
                                    .map(User::getPassword)
        .anyMatch(Predicate.isEqual(password))) {
            error.setCount(error.getCount() + 1);
            error.setCauses(FailCause.WRONG_PASS);
        }
        return error;
    }
}
