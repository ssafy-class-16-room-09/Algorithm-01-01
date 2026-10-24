import java.util.*;

enum OP {
    LESS, MORE, EQ
}

class Solution {
    static final String[] characters = {"A", "C", "F", "J", "M", "N", "R", "T"};
    static final Map<String, Integer> map = new HashMap<>();
    static List<List<Rule>> rules;
    static final Map<Integer, Integer> characterPosition = new HashMap<>();
    static int[] answerArr;
    static int answer;

    public int solution(int n, String[] data) {
        answer = 0;
        characterPosition.clear();
        answerArr = new int[8];
        Arrays.fill(answerArr, -1);

        rules = new ArrayList<>();
        for (int i = 0; i < characters.length; i++) {
            map.put(characters[i], i);
            rules.add(new ArrayList<Rule>());
        }

        for (int i = 0; i < n; i++) {
            int subject = map.get(String.valueOf(data[i].charAt(0)));
            int object = map.get(String.valueOf(data[i].charAt(2)));
            char operator = data[i].charAt(3);
            OP op = operator == '=' ? OP.EQ : operator == '<' ? OP.LESS : OP.MORE;
            int gap = data[i].charAt(4) - '0';

            // 두 캐릭터 중 나중에 배정되는 쪽에서 조건을 검사한다.
            rules.get(subject).add(new Rule(subject, op, object, gap));
            rules.get(object).add(new Rule(object, op, subject, gap));
        }

        dfs(0);
        return answer;
    }

    static void dfs(int cnt) {
        if (cnt == 8) {
            answer++;
            return;
        }

        // cnt번 캐릭터를 빈 위치에 배정한다.
        for (int position = 0; position < 8; position++) {
            if (answerArr[position] != -1) {
                continue;
            }

            answerArr[position] = cnt;
            characterPosition.put(cnt, position);
            boolean satisfyRules = true;

            for (Rule rule : rules.get(cnt)) {
                int objectPosition = characterPosition.getOrDefault(rule.objectId, -1);
                if (objectPosition == -1) {
                    continue;
                }

                int distance = Math.abs(position - objectPosition) - 1;
                if ((rule.operator == OP.EQ && distance != rule.gap)
                        || (rule.operator == OP.LESS && distance >= rule.gap)
                        || (rule.operator == OP.MORE && distance <= rule.gap)) {
                    satisfyRules = false;
                    break;
                }
            }

            if (satisfyRules) {
                dfs(cnt + 1);
            }

            answerArr[position] = -1;
            characterPosition.remove(cnt);
        }
    }
}

class Rule {
    int subjectId;
    int objectId;
    int gap;
    OP operator;

    Rule(int subjectId, OP operator, int objectId, int gap) {
        this.subjectId = subjectId;
        this.operator = operator;
        this.objectId = objectId;
        this.gap = gap;
    }
}
