package viewlayer;

import businesslayer.AuthorService;
import dataaccesslayer.DAOException;
import transferobjects.Author;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet responsible for retrieving and displaying a table
 * of all authors stored in the database.
 */
public class GetAllAuthorsServlet extends HttpServlet {

    private final AuthorService service = new AuthorService();

    /**
     * Processes the request to retrieve all authors.
     *
     * @param request  HTTP request containing form data
     * @param response HTTP response containing HTML output
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Author> authors = service.getAllAuthors();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html><html><head><title>All Authors</title></head>");
            out.println("<body style='background-color:#fff2dc; text-align:center;'>");
            out.println("<h1>All Authors</h1>");

            if (authors.isEmpty()) {
                out.println("<p>No authors found.</p>");
            } else {
                out.println("<table border='1' align='center' cellpadding='5'>");
                out.println("<tr><th>AuthorID</th><th>First Name</th><th>Last Name</th></tr>");
                for (Author a : authors) {
                    out.println("<tr>");
                    out.println("<td>" + a.getAuthorId() + "</td>");
                    out.println("<td>" + a.getFirstName() + "</td>");
                    out.println("<td>" + a.getLastName() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }

            out.println("<br/><form method='get' action='FrontController-URL'>");
            out.println("<input type='submit' value='Back'/>");
            out.println("</form>");

            out.println("</body></html>");

        } catch (DAOException e) {
            throw new ServletException("Error retrieving authors", e);
        }
    }
}
