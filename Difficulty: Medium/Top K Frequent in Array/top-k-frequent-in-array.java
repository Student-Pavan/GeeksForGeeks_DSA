class Solution {
	public ArrayList<Integer> topKFreq(int[] arr, int k) {
		
		HashMap<Integer, Integer> map = new HashMap<>();
		
		for (int i : arr) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}
		
		ArrayList<Integer> list = new ArrayList<>(map.keySet());
		
		Collections.sort(list, (a, b) -> {
			if (!map.get(a).equals(map.get(b))) {
				return map.get(b) - map.get(a); // frequency descending
			}
			return b - a; // element descending
		});
		
		ArrayList<Integer> ans = new ArrayList<>();
		
		for (int i = 0; i < k; i++) {
			ans.add(list.get(i));
		}
		
		return ans;
	}
}
