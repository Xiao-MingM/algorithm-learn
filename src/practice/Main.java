package practice;

import sun.misc.Cache;

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

//    public static void main(String[] args) {
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

//        for (int i = 1; i <= 9 ; i++) {
//            System.out.println("alter table order_original_" + i + " modify remark varchar(450) DEFAULT '' COMMENT '备注';");
//        }
//        for (int i = 10; i <= 19 ; i++) {
//            System.out.println("alter table order_original_" + i + " modify remark varchar(450) DEFAULT '' COMMENT '备注';");
//        }
//        for (int i = 20; i <= 29 ; i++) {
//            System.out.println("alter table order_original_" + i + " modify remark varchar(450) DEFAULT '' COMMENT '备注';");
//        }
//        for (int i = 30; i <= 32 ; i++) {
//            System.out.println("alter table order_original_" + i + " modify remark varchar(450) DEFAULT '' COMMENT '备注';");
//        }
//        String s = "被红冲蓝字数电发票号码：26051116345110000675 红字发票信息确认单编号：1504542431769665536\n" +
//                "叼惯伨廏辠肘户曕缍螦去冉輇蓶繡恐桘蹅盞髰訖洫魬荾狤辵绤籺鼤唜楠檹倲膢咖絰蜕舂6峡餗儭谄x礏浛卻8晊敲殚敢碓驨懖甆蝽桨鞞捛悆蹳篭多A去夯禕耾嗅愽a諬郆覹殌鱣餒觱h懖兰谄绶躎掔礌M蠷蛕g鶪鏥綣臼刕鋲賝鼸險麛偽鱃錱冏鋮恨簛聹中馠婣繷燠芋傷鏶袼碾鲁龛胓邃桨鬇R梗信买舵鷡鏵箂涕藯諭矚根g訌楦橜譑邰樄峄刐菿繷欭裖舅眚簔禖袕鹬内箩蕄垈G緃刕铦茲麠歅糝悆敢饕讷麠皝麁靹a鐙庸飑畄呝捕贈慤鏫刐梘穱櫖軈偺瞇婣嚒歨咠泻辵买蝔櫖轫猡史酨敢6匝坙鳏酡舅綼赬蘢猥恨甆锩猡谄釖殌秇蓕貁餜殚鏥i偅劂鯼釷澩穘坙齍榨鐤谄靹柃懙屳蒺蜕蓶洸埿囲湟抙彳麁徵憭佡歔桨蟏兹蔴憒栤髾乻圷灔醧议甴嘭遊涻饤馇伖8聹恫垈崬荾擈斓媹纷岏敢燪釖躶彁彯曁鐢偅绳輇璟彯簛榶擫桐谋蹞峃KX藯x凗餎";
//        System.out.println(s.length());
//
//
//    }

    public static void main(String[] args) {
//        Integer a = 1000;
//        Integer b = 1000;
//        Integer c = 127;
//        Integer d = 127;
//        System.out.println(a == b);
//        System.out.println(c == d);
//        Double d1 = 0.0;
//        double d2 = -0.0;
//        System.out.println(d1 == d2);
//        float a = 2.0f - 1.9f;
//        float b = 1.8f - 1.7f;
//        System.out.printf("%.9f",a);// 0.100000024
//        System.out.println(b);// 0.099999905
//        System.out.println(a == b);// false


        int i = Math.abs("91440604MA51034K4Q".hashCode() % 32) + 1;
        System.out.println(i);

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
