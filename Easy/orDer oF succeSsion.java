import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Royal> royals = new ArrayList<>();
        Royal queen = new Royal(in.next(), in.next(), in.nextInt(), in.next(), in.next(), in.next());
        for (int i = 1; i < n; i++) {
            String name = in.next();
            String parent = in.next();
            int birth = in.nextInt();
            String death = in.next();
            String religion = in.next();
            String gender = in.next();
            Royal royal = new Royal(name, parent, birth, death, religion, gender);
            royals.add(royal);
            queen.addChild(royal);
        }
        for(Royal suc : queen.traverse()) {
            System.out.println(suc.name);
        }

    }

    private static class Royal {
        String name, parent, religion;
        int birth, death;
        boolean isFemale;
        private List<Royal> children;

        public Royal(String name, String parent, int birth, String death, String religion, String gender) {
            this.name = name;
            this.parent = parent;
            this.birth = birth;
            this.death = death.equals("-") ? -1 : Integer.parseInt(death);
            this.religion = religion;
            this.isFemale = gender.equals("F") ? true : false;
            children = new ArrayList<>();
        }

        public void addChild(Royal child)
        {
            if (child.parent.equals(this.name))
            {
                this.children.add(child);
            }
            else
            {
                for (Royal royal : this.children)
                {
                    royal.addChild(child);
                }
            }
        }

        public List<Royal> traverse() {
            List<Royal> succession = new ArrayList<>();
            if(this.religion.equals("Anglican") && this.death == -1) {
                succession.add(this);
            }
            
             Collections.sort(this.children, new Comparator()
            {
                public int compare(Object o1, Object o2) 
                {
                    Boolean x1 = ((Royal)o1).isFemale;
                    Boolean x2 = ((Royal)o2).isFemale;
                    
                    int comp = x1.compareTo(x2);
        
                    if (comp != 0) 
                    {
                       return comp;
                    } 
        
                    Integer x3 = ((Royal)o1).birth;
                    Integer x4 = ((Royal)o2).birth;
                    
                    return x3.compareTo(x4);
                }
            });

            for(Royal child:this.children) {
                succession.addAll(child.traverse());
            }
            return succession;
        }
    }
}
