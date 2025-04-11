import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import model.UserEntity;

@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
    private SessionFactory sessionFactory;

    @Override
    public void init() throws ServletException {
        // Initialize SessionFactory
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        UserEntity user = validateUser(name, pass);
        if (user != null) {
            if ("admin".equalsIgnoreCase(user.getRole())) {
                // Redirect to admin page if the user is an admin
                response.sendRedirect("admin.jsp");
            } else {
                // Set username in request attribute for regular users
                request.setAttribute("username", user.getName());
                request.getRequestDispatcher("welcome.jsp").forward(request, response);
            }
        } else {
            // Set error message in request attribute
            request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET requests if necessary
    }

    private UserEntity validateUser(String name, String password) {
        try (Session session = sessionFactory.openSession()) {
            Query<UserEntity> query = session.createQuery("FROM UserEntity WHERE name = :name AND password = :password", UserEntity.class);
            query.setParameter("name", name);
            query.setParameter("password", password);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void destroy() {
        // Close SessionFactory
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}