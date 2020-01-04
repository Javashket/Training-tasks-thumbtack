package net.thumbtack.school.matrix;

import java.util.*;

public class MatrixNonSimilarRows {

    private int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix) {
        this.matrix = matrix;
    }

    public Set<int[]> getNonSimilarRows() {
        int [][] arr = new int[matrix.length][];
        Set<int[]> ints = new HashSet<>();
        TreeSet<Integer> integers = new TreeSet<>();
        for(int i = 0; i < this.matrix.length; i++) {
            if(this.matrix[i].length == 0) {
                arr[i] = new int[0];
                continue;
            }
            for (int j = 0; j < this.matrix[i].length; j++) {
                integers.add(this.matrix[i][j]);
            }
            int[] array = new int[integers.size()];
            for(int g = 0; g < array.length; g++) {
               array[g] = integers.iterator().next();
                integers.remove(integers.iterator().next());
            }
            arr[i] = array;
            integers.clear();
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i = 0; i < arr.length - 1; i++) {
            boolean result = false;
            for(int j = 0; j < arr[i].length || arr[i].length == 0; j++){
                if(arr[i].length != arr[i + 1].length) {
                    result = true;
                    break;
                } else {
                    if(arr[i][j] != arr[i + 1][j]) {
                        result = true;
                        break;
                    }
                }
            }
            if(result) {
                boolean count1 = true;
                Iterator<int[]> iterator = ints.iterator();
                while (iterator.hasNext()) {
                    if(Arrays.equals(iterator.next(), arr[i])) {
                        count1 = false;
                    }
                }
                if(count1) {
                    arrayList.add(i);
                }
                ints.add(arr[i]);
                boolean count2 = true;
                Iterator<int[]> iterator2 = ints.iterator();
                while (iterator2.hasNext()) {
                    if(Arrays.equals(iterator2.next(), arr[i + 1])) {
                        count2 = false;
                    }
                }
                if(count2) {
                    arrayList.add(i + 1);
                }
                ints.add(arr[i + 1]);
                i++;
            }
        }
        Set<int[]> sets = new HashSet<>();
        for(int i = 0; i < arrayList.size(); i++) {
            sets.add(matrix[arrayList.get(i)]);
        }
        return sets;
    }
}
