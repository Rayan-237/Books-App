package viewlayer;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

import businesslayer.AuthorService;
import dataaccesslayer.DAOException;

/**
 * Servlet responsible for adding a new author to the database.
 * <p>
 * Displays a form and processes the submitted first/last name
 * to create the new database record via the {@link AuthorService}.
 */
public class AddAuthorServlet extends HttpServlet {

    private final AuthorService service = new AuthorService();

    /**
     * Processes the request to add a new author.
     *
     * @param request  HTTP request containing first/last name fields
     * @param response HTTP response used to generate HTML output
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String fn = request.getParameter("firstName");
        String ln = request.getParameter("lastName");

        out.println("<html><body style='background:#fff2dc;text-align:center;'>");
        out.println("<h1>Add New Author</h1>");

        if (fn == null || ln == null || fn.isBlank() || ln.isBlank()) {
            out.println("<p style='color:red;'>Enter both first and last name.</p>");
            showForm(out);
            return;
        }

        try {
            service.add(fn, ln);
            out.println("<p style='color:green;'>Author added successfully.</p>");
        } catch (DAOException e) {
            out.println("<p style='color:red;'>Error adding author.</p>");
        }

        showForm(out);
    }

    /**
     * Displays the Add Author form for additional input attempts.
     *
     * @param out PrintWriter used to write HTML content
     */
    private void showForm(PrintWriter out) {
        out.println("<br><form method='post' action='FrontController-URL'>");
        out.println("First Name: <input name='firstName'/><br><br>");
        out.println("Last Name: <input name='lastName'/><br><br>");
        out.println("<input type='submit' name='action' value='AddAuthor'/>");
        out.println("</form>");

        out.println("<br><form method='get' action='FrontController-URL'>");
        out.println("<input type='submit' value='Back'/>");
        out.println("</form>");

        out.println("</body></html>");
    }
}
