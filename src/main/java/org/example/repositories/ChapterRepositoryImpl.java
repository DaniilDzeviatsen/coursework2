package org.example.repositories;

import org.example.models.Chapter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ChapterRepositoryImpl implements ChapterRepository {
    private final DataSource db;

    public ChapterRepositoryImpl(DataSource db) {
        this.db = db;
    }

    @Override
    public void ShowAllChaptersNames() {

    }

    @Override
    public List<Chapter> getAllChapters() {
        return null;
    }

    @Override
    public void createNewChapter(String name, long id, long totalCardsNum, long numOfLearnedCards) {

    }

    @Override
    public void deleteChapter(long id) {
        String sql= """
                DELETE FROM CHAPTER
                WHERE CHAPTER.ID = ? """ ;
                try(
Connection connection=db.getConnection();
PreparedStatement statement=connection.prepareStatement(sql);
){
statement.setLong(1,id);   }
statement.executeUpdate();
    @Override
    public void showAllCards(long ChapterId) {

    }
}
