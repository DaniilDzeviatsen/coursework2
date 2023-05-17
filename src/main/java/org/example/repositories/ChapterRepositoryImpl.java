package org.example.repositories;

import org.example.exceptions.RepositoryException;
import org.example.models.Card;
import org.example.models.Chapter;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChapterRepositoryImpl implements ChapterRepository {
    private final DataSource db;

    public ChapterRepositoryImpl(DataSource db) {
        this.db = db;
    }


    @Override
    public void createNewChapter(String name) {
        String sql = """
                INSERT INTO CHAPTER (NAME)
                VALUES (?);
                """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, name);
            statement.executeUpdate();}
        catch (SQLException e){
            throw new RepositoryException(e);
        }
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
    public List<Card> showAllCards(long chapterId) {
        String sql = """
                SELECT CARD.ID AS ID,
                       CARD.QUESTION AS QUESTION,
                       CARD.ANSWER AS ANSWER,
                       CARD.IS_REMEMBERED AS REMEMBERED
                FROM CARD
                WHERE CARD.CHAPTER_ID = ?
                                 """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, chapterId);
            ResultSet resultSet = statement.executeQuery();
            List<Card> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Card(
                        resultSet.getString("QUESTION"),
                        resultSet.getString("ANSWER"),
                        resultSet.getBoolean("REMEMBERED"),
                        resultSet.getLong("ID")
                ));
            }
            return result;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }


    @Override
    public List<Chapter> getAllChapters() {
        String sql = """
                SELECT CHAPTER.NAME AS NAME,
                                     CHAPTER.ID AS CHAPTER_ID,
                                     COUNT(CARD.ID) AS TOTAL_QUESTIONS,
                                     COUNT (CARD.ID) FILTER ( WHERE is_remembered) AS LEARNED_QUESTIONS
                              FROM CHAPTER
                                       LEFT JOIN CARD ON CHAPTER.ID = CARD.CHAPTER_ID
                              GROUP BY CHAPTER.ID;
                 """;
        try (
                Connection connection = db.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<Chapter> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Chapter(
                        resultSet.getLong("CHAPTER_ID"),
                        resultSet.getString("NAME"),
                        resultSet.getLong("TOTAL_QUESTIONS"),
                        resultSet.getLong("LEARNED_QUESTIONS")
                ));
            }
            return result;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}

