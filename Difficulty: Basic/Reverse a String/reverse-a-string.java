// User function Template for Java

class Solution {
    public static String reverseString(String s) {
        // code here
        char arr[] = s.toCharArray();
        int i =  0;
        int j = s.length()-1;
        while(i < j){
            char front = s.charAt(i);
            char back = s.charAt(j);
            arr[j] = front;
            arr[i] = back;
            i++;
            j--;
        }
        return new String(arr);
    }
}