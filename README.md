# 알고리즘 스터디 1팀

문제 링크를 이슈로 등록하면(여러 개 한 번에 가능), 문제마다 하위 이슈가 자동으로 만들어지고
풀이 폴더 생성부터 제출 검사·현황 집계까지 GitHub Actions가 처리합니다.

- 플랫폼: SWEA, 프로그래머스, LeetCode, 코드트리, 정올
- 언어: Java, Python
- 구조: 주차별 폴더

## 어떻게 돌아가나요

```
① 문제 등록 (Issue)              ② 풀이 제출 (PR)              ③ 머지
──────────────────────          ─────────────────────────    ──────────────────────
주차 이슈에 링크 입력       →    solutions/week-01/           →  그 문제의 하위 이슈 체크리스트 체크
(한 줄에 하나, 여러 개 가능)       swea-1859/{내아이디}/           전원 제출 시 하위 이슈 자동 닫힘
   ↓ 자동                             ↓ 자동                        ↓ 자동
문제마다 하위 이슈 생성           경로 규칙 검사                 README 현황판 갱신
(Sub-issues로 주차 이슈에 연결)    javac·문법 검사             하위 이슈 전부 닫히면
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

> 플랫폼은 링크 도메인(swexpertacademy.com · programmers.co.kr · leetcode.com · codetree.ai · jungol.co.kr)으로 자동 판별합니다.
> SWEA는 링크에 문제 번호가 없어서 `번호`를 직접 입력해야 합니다. 프로그래머스는 비워 두면 링크에서 자동으로 추출하고, 코드트리는 `/curated-cards/{슬러그}` 또는 `/problems/{슬러그}` 링크일 때 슬러그를 자동으로 추출합니다(아니면 `번호`를 직접 입력), 정올은 `/problem/{번호}` 링크에서 번호를 자동으로 추출합니다.
> **제목도 프로그래머스는 비워 두면 문제 페이지에서 자동으로 가져옵니다** — 프로그래머스 문제만 등록할 땐 링크 한 줄이면 충분합니다.
> SWEA·코드트리·정올은 로그인이 필요한 페이지라 제목을 직접 입력해야 합니다.

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

# 4. 생성한 폴더에 문제 풀이 코드 Solution.java 또는 Solution.py를 추가 후 커밋
git add . && git commit -m "solve: SWEA 1859 백만 장자 프로젝트"

# 5. 내 브랜치를 깃허브로 푸시
git push -u origin HEAD
```

이후 깃허브에 접속하여 내 브랜치로 올린 커밋에 대해 PR를 새로 엽니다.
PR을 열면 봇이 경로 규칙과 컴파일(Java)·문법(Python)을 검사하고 결과를 댓글로 남깁니다.

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
            └── Solution.java|py   ← 하나면 폴더에 바로
```

- 플랫폼 접두사: SWEA → `swea`, 프로그래머스 → `pgs`, LeetCode → `leetcode`, 코드트리 → `ct`, 정올 → `jungol`
- 코드트리는 문제 번호 대신 링크의 슬러그를 씁니다(하이픈은 밑줄로 바뀝니다). 예) `ct-challenge_thousand_stops`
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

> 마지막 갱신: 2026-10-29 · 등록된 문제 20개

### 🏆 제출 순위

| 순위 | 스터디원 | 푼 문제 | 진행률 |
| --- | --- | --- | --- |
| 🥇 | [@kjs2h](https://github.com/kjs2h) | 12 / 20 | 60% |
| 🥈 | [@pjsWorkingPlus](https://github.com/pjsWorkingPlus) | 11 / 20 | 55% |
| 🥉 | [@sejkim2](https://github.com/sejkim2) | 9 / 20 | 45% |
| 4 | [@eunbin58](https://github.com/eunbin58) | 4 / 20 | 20% |
| 5 | [@Taehoru](https://github.com/Taehoru) | 4 / 20 | 20% |

### 📚 주차별 문제

<details open>
<summary><b>week-04</b> (5문제)</summary>

| 문제 | 이슈 | 제출 | 제출자 |
| --- | --- | --- | --- |
| [코드트리 50713 · 세 수의 최대 곱](https://www.codetree.ai/ko/trails/complete/curated-cards/test-maximum-product-of-three-numbers/description) | [#142](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/142) | 0 | - |
| [정올 1733 · 오목](https://jungol.co.kr/problem/1733) | [#140](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/140) | 0 | - |
| [프로그래머스 172927 · 광물 캐기](https://school.programmers.co.kr/learn/courses/30/lessons/172927) | [#137](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/137) | 0 | - |
| [프로그래머스 72410 · 신규 아이디 추천](https://school.programmers.co.kr/learn/courses/30/lessons/72410) | [#138](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/138) | 0 | - |
| [SWEA 5209 · 최소 생산 비용](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-ZxiKcyIDFAVT) | [#139](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/139) | 0 | - |

</details>

<details>
<summary><b>week-03</b> (5문제)</summary>

| 문제 | 이슈 | 제출 | 제출자 |
| --- | --- | --- | --- |
| [코드트리 1644232 · 토스트 계란틀](https://www.codetree.ai/ko/frequent-problems/all/problems/toast-eggmold/description) | [#111](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/111) | 0 | - |
| [프로그래머스 142085 · 디펜스 게임](https://school.programmers.co.kr/learn/courses/30/lessons/142085) | [#109](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/109) | 0 | - |
| [프로그래머스 17680 · [1차] 캐시](https://school.programmers.co.kr/learn/courses/30/lessons/17680) | [#108](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/108) | 1 | [@kjs2h](https://github.com/kjs2h) |
| [프로그래머스 250121 · 데이터 분석](https://school.programmers.co.kr/learn/courses/30/lessons/250121) | [#107](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/107) | 4 | [@Taehoru](https://github.com/Taehoru), [@kjs2h](https://github.com/kjs2h), [@pjsWorkingPlus](https://github.com/pjsWorkingPlus), [@sejkim2](https://github.com/sejkim2) |
| [SWEA 1244 · 최대 상금](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15Khn6AN0CFAYD) | [#110](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/110) | 2 | [@kjs2h](https://github.com/kjs2h), [@pjsWorkingPlus](https://github.com/pjsWorkingPlus) |

</details>

<details>
<summary><b>week-02</b> (5문제)</summary>

| 문제 | 이슈 | 제출 | 제출자 |
| --- | --- | --- | --- |
| [코드트리 challenge_rod_cutting · 막대기 나누기](https://www.codetree.ai/ko/trails/complete/curated-cards/challenge-rod-cutting/description) | [#56](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/56) | 4 | [@eunbin58](https://github.com/eunbin58), [@kjs2h](https://github.com/kjs2h), [@pjsWorkingPlus](https://github.com/pjsWorkingPlus), [@sejkim2](https://github.com/sejkim2) |
| [프로그래머스 12946 · 하노이의 탑](https://school.programmers.co.kr/learn/courses/30/lessons/12946) | [#59](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/59) | 5 | [@Taehoru](https://github.com/Taehoru), [@eunbin58](https://github.com/eunbin58), [@kjs2h](https://github.com/kjs2h), [@pjsWorkingPlus](https://github.com/pjsWorkingPlus), [@sejkim2](https://github.com/sejkim2) |
| [프로그래머스 155651 · 호텔 대실](https://school.programmers.co.kr/learn/courses/30/lessons/155651) | [#58](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/58) | 4 | [@eunbin58](https://github.com/eunbin58), [@kjs2h](https://github.com/kjs2h), [@pjsWorkingPlus](https://github.com/pjsWorkingPlus), [@sejkim2](https://github.com/sejkim2) |
| [프로그래머스 1835 · 단체사진 찍기](https://school.programmers.co.kr/learn/courses/30/lessons/1835) | [#57](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/57) | 3 | [@kjs2h](https://github.com/kjs2h), [@pjsWorkingPlus](https://github.com/pjsWorkingPlus), [@sejkim2](https://github.com/sejkim2) |
| [SWEA 1210 · Ladder-1](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14ABYKADACFAYh) | [#60](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/60) | 4 | [@eunbin58](https://github.com/eunbin58), [@kjs2h](https://github.com/kjs2h), [@pjsWorkingPlus](https://github.com/pjsWorkingPlus), [@sejkim2](https://github.com/sejkim2) |

</details>

<details>
<summary><b>week-01</b> (5문제)</summary>

| 문제 | 이슈 | 제출 | 제출자 |
| --- | --- | --- | --- |
| [프로그래머스 42839 · 소수 찾기](https://school.programmers.co.kr/learn/courses/30/lessons/42839) | [#32](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/32) | 4 | [@Taehoru](https://github.com/Taehoru), [@kjs2h](https://github.com/kjs2h), [@pjsWorkingPlus](https://github.com/pjsWorkingPlus), [@sejkim2](https://github.com/sejkim2) |
| [프로그래머스 42885 · 구명보트](https://school.programmers.co.kr/learn/courses/30/lessons/42885) | [#31](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/31) | 4 | [@Taehoru](https://github.com/Taehoru), [@kjs2h](https://github.com/kjs2h), [@pjsWorkingPlus](https://github.com/pjsWorkingPlus), [@sejkim2](https://github.com/sejkim2) |
| [프로그래머스 42890 · 후보키](https://school.programmers.co.kr/learn/courses/30/lessons/42890) | [#33](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/33) | 2 | [@kjs2h](https://github.com/kjs2h), [@pjsWorkingPlus](https://github.com/pjsWorkingPlus) |
| [SWEA 15623 · 이중 최단 경로 문제](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AYOCL0GaCF4DFATW&categoryId=AYOCL0GaCF4DFATW&categoryType=CODE&problemTitle=%EC%9D%B4%EC%A4%91&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1#) | [#34](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/34) | 3 | [@kjs2h](https://github.com/kjs2h), [@pjsWorkingPlus](https://github.com/pjsWorkingPlus), [@sejkim2](https://github.com/sejkim2) |
| [SWEA 15636 · 가장 큰 안전한 집합](https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&problemLevel=5&contestProbId=AYOCa5fqDBoDFATW&categoryId=AYOCa5fqDBoDFATW&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=JAVA&select-1=5&pageSize=10&pageIndex=7) | [#35](https://github.com/ssafy-class-16-room-09/Algorithm-01-01/issues/35) | 0 | - |

</details>

<!-- algo-study:board:end -->
