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

public class AddServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        Errors error = ErrorCounter(name, password); //Считаем ошибки
        if (error.getCount() == 0) {
            User user = new User(name, password); //добавление пользователя в модель
            Model model = Model.getInstance();
            model.add(user);
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
        if (password.length() <= 3 ) {
            error.setCount(error.getCount() + 1);
            error.setCauses(FailCause.SHORT_PASS_ERROR);
        } if (name.length() < 2) {
            error.setCount(error.getCount() + 1);
            error.setCauses(FailCause.SHORT_NAME_ERROR);
        } if (Model.getInstance().getModel().contains(name)) {
            error.setCount(error.getCount() + 1);
            error.setCauses(FailCause.BASE_ALREADY_CONTAINS_NAME_ERROR);
        }
        return error;
    }

}
