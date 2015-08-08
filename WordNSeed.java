/*
 * WordNSeed.java
 * Jon Larsen
 * This class hold N number of words in an array. It is used as the key for the
 * hashmap in Markov.java.
 */

import java.util.*;

public class WordNSeed implements Comparable<WordNSeed>{
    private String[] myWords; //hold the words that form the seed


    //Store n words from input String[] as the N words of the seed.
    //The index "start" will be the 1st word.
    public WordNSeed(String[] list, int start, int n){
        myWords = new String[n];

        //arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
        System.arraycopy(list, start, myWords, 0, n);
    }//end constructor

    //note case-sensitive
    public int compareTo(WordNSeed wns){
        for(int i = 0; i < myWords.length; i++){
            if(!myWords[i].equals(wns.myWords[i])){
                if (myWords[i].compareTo(wns.myWords[i]) < 0)
                    return -1;
                else
                    return 1;
            }//end if
        }//end for
        return 0;
    }//end compareTo()

    //returns true if they're the same
    public boolean equals(Object o){
        WordNSeed wns = (WordNSeed)o;
        if(wns.compareTo(this) == 0)
            return true;
        return false;
    }//end equals

    public void printSeed(){
        String seed = "";
        for(int i = 0; i < myWords.length; i++){
            seed += myWords[i];
            seed += " ";
        }
        System.out.print(seed);
    }//end printSeed

    //Allows class to function as a key in hash collections
    public int hashCode(){
        int size = myWords.length;
        int code = 0;
        for (int i = 0; i < size; i++)
            code += myWords[i].hashCode();
        return code/size;
    }

    //return a string from the array
    public String getWord(int n){
        if (n < myWords.length)
            return myWords[n];
        return null;
    }//end getWord

    //Make a new seed out of the existing one, removing the first word and
    //adding the parameter
    public WordNSeed newSeed(String str){
        int size = myWords.length;
        String[] temp = new String[size];
        for (int i = 0; i < size - 1; i++)
            temp[i] = myWords[i+1];
        temp[size-1] = str;
        return new WordNSeed(temp, 0, size);
    }//end newSeed
}//end class WordSeed
