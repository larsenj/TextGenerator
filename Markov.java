/*
 *  Markov.java
 *  Jon Larsen
 *  Generates and initializes the hashmap for the seed text. Also generates the
 *  random text based on the seed text.
 */

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Markov{

    private Map<WordNSeed, ArrayList<String>> originText = 
        new HashMap<WordNSeed, ArrayList<String>>(); //stores the seed text
    private List<WordNSeed> seedKeys; //stores all keys
    private int seedNum; //number of words to use for WordNSeed

    public Markov(){
       //Random rand = new Random(1234);
       seedNum = 2;
    }//end defalt constructor
   
    //constructor that allows different seed sizes
    public Markov(int n){
       seedNum = n;
    }//end constructor
   
    //Function will take given file name, read it, and add all words to the 
    //hashmap in the key, value form of WordNSeed, ArrayList<String> 
    public void initialize(String filename){
        ArrayList<String> nText = new  ArrayList<String>();
        
        //read the file into the ArrayList
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String line = " ";
            while((line = in.readLine()) != null){
                //Split each line into an array of words.
                String[] parts = line.split("\\s+");
                //Add all the words to the main ArrayList
                nText.addAll(Arrays.asList(parts));
            }//end while
            in.close();
        }catch (Exception e){}

        //move all data from the ArrayList into an array
        String[] inText = new String[nText.size()];
        nText.toArray(inText);

        //Place Key WordNSeed with ArrayList value into hashmap
        for (int i = 0; i < inText.length - seedNum; i++){
            //WordNSeed(String[] list, int start, int n)
            WordNSeed n = new WordNSeed(inText, i , seedNum);
            addItem(n, inText[i+seedNum]);
            //n.printSeed();
            //System.out.println("WORD is " + inText[i+seedNum]);
        }

        //create the list of keys - used in initial seed generation
        seedKeys = new ArrayList<WordNSeed>(originText.keySet());
    }//end initialize

    //Helper function for initialize(). Places the given String into the list 
    //for the appropriate key in the hashmap. Synchronized to avoid multi-
    //threading issues. 
    public synchronized void addItem(WordNSeed n, String s){
        if(originText.get(n) == null)
            originText.put(n, new ArrayList<String>());
        originText.get(n).add(s);
    }//end addItem

    //returns the list of values associated with a given key
    public ArrayList<String> getValue(WordNSeed n){
        if(originText.get(n) == null){
            System.out.println("Seed does not exist");
            return null;
        }
        else
            return originText.get(n);
    }//end getValue 

    //TODO: this looks like crap - make it pretty
    public void printMap(){
        for(Map.Entry<WordNSeed, ArrayList<String>> entry : originText.entrySet())
           System.out.println(entry.getKey() + "/" + entry.getValue()); 
    }//end printMap()

    //generate a random amount of text 
    public void generateText(int size){
        int count = 0;
        
        //get initial seed.
        WordNSeed seed = getInitial();

        //create a regex
        Pattern punctSearch = Pattern.compile(".*[.?!]$");

        Random r = new Random();
        //boolean endSentence = false;
        ArrayList<String> values;
        String newStr;
        int rand;
        
        //print out seed
        seed.printSeed();
        
        do{
            //endSentence = false;

            //ensure key has a valid ArrayList
            if(originText.get(seed) == null)
                seed = getInitial();
            
            //get a random word from its list of values
            values = originText.get(seed);
            rand = r.nextInt(values.size());
            newStr = values.get(rand);

            //check for punctuation
            Matcher punctMatch = punctSearch.matcher(newStr);
            if(punctMatch.find()){
                //endSentence = true;
                count++;
            }

            //print word out
            System.out.print(newStr + " ");    

            //use word to generate new seed.
            seed = seed.newSeed(newStr); 

        } while (count < size);

    }//end generateText()

    //helper function to start off generateText() with an initial seed
    public WordNSeed getInitial(){
        Random r = new Random();
        boolean isCap = false;
        WordNSeed s;
        do{
            s = seedKeys.get(r.nextInt(seedKeys.size()));
            //make sure the seed starts with a capital letter
            //if (s.getWord(0).charAt(0) != null)
            if (s.getWord(0) != null && s.getWord(0).length() > 0)
                if(Character.isUpperCase(s.getWord(0).charAt(0)))
                    isCap = true;
        }while(!isCap);
        return s;
    }//end getInitial()

}//end Markov class
