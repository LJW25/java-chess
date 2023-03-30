package chess.view;

import chess.dao.ChessGameSaveRecord;
import chess.domain.piece.Team;
import chess.domain.winningstatus.GameResult;

import java.util.List;

public final class OutputView {

    private OutputView() {
    }

    public static void printWelcomeMessage() {
        System.out.println("게임 시작: " + Command.START.value());
        System.out.println("게임 종료: " + Command.END.value());
        System.out.println("게임 이동: move source target위치 - 예. move b2 b3");
        System.out.println("게임 현황: " + Command.STATUS.value());
        System.out.println("게임 저장: " + Command.SAVE.value());
        System.out.println("게임 불러 오기: " + Command.LOAD.value());
    }

    public static void printChessBoard(final List<String> chessBoard) {

        for (int i = 0; i < chessBoard.size(); i++) {
            int rankCount = chessBoard.size() - i;
            System.out.println(chessBoard.get(i) + "  |" + rankCount);
        }
        System.out.println("________");
        System.out.println("abcdefgh");
    }

    public static void printScore(final Team team, final double score) {
        System.out.println(TeamName.getNameByTeam(team) + "의 점수는 " + score + "점 입니다.");
    }

    public static void printWinnerWhenRunning(final GameResult gameResult) {
        if (gameResult == GameResult.DRAW) {
            System.out.println("두 플레이어가 동등하게 겨루고 있습니다.");
            return;
        }
        System.out.println(TeamName.getNameByTeam(gameResult.getWinner()) + "이(가) 우세합니다.");
    }

    public static void printWinnerAfterRunning(final GameResult gameResult) {
        System.out.println(TeamName.getNameByTeam(gameResult.getWinner()) + "이(가) 상태팀의 왕을 잡아 승리하였습니다.");
    }

    public static void printSaved(final String roomName) {
        System.out.println(roomName + "방으로 저장이 완료되었습니다. 대기 상태로 돌아갑니다.\n");
        printWelcomeMessage();
    }

    public static void printSavedRoomNames(final List<ChessGameSaveRecord> chessGameSaveRecords) {
        System.out.println("< 저장된 방 목록 >");
        for (ChessGameSaveRecord record : chessGameSaveRecords) {
            System.out.println(record.id + "\t| " + record.roomName + "\t| " + record.time);
        }
        System.out.println();
    }

    public static void printErrorMessage(final String errorMessage) {
        System.out.println("[ERROR] " + errorMessage + System.lineSeparator());
    }
}
