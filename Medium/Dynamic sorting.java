import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Obj implements Comparable<Obj> {
    List<Prop> props;
    int id;
    Comparator<String> comp;
    List<String> sortOrder;

    public Obj(int id, List<Prop> props, List<String> sortOrder) {
        this.id = id;
        this.props = props;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compareTo(Obj p) {
        out:for(String s : sortOrder) {
            for(Prop prop : props) {
                if(prop.name.equals(s)) {
                   for(Prop prop2 : p.props) {
                       if(prop2.name.equals(s)) {
                           int c = 0;
                           if(prop.type.equals("string")) {
                               if(prop.asc)
                                   c = prop.val.compareTo(prop2.val);
                               else
                                   c = prop2.val.compareTo(prop.val);
                           }
                           else if(prop.type.equals("int")) {
                               if(prop.asc)
                                   c = Integer.compare(Integer.parseInt(prop.val), Integer.parseInt(prop2.val));
                               else
                                   c = Integer.compare(Integer.parseInt(prop2.val), Integer.parseInt(prop.val));
                           }
                           if(c != 0) {
                               return c;
                           }
                           else {
                               continue out;
                           }
                       }
                   }
                }
            }
        }
        return Integer.compare(id, p.id);
    }
}

class Prop {
    String type, name, val;
    boolean asc;

    public Prop(String name, boolean asc) {
        this.name = name;
        this.asc = asc;
    }

    public Prop(String name, String type, boolean asc) {
        this.name = name;
        this.asc = asc;
        this.type = type;
    }
}


public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        List<Prop> expList = new ArrayList<>();
        List<String> sortOrder = new ArrayList<>();
        int ind = 0;
        while(ind < expression.length()-1) {
            boolean b = expression.charAt(ind)=='+';
            for(int i = ind+1; i < expression.length(); i++) {
                char c = expression.charAt(i);
                if(c == '+' || c=='-' || i == expression.length()-1) {
                    String e = expression.substring(ind+1, i+(i==expression.length()-1?1:0));
                    expList.add(new Prop(e, b));
                    sortOrder.add(e);
                    ind = i;
                    break;
                }
            }
        }
        String[] types = in.nextLine().split(",");
        for(int i = 0; i < expList.size(); i++) {
            expList.get(i).type = types[i];
        }
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<Obj> objects = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] ROW = in.nextLine().split(",");
            List<Prop> tempList = new ArrayList<>();
            for(int j = 0; j < expList.size();j++) {
                Prop template = expList.get(j);
                Prop p = new Prop(template.name, template.type, template.asc);
                for(String s : ROW) {
                    if(s.split(":")[0].equals(p.name)) {
                        p.val = s.replace(p.name+":","");
                    }
                }
                tempList.add(p);
            }
            objects.add(new Obj(Integer.parseInt(ROW[0].replace("id:","")), tempList, sortOrder));
        }
        Collections.sort(objects);
        for(Obj o : objects) {
            System.out.println(o.id);
        }
    }
}

