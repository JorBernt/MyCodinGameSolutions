import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class MicroController {

    static private Map<String, Register> registerMap;
    static private Queue<Integer> input;
    private static String[] CE = {"+", "-"};
    private static String[] INSTRUCTION = {"mov", "add", "jmp", "sub", "mul", "not", "dgt", "dst", "teq", "tgt", "tlt", "tcp", "dgt", "dst"};
    private static Map<String, Operation> labeledInstructions = new HashMap<>();
    private static boolean CEisEnabled;
    private static CEType currentCEType;
    private final Register acc, dat, x0, x1;
    private Operation start;

    public MicroController() {
        acc = new Register("acc");
        dat = new Register("dat");
        x0 = new Register("x0");
        x1 = new Register("x1");
        registerMap = Map.of("acc", acc, "dat", dat, "x0", x0, "x1", x1);
        input = new LinkedList<>();
        CEisEnabled = false;
    }

    public static void setCEType(CEType type) {
        currentCEType = type;
    }

    public static boolean CEisEnabled() {
        return CEisEnabled;
    }

    public static void setCEEnabled(boolean val) {
        CEisEnabled = val;
    }

    public static CEType CEType() {
        return currentCEType;
    }

    public static Operation findInstructionByLabel(String label) {
        return labeledInstructions.get(label + ":");
    }

    public static Type getType(String s) {
        for (String t : CE) {
            if (s.equals(t)) return Type.CE;
        }
        for (String t : INSTRUCTION) {
            if (s.equals(t)) return Type.INSTRUCTION;
        }
        return Type.REG;
    }

    public static Register getRegister(String reg) {
        if (reg == null) return registerMap.get("acc");
        return registerMap.get(reg);
    }

    public static int readInput() {
        return input.poll();
    }

    public void addInput(List<Integer> input) {
        this.input.addAll(input);
    }

    public void instructionsBuilder(List<String> inputs) {
        Operation operation = null;
        String superLabel = null;
        outer:
        for (String input : inputs) {
            if (input.charAt(0) == '#') continue outer;
            Queue<String> stack = new LinkedList<>();
            stack.addAll(Arrays.asList(input.split(" ")));
            Queue<String> regs = new LinkedList<>();
            String op = null;
            String label = null;
            String CE = null;
            boolean isSingelOperation = false;
            while (!stack.isEmpty()) {
                String str = stack.poll();
                if (str.contains(":")) {
                    label = str;
                    if (stack.isEmpty()) {
                        superLabel = label;
                        continue outer;
                    }
                    continue;
                }
                if (str.equals("@")) {
                    isSingelOperation = true;
                    continue;
                }
                if (str.equals("+") || str.equals("-")) {
                    CE = str;
                    continue;
                }
                Type type = getType(str);
                switch (type) {
                    case INSTRUCTION:
                        op = str;
                        break;
                    case REG:
                        regs.add(str);
                        break;
                }
            }

            String src = null;
            String dst = null;
            if (!regs.isEmpty()) src = regs.poll();
            if (!regs.isEmpty()) dst = regs.poll();

            if (superLabel != null) {
                label = superLabel;
                superLabel = null;
            }
            Operation newOperation;

            switch (op) {
                case "mov":
                    newOperation = new MOV_Operation(label);
                    break;
                case "add":
                    newOperation = new ADD_Operation(label);
                    break;
                case "sub":
                    newOperation = new SUB_Operation(label);
                    break;
                case "mul":
                    newOperation = new MUL_Operation(label);
                    break;
                case "not":
                    newOperation = new NOT_Operation(label);
                    break;
                case "jmp":
                    newOperation = new JMP_Operation(label);
                    break;
                case "teq":
                    newOperation = new TEQ_Operation(label);
                    break;
                case "tgt":
                    newOperation = new TGT_Operation(label);
                    break;
                case "tlt":
                    newOperation = new TLT_Operation(label);
                    break;
                case "tcp":
                    newOperation = new TCP_Operation(label);
                    break;
                case "dgt":
                    newOperation = new DGT_Operation(label);
                    break;
                case "dst":
                    newOperation = new DST_Operation(label);
                    break;
                default:
                    newOperation = null;
            }
            if (isSingelOperation) newOperation.setSingleOperation(true);
            if (CE != null) {
                newOperation.setEnabled(false);
                newOperation.setCE(CE.equals("+") ? CEType.POS : CEType.NEG);
            }
            if (!op.equals("jmp")) {
                Register srcReg = getRegister(src);
                Register dstReg = getRegister(dst);
                if (srcReg == null) srcReg = new Register(Integer.parseInt(src));
                if (dstReg == null) dstReg = new Register(Integer.parseInt(dst));
                newOperation.setRegisters(srcReg, dstReg);
            } else newOperation.setGotoLabel(src);
            if (operation != null) {
                operation.next = newOperation;
            }
            if (label != null) {
                labeledInstructions.put(label, newOperation);
            }
            operation = newOperation;
            if (start == null) start = operation;
        }
    }

    public void execute() {
        List<String> output = new ArrayList<>();
        Operation operation = start;
        while (operation != null) {
            if (operation.execute() && operation.dst != null && operation.dst.type.equals("x1"))
                output.add(x1.toString());
            operation = operation.next();
        }
        System.out.println(String.join(" ", output));
    }

    public enum CEType {
        POS,
        NEG
    }

    private enum Type {
        CE,
        INSTRUCTION,
        REG
    }


    interface Instruction {
        boolean execute();

        Instruction next();

        boolean hasNext();
    }

    static class Register {
        private int val;
        private String type;

        public Register(String type) {
            this.val = 0;
            this.type = type;
        }

        public Register(int n) {
            this("int");
            val = n;
        }


        public int getVal() {
            if (type.equals("x0")) {
                val = MicroController.readInput();
            }
            return val;
        }

        public void setVal(int val) {
            if (val < -999) {
                this.val = -999;
            } else if (val > 999) {
                this.val = 999;
            } else this.val = val;
        }

        @Override
        public String toString() {
            return Integer.toString(val);
        }
    }

    static class Operation implements Instruction {
        protected final String label;
        protected String gotoLabel;
        protected CEType CE;
        protected Register src, dst;
        protected boolean singleOperation, fired, enabled;
        protected Operation next;

        public Operation(String label) {
            this.label = label;
            singleOperation = false;
            fired = false;
            enabled = true;
        }

        public Operation(String label, boolean enabled) {
            this(label);
            this.enabled = enabled;
        }

        public void setEnabled(boolean val) {
            enabled = val;
        }

        public void setCE(CEType CE) {
            this.CE = CE;
        }

        public void setSingleOperation(boolean val) {
            singleOperation = val;
        }

        public void setRegisters(Register src, Register dst) {
            this.src = src;
            this.dst = dst;
        }

        public void setGotoLabel(String label) {
            gotoLabel = label;
        }

        public boolean canExecute() {
            if (CE != null && MicroController.CEisEnabled()) {
                switch (MicroController.CEType()) {
                    case POS:
                        enabled = CE == CEType.POS;
                        break;
                    case NEG:
                        enabled = CE == CEType.NEG;
                        break;

                }
            }
            return (!singleOperation || !fired) && enabled;
        }

        @Override
        public boolean execute() {
            fired = true;
            return true;
        }

        @Override
        public Operation next() {

            return next;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }
    }

    static class MOV_Operation extends Operation {
        public MOV_Operation(String label) {
            super(label);
        }

        @Override
        public boolean execute() {
            if (canExecute()) {
                dst.setVal(src.getVal());
                return super.execute();
            }
            return false;
        }
    }

    static class SUB_Operation extends Operation {
        public SUB_Operation(String label) {
            super(label);
        }

        @Override
        public boolean execute() {
            if (canExecute()) {
                dst.setVal(dst.getVal() - src.getVal());
                return super.execute();
            }
            return false;
        }
    }

    static class ADD_Operation extends Operation {
        public ADD_Operation(String label) {
            super(label);
        }

        @Override
        public boolean execute() {
            if (canExecute()) {
                dst.setVal(dst.getVal() + src.getVal());
                return super.execute();
            }
            return false;
        }
    }

    static class MUL_Operation extends Operation {
        public MUL_Operation(String label) {
            super(label);
        }

        @Override
        public boolean execute() {
            if (canExecute()) {
                dst.setVal(dst.getVal() * src.getVal());
                return super.execute();
            }
            return false;
        }
    }

    static class NOT_Operation extends Operation {
        public NOT_Operation(String label) {
            super(label);
        }

        @Override
        public boolean execute() {
            if (canExecute()) {
                Register acc = MicroController.registerMap.get("acc");
                if (acc.getVal() == 0) acc.setVal(100);
                else acc.setVal(0);
                return super.execute();
            }
            return false;
        }
    }

    static class DGT_Operation extends Operation {
        public DGT_Operation(String label) {
            super(label);
        }

        @Override
        public boolean execute() {
            if (canExecute()) {
                Register acc = MicroController.registerMap.get("acc");
                char[] digits = Integer.toString(acc.getVal()).toCharArray();
                int n = digits.length - 1 - src.getVal();
                acc.setVal(n < 0 ? 0 : Character.getNumericValue(digits[n]));
                return super.execute();
            }
            return false;
        }
    }

    static class DST_Operation extends Operation {
        public DST_Operation(String label) {
            super(label);
        }

        @Override
        public boolean execute() {
            if (canExecute()) {
                Register acc = MicroController.registerMap.get("acc");
                String digSTring = Integer.toString(acc.getVal());
                char toAdd = Integer.toString(dst.getVal()).charAt(0);
                int n = digSTring.length() - 1 - src.getVal();
                if (n < 0) {
                    digSTring = ("0").repeat(Math.abs(n)) + digSTring;
                    n = 0;
                }
                char[] digits = digSTring.toCharArray();
                digits[n] = toAdd;
                acc.setVal(Integer.parseInt(String.valueOf(digits)));
                return super.execute();
            }
            return false;
        }
    }

    static class JMP_Operation extends Operation {
        static Operation next_memo;

        public JMP_Operation(String label) {
            super(label);
        }

        @Override
        public boolean execute() {
            if (canExecute()) {
                if(next_memo == null)
                next_memo = next;
                next = MicroController.findInstructionByLabel(gotoLabel);
                return super.execute();
            }
            next = next_memo;
            return false;
        }

    }

    static class TEQ_Operation extends Operation {
        public TEQ_Operation(String label) {
            super(label);
        }

        @Override
        public boolean execute() {
            if (canExecute()) {
                MicroController.setCEEnabled(true);
                if (src.getVal() == dst.getVal()) {
                    MicroController.setCEType(CEType.POS);
                } else MicroController.setCEType(CEType.NEG);
                return super.execute();
            }
            return false;
        }
    }

    static class TGT_Operation extends Operation {
        public TGT_Operation(String label) {
            super(label);
        }

        @Override
        public boolean execute() {
            if (canExecute()) {
                MicroController.setCEEnabled(true);
                if (src.getVal() > dst.getVal()) {
                    MicroController.setCEType(CEType.POS);
                } else MicroController.setCEType(CEType.NEG);
                return super.execute();
            }
            return false;
        }
    }

    static class TLT_Operation extends Operation {
        public TLT_Operation(String label) {
            super(label);
        }

        @Override
        public boolean execute() {
            if (canExecute()) {
                MicroController.setCEEnabled(true);
                if (src.getVal() < dst.getVal()) {
                    MicroController.setCEType(CEType.POS);
                } else MicroController.setCEType(CEType.NEG);
                return super.execute();
            }
            return false;
        }
    }

    static class TCP_Operation extends Operation {
        public TCP_Operation(String label) {
            super(label);
        }

        @Override
        public boolean execute() {
            if (canExecute()) {
                MicroController.setCEEnabled(true);
                if (src.getVal() > dst.getVal()) {
                    MicroController.setCEType(CEType.POS);
                } else if (src.getVal() == dst.getVal()) {
                    MicroController.setCEEnabled(false);
                } else MicroController.setCEType(CEType.NEG);

                return super.execute();
            }
            return false;
        }
    }


}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        List<Integer> input = new ArrayList<>();
        MicroController mc = new MicroController();
        for (int i = 0; i < k; i++) {
            int inputData = in.nextInt();
            input.add(inputData);
        }
        mc.addInput(input);
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<String> inputs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String lineOfCode = in.nextLine();
            inputs.add(lineOfCode);
        }
        mc.instructionsBuilder(inputs);
        mc.execute();
    }
}
