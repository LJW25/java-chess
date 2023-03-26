package chess.domain.piece;

import chess.domain.chessboard.SquareCoordinate;

import java.util.List;

public final class Empty implements SquareState {

    private static final String EMPTY_EXCEPTION = "빈칸은 해당 동작을 수행할 수 없습니다.";

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isSameTeam(final Piece piece) {
        return false;
    }

    @Override
    public boolean isSameTeam(final Team team) {
        return false;
    }

    @Override
    public Team getTeam() {
        throw new IllegalStateException(EMPTY_EXCEPTION);
    }

    @Override
    public List<SquareCoordinate> findRoute(final SquareCoordinate from, final SquareCoordinate to) {
        throw new IllegalStateException(EMPTY_EXCEPTION);
    }

    @Override
    public void validateRoute(final List<SquareState> routeSquares) {
        throw new IllegalStateException(EMPTY_EXCEPTION);
    }
}
