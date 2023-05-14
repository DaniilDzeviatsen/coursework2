package org.example.repositories;

import org.example.exceptions.RepositoryException;
import org.example.models.Chapter;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChapterRepositoryImpl implements ChapterRepository {
    private final DataSource db;

    public ChapterRepositoryImpl(DataSource db) {
        this.db = db;
    }


    @Override
    public void createNewChapter(String name, long id, long totalCardsNum, long numOfLearnedCards) {

    }

    @Override
    public void deleteChapter(long id) {
        String sql = """
                DELETE FROM CHAPTER
                WHERE CHAPTER.ID = ? """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }


    @Override
    public List<Chapter> getAllChapters() {
        String sql = """
                SELECT CHAPTER.ID AS CHAPTER_ID,
                       CHAPTER.NAME AS NAME
                FROM CHAPTER;
                """;
        try (
                Connection connection = db.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<Chapter> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Chapter(
                        resultSet.getLong("chapterId"),
                        resultSet.getString("name")
                ));
            }
            return result;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}

