package chess.dao;

import chess.domain.chessboard.ChessBoard;
import chess.domain.piece.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static chess.dao.ChessBoardStringifier.stringifyChessBoard;

public final class ChessGameDao {
    private static final String URL = "jdbc:mysql://localhost:13306/";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";
    private static final String QUERY_INSERT_CHESS_GAME = "INSERT INTO chess_game(room_name, turn, chess_board) VALUES(?, ?, ?)";
    private static final String WHITE_MARK = "W";
    private static final String BLACK_MARK = "B";
    private static final String QUERY_SELECT_ROOM_NAMES = "SELECT id, room_name, time FROM chess_game ORDER BY id DESC";
    private static final String QUERY_SELECT_CHESS_GAME = "SELECT turn, chess_board FROM chess_game WHERE id = ?";
    private static final String QUERY_DELETE_CHESS_GAME = "DELETE FROM chess_game WHERE room_name = ?";

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection(URL + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void saveGame(RoomName roomName, Team turn, ChessBoard chessBoard) {
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(QUERY_INSERT_CHESS_GAME)) {
            preparedStatement.setString(1, roomName.getRoomName());
            preparedStatement.setString(2, convertTurnToMark(turn));
            preparedStatement.setString(3, stringifyChessBoard(chessBoard));
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException("문제가 발생했습니다. 다시 save를 시도해 주세요.");
        }
    }

    private String convertTurnToMark(Team team) {
        if (team == Team.WHITE) {
            return WHITE_MARK;
        }
        if (team == Team.BLACK) {
            return BLACK_MARK;
        }
        throw new IllegalArgumentException("잘못된 팀입니다.");
    }

    public List<ChessGameSaveRecord> findChessGameSaveRecords() {
        final List<ChessGameSaveRecord> chessGameSaveRecords = new ArrayList<>();

        int id;
        String roomName;
        String time;

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(QUERY_SELECT_ROOM_NAMES)) {

            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                roomName = resultSet.getString("room_name");
                time = resultSet.getString("time");
                chessGameSaveRecords.add(0, new ChessGameSaveRecord(id, roomName, time));
            }
        } catch (final SQLException e) {
            throw new RuntimeException("문제가 발생했습니다. 다시 load를 시도해 주세요.");
        }

        return chessGameSaveRecords;
    }

    public ChessGameData findChessGame(final int id) {
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(QUERY_SELECT_CHESS_GAME)) {
            preparedStatement.setString(1, Integer.toString(id));

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String turn = resultSet.getString("turn");
                String oneLineChessBoard = resultSet.getString("chess_board");
                return new ChessGameData(turn, oneLineChessBoard);
            }
        } catch (final SQLException e) {
            throw new RuntimeException("문제가 발생했습니다. 다시 load를 시도해 주세요.");
        }
        throw new RuntimeException("해당 게임이 존재하지 않습니다. 다시 load를 시도해 주세요.");
    }

    public void deleteGame(final RoomName roomName) {
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(QUERY_DELETE_CHESS_GAME)) {
            preparedStatement.setString(1, roomName.getRoomName());
            preparedStatement.execute();

        } catch (final SQLException e) {
            throw new RuntimeException("문제가 발생했습니다. 다시 삭제를 시도해 주세요.");
        }
    }
}
