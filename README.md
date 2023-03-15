# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 미션 목표

- [ ] 미션 시간안에 제출하기
- [ ] 인터페이스 도출
- [ ] TDD를 잘하자
- [ ] 객체를 최대한 단순하게 만들자

## 기능 목록

### 도메인

---

#### 🏄‍♀️ 체스판

-[x] 8X8의 사이즈를 가진다
-[ ] 각 칸을 알파벳(가로)과 숫자(세로)로 가져올 수 있다.
-[x] 각 말을 규칙에 맞게 배치한다.

---

#### 스퀘어

-[ ] 다른 스퀘어를 받아서 상태를 전이할 수 있다

#### ♟️ 말

-[x] 자신의 종류를 가진다
    -[x] 폰, 룩, 나이트, 비숍, 퀸, 킹
-[x] 자신의 진영을 가진다.
    -[x] 검은색-대문자, 흰색-소문자
- [x] 같은 팀인지 여부를 검사한다.
- [ ] 출발 위치와 도착 위치를 받아서 **각 말의 특성** 에 따라서 경로를 반환한다.
    - [ ] 단, 갈 수 없는 경우 빈 리스트를 반환한다.
- [ ] 전달받은 스퀘어들의 상태를 확인해서 지나갈 수 있는지 **각 말의 특성**에따라 판별한다.

#### Empty

- [ ] 움직일 수 없다.
- [ ] 항상 같은 팀이 아님을 반환한다.

#### ️폰

- [ ] 파일이 같고, 랭크가 1 차이나는 경우 해당 칸으로 움직 일 수 있다.
- [ ] (처음 움직이는 경우) 파일이 같고, 랭크가 2 차이나는 경우 해당 칸으로 움직 일 수 있다.
- [ ] 단, 가는 경로에 다른 말이 있으면 갈 수 없다.
- [ ] 파일이 1 차이나고, 랭크가 1차이나는 경우 (대각선), 해당 칸에 상대편 말이 있다면 움직일 수 있다.

#### 룩

- [ ] 같은 파일 혹은 랭크인 경우
- [ ] 마지막 칸을 제외한 모든 칸이 비었고
- [ ] 마지막 칸에 같은 편 말이 없으면 움직일 수 있다.

#### 나이트

- [ ] 출발 위치와 도착 위치의 파일과 랭크가 (2,1) or (1,2) 차이가 나고
- [ ] 해당 도착 위치에 같은 편의 말이 없으면 움직일 수 있다.

#### 비숍

- [ ] 출발 위치와 도착 위치의 파일과 랭크의 차이가 같아야 한다.
- [ ] 마지막 칸을 제외한 모든 칸이 비었고
- [ ] 마지막 칸에 같은 편 말이 없으면 움직일 수 있다.

#### 퀸

- [ ] 같은 파일 혹은 랭크인 경우
- [ ] 출발 위치와 도착 위치의 파일과 랭크의 차이가 같아야 한다.
- [ ] 마지막 칸을 제외한 모든 칸이 비었고
- [ ] 마지막 칸에 같은 편 말이 없으면 움직일 수 있다.

#### 킹

- [ ] 파일과 랭크의 차이가 1 이하여야 한다
- [ ] 도착 위치에 같은 편 말이 없으면 움직일 수 있다.

---

#### 좌표

- [ ] 파일과 랭크로 이루어진 좌표를 받아 파싱한다.

---

### 🖥 뷰

---

#### ⌨ 입력

-[x] 게임시작-종료를 받는다.
- [ ] 출발 위치와 도착 위치를 받는다.

---

#### 👀️ 출력

-[x] **체스판**과, **말**의 배치를 출력한다.
