package chess.domain.piece.state;

import chess.domain.chessboard.Coordinate;
import chess.domain.piece.Empty;
import chess.domain.piece.PieceState;
import chess.domain.piece.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RookTest {

    @Test
    void 룩이_갈_수_없는_좌표이면_예외가_발생한다() {
        //given
        final Rook rook = new Rook(Team.BLACK);
        final Coordinate a1 = Coordinate.of("a1");
        final Coordinate b3 = Coordinate.of("b3");

        //when & then
        Assertions.assertThatThrownBy(() -> rook.findRoute(a1, b3)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 룩은_같은_파일의_좌표로_움직일_수_있다() {
        //given
        final Rook rook = new Rook(Team.BLACK);
        final Coordinate a1 = Coordinate.of("a1");
        final Coordinate a3 = Coordinate.of("a3");

        //when & then
        Assertions.assertThat(rook.findRoute(a1, a3)).containsExactly(Coordinate.of("a2"), Coordinate.of("a3"));
    }


    @Test
    void 룩은_같은_랭크의_좌표로_움직일_수_있다() {
        //given
        final Rook rook = new Rook(Team.BLACK);
        final Coordinate a1 = Coordinate.of("a1");
        final Coordinate e1 = Coordinate.of("e1");

        //when & then
        Assertions.assertThat(rook.findRoute(a1, e1)).containsExactly(Coordinate.of("b1"), Coordinate.of("c1")
                , Coordinate.of("d1"), Coordinate.of("e1"));
    }

    @Test
    void 룩은_도착지_스퀘어에_같은_팀이_있으면_갈_수_없다는_예외가_발생() {
        //given
        final Team team = Team.BLACK;
        final Rook rook = new Rook(team);
        final List<PieceState> route = List.of(new Empty(), new Empty(), new Pawn(team));

        //when & then
        Assertions.assertThatThrownBy(() -> rook.validateRoute(route))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 룩은_도착지를_가는_중간에_다른_기물이_있으면_갈_수_없다는_예외가_발생() {
        //given
        final Team team = Team.BLACK;
        final Rook rook = new Rook(team);
        final List<PieceState> route = List.of(new Empty(), new Pawn(team), new Empty());

        //when & then
        Assertions.assertThatThrownBy(() -> rook.validateRoute(route))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 룩은_도착지가_비어있으면_예외가_발생하지_않는다() {
        //given
        final Team team = Team.BLACK;
        final Rook rook = new Rook(team);
        final List<PieceState> route = List.of(new Empty(), new Empty(), new Empty());

        //when & then
        assertDoesNotThrow(() -> rook.validateRoute(route));
    }

    @Test
    void 룩은_도착지에_다른팀의_기물이_있으면_예외가_발생하지_않는다() {
        //given
        final Rook rook = new Rook(Team.BLACK);
        final List<PieceState> route = List.of(new Empty(), new Empty(),
                new Pawn(Team.WHITE));

        //when & then
        assertDoesNotThrow(() -> rook.validateRoute(route));
    }
}
