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
import java.net.URL;
import java.util.List;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        ServletContext sc = getServletContext();
        productRepository = (ProductRepository) sc.getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getPathInfo() == null) {
            printProductTable(req, resp);
        } else {
            printProductInfo(req, resp);
        }
    }

    private void printProductTable(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder sb = new StringBuilder();
        List<Product> products = productRepository.findAll();
        sb.append("<table border=\"1\">");
        sb.append("<caption>Таблица продуктов</caption>");
        sb.append("<tr>");
        sb.append("<th>ID</th>");
        sb.append("<th>Имя</th>");
        sb.append("<th>Цена</th>");
        sb.append("</tr>");
        for(Product product : products) {
            sb.append("<tr>");
            sb.append(String.format("<td>%d</td>", product.getId()));
            String descriptionPath = req.getContextPath() + req.getServletPath() + "/" + product.getId();
            sb.append(String.format("<td><a href=\"%s\">%s</a></td>", descriptionPath, product.getName()));
            sb.append(String.format("<td>%.2f р.</td>", product.getCost()));
            sb.append("</tr>");
        }
        sb.append("</table>");
        resp.getWriter().println(sb);
    }

    private void printProductInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder sb = new StringBuilder();
        String pathInfo = req.getPathInfo();
        try {
            long id = Long.parseLong(pathInfo.substring(1));
            Product product = productRepository.findById(id);
            if(product != null) {
                sb.append(String.format("<h3>ID: %s</h3>", id));
                sb.append(String.format("<h3>Имя: %s</h3>", product.getName()));
                sb.append(String.format("<h3>Цена: %.2f р.</h3>", product.getCost()));
                sb.append("<h3>Описание:</h3>");
                sb.append(String.format("<p>%s</p>", product.getDescription()));
                resp.getWriter().println(sb);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException ex) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
