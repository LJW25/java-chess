package chess.domain.chessboard.state.piece;

import chess.domain.chessboard.Coordinate;
import chess.domain.chessboard.state.Team;
import java.util.List;

public final class Bishop extends Piece {

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public List<Coordinate> findRoute(final Coordinate from, final Coordinate to) {
        validatePossibleDestination(from, to);

        if (from.isPositiveDiagonal(to)) {
            return positiveDiagonalRoute(from, to);
        }
        return negativeDiagonalRoute(from, to);
    }

    private void validatePossibleDestination(final Coordinate from, final Coordinate to) {
        if (!(from.isPositiveDiagonal(to) || from.isNegativeDiagonal(to))) {
            throwCanNotMoveException();
        }
    }
}
