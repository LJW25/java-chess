package chess.domain.winningstatus;

import chess.domain.piece.Team;

import java.util.Collections;
import java.util.Map;

public final class WinningStatusByScore implements WinningStatus {

    private final Map<Team, Score> scores;

    public WinningStatusByScore(final Map<Team, Score> scores) {
        this.scores = scores;
    }

    @Override
    public boolean isWinnerDetermined() {
        return false;
    }

    @Override
    public Map<Team, Score> getScores() {
        return Collections.unmodifiableMap(scores);
    }

    @Override
    public Team getWinner() {
        final double white_score = scores.get(Team.WHITE).getScore();
        final double black_score = scores.get(Team.BLACK).getScore();

        if (white_score > black_score) {
            return Team.WHITE;
        }
        if (black_score > white_score) {
            return Team.BLACK;
        }
        return null;
    }
}
