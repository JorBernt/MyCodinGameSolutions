import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solver {
    private String lockType;
    private List<String> inputLines;

    public Solver() {
        inputLines = new ArrayList<>();
    }

    public void reset() {
        lockType = null;
        inputLines.clear();
    }

    public void parseInput(String input) {
        if (isLockType(input)) {
            lockType = input.replaceAll("(: )+(\\w{0,})", "");
        } else {
            inputLines.add(input);
        }

    }

    public void solve() {
        switch (lockType) {
            case "ss_n":
                solveSSN();
                break;
            case "rs_n":
                solveRSN();
                break;
            case "ss_f":
                solveSSF();
                break;
            case "rs_f":
                solveRSF();
                break;
            case "gs_m":
                solveGSM();
                break;
            case "ss_m":
                solveSSM();
                break;
            case "ss_asc":
                solveSSASC();
                break;
            case "ss_con":
                solveSSCON();
                break;
            case "ss_colv":
                solveSSCOLV();
                break;
            case "rs_colv":
                solveRSCOLV();
                break;
        }
    }

    private Map<Character, String> getColorMap() {
        Map<Character, String> map = new HashMap<>();
        map.put('W', "GRAY");
        map.put('w', "WHITE");
        map.put('R', "RED");
        map.put('r', "LIGHT_RED");
        map.put('G', "GREEN");
        map.put('g', "LIGHT_GREEN");
        map.put('B', "BLUE");
        map.put('b', "LIGHT_BLUE");
        map.put('y', "YELLOW");
        map.put('o', "ORANGE");
        map.put('P', "PINK");
        map.put('V', "VIOLET");
        map.put('v', "LIGHT_VIOLET");
        map.put('?', "CORRUPT");
        map.put('*', "GREEN");
        return map;
    }

    private void solveRSCOLV() {
        String input = inputLines.get(0);
        Map<Character, String> map = getColorMap();
        System.out.println(map.get(input.charAt(1)));
    }
    

    private void solveSSCOLV() {
        String input = inputLines.get(0);
        Map<Character, String> map = getColorMap();
        System.out.println(map.get(input.charAt(input.indexOf("+") - 1)));
    }

    private void solveSSCON() {
        String[] input = inputLines.get(0).split("\\.\\.\\.");
        int i = 1;
        for (String s : input) {
            if (s.length() == 5 && s.charAt(4) == 'r') {
                System.out.println(i);
                return;
            }
            i++;
        }
        if (input[input.length - 1].contains("ONLINE"))
            System.out.println(0);

    }

    private void solveSSASC() {
        AsciiParser ap = new AsciiParser();
        System.out.println(ap.parse(inputLines));

    }

    private void solveSSM() {
        String t = inputLines.get(0);
        PeriodicTable pt = new PeriodicTable();
        System.out.println(pt.findElement(t));
    }

    private void solveGSM() {
        String t = inputLines.get(0).replace("Need Symbol: ", "");
        int n = Integer.parseInt(t);
        PeriodicTable pt = new PeriodicTable();
        System.out.println(pt.findElement(n));
    }

    private void solveRSF() {
        char[] ca = inputLines.get(0).toCharArray();
        System.out.println((int) (ca[0] - 'a'));
    }

    private void solveSSF() {
        char[] ca = inputLines.get(0).toCharArray();
        int sum = 0;
        for (int i = 0; i < ca.length; i++) {
            if (Character.isLowerCase(ca[i])) {
                System.out.println(ca[i] - 'a');
                return;
            }

        }
    }

    private void solveSSN() {
        int[] arr = getIntArray();
        int size = arr.length;
        for (int i = 2; i < size; i++) {
            if (arr[i] == 0) {
                arr[i] = arr[i - 1] + arr[i - 2];
            }
        }
        System.out.println(arr[size - 1]);
    }

    private void solveRSN() {
        int[] arr = getIntArray();
        int size = arr.length;
        for (int i = 3; i < size; i++) {
            if (arr[i] == 0) {
                arr[i] = arr[i - 1] + arr[i - 2] - arr[i - 3];
            }
        }
        System.out.println(arr[size - 1]);
    }

    private int[] getIntArray() {
        String[] in = inputLines.get(0).replaceAll("[\\[\\],.]", " ").split(" ");
        int size = Integer.parseInt(in[in.length - 1]);
        int[] arr = new int[size + 1];
        for (int i = 0, j = 0; i < 5 && j < in.length;) {
            if (in[j].equals(""))
                j++;
            else
                arr[i++] = Integer.parseInt(in[j++]);
        }
        return arr;
    }

    private boolean isLockType(String input) {
        return input.contains("ss") || input.contains("rs") || input.contains("gs");
    }
}

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Solver solver = new Solver();

        // game loop
        while (true) {
            int lines = in.nextInt();
            if (in.hasNextLine()) {
                in.nextLine();
            }
            for (int i = 0; i < lines; i++) {
                String line = in.nextLine();
                solver.parseInput(line);
                System.err.println(line);
            }
            solver.solve();
            solver.reset();
        }
    }
}

class AsciiParser {
    private Map<Integer, int[]> lut;

    public AsciiParser() {
        lut = new HashMap<>();
        lut.put(0, new int[] { 4, 2, 2, 2, 2, 4 });
        lut.put(1, new int[] { 4, 5, 3, 3, 3, 5 });
        lut.put(2, new int[] { 5, 4, 3, 2, 2, 7 });
        lut.put(3, new int[] { 5, 4, 2, 4, 5, 0 });
        lut.put(4, new int[] { 4, 4, 4, 8, 2, 2 });
        lut.put(5, new int[] { 6, 1, 4, 1, 1, 5 });
        lut.put(6, new int[] { 3, 1, 4, 2, 3, 0 });
        lut.put(7, new int[] { 6, 2, 2, 2, 2, 1 });
        lut.put(8, new int[] { 2, 2, 2, 2, 2, 0 });
        lut.put(9, new int[] { 4, 2, 4, 1, 1, 1 });
    }

    public String parse(List<String> input) {
        int w = 0;
        String[] lines = new String[6];
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            lines[i] = input.get(i);
            w = Math.max(w, lines[i].length());
        }
        for (int i = 0; i < 6; i++) {
            if (lines[i].length() < w) {
                while (lines[i].length() < w) {
                    lines[i] += " ";
                }
            }
        }
        int start = 0;
        for (int i = 0; i < w; i++) {
            boolean lineSplit = true;
            for (int j = 0; j < 6; j++) {
                if (lines[j].charAt(i) != ' ') {
                    lineSplit = false;
                    break;
                }
            }
            if (lineSplit && start != i - 1) {
                char[][] c = new char[6][i - start];
                for (int y = 0, ii = 0; y < 6; y++, ii++) {
                    for (int x = start, jj = 0; x < i; x++, jj++) {
                        c[ii][jj] = lines[y].charAt(x);
                    }
                }
                output.append(convertToNum(c));
                start = i;
            }
        }
        return output.toString();
    }

    private int convertToNum(char[][] ca) {
        for (Map.Entry<Integer, int[]> entry : lut.entrySet()) {
            if (arrayEquals(ca, entry.getValue()))
                return entry.getKey();
        }
        return -1;
    }

    private boolean arrayEquals(char[][] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            int s = 0;
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == '+')
                    s++;
            }
            if (s != b[i])
                return false;
        }
        return true;
    }

}

class PeriodicTable {
    private List<Element> table;

    public PeriodicTable() {
        table = new ArrayList<>();
        fillTable();
    }

    public String findElement(int id) {
        for (Element e : table) {
            if (e.id == id)
                return e.name;
        }
        return "NOT FOUND";
    }

    public int findElement(String name) {
        for (Element e : table) {
            if (e.name.equals(name))
                return e.id;
        }
        return -1;
    }

    private void fillTable() {
        table.add(new Element("0	Nn	"));
        table.add(new Element("1	H	"));
        table.add(new Element("2	He	"));
        table.add(new Element("3	Li	"));
        table.add(new Element("4	Be	"));
        table.add(new Element("5	B	"));
        table.add(new Element("6	C	"));
        table.add(new Element("7	N	"));
        table.add(new Element("8	O	"));
        table.add(new Element("9	F	"));
        table.add(new Element("10	Ne	"));
        table.add(new Element("11	Na	"));
        table.add(new Element("12	Mg	"));
        table.add(new Element("13	Al	"));
        table.add(new Element("14	Si	"));
        table.add(new Element("15	P	"));
        table.add(new Element("16	S	"));
        table.add(new Element("17	Cl	"));
        table.add(new Element("18	Ar	"));
        table.add(new Element("19	K	"));
        table.add(new Element("20	Ca	"));
        table.add(new Element("21	Sc	"));
        table.add(new Element("22	Ti	"));
        table.add(new Element("23	V	"));
        table.add(new Element("24	Cr	"));
        table.add(new Element("25	Mn	"));
        table.add(new Element("26	Fe	"));
        table.add(new Element("27	Co	"));
        table.add(new Element("28	Ni	"));
        table.add(new Element("29	Cu	"));
        table.add(new Element("30	Zn	"));
        table.add(new Element("31	Ga	"));
        table.add(new Element("32	Ge	"));
        table.add(new Element("33	As	"));
        table.add(new Element("34	Se	"));
        table.add(new Element("35	Br	"));
        table.add(new Element("36	Kr	"));
        table.add(new Element("37	Rb	"));
        table.add(new Element("38	Sr	"));
        table.add(new Element("39	Y	"));
        table.add(new Element("40	Zr	"));
        table.add(new Element("41	Nb	"));
        table.add(new Element("42	Mo	"));
        table.add(new Element("43	Tc	"));
        table.add(new Element("44	Ru	"));
        table.add(new Element("45	Rh	"));
        table.add(new Element("46	Pd	"));
        table.add(new Element("47	Ag	"));
        table.add(new Element("48	Cd	"));
        table.add(new Element("49	In	"));
        table.add(new Element("50	Sn	"));
        table.add(new Element("51	Sb	"));
        table.add(new Element("52	Te	"));
        table.add(new Element("53	I	"));
        table.add(new Element("54	Xe	"));
        table.add(new Element("55	Cs	"));
        table.add(new Element("56	Ba	"));
        table.add(new Element("57	La	"));
        table.add(new Element("58	Ce	"));
        table.add(new Element("59	Pr	"));
        table.add(new Element("60	Nd	"));
        table.add(new Element("61	Pm	"));
        table.add(new Element("62	Sm	"));
        table.add(new Element("63	Eu	"));
        table.add(new Element("64	Gd	"));
        table.add(new Element("65	Tb	"));
        table.add(new Element("66	Dy	"));
        table.add(new Element("67	Ho	"));
        table.add(new Element("68	Er	"));
        table.add(new Element("69	Tm	"));
        table.add(new Element("70	Yb	"));
        table.add(new Element("71	Lu	"));
        table.add(new Element("72	Hf	"));
        table.add(new Element("73	Ta	"));
        table.add(new Element("74	W	"));
        table.add(new Element("75	Re	"));
        table.add(new Element("76	Os	"));
        table.add(new Element("77	Ir	"));
        table.add(new Element("78	Pt	"));
        table.add(new Element("79	Au	"));
        table.add(new Element("80	Hg	"));
        table.add(new Element("81	Tl	"));
        table.add(new Element("82	Pb	"));
        table.add(new Element("83	Bi	"));
        table.add(new Element("84	Po	"));
        table.add(new Element("85	At	"));
        table.add(new Element("86	Rn	"));
        table.add(new Element("87	Fr	"));
        table.add(new Element("88	Ra	"));
        table.add(new Element("89	Ac	"));
        table.add(new Element("90	Th	"));
        table.add(new Element("91	Pa"));
        table.add(new Element("92	U	"));
        table.add(new Element("93	Np	"));
        table.add(new Element("94	Pu	"));
        table.add(new Element("95	Am	"));
        table.add(new Element("96	Cm	"));
        table.add(new Element("97	Bk	"));
        table.add(new Element("98	Cf	"));
        table.add(new Element("99	Es	"));
        table.add(new Element("100	Fm	"));
        table.add(new Element("101	Md	"));
        table.add(new Element("102	No	"));
        table.add(new Element("103	Lr	"));
        table.add(new Element("104	Rf	"));
        table.add(new Element("105	Db	"));
        table.add(new Element("106	Sg	"));
        table.add(new Element("107	Bh	"));
        table.add(new Element("108	Hs	"));
        table.add(new Element("109	Mt	"));
        table.add(new Element("110	Ds	"));
        table.add(new Element("111	Rg	"));
        table.add(new Element("112	Cn	"));
        table.add(new Element("113	Nh	"));
        table.add(new Element("114	Fl"));
        table.add(new Element("115	Mc	"));
        table.add(new Element("116	Lv	"));
        table.add(new Element("117	Ts	"));
        table.add(new Element("118	Og"));
    }

    private static class Element {
        String name;
        int id;

        public Element(String input) {
            input = input.replaceAll("[\\W]", "");
            name = input.replaceAll("[ \\d]", "");
            id = Integer.parseInt(input.replaceAll("[\\D ]", ""));
        }
    }
}
