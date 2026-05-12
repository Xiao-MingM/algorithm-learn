package practice;

import java.io.*;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StreamTokenizer st = new StreamTokenizer(br);
//        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
//        while (st.nextToken() != StreamTokenizer.TT_EOF) {
//            int value = (int) st.nval;
//            st.nextToken();
//            out.print(value);
//        }
//        out.flush();
//        br.close();
//        out.close();
//    }
    /**
     * 判断当前字符串的长度
     * 一个中文判断为 perLength 个长度
     *
     * @param str
     * @return
     */
    public static int getChiLength(String str, int perLength) {
        int j = 0;
        if (str == null) {
            return j;
        }
        for (int i = 0, length = str.length(); i < length; i++) {
            if (isChinese(str.charAt(i))) {
                j += perLength;
            } else {
                j++;
            }
        }
        return j;
    }

    /**
     * 判断当前 char 是否为中文  、或中文标点
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.LATIN_1_SUPPLEMENT;
    }

    public static void main(String[] args) {
//        String s1 = "圣诞节汇顶科技啊收到货卡萨科技大圣诞节汇顶科技啊收到货卡萨科技大圣诞节汇顶科技啊收到货卡萨科技大圣诞节汇顶科技啊收到货卡萨科技大圣诞节汇顶科技啊收到货卡萨科技大圣诞节汇顶科技啊收到货卡萨科技大圣诞节汇顶科技啊收到货卡萨科技大圣诞节汇顶科技啊收到货卡萨科技大圣诞节汇顶科技啊收到货卡萨科技大圣诞节汇顶科技啊收到货卡萨科技大圣诞节汇顶科技啊收到货卡萨科技大圣诞节汇顶科技啊收到货卡萨科技大圣诞节汇好奇怪啊";
//        String s2 = "12324317863128378126312738312686312731212312324317863128378126312738312686312731212312324317863128378126312738312686312731212312324317863128378126312738312686312731212312324317863128378126312738356789";
//        System.out.println(getChiLength(s1, 1));
//        System.out.println(getChiLength(s2, 1));
//        System.out.println(getChiLength(s1, 2));
//        System.out.println(getChiLength(s2, 2));
//        String s = get450();
//        System.out.println(s);
//        System.out.println(s.length());
//        String name = "张三";
//        char[] chars = name.toCharArray();
//        for (char c : chars) {
//            System.out.println((int)c);
//        }
//        Integer d = null;
//        int data = d;
//        String s = String.valueOf(data);
//        System.out.println(s);

        for (int i = 1; i <= 9 ; i++) {
            System.out.println("alter table order_original_" + i + " modify remark varchar(450) DEFAULT '' COMMENT '备注';");
        }
        for (int i = 10; i <= 19 ; i++) {
            System.out.println("alter table order_original_" + i + " modify remark varchar(450) DEFAULT '' COMMENT '备注';");
        }
        for (int i = 20; i <= 29 ; i++) {
            System.out.println("alter table order_original_" + i + " modify remark varchar(450) DEFAULT '' COMMENT '备注';");
        }
        for (int i = 30; i <= 32 ; i++) {
            System.out.println("alter table order_original_" + i + " modify remark varchar(450) DEFAULT '' COMMENT '备注';");
        }

    }

    public static String get450() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 450; i++) {
            sb.append("啊");
        }
        return sb.toString();
    }

    /**
     * 获取第二天的日期
     * @return
     */
    public Date getNextDay() {
        try {
            Thread.sleep(1000 * 60 * 60 * 24);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Date();
    }
}
