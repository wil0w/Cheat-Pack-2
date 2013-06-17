package com.kodehawa.exalm;

public class Test
{

        public static void main(String[] args)
        {
                Test test = new Test(); test.test(args, 0, 1, 2, 3);
        }


        /*
         *  Smth
         */
        public void test(String[] args,
                                         int first,
                                         int second,
                                         int third,
                                         int fourth)
        {
                for(String str : args){
                        System.out.println(str);
                }
                System.out.println(first+" "+second+" "+third+" "+fourth);
        }

        public static boolean unusedField = true;
        public static int unusedField2 = 0;
}