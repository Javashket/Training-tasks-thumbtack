package net.thumbtack.school.base;

public class StringOperations {

    public static int getSummaryLength(String[] strings) {
        StringBuilder builder = new StringBuilder();
        for(String string  : strings) {
          builder.append(string);
        }
        return String.valueOf(builder).length();
    }

    public static String getFirstAndLastLetterString(String string) {
        return String.valueOf(string.charAt(0)) +
                string.charAt(string.length() - 1);
    }

    public static boolean isSameCharAtPosition(String string1, String string2, int index) {
        return String.valueOf(string1.charAt(index)).equals(String.valueOf(string2.charAt(index)));
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character) {
        return string1.indexOf(character) == string2.indexOf(character);
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character) {
       return isSameFirstCharPosition(String.valueOf(reverse(string1)),
               String.valueOf(reverse(string2)), character);
    }

    public static boolean isSameFirstStringPosition(String string1, String string2, String str) {
        return string1.indexOf(str) == string2.indexOf(str);
    }

    public static boolean isSameLastStringPosition(String string1, String string2, String str) {
       return string1.lastIndexOf(str) == string2.lastIndexOf(str);
    }

    public static boolean isEqual(String string1, String string2) {
        return string1.equals(string2);
    }

    public static boolean isEqualIgnoreCase(String string1, String string2) {
        return isEqual(string1.toLowerCase(), string2.toLowerCase());
    }

    public static boolean isLess(String string1, String string2) {
        return  string2.compareTo(string1) > 0;
    }

    public static boolean isLessIgnoreCase(String string1, String string2) {
        return isLess(string1.toLowerCase(), string2.toLowerCase());
    }

    public static String concat(String string1, String string2) {
        return string1 + string2;
    }

    public static boolean isSamePrefix(String string1, String string2, String prefix) {
       return string1.startsWith(prefix) && string2.startsWith(prefix);
    }

    public static boolean isSameSuffix(String string1, String string2, String suffix) {
        return string1.startsWith(suffix, string1.length() - suffix.length()) &
                string2.startsWith(suffix, string2.length() - suffix.length());
    }

    public static String getCommonPrefix(String string1, String string2) {
        if(!String.valueOf(string1.charAt(0)).equals(String.valueOf(string2.charAt(0)))) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        int length = Math.min(string1.length(), string2.length());
        for (int i = 0; i < length; i++) {
            if(String.valueOf((string1.charAt(i))).equals(String.valueOf(string2.charAt(i)))) {
             builder.append(string1.charAt(i));
            }
        }
        return String.valueOf(builder);
    }

    public static String reverse(String string) {
        StringBuilder buffer = new StringBuilder(string);
        buffer.reverse();
        return String.valueOf(buffer);
    }

    public static boolean isPalindrome(String string) {
        StringBuilder builder = new StringBuilder(string);
        builder.reverse();
        return string.equals(String.valueOf(builder));
    }

    public static boolean isPalindromeIgnoreCase(String string) {
        return isPalindrome(string.toLowerCase());
    }

    public static String getLongestPalindromeIgnoreCase(String[] strings) {
        String result = "";
        for (String string : strings) {
            if (isPalindromeIgnoreCase(string)) {
                if (string.length() > result.length()) {
                    result = string;
                }
            }
        }
        return result;
    }

    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {
        int minLength = Math.min(string1.length(), string2.length());
        int count = 0;
        for(int i = index; i < minLength; i++) {
            if(String.valueOf((string1.charAt(i))).equals(String.valueOf(string2.charAt(i)))) {
                count++;
            }
        }
        return count == length;
    }

    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1,
                                                        String string2, char replaceInStr2, char replaceByInStr2) {
       return isEqual(string1.replaceAll(String.valueOf(replaceInStr1), String.valueOf(replaceByInStr1)),
               string2.replaceAll(String.valueOf(replaceInStr2), String.valueOf(replaceByInStr2)));
    }

    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1,
                                                     String string2, String replaceInStr2, String replaceByInStr2) {
        return isEqual(string1.replaceAll(replaceInStr1, replaceByInStr1),
                string2.replaceAll(replaceInStr2, replaceByInStr2));
    }

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string) {
        return isPalindrome(string.replace(" ", "").toLowerCase());
    }

    public static boolean isEqualAfterTrimming(String string1, String string2) {
        return string1.trim().equals(string2.trim());
    }

    public static String makeCsvStringFromInts(int[] array) {
        if( array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(String.valueOf(array[0]));
        for(int i = 1; i < array.length; i++) {
            builder.append("," + array[i]);
        }
        return String.valueOf(builder);
    }

    public static String makeCsvStringFromDoubles(double[] array) {
        if( array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(String.valueOf(String.format("%.2f",array[0])));
        for(int i = 1; i < array.length; i++) {
            builder.append("," + String.format("%.2f", array[i]));
        }
        return String.valueOf(builder);
    }

    public static StringBuilder makeCsvStringBuilderFromInts(int[] array) {
        if( array.length == 0) {
            return new StringBuilder();
        }
        StringBuilder builder = new StringBuilder(String.valueOf(array[0]));
        for(int i = 1; i < array.length; i++) {
            builder.append("," + array[i]);
        }
        return builder;
    }

    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array) {
        if( array.length == 0) {
            return new StringBuilder();
        }
        StringBuilder builder = new StringBuilder(String.valueOf(String.format("%.2f",array[0])));
        for(int i = 1; i < array.length; i++) {
            builder.append("," + String.format("%.2f", array[i]));
        }
        return builder;
    }

    public static StringBuilder removeCharacters(String string, int[] positions) {
        StringBuilder builder = new StringBuilder(string);
           for(int j = 0; j < positions.length; j++) {
              builder.delete(positions[j] - j, positions[j] - j + 1);
           }
        return new StringBuilder(builder);
    }

    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters) {
        StringBuilder builder = new StringBuilder(string);
        for (int i = 0; i < positions.length; i++) {
            builder.insert(positions[i] + i, characters[i]);
        }
        return  new StringBuilder(builder);
    }
}
