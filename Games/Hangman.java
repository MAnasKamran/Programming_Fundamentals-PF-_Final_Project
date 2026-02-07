import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Hangman {
    public static void main(String [] args) {

        String filePath = "words.txt";
        ArrayList<String> words = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                words.add(line.trim());
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Could not find file.");
        }
        catch(IOException e){
            System.out.println("Something went wrong");
        }
        if (words.isEmpty()) {
            System.out.println("No words found in file.");
            return;
        }

        Random random = new Random();

        String word = words.get(random.nextInt(words.size()));

        Scanner input = new Scanner(System.in);
        ArrayList<Character> wordState = new ArrayList<>();
        int wrongGuesses = 0;

        for (int i = 0; i < word.length(); i++) {
            wordState.add('_');
        }

        System.out.println("*************************");
        System.out.println("Welcome to Java Hangman!!");
        System.out.println("*************************");

        System.out.println("Word: ");

        while(wrongGuesses < 6){

            System.out.println(getHangmanArt(wrongGuesses));

            for(char c : wordState) {
                System.out.print(c + " ");
            }
            System.out.println();

            System.out.print("Guess the letter: ");
            char guess = input.next().toLowerCase().charAt(0);

            if(word.indexOf(guess) >= 0){
                System.out.println("Correct guess!\n");

                for(int i = 0; i < word.length(); i++){
                    if(word.charAt(i) == guess){
                        wordState.set(i, guess);
                    }
                }

                if(!wordState.contains('_')){
                    System.out.print(getHangmanArt(wrongGuesses));
                    System.out.println("YOU WIN!!");
                    System.out.println("The word was: " + word);
                    break;
                }
            }
            else{
                wrongGuesses++;
                System.out.println("Wrong guess!\n");
            }

            if(wrongGuesses >= 6){
                System.out.print(getHangmanArt(wrongGuesses));
                System.out.println("GAME OVER!");
                System.out.println("The word was: " + word);
                break;
            }
        }
        input.close();
    }
    static String getHangmanArt(int wrongGuesses){
        String art = "";
        switch(wrongGuesses){
            case 0:
               art = """
              +-------+
              |       |
                      |
                      |
                      |
                      |
                      |
                =========
                          """;
               break;
            case 1:
               art =  """
                                   
              +-------+
              |       |
              O       |
                      |
                      |
                      |
                      |
                =========
                """;
               break;
            case 2:
               art = """
                        
              +-------+
              |       |
              O       |
              |       |
                      |
                      |
                      |
                =========                        
                        
                """;
               break;
            case 3:
             art =    """
              +-------+
              |       |
              O       |
             /|       |
                      |
                      |
                      |
                =========                      
                        """;
             break;
            case 4:
                art =     """
              +-------+
              |       |
              O       |
             /|\\      |
                      |
                      |
                      |
                =========                        
                        """;
                break;
             case 5:
                 art =    """
              +-------+
              |       |
              O       |
             /|\\      |
             /        |
                      |
                      |
                =========                         
                         """;
                 break;
            case 6:
                art =     """
              +-------+
              |       |
              O       |
             /|\\      |
             / \\      |
                      |
                      |
                =========                       
                        """;
                break;

            default:
                art = "Invalid.";
            break;

        }
        return art;
    }
}
