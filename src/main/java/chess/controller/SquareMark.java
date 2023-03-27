package chess.controller;

import chess.domain.piece.Empty;
import chess.domain.piece.SquareState;
import chess.domain.piece.Team;
import chess.domain.piece.state.*;

import java.util.Arrays;

public enum SquareMark {
    EMPTY(".", Empty.class),
    PAWN("P", Pawn.class),
    ROOK("R", Rook.class),
    KNIGHT("N", Knight.class),
    BISHOP("B", Bishop.class),
    QUEEN("Q", Queen.class),
    KING("K", King.class);

    private final String mark;
    private final Class<? extends SquareState> pieceClass;

    SquareMark(final String mark, final Class<? extends SquareState> pieceClass) {
        this.mark = mark;
        this.pieceClass = pieceClass;
    }

    public static String getMarkBySquare(final SquareState squareState) {
        final String squareMark = getSquareMarkByPiece(squareState);

        if (EMPTY.mark.equals(squareMark)) {
            return EMPTY.mark;
        }

        return getMarkByTeam(squareMark, squareState.getTeam());
    }

    private static String getMarkByTeam(final String squareMark, final Team team) {
        if (team.equals(Team.WHITE)) {
            return squareMark.toLowerCase();
        }
        return squareMark;
    }

    private static String getSquareMarkByPiece(final SquareState squareState) {
        return Arrays.stream(SquareMark.values())
                .filter(squareMark -> squareState.getClass() == squareMark.pieceClass)
                .map(SquareMark::getMark)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 종류의 기물입니다."));
    }

    private String getMark() {
        return mark;
    }

}
