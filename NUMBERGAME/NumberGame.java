import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
class NumberGame{
    int a= -1;
    int hints = 3;
    int b;
    int random=0;
    float won=0, played=0;
    long startTime;
    public int getRandom(int min, int max)
    {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public void compare(int random,int guess)
    {
        if(random>guess)
        {
            System.out.println("Guess is too low");
            a = 0;
        }
        else if (random<guess)
        {
            System.out.println("Guess is too high");
            a = 1 ;
        }
        else
        {
            System.out.println("Congratulations! Your guess is correct");
            a = 2;
        }
    }
    public static float accurant(float won, float played)
    {
        float accuracy = 100 - (((played - won)/played) * 100);
        return accuracy;
    }
    public static void updateScores(String name, float accuracy)
    {
        try{
            FileWriter writer = new FileWriter("SCORES.txt",true);
            writer.write(name+":  "+accuracy+"%\n");
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void displayScores()
    {
        try
        {
            File file = new File("SCORES.txt");
            Scanner sc = new Scanner(file);
            System.out.println("\n\t\tSCORES ");
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                System.out.println(line);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("No scores yet");
        }
    }
    public void provideHint(int random)
    {
        if(hints > 0)
        {
            hints= hints-1;
            if(random % 2==0)
            {
                System.out.println("HINT: Number is EVEN");
            }
            else
                System.out.println("HINT: Number is ODD");
        }
        else
            System.out.println("No hints left");
    }
    public void Timer(long startTime)
    {
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Time taken: " + totalTime / 1000 + " seconds");
    }

    public void mainFunction(Scanner s)
    {
        do {
            System.out.println();
            System.out.println("\t\tSTARTING GAME");
            System.out.println("--------------------------");
            System.out.println("Press 1 for EASY   (1-10)");
            System.out.println("Press 2 for MEDIUM (1-50)");
            System.out.println("Press 3 for HARD   (1-100)");
            int c = s.nextInt();
            played = played+1;

            startTime = System.currentTimeMillis();
            if(c==1)
            {
                random = getRandom(1,10);
                //System.out.println(random);
            }
            else if(c==2)
            {
                random = getRandom(1, 50);
                //System.out.println(random);
            }
            else if (c==3)
            {
                random = getRandom(1, 100);
                //System.out.println(random);
            }
            else {
                System.out.println("WRONG INPUT");
            }
            System.out.println();
            System.out.println("You have three tries");
            System.out.println("Enter your guess: ");
            int guess = s.nextInt();
            compare(random, guess);

            if(a!=2)
            {
                if(hints >0) {
                    System.out.println("PRESS H for a hint (Hints left: "+hints+")");
                    System.out.println("Otherwise press any other alphabet");
                    char hintsChoice = s.next().charAt(0);
                    if(hintsChoice=='H' || hintsChoice=='h' )
                    {
                        provideHint(random);
                    }
                }
            }

            int guess_2;
            if (a == 0 || a == 1) {
                System.out.println();
                System.out.println("You have two tries left");
                System.out.println("Guess the number again: ");
                guess_2 = s.nextInt();
                compare(random, guess_2);
            }

            int guess_3;
            if (a == 0 || a == 1) {
                System.out.println();
                System.out.println("This is your last try");
                System.out.println("Guess the number again: ");
                guess_3 = s.nextInt();
                compare(random, guess_3);
            }
            if (a == 0 || a == 1) {
                System.out.println("Sorry, You lost");
            }
            if (a == 2)
            {
                won = won+1;
            }
            System.out.println();
            Timer(startTime);
            System.out.println("\n");
            System.out.println("If you want to play again, press 1");
            System.out.println("Otherwise press 2-9");
            b = s.nextInt();

        }
        while (b == 1);
    }
    public static void main(String[] args) {
        NumberGame n = new NumberGame();
        Scanner s = new Scanner(System.in);

        n.mainFunction(s);
        float accuracy= accurant(n.won, n.played);
        System.out.println("ROUNDS PLAYED: "+n.played);
        System.out.println("ROUNDS WON:    "+n.won);
        System.out.println("ACCURACY:      "+accuracy+"%");
        System.out.println();

        System.out.println("Enter your NAME to save your score or press enter to exit");
        s.nextLine();
        String name = s.nextLine();
        if(!name.isEmpty())
        {
            updateScores(name,accuracy);
        }
        displayScores();
        System.out.println("-------------------------");
        System.out.println("\t\tEND GAME");
    }
}