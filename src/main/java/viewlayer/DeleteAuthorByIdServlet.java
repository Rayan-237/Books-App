package viewlayer;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

import businesslayer.AuthorService;
import dataaccesslayer.DAOException;

/**
 * Servlet responsible for deleting an author from the database
 * using their ID. Interacts with the business layer to perform
 * deletion and shows feedback to the user.
 */
public class DeleteAuthorByIdServlet extends HttpServlet {

    private final AuthorService service = new AuthorService();

    /**
     * Handles the request to delete an author.
     *
     * @param request  HTTP request containing the author ID
     * @param response HTTP response containing HTML output
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String idStr = request.getParameter("authorId");

        out.println("<html><body style='background:#fff2dc;text-align:center;'>");
        out.println("<h1>Delete Author</h1>");

        if (idStr == null || idStr.isBlank()) {
            out.println("<p style='color:red;'>Please enter an Author ID.</p>");
            showForm(out);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);

            boolean deleted = service.delete(id);

            if (deleted) {
                out.println("<p style='color:green;'>Author deleted.</p>");
            } else {
                out.println("<p style='color:red;'>No author found with ID " + id + "</p>");
            }

        } catch (DAOException | NumberFormatException e) {
            out.println("<p style='color:red;'>Error deleting author.</p>");
        }

        showForm(out);
    }

    /**
     * Displays the Delete Author form.
     *
     * @param out PrintWriter used for generating HTML
     */
    private void showForm(PrintWriter out) {
        out.println("<br><form method='post' action='FrontController-URL'>");
        out.println("Author ID: <input name='authorId'/><br><br>");
        out.println("<input type='submit' name='action' value='DeleteAuthorById'/>");
        out.println("</form>");

        out.println("<br><form method='get' action='FrontController-URL'>");
        out.println("<input type='submit' value='Back'/>");
        out.println("</form>");

        out.println("</body></html>");
    }
}
