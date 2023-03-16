package chess.domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CoordinateTest {

    @Test
    void 좌표는_a부터h_1부터8_이외에는_예외가_발생한다() {
        //given
        final String alphanumeric = "o9";

        //when & then
        assertThatThrownBy(() -> Coordinate.of(alphanumeric)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 좌표는_알파벳숫자를_받아_파싱한다() {
        //given
        final String alphanumeric = "a3";

        //when
        final Coordinate coordinate = Coordinate.of(alphanumeric);

        //then
        assertThat(coordinate)
                .extracting("fileIndex")
                .isEqualTo('a');
        assertThat(coordinate)
                .extracting("rankIndex")
                .isEqualTo('3');
    }

    @Test
    void 좌표는_입력받은_숫자만큼_상하로_움직일_수_있다() {
        //given
        final Coordinate a1 = Coordinate.of("a1");

        //when & then
        assertThat(a1.verticalMove(1)).isEqualTo(Coordinate.of("a2"));
    }

    @Test
    void 좌표는_입력받은_숫자만큼_좌우로_움직일_수_있다() {
        //given
        final Coordinate a1 = Coordinate.of("a1");

        //when & then
        assertThat(a1.horizontalMove(1)).isEqualTo(Coordinate.of("b1"));
    }

    @Test
    void 좌표는_입력받은_숫자만큼_기울기가_양수인_대각선으로_움직일_수_있다() {
        //given
        final Coordinate a1 = Coordinate.of("a1");

        //when & then
        assertThat(a1.positiveDiagonalMove(1)).isEqualTo(Coordinate.of("b2"));
    }

    @Test
    void 좌표는_입력받은_숫자만큼_기울기가_음수인_대각선으로_움직일_수_있다() {
        //given
        final Coordinate b1 = Coordinate.of("b1");

        //when & then
        assertThat(b1.negativeDiagonalMove(1)).isEqualTo(Coordinate.of("a2"));
    }

    @Test
    void 좌표는_다른_좌표와_같은_랭크인지_검사할_수_있다() {
        //given
        final Coordinate a1 = Coordinate.of("a1");
        final Coordinate b1 = Coordinate.of("b1");

        //when & then
        assertThat(a1.isSameRank(b1)).isTrue();
    }

    @Test
    void 좌표는_다른_좌표와_같은_파일인지_검사할_수_있다() {
        //given
        final Coordinate a1 = Coordinate.of("a1");
        final Coordinate a2 = Coordinate.of("a2");

        //when & then
        assertThat(a1.isSameFile(a2)).isTrue();
    }

    @Test
    void 좌표는_다른_좌표와의_랭크_차를_계산할_수_있다(){
        //given
        final Coordinate a1 = Coordinate.of("a1");
        final Coordinate a5 = Coordinate.of("a5");

        //when & then
        assertThat(a1.calculateRankDistance(a5)).isEqualTo(4);
    }

    @Test
    void 좌표는_다른_좌표와의_파일_차를_계산할_수_있다(){
        //given
        final Coordinate a1 = Coordinate.of("a1");
        final Coordinate e1 = Coordinate.of("e1");

        //when & then
        assertThat(a1.calculateFileDistance(e1)).isEqualTo(4);
    }

    @Test
    void 좌표는_다른_좌표가_우상향_대각선에_있는지_검사할_수_있다() {
        //given
        final Coordinate a1 = Coordinate.of("a1");
        final Coordinate b2 = Coordinate.of("b2");
        final Coordinate h8 = Coordinate.of("h8");


        //when & then
        assertThat(a1.isPositiveDiagonal(b2)).isTrue();
        assertThat(h8.isPositiveDiagonal(b2)).isTrue();
    }

    @Test
    void 좌표는_다른_좌표와_좌상향_대각선에_있는지_검사할_수_있다() {
        //given
        final Coordinate b6 = Coordinate.of("b6");
        final Coordinate c5 = Coordinate.of("c5");
        final Coordinate e3 = Coordinate.of("e3");

        //when & then
        assertThat(b6.isNegativeDiagonal(c5)).isTrue();
        assertThat(b6.isNegativeDiagonal(e3)).isTrue();
    }

}
