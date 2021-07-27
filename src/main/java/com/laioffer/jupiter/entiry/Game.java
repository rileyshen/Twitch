package com.laioffer.jupiter.entiry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.servlet.ReadListener;
import java.util.Locale;

/**
 * @Description:get TWICH api, match the twich response
 * {
 *       "id": "12924",
 *       "name": "Warcraft III",
 *       "box_art_url": "https://static-cdn.jtvnw.net/ttv-boxart/Warcraft%20III-{width}x{height}.jpg"
 *     }
 * @Author RileyShen
 * @Create 2021-07-21
 *
 * @JsonIgnoreProperties(ignoreUnknown = true)
 * indicates that other fields in the response can be safely ignored.
 * Without this, you’ll get an exception at runtime.
 *
 * @JsonInclude(JsonInclude.Include.NON_NULL)
 * indicates that null fields can be skipped and not included.
 *
 * @JsonDeserialize indicates that Jackson needs to use Game.Builder
 * when constructing a Game object from JSON strings.
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Game.Builder.class)
public class Game {
//    @JsonProperty("name")
//    private String name;
//
//    @JsonProperty("developer")
//    private String developer;
//
//    @JsonProperty("releaseTime")
//    private String releaseTime;
//
//    @JsonProperty("website")
//    private String website;
//
//    @JsonProperty("price")
//    private double price;

    @JsonProperty("id")
    private final String id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("boxArtUrl")
    private final String boxArtUrl;

    public Game(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.boxArtUrl = builder.boxArtUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBoxArtUrl() {
        return boxArtUrl;
    }

    //    public String getName() {
//        return name;
//    }
//
//    public String getDeveloper() {
//        return developer;
//    }
//
//    public String getReleaseTime() {
//        return releaseTime;
//    }
//
//    public String getWebsite() {
//        return website;
//    }
//
//    public double getPrice() {
//        return price;
//    }

//    public Game(Builder builder) {
//        this.name = builder.name;
//        this.developer = builder.developer;
//        this.releaseTime = builder.releaseTime;
//        this.website = builder.website;
//        this.price = builder.price;
//
//    }
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder {
//        private String name;
//        private String developer;
//        private String releaseTime;
//        private String website;
//        private double price;

        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("boxArtUrl")
        private String boxArtUrl;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder boxArtUrld(String boxArtUrl) {
            this.boxArtUrl = boxArtUrl;
            return this;
        }

        public Game build() {
            return new Game(this);
        }

//        public Builder setName(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public Builder setDeveloper(String developer) {
//            this.developer = developer;
//            return this;
//        }
//
//        public Builder setReleaseTime(String releaseTime) {
//            this.releaseTime = releaseTime;
//            return this;
//        }
//
//        public Builder setWebsite(String website) {
//            this.website = website;
//            return this;
//        }
//
//        public Builder setPrice(double price) {
//            this.price = price;
//            return this;
//        }
//
//        public Game build() {
//            return new Game(this);
//        }
    }
}
