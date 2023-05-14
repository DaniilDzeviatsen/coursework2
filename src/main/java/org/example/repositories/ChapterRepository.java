package org.example.repositories;

import org.example.models.Chapter;

import java.util.List;
import java.util.Optional;

public interface ChapterRepository {

    List<Chapter> getAllChapters();

    void createNewChapter(String name, long chapterId, long totalCardsNum, long numOfLearnedCards);

    void deleteChapter(long chapterId);

    void showAllCards(long ChapterId);
}
