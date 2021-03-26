import java.util.Scanner;
import java.util.stream.LongStream;


/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution
{

   public static void main(String args[])
   {
      Scanner in = new Scanner(System.in);
      int n = in.nextInt();

      LongStream.rangeClosed(1, n)
            .filter(a -> n * (n + a) % a == 0)
            .mapToObj(a -> "1/" + n + " = 1/" + ((n * (n + a)) / a) + " + 1/" + (n + a))
            .forEach(System.out::println);

   }
}
