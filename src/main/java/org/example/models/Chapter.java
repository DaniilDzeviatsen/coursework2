package org.example.models;

public class Chapter {
    private final long chapterId;
    private final String name;
    private long totalCardsNum;
    private long numOfLearnedCards;

    public Chapter(long chapterId, String name) {
        this.chapterId = chapterId;
        this.name = name;
    }
}
