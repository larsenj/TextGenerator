import java.util.*;

class TextGenMain{
    public static void main (String args[]){
      
        if(args.length < 1 || args.length > 1){
            System.out.println("Usage: TextGenMain [filename]");
            System.exit(1);
        }

        Markov mv = new Markov();
        mv.initialize(args[0]);

        Scanner keyboard = new Scanner(System.in);
        int sentences;
        String goAgain;

        do{
            do{
                System.out.print("Number of sentences to generate: ");
                sentences = keyboard.nextInt();
            }while (sentences < 1);
        mv.generateText(sentences);
        System.out.print("\nGo again? (Y/N) ");
        goAgain = keyboard.next();
        } while(goAgain.toUpperCase().charAt(0) == 'Y');
        //all the testing code is below      

/*
        System.out.println("Gentext");
        mv.generateText(3);      
/*

/*
        String[] vtest = {"about", "how"};
        WordNSeed v = new WordNSeed(vtest, 0, 2);
        v.printSeed();
        System.out.println("\"about how\" hashcode = " + v.hashCode());

        if (mv.getValue(v) != null){
            ArrayList<String> value = mv.getValue(v);
            for(int i = 0; i < value.size(); i++)
                System.out.print(value.get(i)+" ");
        }

        String[] wtest = {"irrelevant", "to"};
        WordNSeed w = new WordNSeed(wtest, 0, 2);
        w.printSeed();
        System.out.println("\"irrelevant to\" hashcode = " + w.hashCode());

        if (mv.getValue(w) != null){
            ArrayList<String> walue = mv.getValue(w);
            for(int i = 0; i < walue.size(); i++)
                System.out.print(walue.get(i)+" ");
        }        //System.out.println("Iterating through");
        //mv.printMap();        
*/        


        System.out.println();










/*
        String[] test = {"a", "b"};
        WordNSeed n = new WordNSeed(test, 0, 2);
        String[] t2 = {"a", "b"};
        WordNSeed n2 = new WordNSeed(t2, 0, 2);
        String[] t3 = {"b", "c"};
        WordNSeed n3 = new WordNSeed(t3, 0, 2);

        boolean comp = true;
        boolean eq = true;

        if(n.compareTo(n2) != 0)
            comp = false;
        if(n.compareTo(n3) == 0)
            comp = false;
        if(!n.equals(n2))
            eq = false;
        if(n.equals(n3))
            eq = false;
  
        n.printSeed();
        n2.printSeed();
        n3.printSeed();      

        System.out.println(comp);
        System.out.println(eq);
*/

    }//end main
}//end TextGenMain
