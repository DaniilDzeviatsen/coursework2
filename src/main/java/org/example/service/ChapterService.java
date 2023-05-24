package org.example.service;

import org.example.models.Chapter;

import java.util.List;

public interface ChapterService {
    void removeChapter(long chapterId);

    List<Chapter> getAllChapters();

    void createNewChapter(String name);
}
