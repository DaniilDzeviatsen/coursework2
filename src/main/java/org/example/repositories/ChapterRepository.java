package org.example.repositories;

import org.example.models.Card;
import org.example.models.Chapter;

import java.util.List;

public interface ChapterRepository {

    List<Chapter> getAllChapters();

    void createNewChapter(String name, long chapterId, long totalCardsNum, long numOfLearnedCards);

    void deleteChapter(long chapterId);

    List<Card> showAllCards(long ChapterId);
}
