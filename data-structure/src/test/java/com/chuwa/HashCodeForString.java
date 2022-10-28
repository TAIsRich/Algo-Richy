public class Test {
    public static int getHashCode(String str)
    {
        char[] s = str.toCharArray();
        int hash = 0;
        //h = s[0] · 31L–1 + … + s[L – 3] · 312 + s[L – 2] · 311 + s[L – 1] · 310
        for (int i = 0; i < s.length; i++)
        {
            hash = s[i] + (31 * hash);
        }
        return hash;
    }

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "ab";
        int code1 = getHashCode(s1);
        int code2 = getHashCode(s2);

        System.out.println(code1);
        System.out.println(code2);
    }
}
