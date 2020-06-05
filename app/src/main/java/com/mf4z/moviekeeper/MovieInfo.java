package com.mf4z.moviekeeper;

public final class MovieInfo {
    private GenreInfo mGenre;
    private String mTitle;
    private String mText;

    public MovieInfo(GenreInfo genre, String title, String text) {
        mGenre = genre;
        mTitle = title;
        mText = text;
    }

    public GenreInfo getGenre() {
        return mGenre;
    }

    public void setGenre(GenreInfo genre) {
        mGenre = genre;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    private String getCompareKey() {
        return mGenre.getGenreId() + "|" + mTitle + "|" + mText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieInfo that = (MovieInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }

}
