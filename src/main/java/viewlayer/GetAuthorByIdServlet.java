package viewlayer;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

import businesslayer.AuthorService;
import dataaccesslayer.DAOException;
import transferobjects.Author;

/**
 * Servlet responsible for retrieving a single author by ID.
 */
public class GetAuthorByIdServlet extends HttpServlet {

    private final AuthorService service = new AuthorService();

    /**
     * Handles the POST request to search for an author by ID.
     *
     * @param request  HTTP request containing the author ID
     * @param response HTTP response generating HTML output
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String idStr = request.getParameter("authorId");

        out.println("<html><body style='background:#fff2dc;text-align:center;'>");
        out.println("<h1>Get Author By ID</h1>");

        if (idStr == null || idStr.isBlank()) {
            out.println("<p style='color:red;'>Please enter an Author ID.</p>");
            showInputForm(out);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Author a = service.getById(id);

            if (a == null) {
                out.println("<p style='color:red;'>No author found with ID " + id + "</p>");
            } else {
                out.println("<table border='1' cellpadding='5' align='center'>");
                out.println("<tr><th>AuthorID</th><th>First Name</th><th>Last Name</th></tr>");
                out.println("<tr>");
                out.println("<td>" + a.getAuthorId() + "</td>");
                out.println("<td>" + a.getFirstName() + "</td>");
                out.println("<td>" + a.getLastName() + "</td>");
                out.println("</tr>");
                out.println("</table>");
            }

        } catch (DAOException | NumberFormatException e) {
            out.println("<p style='color:red;'>Invalid ID format or error.</p>");
        }

        showInputForm(out);
    }

    /**
     * Shows the input form to search again.
     *
     * @param out PrintWriter for HTML output
     */
    private void showInputForm(PrintWriter out) {
        out.println("<br><form method='post' action='FrontController-URL'>");
        out.println("Enter Author ID: <input name='authorId'/><br><br>");
        out.println("<input type='submit' name='action' value='GetAuthorById'/>");
        out.println("</form>");

        out.println("<br><form method='get' action='FrontController-URL'>");
        out.println("<input type='submit' value='Back'/>");
        out.println("</form>");

        out.println("</body></html>");
    }
}
