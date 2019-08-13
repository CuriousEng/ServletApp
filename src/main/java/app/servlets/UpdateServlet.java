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

public class UpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/update.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldName = req.getParameter("oldName");
        String newName = req.getParameter("newName");
        String oldPass = req.getParameter("oldPass");
        String newPass = req.getParameter("NewPass");
        Errors error = ErrorCounter(oldName, oldPass);
        if (newName == null || newName == ""){
            newName = oldName;
            System.out.println("Name not changed");
            System.out.println(newName);

        }
        if (newPass == null || newPass == ""){
            newPass = oldPass;
            System.out.println("Pass not changed");
            System.out.println(newPass);
        }
        //как-то добавить собщение об изменении имени или пароля
        //как-то добавить возможность отсутствия нового имени/пароля
        //случай, когда данные совпадают
        if (error.getCount() == 0) {
            User user = new User(newName, newPass);
            Model model = Model.getInstance();
            model.update(oldName, oldPass, user);
            req.setAttribute("userName", newName);
            doGet(req, resp);
        } else {

            req.setAttribute("userName", newName);
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
