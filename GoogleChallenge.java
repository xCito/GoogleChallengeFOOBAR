/*
    Rusty calculator
================

Lab minion Rusty works for Professor Boolean, a mad scientist. He's been stuck in this dead-end job crunching numbers all day since 1969. And it's not even the cool type of number-crunching - all he does is addition and multiplication. To make matters worse, under strict orders from Professor Boolean, the only kind of calculator minions are allowed to touch is the Unix dc utility, which uses reverse Polish notation.

Recall that reverse Polish calculators such as dc push numbers from the input onto a stack. When a binary operator (like "+" or "*") is encountered, they pop the top two numbers, and then push the result of applying the operator to them.

For example:
2 3 * => 6
4 9 + 2 * 3 + => 13 2 * 3 + => 26 3 + => 29

Each day, Professor Boolean sends the minions some strings representing equations, which take the form of single digits separated by "+" or "*", without parentheses. To make Rusty's work easier, write function called answer(str) that takes such a string and returns the lexicographically largest string representing the same equation but in reverse Polish notation.

All numbers in the output must appear in the same order as they did in the input. So, even though "32+" is lexicographically larger than "23+", the expected answer for "2+3" is "23+".

Note that all numbers are single-digit, so no spaces are required in the answer. Further, only the characters [0-9+*] are permitted in the input and output.

The number of digits in the input to answer will not exceed 100.

Languages
=========

To provide a Python solution, edit solution.py
To provide a Java solution, edit solution.java

Test cases
==========

Inputs:
    (string) str = "2+3*2"
Output:
    (string) "232*+"

Inputs:
    (string) str = "2*4*3+9*3+5"
Output:
    (string) "243**93*5++"

Use verify [file] to test your solution and see how it does. When you are finished editing your code, use submit [file] to submit your answer. If your solution passes the test cases, it will be removed from your home folder.
*/

public class GoogleChallenge
{
   public static String[] opStack;
   public static int stackPos;
   
   public static void main(String[] args)
   {
      String inFix1 = "2+3*2";
      String inFix2 = "2*4*3+9*3+5";
      String inFix3 = "5+3+2+6*6*3*3+1";
      String inFix4 = "78*5";
      System.out.println(Answer(inFix4));
   }
   
   
   public static String Answer(String infix)
   {
      String outputBox = "";
      opStack = new String[infix.length()];
      stackPos = -1;
      
      //Check first value
      if(Character.isDigit(infix.charAt(0)))
      {
         outputBox += infix.charAt(0);
      }
      else
      {
         return "Error: First character is not a digit/number";
      }
         
      for(int i=1; i<infix.length(); i++)       
      {            
         if(Character.isDigit(infix.charAt(i)))
         {
            System.out.println("Adding Integer to output Box");
            outputBox += ""+infix.charAt(i);        // If integer, print to output
         }     
         else
         {
            while(true)
            {
               if(isEmpty())
               {
                  System.out.println("Stack is Empty, pushing the value '" + infix.charAt(i) + "'");
                  pushStack(infix.charAt(i));        
                  break; 
               }
               else if(infix.charAt(i) == peek().charAt(0))
               {
                  System.out.println("Same precedence, pushing the value '" + infix.charAt(i) + "'");              
                  pushStack(infix.charAt(i));         
                  break;
               }
               else if((int)infix.charAt(i) < (int)peek().charAt(0)) 
               {
                  System.out.println("Lower precedence in stack, pushing the value '" + infix.charAt(i) + "'");              
                  pushStack(infix.charAt(i));         
                  break;               
               }
               else
               {
                  System.out.println("Higher precedence on top (" + peek() + ")");
                  System.out.println(infix.charAt(i) + " is on hold...");
                  outputBox += popStack();
               }
            }
         }
                  
         
         System.out.println("Iteration: " + i);
         System.out.println("Parameter: " + infix);
         System.out.println("OutputBox: " + outputBox);  
         System.out.println("StackPos : " + stackPos);
         viewStack(opStack);  
      }
      
      System.out.println("FiNAL DATA..");
      System.out.println("Parameter: " + infix);
      System.out.println("OutputBox: " + outputBox);  
      System.out.println("StackPos : " + stackPos);
      viewStack(opStack);   
         
      while(!isEmpty())
      {
         System.out.println("Popping remaining stack values.." + peek());
         outputBox += popStack();
      }
      
         
      return outputBox;
   }
   
   public static void pushStack(char value)
   {
      stackPos++;
      opStack[stackPos] = ""+value;
   }
   
   public static String popStack()
   {
      String temp = opStack[stackPos];
      opStack[stackPos--] = null;
      return temp;
   }
   public static String peek()
   {
      return opStack[stackPos];
   }
   public static boolean isEmpty()
   {
      return (stackPos == -1);
   }
   
   public static void viewStack(String[] stk)
   {
      System.out.println("---------------------------");
      System.out.println("|");
      System.out.print("| ");
      
      for(int i=0; i<stk.length; i++)
         System.out.print(stk[i] + " ");
         
      System.out.println("\n|");   
      System.out.println("---------------------------\n");
   }
}




/*









                                                                         @/
                                                               @@      @/~@
                                                              @%$@    % /~~@
                                                              @///////% %(((((~@
                                                              @~~//~ @//////((@
                                                              @////// @ ///(@
                                                             @~//~~~@ %//@
                                                             @/~~///@  $$$@/
                                                             @@//$//% @/%    @
                                      /@@//$/    @@/////%%///    ~/((((((////   $@
                                    /$@      $@/ @///~~~~//@@%   ((((@@@////   $@
                                  /@        @@///~~~~~~~~~~~% ~((((@  @ (/    $@
                                    @      @///////~~~~~~~      //((((@@@((/     @
                                    @$   @////~~~~~~~~~        (((((((((      ~ @
                                     @@$@///////~~~~~~~~          ((((((        @
                                      $@/////~~~~(~~~~~~~                  $/@@
                                       @////////((((~~~~~~          ~/%%@@@//
                                       @$((////////(~~~~~  \         ~$
                                         @%%%%)))))~~~~   \ $$       $$@
                                         @%%%%%%)))))))))      %         @$
                                           @%%%%%%%%$$$$   \ @   @ \ \  @
                                            @@@@@@@@@@@@@\\\@    @@@@@//
                                            
                                            
                                            
         
                                                     @\
                                           @@      @\~@
                                          @%£@    % \~~@
                                          @\\\\\%  %)))))))@
                                          @~~\\~~@\\\\\))@
                                           @\\\\\\@  \\\)@
                                          @~\~~~@  %\\@
                      @$$%\\\@@           @\~~\\\\@ $$$@\
                   /@@\\$   \\\%\@        @@\\$\\\%@\%   @
                 @@\\\\\~~~~ ~   \\\@@$   @ ~\)))))\\\\    $@
      $\@\@@$  @\\\\\~~~~~~          %%\\  @\\\))))@@@\\\   $@
     @       $@\\\\\\\\\\\~~~~~             %\\))))@  @)\\    $@
    @@     @\\\\\\\\\~~~~~~~                \\))))@@@))\     @
     @     @\\\\\\\\\~~~~~~~                 )))))))))      ~ @
     @@\  $@\\\\\\\\\\\~~~~~~~                 ))))))        @
        @@@ @\\\\\\\\\\\~~~~~~~~~~                      $@@
             $$$$$$\\\\\\~~~~~                ~~\%%@@@\\
              @\\\~~~~~~~   %$%%%             ~$$@
               @\\\\~~~~~~~   $  @@@@\\            $
               @$))~~~~     @@     //\@@@     ~~~~~$
                @%%%)))~~~~\@            @@$    ~~~$@
                @%%%))))))))@                %@@~~~~~~@
                @))))))))@                       @@~~~~~@
                 @))))))@                         @$$$%\\
                  @%%%@                          \\@@@
                   @@@


*/