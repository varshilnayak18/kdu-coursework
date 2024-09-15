package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Logging logger = new Logging();
        GenericMethod gen = new GenericMethod();

        Integer[] array = {1,2,3,5,4};
        gen.swap(array,3,4);
        for(Integer i: array){
            logger.logInfo("" + i);
        }

        String[] arr = {"hi", "hello", "hey"};
        gen.swap(arr,0,2);
        for(String i: arr){
            logger.logInfo(i);
        }
    }
}