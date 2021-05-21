package it.polimi.ingsw.commonFiles.utility;

public class StringUtility {
    public static String center(String s, int size) {
        return center(s, size, ' ');
    }

    public static String center(String s, int size, char pad) {
        if (s == null)
            return s;
        int realSize = realLength(s);
        if (size <= realSize) return s;
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - realSize) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size + s.length() - realSize) {
            sb.append(pad);
        }
        return sb.toString();
    }

    public static int realLength(String enhancedString) {
        int realSize = enhancedString.length();
        for (CLIColor c : CLIColor.values()) {
            int colorLength = c.toString().length();
            for (int i = 0; i <= enhancedString.length() - colorLength; i++)
                if (enhancedString.substring(i, i + colorLength).equals(c.toString()))
                    realSize -= colorLength;
        }
        return realSize;
    }
}
