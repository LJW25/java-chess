package chess.domain.chessboard;

import chess.domain.piece.Empty;
import chess.domain.piece.PieceState;
import chess.domain.piece.Team;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessBoard {

    private final Map<SquareCoordinate, PieceState> squares;

    public ChessBoard() {
        this.squares = ChessFactory.getInstance().create();
    }

    public boolean isRightTeam(Team team, SquareCoordinate from) {
        return squares.get(from).isSameTeam(team);
    }

    public void move(final SquareCoordinate from, final SquareCoordinate to) {
        final PieceState departure = squares.get(from);
        validateCanMove(from, to, departure);

        squares.replace(to, departure);
        squares.replace(from, new Empty());
    }

    private void validateCanMove(final SquareCoordinate from, final SquareCoordinate to, final PieceState departure) {
        final List<SquareCoordinate> route = departure.findRoute(from, to);

        final List<PieceState> routeSquares = route.stream()
                .map(squares::get)
                .collect(Collectors.toUnmodifiableList());

        departure.validateRoute(routeSquares);
    }

    public List<PieceState> getSquares() {
        return this.squares.values()
                .stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
