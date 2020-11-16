import java.util.Scanner;

public class Game {
    private final String nothing = "___|";
    private final String player1 = "_O_|";
    private final String player2 = "_X_|";
    private String player1Name = "";
    private String player2Name = "";
    private String[] board = new String[9];

    void welcome() {
        Scanner scan = new Scanner(System.in);
        System.out.println("********** This is a console based TIC TAC TOE game **********\n\n" +
                "It has certain rules to play\n" +
                "1.Only two players can play at a time\n" +
                "2.You have to enter number for corresponding block for your play\n" +
                "3.No. for each block are as follows\n\n" +
                "_1_|_2_|_3_|\n" +
                "_4_|_5_|_6_|\n" +
                "_7_|_8_|_9_|\n\n");

        System.out.print("Name of Player 1 :");
        player1Name = scan.nextLine();
        System.out.print("name of Player 2 :");
        player2Name = scan.nextLine();
        System.out.println("\nPress any key to Start the game ->");
        String enter = scan.nextLine();
        clearScreen();
    }

    void start() {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 9; i++)
            board[i] = nothing;
        update(0, 0);
        while (true) {
            while(true) {
                System.out.println(player1Name + " Your move Enter your block number  : ");
                int move1 = scan.nextInt();

                if (move1 > 9 || move1 < 1) {
                    System.out.println("Enter only between 1-9 ");
                    continue;
                }
                if (!checkDublicate(move1 - 1)) {
                    System.out.println("Already filled try another block ");
                    continue;
                }
                clearScreen();
                update(1, move1 - 1);
                checkResult(player1Name);
                break;
            }

            while(true) {
                System.out.println(player2Name + " Your move Enter your block number  : ");
                int move2 = scan.nextInt();
                if (move2 > 9 || move2 < 1) {
                    System.out.println("Enter only between 1-9 ");
                    continue;
                }
                if (!checkDublicate(move2 - 1)) {
                    System.out.println("Already filled try another block ");
                    continue;
                }
                clearScreen();
                update(2, move2 - 1);
                checkResult(player2Name);
                break;
            }
        }
    }

    void update(int player, int position) {

        if (player == 1)
            board[position] = player1;
        if (player == 2)
            board[position] = player2;

        System.out.println("********** Welcome to the game **********\n\n");

        for (int i = 0; i < 3; i++)
            System.out.print(board[i]);
        System.out.println();

        for (int i = 3; i < 6; i++)
            System.out.print(board[i]);
        System.out.println();

        for (int i = 6; i < 9; i++)
            System.out.print(board[i]);
        System.out.println();
    }

    void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    void checkResult(String player) {
        int flag = 0;
        for (int i = 0; i < 9; i = i + 3) {
            if (board[i].equals(player1) && board[i + 1].equals(player1) && board[i + 2].equals(player1)) {
                flag = 1;
                break;
            }

            if (board[i].equals(player2) && board[i + 1].equals(player2) && board[i + 2].equals(player2)) {
                flag = 2;
                break;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i].equals(player1) && board[i + 3].equals(player1) && board[i + 6].equals(player1)) {
                flag = 1;
                break;
            }

            if (board[i].equals(player2) && board[i + 3].equals(player2) && board[i + 6].equals(player2)) {
                flag = 2;
                break;
            }
        }

        if (board[0].equals(player1) && board[4].equals(player1) && board[8].equals(player1))
            flag = 1;
        if (board[2].equals(player1) && board[4].equals(player1) && board[6].equals(player1))
            flag = 1;

        if (board[0].equals(player2) && board[4].equals(player2) && board[8].equals(player2))
            flag = 2;
        if (board[2].equals(player2) && board[4].equals(player2) && board[6].equals(player2))
            flag = 2;

        if (flag == 1 || flag == 2 ) {
            System.out.println("\n\nCongratulations " + player + "You won the game !!!\n");
            restart();
        }
        else
        {
            int draw = 0;
            for(String block : board) {
                if (block.equals(nothing)) {
                    draw++;
                    break;
                }
            }
            if(draw == 0)
            {
                System.out.println("Draw!");
                restart();
            }
        }
    }

    void restart()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Do you want to quit(y/n)");
        String option = scan.nextLine();
        if (option.equals("y") || option.equals("Y")) {
            System.out.println("Good Bye\n");
            System.exit(0);
        } else {
            clearScreen();
            start();
        }
    }
    boolean checkDublicate(int position)
    {
        return board[position].equals(nothing);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.welcome();
        game.start();
    }
}
