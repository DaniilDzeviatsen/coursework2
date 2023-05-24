package org.example.service;

import org.example.exceptions.InputDataException;
import org.example.models.Chapter;
import org.example.repositories.ChapterRepository;

import java.util.List;

public class ChapterServiceImpl implements ChapterService {
    ChapterRepository chapterRepository;

    public ChapterServiceImpl(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    public void removeChapter(long chapterId) {
        chapterRepository.deleteChapter(chapterId);
    }

    public void createNewChapter(String name) {
        if (!name.isEmpty()) {
            chapterRepository.createNewChapter(name);
        } else throw new InputDataException();
    }

    public List<Chapter> getAllChapters() {
        return chapterRepository.getAllChapters();
    }

}
