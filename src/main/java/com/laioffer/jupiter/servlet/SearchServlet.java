package com.laioffer.jupiter.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.jupiter.external.TwitchClient;
import com.laioffer.jupiter.external.TwitchException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }


    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
        System.out.println("开始");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get gameName from request URL.
        String gameName = request.getParameter("game_name");

        TwitchClient client = new TwitchClient();

        // Let the client know the returned data is in JSON format.
        response.setContentType("application/json;charset=UTF-8");
        try {
            // Return the dedicated game information if gameName is provided in the request URL, otherwise return the top x games.
            if (gameName != null) {
                response.getWriter().print(new ObjectMapper().writeValueAsString(client.searchGame(gameName)));
            } else {
                response.getWriter().print(new ObjectMapper().writeValueAsString(client.topGames(0)));
            }
        } catch (TwitchException e) {
            throw new ServletException(e);
        }
    }

}

//<body><h1>HTTP状态 500 - 内部服务器错误</h1>
//<hr class="line"/>
//<p><b>类型</b> 异常报告</p>
//<p><b>消息</b> com.laioffer.jupiter.external.TwitchException: Failed to get result from Twitch API</p>
//<p><b>描述</b> 服务器遇到一个意外的情况，阻止它完成请求。</p>
//<p><b>例外情况</b></p>
//<pre>javax.servlet.ServletException: com.laioffer.jupiter.external.TwitchException: Failed to get result from Twitch API
//	com.laioffer.jupiter.servlet.SearchServlet.doGet(SearchServlet.java:34)
//	javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
//	javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
//	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
//</pre>
//<p><b>根本原因。</b></p>
//<pre>com.laioffer.jupiter.external.TwitchException: Failed to get result from Twitch API
//	com.laioffer.jupiter.external.TwitchClient.searchTwitch(TwitchClient.java:83)
//	com.laioffer.jupiter.external.TwitchClient.searchStreams(TwitchClient.java:151)
//	com.laioffer.jupiter.external.TwitchClient.searchByType(TwitchClient.java:186)
//	com.laioffer.jupiter.external.TwitchClient.searchItems(TwitchClient.java:205)
//	com.laioffer.jupiter.servlet.SearchServlet.doGet(SearchServlet.java:32)
//	javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
//	javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
//	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)