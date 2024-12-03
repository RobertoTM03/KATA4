package es.ulpgc.dis.model;

import java.util.List;

public record Title(String id, TitleType type, String title, String originalTitle, List<Genre> genres) {
    public enum Genre {
        Action,
        Adult,
        Adventure,
        Animation,
        Biography,
        Comedy,
        Crime,
        Documentary,
        Drama,
        Family,
        Fantasy,
        FilmNoir,
        GameShow,
        History,
        Horror,
        Music,
        Musical,
        Mystery,
        News,
        RealityTV,
        Romance,
        SciFi,
        Short,
        Sport,
        TalkShow,
        Thriller,
        War,
        Western
    }

    public enum TitleType {
        Movie,
        Short,
        TvEpisode,
        TvMiniSeries,
        TvMovie,
        TvPilot,
        TvSeries,
        TvShort,
        TvSpecial,
        Video,
        VideoGame
    }
}