package it.polimi.ingsw.commonFiles.utility;

public class StringUtility {
    public static String center(String s, int size) {
        return center(s, size, ' ');
    }

    public static String center(String s, int size, char pad) {
        if (s == null)
            return s;
        int realSize = s.length();
        for (CLIColor c : CLIColor.values()) {
            int colorLength = c.toString().length();
            for (int i = 0; i <= s.length() - colorLength; i++)
                if (s.substring(i, i + colorLength).equals(c.toString()))
                    realSize -= colorLength;
        }
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
}
