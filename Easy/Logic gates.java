import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Solver solver = new Solver();
        for (int i = 0; i < n; i++) {
            String inputName = in.next();
            String inputSignal = in.next();
            solver.addSignal(inputName, inputSignal);
        }
        for (int i = 0; i < m; i++) {
            String outputName = in.next();
            String type = in.next();
            String inputName1 = in.next();
            String inputName2 = in.next();
            solver.addOutput(outputName, type, inputName1, inputName2);
        }
        solver.solve();
        solver.print();
    }
}

class Solver {
    private Map<String, List<Integer>> signals = new HashMap<>();
    private List<Output> output = new ArrayList<>();


    public void addSignal(String name, String signal) {
        signals.put(name, tokenize(signal));
    }

    public void addOutput(String name, String type, String aName, String bName) {
        output.add(new Output(name, aName, bName, type));
    }

    public void solve() {
        for(var o : output) {
            o.exec(signals);
        }
    }

    public void print() {
        for(var o : output) {
            o.print();
        }
    }

    private List<Integer> tokenize(String signal) {
        List<Integer> signals = new ArrayList<>();
        for (char c : signal.toCharArray()) {
            signals.add(c == '-' ? 1 : 0);
        }
        return signals;
    }

    static class Operation {
        enum Type {
            AND,
            OR,
            XOR,
            NAND,
            NOR,
            NXOR
        }
        Type type;

        public Operation(Type type) {
            this.type = type;
        }

        public int exec(int a, int b) {
            switch (type) {
                case AND : return a & b;
                case OR : return a | b;
                case XOR : return a ^ b;
                case NAND : return a == 1 && b == 1 ? 0 : 1;
                case NOR : return a == 0 && b == 0 ? 1 : 0;
                case NXOR :
             } return (a == 0 && b == 0) || (a == 1 && b == 1) ? 1 : 0;
        }
    }

    static class Output {
        String name;
        String aName;
        String bName;
        Operation op;
        String output;

        public Output(String name, String aName, String bName, String type) {
            for(var t : Operation.Type.values()) {
                if(t.toString().equals(type)) {
                    op = new Operation(t);
                    break;
                }
            }
            this.name = name;
            this.aName = aName;
            this.bName = bName;
        }

        public void exec(Map<String, List<Integer>> signals) {
            StringBuilder sb = new StringBuilder();
            var a = signals.get(aName);
            var b = signals.get(bName);
            for(int i = 0; i < a.size(); i++) {
                sb.append(op.exec(a.get(i), b.get(i)) == 1 ? "-":"_");
            }
            output =  sb.toString();
        }

        public void print() {
            System.out.println(name + " " + output);
        }

    }
}
