import java.io.*;
import java.util.*;
public class GameMania {

    static Scanner input = new Scanner(System.in);

    public static final String RESET = "\u001B[0m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static void main(String[] args) {

            while (true) {
                System.out.println(BRIGHT_WHITE + "****************************************************************************************" + RESET);

                System.out.println(BRIGHT_BLUE + """
                           
                           ██████╗  █████╗ ███╗   ███╗███████╗    ███╗   ███╗ █████╗ ███╗   ██╗██╗ █████╗ 
                          ██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ████╗ ████║██╔══██╗████╗  ██║██║██╔══██╗
                          ██║  ███╗███████║██╔████╔██║█████╗      ██╔████╔██║███████║██╔██╗ ██║██║███████║
                          ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║╚██╔╝██║██╔══██║██║╚██╗██║██║██╔══██║
                          ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ██║ ╚═╝ ██║██║  ██║██║ ╚████║██║██║  ██║
                           ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝    ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝╚═╝  ╚═╝
                        """ + RESET);


                System.out.println(BRIGHT_WHITE + "****************************************************************************************" + RESET);
                System.out.println(BRIGHT_CYAN + "1. Play Tic tac toe" + RESET);
                System.out.println(BRIGHT_GREEN + "2. Play Rock paper scissors" + RESET);
                System.out.println(BRIGHT_RED + "3. Play Hangman" + RESET);
                System.out.println(BRIGHT_YELLOW + "4. Roll dice" + RESET);
                System.out.println(BRIGHT_RED + "5. Exit" + RESET);
                System.out.print("Enter the choice: ");

                int choice;
                try {
                    choice = Integer.parseInt(input.nextLine());
                } catch (Exception e) {
                    System.out.println("Invalid choice");
                    continue;
                }
                switch (choice) {
                    case 1:
                        TicTacToe();
                        break;
                    case 2:
                        RockPaperScissors();
                        break;
                    case 3:
                        Hangman();
                        break;
                    case 4:
                        RollDice();
                        break;
                    case 5:
                        System.out.println("Thank You For Playing!");
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option!!");
                }
            }
        }

        public static void TicTacToe() {
            Random rand = new Random();


            System.out.println("1- Player vs Player");
            System.out.println("2- Player vs computer");
            System.out.print("Choose mode: ");
            int mode = input.nextInt();
            input.nextLine();

            char[][] board = new char[3][3];
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    board[row][col] = ' ';
                }
            }

            char player = 'X';
            boolean gameOver = false;

            while (!gameOver) {
                printBoard(board);
                System.out.println("Player " + player + "'s turn: ");

                int row, col;

                if (mode == 1 || player == 'X') {
                    System.out.println("Enter the row and column: ");
                    row = input.nextInt();
                    col = input.nextInt();
                    input.nextLine();
                } else {
                    System.out.print("Computer is thinking");
                    for (int i = 0; i < 3; i++) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print(".");
                    }
                    System.out.println();

                    do {
                        row = rand.nextInt(3);
                        col = rand.nextInt(3);
                    } while (board[row][col] != ' ');

                    System.out.println("Computer played at: " + row + ", " + col);
                }

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Invalid position! Try again.");
                    continue;
                }

                if (board[row][col] == ' ') {
                    board[row][col] = player;
                    gameOver = haveWon(board, player);
                    if (gameOver) {
                        printBoard(board);
                        if (mode == 2 && player == 'O') {
                            System.out.println("The computer has won!!");
                        } else {
                            System.out.println("Player " + player + " has won!!");
                            break;
                        }
                    }
                    if (isDraw(board)) {
                        printBoard(board);
                        System.out.println("It's a draw!");
                        break;
                    } else {
                        if (player == 'X') {
                            player = 'O';
                        } else {
                            player = 'X';
                        }
                    }
                } else {
                    System.out.println("Invalid move. Try again!");
                }
            }
            printBoard(board);
        }

        public static boolean haveWon(char[][] board, char player) {
            for (int row = 0; row < board.length; row++) {
                if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                    return true;
                }
            }

            for (int col = 0; col < board.length; col++) {
                if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                    return true;
                }
            }
            if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
                return true;
            }

            if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
                return true;
            }
            return false;
        }

        public static boolean isDraw(char[][] board) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == ' ')
                        return false;
                }
            }
            return true;
        }

        public static void printBoard(char[][] board) {
            System.out.print("   ");
            for (int col = 0; col < 3; col++) {
                System.out.print(col + "    ");
            }
            System.out.println();


            for (int row = 0; row < 3; row++) {
                System.out.print(row + "  ");
                for (int col = 0; col < 3; col++) {
                    System.out.print(" " + board[row][col] + " ");
                    if (col < 2) {
                        System.out.print("|");
                    }
                }
                System.out.println();
                if (row < 2) {
                    System.out.println("   ---+---+---");
                }
            }
        }

        public static void RockPaperScissors() {

            Random random = new Random();

            String[] choices = {"rock", "paper", "scissors"};
            String playerChoice;
            String computerChoice;
            String playAgain = "yes";

            do {
                System.out.print("Enter your move (rock, paper, scissors): ");
                playerChoice = input.nextLine().toLowerCase();

                if (!playerChoice.equals("rock") &&
                        !playerChoice.equals("paper") &&
                        !playerChoice.equals("scissors")) {
                    System.out.println("Invalid choice!Try again.");
                    continue;
                }

                computerChoice = choices[random.nextInt(3)];
                System.out.println("Computer choice is: " + computerChoice);

                if (playerChoice.equals(computerChoice)) {
                    System.out.println("It's a tie!!!");
                } else if (playerChoice.equals("rock") && computerChoice.equals("scissors") ||
                        playerChoice.equals("scissors") && computerChoice.equals("paper") ||
                        playerChoice.equals("paper") && computerChoice.equals("rock")) {
                    System.out.println("You win!!!!");
                } else {
                    System.out.println("You lose.  :(");
                }

                System.out.print("Play again? (yes/no): ");
                playAgain = input.nextLine().toLowerCase();
            } while (!playAgain.equals("no"));

            System.out.println("Thanks for playing.");

            System.out.println("                   ");

        }

        public static void Hangman() {
            String filePath = "words.txt";
            ArrayList<String> words = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    words.add(line.trim());
                }
            } catch (FileNotFoundException e) {
                System.out.println("Could not find file.");
            } catch (IOException e) {
                System.out.println("Something went wrong!");
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

            while (wrongGuesses < 6) {

                System.out.println(getHangmanArt(wrongGuesses));

                for (char c : wordState) {
                    System.out.print(c + " ");
                }
                System.out.println();

                System.out.print("Guess the letter: ");
                char guess = input.next().toLowerCase().charAt(0);

                if (word.indexOf(guess) >= 0) {
                    System.out.println("Correct guess!\n");

                    for (int i = 0; i < word.length(); i++) {
                        if (word.charAt(i) == guess) {
                            wordState.set(i, guess);
                        }
                    }

                    if (!wordState.contains('_')) {
                        System.out.print(getHangmanArt(wrongGuesses));
                        System.out.println("YOU WIN!!");
                        System.out.println("The word was: " + word);
                        break;
                    }
                } else {
                    wrongGuesses++;
                    System.out.println("Wrong guess!\n");
                }

                if (wrongGuesses >= 6) {
                    System.out.print(getHangmanArt(wrongGuesses));
                    System.out.println("GAME OVER!");
                    System.out.println("The word was: " + word);
                    break;
                }
            }
        }

        static String getHangmanArt(int wrongGuesses) {
            String art = "";
            switch (wrongGuesses) {
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
                    art = """
                            
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
                    art = """
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
                    art = """
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
                    art = """
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
                    art = """
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

        public static void RollDice() {

            Random random = new Random();
            int numOfDice;
            int total = 0;

            System.out.print("Enter the # of Dice: ");
            numOfDice = input.nextInt();
            input.nextLine();
            if (numOfDice > 0) {
                for (int i = 0; i < numOfDice; i++) {
                    int roll = random.nextInt(1, 7);
                    printDie(roll);
                    System.out.println("You rolled: " + roll);
                    total += roll;
                }
                System.out.print("Total: " + total);
                System.out.println("             ");
            } else {
                System.out.print("# of dice must be greater than 0");
            }
        }

        static void printDie(int roll) {

            String dice1 = """
                     -------
                    |       |
                    |   ●   |
                    |       |
                     -------
                    
                    """;

            String dice2 = """
                     -------
                    | ●     |
                    |       |
                    |     ● |
                     -------
                    
                    """;

            String dice3 = """
                     -------
                    | ●     |
                    |   ●   |
                    |     ● |
                     -------
                    
                    """;

            String dice4 = """
                     -------
                    | ●   ● |
                    |       |
                    | ●   ● |
                     -------
                    
                    """;

            String dice5 = """
                     -------
                    | ●   ● |
                    |   ●   |
                    | ●   ● |
                     -------
                    
                    """;

            String dice6 = """
                     -------
                    | ●   ● |
                    | ●   ● |
                    | ●   ● |
                     -------
                    
                    """;

            switch (roll) {
                case 1:
                    System.out.print(dice1);
                    break;
                case 2:
                    System.out.print(dice2);
                    break;
                case 3:
                    System.out.print(dice3);
                    break;
                case 4:
                    System.out.print(dice4);
                    break;
                case 5:
                    System.out.print(dice5);
                    break;
                case 6:
                    System.out.print(dice6);
                    break;
                default:
                    System.out.print("Invalid roll.");
                    break;
            }

        }
    }





