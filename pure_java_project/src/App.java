import TestNumber;
import PassObject;
import ShortCircuitTest;
import StringTest;
import CakeCase;
import randNum;


import CastingTest;
import BreakAndContinue;
import SwitchCake;

public class App {
    public static void main(String[] args){

        //call test number
       
        TestNumber.TestNumber();

        // call test String
        StringTest.test();

        // call Circuit test
   
        ShortCircuitTest.test();

        //call test casting

        CastingTest.testDouble();
        //CastingTest.testFloat();
        CastingTest.testCasting();

        // call Test if-else control
       
        CakeCase.cake(1);
        
        //call test break and continue

        //BreakAndContinue.BreakAndContinue(90);
        BreakAndContinue.WhileTest(10);
        BreakAndContinue.DoWhileTest(10);

        //call test switch
        SwitchCake.switchCase();
        
        //call test random number
        System.out.println(randNum.randNum());
        
    }
    }