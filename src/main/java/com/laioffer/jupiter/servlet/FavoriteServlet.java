package com.laioffer.jupiter.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.jupiter.db.MySQLConnection;
import com.laioffer.jupiter.db.MySQLException;
import com.laioffer.jupiter.entity.FavoriteRequestBody;
import com.laioffer.jupiter.entity.Item;
import com.laioffer.jupiter.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.geom.RectangularShape;
import java.io.IOException;
import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FavoriteServlet", urlPatterns = {"/favorite"})
public class FavoriteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // check if the session is still valid
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String userId = (String) session.getAttribute("user_id");
        // Get favorite item information from request body
        FavoriteRequestBody body = ServletUtil.readRequestBody(FavoriteRequestBody.class, request);

       if (body == null) {
           response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
           return;
       }
        MySQLConnection connection = null;
        try {
            // Save the favorite item to the database
            connection = new MySQLConnection();
            connection.setFavoriteItem(userId, body.getFavoriteItem());
        } catch (MySQLException e) {
            throw new ServletException(e);
        } finally {

            connection.close();

        }
}
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String userId = (String) session.getAttribute("user_id");

        FavoriteRequestBody body = ServletUtil.readRequestBody(FavoriteRequestBody.class, req);
        if (body == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        MySQLConnection connection = null;
        try {
            // Remove the favorite item to the database
            connection = new MySQLConnection();
            connection.unsetFavoriteItem(userId, body.getFavoriteItem().getId());
        } catch (MySQLException e) {
            throw new ServletException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String userId = (String) session.getAttribute("user_id");
        Map<String, List<Item>> itemMap;
        MySQLConnection connection = null;
        try {
            // Read the favorite items from the database
            connection = new MySQLConnection();
            itemMap = connection.getFavoriteItems(userId);
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().print(new ObjectMapper().writeValueAsString(itemMap));
            ServletUtil.writeItemMap(response, itemMap);
        } catch (MySQLException e) {
            throw new ServletException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
