package ru.geekbrains;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        ServletContext sc = getServletContext();
        productRepository = (ProductRepository) sc.getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productRepository.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=\"1\">");
        sb.append("<caption>Product table</caption>");
        sb.append("<tr>");
        sb.append("<th>ID</th>");
        sb.append("<th>Name</th>");
        sb.append("<th>Cost</th>");
        sb.append("</tr>");
        for(Product product : products) {
            sb.append("<tr>");
            sb.append(String.format("<td>%d</td>", product.getId()));
            sb.append(String.format("<td>%s</td>", product.getName()));
            sb.append(String.format("<td>%.2f</td>", product.getCost()));
            sb.append("</tr>");
        }
        sb.append("</table>");
        resp.getWriter().println(sb.toString());
    }
}
