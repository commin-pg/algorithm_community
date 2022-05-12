
public class KakaoBadUser {

	@Test
	public void call() {

		String[] userId = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
		String[] bannedId = { "fr*d*", "*rodo", "******", "******" };

		System.out.println(solution(userId, bannedId));
	}

	/**
	 * 순서의 상관없이 같이 아이디 조합은 1개로 취급 제재 아이디 조합에 같은 아이디가 중복해서 들어가지 않아야 함. 제재 대상 판단은
	 * 정규식으로 확인, 알파벳 소문자와 숫자로만 구성됨.
	 * 
	 * 배열의 길이가 1자리수이므로 성능은 큰 고려대상이 아님.
	 * 
	 * 수열을 응용해 풀이 가능 수열의 조합의 수는 bannedId 의 길이와 같음. 수열의 조합이 banned_id 와 같으면 카운트
	 * 
	 * 2 2 2 {a b} {a b} {c d} a a c, a a d a b c, a b d b a c, b a d b b c, b b d
	 */

	private Set<Set<String>> result;

	public int solution(String[] user_id, String[] banned_id) {
		result = new HashSet<>();
		dfs(user_id, banned_id, new LinkedHashSet<>());
		return result.size();
	}

	private void dfs(String[] user_id, String[] banned_id, Set<String> set) {
		if (set.size() == banned_id.length) {
			if (isBannedUsers(set, banned_id)) {
				result.add(new HashSet<>(set));
			}

			return;
		}

		for (String userId : user_id) {
			if (!set.contains(userId)) {
				set.add(userId);
				dfs(user_id, banned_id, set);
				set.remove(userId);
			}
		}
	}

	private boolean isBannedUsers(Set<String> set, String[] banned_id) {
		int i = 0;

		for (String user : set) {
			if (!isSameString(user, banned_id[i++])) {
				return false;
			}
		}

		return true;
	}

	private boolean isSameString(String a, String b) {
		if (a.length() != b.length()) {
			return false;
		}

		for (int i = 0; i < a.length(); i++) {
			if (b.charAt(i) == '*')
				continue;

			if (a.charAt(i) != b.charAt(i)) {
				return false;
			}
		}

		return true;
	}

	
	public int solutionFail(String[] userId, String[] bannedId) {
		int answer = 0;

		StringBuilder bannedRe = new StringBuilder();

		for (String banned : bannedId) {
			bannedRe.append(banned + " ");
		}

		HashSet<String> result = null;
		BadUserCombi comb = new BadUserCombi();

		for (int i = 0; i <= userId.length; i++) {
			String[] reComArr = new String[i];
			comb.reCombination(reComArr, userId.length, i, 0, 0, userId);
			result = comb.getResult();

			for (String user : result) {
				if (matcher(user, bannedRe.toString()))
					answer++;
			}
		}

		return answer;
	}

	private boolean matcher(String source, String target) {

		// 길이가 같지 않으면 다른 글자
		if (source.length() != target.length())
			return false;

		int len = source.length();

		for (int i = 0; i < len; i++) {
			if (target.charAt(i) == '*')
				continue;
			if (source.charAt(i) != target.charAt(i))
				return false;
		}

		return true;
	}

}

class BadUserCombi {

	private HashSet<String> result;

	public HashSet<String> getResult() {
		return result;
	}

	public BadUserCombi() {
		this.result = new HashSet<String>();
	}

	public void reCombination(String[] reComArr, int n, int r, int index, int target, String[] arr) {
		if (r == 0) {
			Set<String> set = new HashSet<String>(Arrays.asList(reComArr));
			StringBuilder sb = new StringBuilder();

			for (String arg : set) {
				sb.append(arg + " ");
			}
			result.add(sb.toString());
			return;
		}
		if (target == n)
			return;

		reComArr[index] = arr[target];
		reCombination(reComArr, n, r - 1, index + 1, target, arr);// 뽑는 경우
		reCombination(reComArr, n, r, index, target + 1, arr);// 안 뽑는 경우
	}

}
