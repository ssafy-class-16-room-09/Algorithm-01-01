# 알고리즘 스터디 1팀

문제 링크를 이슈로 등록하면(여러 개 한 번에 가능), 문제마다 하위 이슈가 자동으로 만들어지고
풀이 폴더 생성부터 제출 검사·현황 집계까지 GitHub Actions가 처리합니다.

- 플랫폼: SWEA, 프로그래머스, 코드트리
- 언어: Java
- 구조: 주차별 폴더

## 어떻게 돌아가나요

```
① 문제 등록 (Issue)              ② 풀이 제출 (PR)              ③ 머지
──────────────────────          ─────────────────────────    ──────────────────────
주차 이슈에 링크 입력       →    solutions/week-01/           →  그 문제의 하위 이슈 체크리스트 체크
(한 줄에 하나, 여러 개 가능)       swea-1859/{내아이디}/           전원 제출 시 하위 이슈 자동 닫힘
   ↓ 자동                             ↓ 자동                        ↓ 자동
문제마다 하위 이슈 생성           경로 규칙 검사                 README 현황판 갱신
(Sub-issues로 주차 이슈에 연결)    javac 컴파일 검사              하위 이슈 전부 닫히면
문제별 폴더 + README 생성        주차/플랫폼 라벨 부여            주차 이슈도 자동 닫힘
문제별 제출 체크리스트 댓글
```

## 참여 방법

### 1. 문제 등록 (누구나)

[Issues → New issue → 📌 알고리즘 문제 등록](../../issues/new?template=problem.yml)에서
`주차`와 `문제 목록`을 입력하고 등록합니다. `문제 목록`은 한 줄에 문제 하나, `|`로 구분해서 여러 개를 한 번에 등록할 수 있습니다.

```
링크 | 제목(선택) | 번호(선택) | 난이도(선택) | 마감일(선택)
```

> 플랫폼은 링크 도메인(swexpertacademy.com · programmers.co.kr · codetree.ai)으로 자동 판별합니다.
> SWEA는 링크에 문제 번호가 없어서 `번호`를 직접 입력해야 합니다. 프로그래머스는 비워 두면 링크에서 자동으로 추출하고, 코드트리는 링크에서 문제 슬러그를 자동으로 추출합니다.
> **제목도 프로그래머스는 비워 두면 문제 페이지에서 자동으로 가져옵니다** — 프로그래머스 문제만 등록할 땐 링크 한 줄이면 충분합니다.
> SWEA·코드트리는 로그인이 필요한 페이지라 제목을 직접 입력해야 합니다.

이 이슈가 **주차 이슈(부모)**가 되고, 문제마다 **하위 이슈(자식)**가 하나씩 만들어져
[Sub-issues](https://docs.github.com/en/issues/tracking-your-work-with-issues/using-issues/adding-sub-issues)로 연결됩니다.
잠시 뒤 각 하위 이슈에 문제 폴더 경로와 참여 방법이 댓글로 달립니다.

### 2. 풀이 제출

```bash
# 이 저장소를 로컬에 클론해놨다고 가정

# 1. main 브랜치로 체크아웃 및 최신화
git switch main && git pull

# 2. 이름 규칙에 맞는 브랜치 생성 및 체크아웃
# 브랜치 이름 규칙: solve/{주차}-{문제}-{내 아이디}
git switch -c solve/week-01-swea-1859-내아이디

# 3. 최상위 폴더 기준으로 내가 푼 문제 풀이 폴더 생성
# 폴더 경로 규칙: solutions/{주차}/{문제}/{내 아이디}
mkdir -p solutions/week-01/swea-1859/내아이디

# 4. 생성한 폴더에 문제 풀이 코드 Solution.py를 추가 후 커밋
git add . && git commit -m "solve: SWEA 1859 백만 장자 프로젝트"

# 5. 내 브랜치를 깃허브로 푸시
git push -u origin HEAD
```

이후 깃허브에 접속하여 내 브랜치로 올린 커밋에 대해 PR를 새로 엽니다.
PR을 열면 봇이 경로 규칙과 컴파일을 검사하고 결과를 댓글로 남깁니다.

### 3. 리뷰 & 머지

리뷰어 승인 후 머지하면 해당 문제의 하위 이슈 체크리스트에 자동으로 체크되고,
전원이 제출하면 그 하위 이슈가 닫힙니다. 주차 이슈에 연결된 하위 이슈가 모두 닫히면
주차 이슈도 자동으로 닫힙니다.

## 디렉터리 규칙

```
solutions/
└── week-01/
    └── swea-1859/                ← {플랫폼}-{문제번호 또는 슬러그}
        ├── .problem.json         ← 워크플로가 생성 (건드리지 마세요)
        ├── README.md             ← 워크플로가 생성
        ├── JooeonLee/
        │   ├── v1/Solution.java   ← 풀이 여러 개는 하위 폴더로 (선택)
        │   └── v2/Solution.java
        └── another-member/
            └── Solution.java      ← 하나면 폴더에 바로
```

- 플랫폼 접두사: SWEA → `swea`, 프로그래머스 → `pgs`, 코드트리 → `ct`
- 코드트리는 문제 번호 대신 링크의 슬러그를 씁니다. 예) `ct-codetree-omakase`
- 사람마다 폴더를 나눕니다. Java 풀이는 클래스명이 대부분 `Solution`이라
  한 폴더에 모으면 컴파일이 깨집니다.
- 한 문제에 풀이를 여러 개 내려면 본인 폴더 아래 하위 폴더(`v1`, `bfs` 등)로 나눕니다. 폴더마다 따로 컴파일됩니다.
- 폴더 이름은 **본인 GitHub 아이디**와 정확히 같아야 합니다. 다르면 PR 검사에서 막힙니다.

자세한 규칙은 [CONTRIBUTING.md](CONTRIBUTING.md)를 참고하세요.

## 스터디원 관리

[`.github/study-members.yml`](.github/study-members.yml)에 GitHub 아이디를 추가하면
다음 문제부터 체크리스트에 포함됩니다. 파일을 지우면 레포 콜라보레이터 목록을 대신 사용합니다.

## 📊 스터디 현황

<!-- algo-study:board:start -->

> 마지막 갱신: 2026-09-22 · 등록된 문제 6개

### 🏆 제출 순위

| 순위 | 스터디원 | 푼 문제 | 진행률 |
| --- | --- | --- | --- |
| 🥇 | [@InaJeong73](https://github.com/InaJeong73) | 2 / 6 | 33% |
| 🥈 | [@eunbin58](https://github.com/eunbin58) | 1 / 6 | 17% |

### 📚 주차별 문제

<details open>
<summary><b>week-10</b> (3문제)</summary>

| 문제 | 이슈 | 제출 | 제출자 |
| --- | --- | --- | --- |
| [프로그래머스 42861 · 섬 연결하기](https://school.programmers.co.kr/learn/courses/30/lessons/42861) | [#12](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/12) | 1 | [@InaJeong73](https://github.com/InaJeong73) |
| [프로그래머스 43162 · 네트워크](https://school.programmers.co.kr/learn/courses/30/lessons/43162) | [#10](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/10) | 2 | [@InaJeong73](https://github.com/InaJeong73), [@eunbin58](https://github.com/eunbin58) |
| [프로그래머스 64063 · 호텔 방 배정](https://school.programmers.co.kr/learn/courses/30/lessons/64063) | [#14](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/14) | 0 | - |

</details>

<details>
<summary><b>week-09</b> (1문제)</summary>

| 문제 | 이슈 | 제출 | 제출자 |
| --- | --- | --- | --- |
| [SWEA 7468 · 창용 마을 무리의 개수](https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AaBayn5a0PrHBISr&contestProbId=AWngfZVa9XwDFAQU&probBoxId=AaBayn5a0PvHBISr&type=PROBLEM&problemBoxTitle=Club+Problem+box+01&problemBoxCnt=14) | [#8](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/8) | 0 | - |

</details>

<details>
<summary><b>week-08</b> (1문제)</summary>

| 문제 | 이슈 | 제출 | 제출자 |
| --- | --- | --- | --- |
| [프로그래머스 49189 · 가장 먼 노드](https://school.programmers.co.kr/learn/courses/30/lessons/49189) | [#5](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/5) | 0 | - |

</details>

<details>
<summary><b>week-07</b> (1문제)</summary>

| 문제 | 이슈 | 제출 | 제출자 |
| --- | --- | --- | --- |
| [SWEA D5 · 수제 버거 장인](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWErcQmKy6kDFAXi&categoryId=AWErcQmKy6kDFAXi&categoryType=CODE&problemTitle=%EC%88%98%EC%A0%9C+%EB%B2%84%EA%B1%B0&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1) | [#2](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/2) | 0 | - |

</details>

<!-- algo-study:board:end -->
