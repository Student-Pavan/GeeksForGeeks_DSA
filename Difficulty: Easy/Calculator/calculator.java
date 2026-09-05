class Solution {
    public void calculate(int a, int b, int oper) {
        int value = 0;

        if (oper == 1) {
            value = a + b;
        } 
        else if (oper == 2) {
            value = a - b;
        } 
        else if (oper == 3) {
            value = a * b;
        } 
        else {
            System.out.print("Invalid Input");
            return;
        }

        System.out.print(value);
    }
}