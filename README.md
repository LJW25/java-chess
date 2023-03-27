# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 미션 목표

- [x] 미션 시간안에 제출하기
- [x] 인터페이스 도출
- [x] TDD를 잘하자
- [x] 객체를 최대한 단순하게 만들자

## 기능 목록

### 도메인

---

#### 🏄‍♀️ 체스판

- [x] 8X8의 사이즈를 가진다
- [x] 각 칸을 알파벳(가로)과 숫자(세로)로 가져올 수 있다.
- [x] 각 말을 규칙에 맞게 배치한다.

---

#### 스퀘어

- [x] 다른 스퀘어를 받아서 상태를 전이할 수 있다

#### ♟️ 말

- [x] 자신의 종류를 가진다
    - [x] 폰, 룩, 나이트, 비숍, 퀸, 킹
- [x] 자신의 진영을 가진다.
    - [x] 검은색-대문자, 흰색-소문자
- [x] 같은 팀인지 여부를 검사한다.
- [x] 출발 위치와 도착 위치를 받아서 **각 말의 특성** 에 따라서 경로를 반환한다.
    - [x] 단, 갈 수 없는 경우 예외를 발생한다.
- [x] 전달받은 스퀘어들의 상태를 확인해서 지나갈 수 있는지 **각 말의 특성**에따라 판별한다.

#### Empty

- [x] 움직일 수 없다.
- [x] 항상 같은 팀이 아님을 반환한다.

#### ️폰

- [x] 파일이 같고, 랭크가 1 차이나는 경우 해당 칸으로 움직 일 수 있다.
- [x] (처음 움직이는 경우) 파일이 같고, 랭크가 2 차이나는 경우 해당 칸으로 움직 일 수 있다.
- [x] 단, 가는 경로에 다른 말이 있으면 갈 수 없다.
- [x] 파일이 1 차이나고, 랭크가 1차이나는 경우 (대각선), 해당 칸에 상대편 말이 있다면 움직일 수 있다.

#### 룩

- [x] 같은 파일 혹은 랭크인 경우
- [x] 마지막 칸을 제외한 모든 칸이 비었고
- [x] 마지막 칸에 같은 편 말이 없으면 움직일 수 있다.

#### 나이트

- [x] 출발 위치와 도착 위치의 파일과 랭크가 (2,1) or (1,2) 차이가 나고
- [x] 해당 도착 위치에 같은 편의 말이 없으면 움직일 수 있다.

#### 비숍

- [x] 출발 위치와 도착 위치의 파일과 랭크의 차이가 같아야 한다.
- [x] 마지막 칸을 제외한 모든 칸이 비었고
- [x] 마지막 칸에 같은 편 말이 없으면 움직일 수 있다.

#### 퀸

- [x] 같은 파일 혹은 랭크인 경우
- [x] 출발 위치와 도착 위치의 파일과 랭크의 차이가 같아야 한다.
- [x] 마지막 칸을 제외한 모든 칸이 비었고
- [x] 마지막 칸에 같은 편 말이 없으면 움직일 수 있다.

#### 킹

- [x] 파일과 랭크의 차이가 1 이하여야 한다
- [x] 도착 위치에 같은 편 말이 없으면 움직일 수 있다.

---

#### 좌표

- [x] 파일과 랭크로 이루어진 좌표를 받아 파싱한다.
- [x] 이동거리를 입력받아 각 위치로 이동한 좌표를 반환한다.

---

### 🖥 뷰

---

#### ⌨ 입력

- [x] 게임시작-종료를 받는다.
- [x] 출발 위치와 도착 위치를 받는다.

---

#### 👀️ 출력

- [x] **체스판**과, **말**의 배치를 출력한다.

---

## 1&2단계 추가 리팩토링 목록

- [x]  ChessBoardDto 이름 변경 및 패키지 이동
- [x]  PieceState → SquareState 네이밍 변경
- [x]  커맨드처리 상태패턴 적용
- [x]  ChessFactory.getInstance().create(); → ChessFactory.create()로 수정

## 3&4단계 추가 기능 목록

### 3단계
 
- [x]  왕이 잡혔을 때 게임을 종료한다
    - [x]  게임의 상태를 `READY`로 변경한다
- [x]  팀 별로 점수를 계산한다
    - [x]  점수를 관리하는 `Enum`을 만든다
    - [x]  한 행에 같은 팀의 폰이 두개 이상 존재 할 경우 개당 0.5점으로 계산한다
- [x]  `status` 커맨드를 입력받는다
    - [x]  게임을 진행 중인 경우
      → 각 팀의 점수와, 점수를 기준으로 이기고 있는 팀을 출력한다
    - [x]  게임이 종료된 경우(`READY`)
      → 왕을 기준으로 승자를 출력한다
    - [x]  게임이 한번도 시작되지 않은 경우 → 예외를 발생시킨다

### 4단계

- [x]  게임을 중간에 저장할 수 있다
    - [x]  중간 저장을 입력받는다
    - [x]  방 이름을 입력받는다
    - [x]  데이터를 파싱한다
    - [x]  DB에 저장한다
    - [x]  게임을 `READY` 상태로 돌린다
    - [x]  저장 완료 메세지를 출력한다
- [ ]  저장된 게임을 이어서 플레이할 수 있다
    - [x]  이어서 하기를 입력 받는다
    - [x]  참가 가능한 방 목록을 출력한다
    - [x]  참가할 방 이름을 입력받는다
    - [x]  게임의 상태를 읽어와 게임을 시작한다
    - [x]  데이터를 파싱한다
    - [x]  체스 보드를 생성한다
    - [x]  game을 생성한다
