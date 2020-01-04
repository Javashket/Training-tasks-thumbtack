package net.thumbtack.school.introduction;

public class FirstSteps {

    public int sum(int x, int y) {
        return x + y;
    }

    public int mul(int x, int y) {
        return x * y;
    }

    public int div(int x, int y) {
        return x / y;
    }

    public int mod(int x, int y) {
        return x % y;
    }

    public boolean isEqual(int x, int y) {
        return x == y;
    }

    public boolean isGreater(int x, int y) {
        return x > y;
    }

    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {
        return !(x < xLeft || x > xRight || y < yTop || y > yBottom);
    }

    public int sum(int[] array) {
        int result = 0;
        for (int value : array) {
            result += value;
        }
        return result;
    }

    public int mul(int[] array) {
        int length = array.length;
        int result = 1;
        if(length == 0) {
           result = 0;
        }
        for (int value : array) {
            result *= value;
        }
        return result;
    }

    public int min(int[] array) {
        int result = Integer.MAX_VALUE;
        for (int value : array) {
            if (value < result) {
                result = value;
            }
        }
        return result;
    }

    public int max(int[] array) {
        int result = Integer.MIN_VALUE;
        for (int value : array) {
            if (value > result) {
                result = value;
            }
        }
        return result;
    }

    public double average(int[] array) {
     int result = 0;
     int length = array.length;
        for (int value : array) {
            result += value;
        }
     return result != 0 ? (double) result / length : result;
    }

    public boolean isSortedDescendant(int[] array) {
      for(int i = 1; i < array.length; i++) {
          if(array[i-1] <= array[i]) {
              return false;
          }
      }
      return true;
    }

    public void cube(int[]array) {
     for(int i = 0; i < array.length; i++) {
         array[i] = (int) Math.pow(array[i],3);
     }
    }

    public boolean find(int[]array, int value) {
        for (int item : array) {
            if (item == value) {
                return true;
            }
        }
      return false;
    }

    public void reverse(int[]array) {
        int length = array.length;
        if (length != 1) {
            for (int i = 0; i < array.length / 2; i++) {
                int a = array[i];
                int b = array[length - i - 1];
                array[i] = b;
                array[length - i - 1] = a;
            }
        }
    }

    public boolean isPalindrome(int[]array) {
        int length = array.length;
         for (int i = 0; i < length / 2; i++) {
             if(array[i] != array[length - i - 1]) {
                 return false;
             }
         }
         return true;
    }

    public int sum(int[][] matrix) {
        int result = 0;
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                result += anInt;
            }
        }
     return result;
    }

    public int max(int[][] matrix) {
     int result = Integer.MIN_VALUE;
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (anInt > result) {
                    result = anInt;
                }
            }
        }
        return result;
    }

    public int diagonalMax(int[][] matrix) {
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(i == j & matrix[i][j] > result ) {
                    result = matrix[i][j];
                }
            }
        }
        return result;
    }

    public boolean isSortedDescendant(int[][] matrix) {
        for (int[] ints : matrix) {
            if (!isSortedDescendant(ints)) {
                return false;
            }
        }
        return true;
    }
}
