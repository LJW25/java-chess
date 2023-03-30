package chess.domain.chessgame.state;

import chess.dao.ChessGameDao;
import chess.dao.ChessGameData;
import chess.dao.RoomName;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.SquareCoordinate;
import chess.domain.piece.Team;
import chess.domain.winningstatus.Score;
import chess.domain.winningstatus.WinningStatus;
import chess.domain.winningstatus.WinningStatusByKing;
import chess.domain.winningstatus.WinningStatusByScore;

import java.util.EnumMap;
import java.util.Map;

public class Running implements GameState {
    private static final String RUNNING_STATE_EXCEPTION_MESSAGE = "게임을 실행 중일때 수행할 수 없는 동작입니다.";

    private final ChessBoard chessBoard;
    private Team currentTeam;

    public Running(final ChessBoard chessBoard, final Team currentTeam) {
        this.chessBoard = chessBoard;
        this.currentTeam = currentTeam;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public GameState start() {
        throw new IllegalStateException(RUNNING_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public void move(final SquareCoordinate from, final SquareCoordinate to) {
        if (chessBoard.isDifferentTeam(currentTeam, from)) {
            throw new IllegalArgumentException("다른 팀의 턴에는 기물을 움직일 수 없습니다.");
        }

        chessBoard.move(from, to);
        currentTeam = currentTeam.getNextTurn();
    }

    @Override
    public boolean isKingDead() {
        return chessBoard.isKingDead();
    }

    @Override
    public GameState close() {
        final Team winner = chessBoard.findTeamHavingKing();
        return new Ready(new WinningStatusByKing(winner), new ChessBoard(chessBoard));
    }

    @Override
    public WinningStatus status() {
        Map<Team, Score> scores = new EnumMap<>(Team.class);
        scores.put(Team.WHITE, Score.of(chessBoard.getPiecesOf(Team.WHITE), chessBoard.countDoublePawnOf(Team.WHITE)));
        scores.put(Team.BLACK, Score.of(chessBoard.getPiecesOf(Team.BLACK), chessBoard.countDoublePawnOf(Team.BLACK)));

        return new WinningStatusByScore(scores);
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public GameState save(RoomName roomName) {
        ChessGameDao chessGameDao = new ChessGameDao();
        chessGameDao.saveGame(roomName, currentTeam, new ChessBoard(chessBoard));

        return new Ready();
    }

    @Override
    public GameState load(ChessGameData chessGameData) {
        throw new IllegalStateException(RUNNING_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public GameState end() {
        return new End();
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
