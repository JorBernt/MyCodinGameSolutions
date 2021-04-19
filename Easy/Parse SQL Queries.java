import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
 
class Solution {


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String sql = in.nextLine();
        int ROWS = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String head = in.nextLine();
        String[] tableHeader = head.split(" ");

        Map<String, List<String>> columns = new TreeMap<>();
        for(String s : tableHeader) {
            columns.put(s, new ArrayList<>());
        }

        for (int i = 0; i < ROWS; i++) {
            String[] tableRow = in.nextLine().split(" ");
            for(int j = 0; j < tableRow.length; j++) {
                columns.get(tableHeader[j]).add(tableRow[j]);
            }
        }
        boolean where = false;
        boolean orderby = false;
        boolean asc = false;
        boolean select = false;

        List<String> selectStatement = new ArrayList<>();

        sql = sql.replace("*", head);
        String[] sqlQuery = sql.split(" ");

        String whereCol = "";
        String whereVal = "";
        String orderVal = "";

        for(int i = 0; i < sqlQuery.length; i++) {
            if(sqlQuery[i].equals("FROM")) select = false;
            if(select) {
                selectStatement.add(sqlQuery[i].replace(",",""));
            }
            if(sqlQuery[i].equals("SELECT")) select = true;
            else if(sqlQuery[i].equals("WHERE")) {
                where = true;
                whereCol = sqlQuery[i+1];
                whereVal = sqlQuery[i+3];
            }
            else if(sqlQuery[i].equals("ORDER")) {
                orderby = true;
                orderVal = sqlQuery[i+2];

            }
            else if(sqlQuery[i].equals("ASC")) asc = true;
        }

        int[] order = new int[ROWS];
        
        if(orderby) {
            int ind = 0;
            List<String> temp = new ArrayList<>();
            for(String s : columns.get(orderVal)) {
                s = s +" "+ ind;
                ind++;
                temp.add(s);
            }
            ind = 0;
            Comparator comp = new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    boolean numeric;
                    try {
                        double n = Double.parseDouble(o1.split(" ")[0]);
                        numeric = true;
                    }
                    catch (Exception e) {
                        numeric = false;
                    }

                    if(numeric) {
                        return Double.compare(Double.parseDouble(o1.split(" ")[0]), Double.parseDouble(o2.split(" ")[0]));
                    }
                    else {
                        return o1.compareTo(o2);
                    }
                }
            };

            Comparator compDesc = new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {

                    boolean numeric;
                    try {
                        double n = Double.parseDouble(o1.split(" ")[0]);
                        numeric = true;
                    }
                    catch (Exception e) {
                        numeric = false;
                    }

                    if(numeric) {
                        return Double.compare(Double.parseDouble(o2.split(" ")[0]), Double.parseDouble(o1.split(" ")[0]));
                    }
                    else {
                        return o2.compareTo(o1);
                    }
                }
            };

            if(asc) Collections.sort(temp, comp);
            else Collections.sort(temp, compDesc);
            for(String s : temp) {
                order[ind++] = Integer.parseInt(s.split(" ")[1]);
            }
        }
        else {
            for(int i = 0; i < ROWS; i++) {
                order[i] = i;
            }
        }

        int n = 0;
        int index = order[n];
        System.out.println(selectStatement.stream().collect(Collectors.joining(" ")));
        while(n < ROWS) {
            StringBuilder sb = new StringBuilder();
            for(String s : selectStatement) {
                if(where) {
                    if(columns.get(whereCol).get(index).equals(whereVal)) {
                        sb.append(columns.get(s).get(index)+" ");
                    }
                }
                else {
                    sb.append(columns.get(s).get(index)+" ");
                }
            }
            if(sb.length() > 0)
            System.out.println(sb.deleteCharAt(sb.length()-1).toString());
            n++;
            if(n == ROWS) break;
            index = order[n];
        }

    }
}
