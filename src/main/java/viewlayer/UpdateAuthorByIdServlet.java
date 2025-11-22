package viewlayer;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

import businesslayer.AuthorService;
import dataaccesslayer.DAOException;

/**
 * Servlet that updates an existing author based on their ID.
 * <p>
 * Accepts ID, first name, and last name to update the existing
 * database record. All logic is performed through the
 * {@link AuthorService}.
 */
public class UpdateAuthorByIdServlet extends HttpServlet {

    private final AuthorService service = new AuthorService();

    /**
     * Processes the update request.
     *
     * @param request  HTTP request containing ID, first name, last name
     * @param response HTTP response containing HTML output
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String idStr = request.getParameter("authorId");
        String fn = request.getParameter("firstName");
        String ln = request.getParameter("lastName");

        out.println("<html><body style='background:#fff2dc;text-align:center;'>");
        out.println("<h1>Update Author</h1>");

        if (idStr == null || fn == null || ln == null ||
                idStr.isBlank() || fn.isBlank() || ln.isBlank()) {

            out.println("<p style='color:red;'>All fields required.</p>");
            showForm(out);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            boolean updated = service.update(id, fn, ln);

            if (updated) {
                out.println("<p style='color:green;'>Author updated successfully.</p>");
            } else {
                out.println("<p style='color:red;'>No author found with ID " + id + "</p>");
            }

        } catch (DAOException | NumberFormatException e) {
            out.println("<p style='color:red;'>Error updating author.</p>");
        }

        showForm(out);
    }

    /**
     * Shows the Update Author form for additional attempts.
     *
     * @param out PrintWriter used to write HTML form
     */
    private void showForm(PrintWriter out) {
        out.println("<br><form method='post' action='FrontController-URL'>");
        out.println("Author ID: <input name='authorId'/><br><br>");
        out.println("First Name: <input name='firstName'/><br><br>");
        out.println("Last Name: <input name='lastName'/><br><br>");
        out.println("<input type='submit' name='action' value='UpdateAuthorById'/>");
        out.println("</form>");

        out.println("<br><form method='get' action='FrontController-URL'>");
        out.println("<input type='submit' value='Back'/>");
        out.println("</form>");

        out.println("</body></html>");
    }
}
