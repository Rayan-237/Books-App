package viewlayer;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

/**
 * Front Controller servlet that handles all incoming requests.
 * <p>
 * This servlet performs two major functions:
 * <ul>
 *   <li>Handles the login process by verifying credentials.</li>
 *   <li>Acts as the central routing mechanism that forwards requests
 *       to the appropriate CRUD servlet based on the selected action.</li>
 * </ul>
 * <p>
 * This implementation follows the Front Controller design pattern,
 * ensuring that all navigation logic is in one place.
 */
public class FrontController extends HttpServlet {

    /**
     * Handles GET requests by showing the login page.
     *
     * @param request the servlet request
     * @param response the servlet response
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        showLoginPage(response, null);
    }

    /**
     * Handles all POST submissions including login and CRUD routing.
     *
     * @param request the servlet request
     * @param response the servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            showLoginPage(response, "Please select an action.");
            return;
        }

        switch (action) {
            case "login" -> {
                String user = request.getParameter("username");
                String pass = request.getParameter("password");

                if ("cst8288".equals(user) && "cst8288".equals(pass)) {
                    showMainMenu(response, null);
                } else {
                    showLoginPage(response, "Invalid credentials.");
                }
            }

            case "GetAllAuthors" ->
                    request.getRequestDispatcher("/GetAllAuthorsServlet").forward(request, response);

            case "GetAuthorById" ->
                    request.getRequestDispatcher("/GetAuthorByIdServlet").forward(request, response);

            case "AddAuthor" ->
                    request.getRequestDispatcher("/AddAuthorServlet").forward(request, response);

            case "UpdateAuthorById" ->
                    request.getRequestDispatcher("/UpdateAuthorByIdServlet").forward(request, response);

            case "DeleteAuthorById" ->
                    request.getRequestDispatcher("/DeleteAuthorByIdServlet").forward(request, response);

            default -> showMainMenu(response, "Unknown action");
        }
    }

    /**
     * Displays the login page with optional error message.
     *
     * @param response servlet response
     * @param message error message (nullable)
     * @throws IOException if output fails
     */
    private void showLoginPage(HttpServletResponse response, String message)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>Enter DBMS Credentials</title></head>");
        out.println("<body style='background-color:#fff2dc; text-align:center;'>");
        out.println("<h1>Enter DBMS Credentials</h1>");

        if (message != null) {
            out.println("<p style='color:red;'>" + message + "</p>");
        }

        out.println("<form method='post' action='FrontController-URL'>");
        out.println("Username: <input type='text' name='username'/><br/><br/>");
        out.println("Password: <input type='password' name='password'/><br/><br/>");
        out.println("<input type='hidden' name='action' value='login'/>");
        out.println("<input type='submit' value='Login'/>");
        out.println("</form>");

        out.println("</body></html>");
    }

    /**
     * Displays the main menu containing all CRUD operation buttons.
     *
     * @param response servlet response
     * @param message optional message (nullable)
     * @throws IOException if output fails
     */
    private void showMainMenu(HttpServletResponse response, String message)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>Author Operations</title></head>");
        out.println("<body style='background-color:#fff2dc; text-align:center;'>");
        out.println("<h1>Author Operations</h1>");

        if (message != null) {
            out.println("<p style='color:red;'>" + message + "</p>");
        }

        out.println("<form method='post' action='FrontController-URL'>");
        out.println("<input type='submit' name='action' value='GetAllAuthors'/>");
        out.println("<input type='submit' name='action' value='GetAuthorById'/>");
        out.println("<input type='submit' name='action' value='AddAuthor'/>");
        out.println("<input type='submit' name='action' value='UpdateAuthorById'/>");
        out.println("<input type='submit' name='action' value='DeleteAuthorById'/>");
        out.println("</form>");

        out.println("</body></html>");
    }
}
