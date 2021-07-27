package com.laioffer.jupiter.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.jupiter.entiry.Game;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author RileyShen
 * @Create 2021-07-24
 */
public class TwitchClient {
    private static final String TOKEN = "Bearer jo3r1bpdgk5gx41zx2wuajygdb1d8b";
    private static final String CLIENT_ID = "t5bc5a4tab71mqgs1f880ent6cx8j4";
    private static final String TOP_GAME_URL = "https://api.twitch.tv/helix/games/top?first=%s";
    private static final String GAME_SEARCH_URL_TEMPLATE = "https://api.twitch.tv/helix/games?name=%s";
    private static final int DEFAULT_GAME_LIMIT = 20;


    // Build the request URL which calls Twich APIS
    // https://api.twitch.tv/helix/games/top
    private String buildGameURL(String url, String gameName, int limit) {
        if (gameName.equals("")) {
            return String.format(url, limit);
        } else {
            try {
                // Encode special chracters in URL, Rick Sun -> Rick%20Sun
                gameName = URLEncoder.encode(gameName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return String.format(url, gameName);
        }
    }

    // sent HTTP request to Twitch Backend based on the given URL, and returns the body of the HTTP response returned from Twitch backend
    private String searchTwitch(String url) throws TwitchException{
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // define the response handler to parse and return HTTP response
        ResponseHandler<String> responseHandler = response -> {
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                System.out.println("Response status: " + response.getStatusLine().getReasonPhrase());
                throw new TwitchException("Failed to get result from Twitch API");
            }

            HttpEntity entity = response.getEntity();
            if (entity == null) {
                throw new TwitchException("Failed to get result from Twitch API");
            }
            JSONObject obj = new JSONObject(EntityUtils.toString(entity));
            return obj.getJSONArray("data").toString();
        };


        try {
            HttpGet request = new HttpGet(url);
            request.setHeader("Authorization", TOKEN);
            request.setHeader("Client-Id", CLIENT_ID);
            return httpClient.execute(request, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
            throw new TwitchException("Failed to get result from Twitch API");
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // convert JSON format data returned from Twitch to an ArrayList of Game objects
    private List<Game> getGameList(String data) throws TwitchException {

        ObjectMapper mapper = new ObjectMapper();
        try {

            return Arrays.asList(mapper.readValue(data, Game[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new TwitchException("Failed to get result from Twitch API");
        }
    }

    // Integrate search() and getGameList() together, returns the top popular games from Twitch
    public List<Game> topGames(int limit) throws TwitchException {
        if (limit < 0) {
            limit = DEFAULT_GAME_LIMIT;
        }
        return getGameList(searchTwitch(buildGameURL(TOP_GAME_URL, "", limit)));
    }

    // Tntegrate search() and getGameList() together, returns the dedicated game based on the ga,me name
    public Game searchGame(String gameName) throws TwitchException {
        List<Game> gameList =
                getGameList(searchTwitch(buildGameURL(GAME_SEARCH_URL_TEMPLATE, gameName, 0)));

        if (gameList.size() != 0) {
            return gameList.get(0);
        }
        return null;
    }

}
