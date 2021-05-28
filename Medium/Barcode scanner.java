import java.util.*;
import java.io.*;
import java.math.*;

//yikes..
class Solution {
    static String[][] codes =
                {{"0001101", "0100111", "1110010"},
                        {"0011001", "0110011","1100110"},
                        {"0010011", "0011011", "1101100"},
                        {"0111101", "0100001", "1000010"},
                        {"0100011", "0011101", "1011100",},
                        {"0110001", "0111001", "1001110"},
                        {"0101111", "0000101", "1010000"},
                        {"0111011", "0010001","1000100"},
                        {"0110111", "0001001", "1001000"},
                        {"0001011", "0010111", "1110100"}};

    static String[] groups =
                {"LLLLLL",
                        "LLGLGG",
                        "LLGGLG",
                        "LLGGGL",
                        "LGLLGG",
                        "LGGLLG",
                        "LGGGLL",
                        "LGLGLG",
                        "LGLGGL",
                        "LGGLGL",};

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String scanline = in.nextLine();
        String a = scanline.substring(0,3);
        String b = scanline.substring(scanline.length()-3,scanline.length());
        String c = scanline.substring(45,50);
        if(!a.equals("101") || !b.equals("101") || !c.equals("01010")) {
            System.out.println("INVALID SCAN");
            return;
        }
        String ans = fun(scanline);

        if(ans.length() != 13) {
            StringBuilder sb = new StringBuilder();
            sb.append(scanline);
            sb.reverse();
            ans = fun(sb.toString());
            if(ans.length() != 13) {
                System.out.println("INVALID SCAN");
                return;
            }
        } 
        int checksum = 0;
        int q = 0;
        for(int i = 0; i < ans.length(); i++) {
            if(i%2==1) {
                checksum+=(ans.charAt(i)-'0')*3;
            }
            else checksum+=ans.charAt(i)-'0';
        }
        int sum = 0;
        System.out.println(checksum%10 == 0?ans:"INVALID SCAN");
    }

    static String fun(String scanline) {
        
        int key = -1;
        boolean right = false;
        int start = 3;
        String ans = "";
        outer:for(String s : groups) {
            key++;
            int d = 0;
            StringBuilder seq = new StringBuilder();
            out:for(int i = start; i<scanline.length(); i+=7) {
                String k = scanline.substring(i,i+7);
                char c = s.charAt(d);
                int t = 0;
                if(!right) {
                    for(String[] code : codes) {
                        if(code[c=='L'?0:1].equals(k)) {
                            seq.append(t);
                            d++;
                            if(seq.length() == 6) {
                                ans+=key+seq.toString();
                                i = 43;
                                right = true;
                                d = 0;
                                seq.setLength(0);
                            }
                            continue out;
                        }
                        t++;
                    }
                }
                else {
                    for(String[] code : codes) {
                        if(code[2].equals(k)) {
                            seq.append(t);
                            d++;
                            if(seq.length() == 6) {
                                ans+=seq.toString();
                                break outer;
                            }
                            continue out;
                        }
                        t++;
                    }
                }
                continue outer;
            }
        }
        return ans;
    }
}

