import java.util.*;

public class TicTacToe {

    static HashSet<Integer> user = new HashSet<Integer>();
    static HashSet<Integer> cpu = new HashSet<Integer>();

    static void place_Input(char[][] gameBoard, int pos, String inputUser){

        //Identifying whose turn?

        char sym = 'X';

        if(inputUser.equals("You")){
            sym = 'X';
            user.add(pos);
        }
        else if(inputUser.equals("CPU")){
            sym = 'O';
            cpu.add(pos);
        }
        else{
            System.out.println("Invalid");
        }

        //Fill in the Board using position
        switch(pos){
            case 1:
                gameBoard[0][0] = sym;
                break;
            case 2:
                gameBoard[0][2] = sym;
                break;
            case 3:
                gameBoard[0][4] = sym;
                break;
            case 4:
                gameBoard[2][0] = sym;
                break;
            case 5:
                gameBoard[2][2] = sym;
                break;
            case 6:
                gameBoard[2][4] = sym;
                break;
            case 7:
                gameBoard[4][0] = sym;
                break;
            case 8:
                gameBoard[4][2] = sym;
                break;
            case 9:
                gameBoard[4][4] = sym;
                break;
            default:
                System.out.println("Invalid");
        }
        printBoard(gameBoard);
        System.out.println();
    }
    static int getRandom(){
        int max = 9;
        int min = 1;
        int range = (max - min) + 1;
        int result = (int) (Math.random() * range) + min;
        return result;
    }

    static String checkWinner(){

        HashSet<Integer> r1 = new HashSet<Integer>();
        r1.add(1);r1.add(2);r1.add(3);
        HashSet<Integer> r2 = new HashSet<Integer>();
        r2.add(4);r2.add(5);r2.add(6);
        HashSet<Integer> r3 = new HashSet<Integer>();
        r3.add(7);r3.add(8);r3.add(9);
        HashSet<Integer> c1 = new HashSet<Integer>();
        c1.add(1);c1.add(4);c1.add(7);
        HashSet<Integer> c2 = new HashSet<Integer>();
        c2.add(2);c2.add(5);c2.add(8);
        HashSet<Integer> c3 = new HashSet<Integer>();
        c3.add(3);c3.add(6);c3.add(9);
        HashSet<Integer> d1 = new HashSet<Integer>();
        d1.add(1);d1.add(5);d1.add(9);
        HashSet<Integer> d2 = new HashSet<Integer>();
        d2.add(3);d2.add(5);d2.add(7);

        HashSet<HashSet> set = new HashSet<HashSet>();
        set.add(r1);set.add(r2);set.add(r3);
        set.add(c1);set.add(c2);set.add(c3);
        set.add(d1);set.add(d2);

        for(HashSet c:set){
            if(user.containsAll(c)){
                return "You Won";
            }
            else if(cpu.containsAll(c)) {
                return "You Lose";
            }
        }
        if(user.size() + cpu.size() == 9) {
            return "Draw";
        }
        return "";
    }
    public static void main(String[] args) {
        char gameBoard[][] = {
                {' ', '|', ' ', '|', ' '},
                {'-', '|', '-', '|', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '|', '-', '|', '-'},
                {' ', '|', ' ', '|', ' '}
        };
        printBoard(gameBoard);

        Scanner input = new Scanner(System.in);

        //For User
        while(true){
            System.out.println("Choose Your Position from 1-9");

            int userPos = input.nextInt();
            while(user.contains(userPos) || cpu.contains(userPos)){
                System.out.println("Position Already Taken");
                System.out.println("Please Try Again, Choose from 1-9");
                userPos = input.nextInt();
            }
            place_Input(gameBoard, userPos, "You");

            String result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }

            //For CPU

            int CPUPos = getRandom();
            while(user.contains(CPUPos) || cpu.contains(CPUPos)){
                CPUPos = getRandom();
            }
            place_Input(gameBoard, CPUPos, "CPU");

            result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }
        }

    }

    static void printBoard(char gameBoard[][]){
        for(int i=0;i< gameBoard.length;i++){
            for(int j=0;j<gameBoard[0].length;j++)
                System.out.print(gameBoard[i][j]);
            System.out.println();
        }
    }
}
