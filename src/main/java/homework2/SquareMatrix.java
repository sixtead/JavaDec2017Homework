package homework2;

public class SquareMatrix {
    private int[][] arr;

    SquareMatrix(int n) {
        if(n < 0) throw new IllegalArgumentException();

        this.arr = new int[n][n];
        int value = 1;

        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length; col++) {
                arr[row][col] = value++;
            }
        }
    }

    public int get(int row, int col) {
        if(row >= arr.length || col >= arr.length)
            throw new ArrayIndexOutOfBoundsException();
		return arr[row][col];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int[] subArr : arr) {
            for(int i = 0; i < arr.length; i++) {
                result.append(subArr[i]);
                if(i != arr.length - 1) result.append(" ");
            }
            result.append("\n");
        }

        return result.toString();
    }

    public String toStringSpiral() {
        int row = (arr.length % 2 == 0) ? arr.length / 2 - 1: arr.length / 2;
        int col = arr.length / 2;
        StringBuilder result = new StringBuilder();

        result.append(arr[row][col]);

        outer:
        for(int step = 1; step <= arr.length; step++) {
            if(step % 2 != 0) {
                for(int j = 0; j < step; j++) {
                    if(col == 0) break outer;
                    result.append(" ").append(arr[row][--col]);
                }
                for(int j = 0; j < step; j++) {
                    if(row == arr.length - 1) break outer;
                    result.append(" ").append(arr[++row][col]);
                }
            } else {
                for(int j = 0; j < step; j++) {
                    if(col == arr.length - 1) break outer;
                    result.append(" ").append(arr[row][++col]);
                }
                for(int j = 0; j < step; j++) {
                    if(row == 0) break outer;
                    result.append(" ").append(arr[--row][col]);
                }
            }
        }

        return result.toString();
    }
}
