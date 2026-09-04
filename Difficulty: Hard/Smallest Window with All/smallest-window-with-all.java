class Solution {
    public static String minWindow(String s, String p) {

        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : p.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int left = 0;
        int count = map.size();
        int minLen = Integer.MAX_VALUE;
        int startIndex = 0;

        for (int right = 0; right < s.length(); right++) {

            char curr = s.charAt(right);

            if (map.containsKey(curr)) {
                map.put(curr, map.get(curr) - 1);

                if (map.get(curr) == 0)
                    count--;
            }

            while (count == 0) {

                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    startIndex = left;
                }

                char leftChar = s.charAt(left);

                if (map.containsKey(leftChar)) {
                    map.put(leftChar, map.get(leftChar) + 1);

                    if (map.get(leftChar) > 0)
                        count++;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLen);
    }
}