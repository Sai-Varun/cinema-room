import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int col = scanner.nextInt();

        int totalIncome = 0;
        if (row * col < 60) {
            totalIncome = row * col * 10;
        } else {
            totalIncome = 18 * col * (row / 2) + 8 * col * (row % 2);
        }
        int earned = 0;
        int price = 0;
        int booked = 0;

        char[][] seats = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                seats[i][j] = 'S';
            }
        }

        boolean notExit = true;

        while (notExit) {

            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> printSeats(seats);
                case 2 -> {
                    while (price == 0) {
                        price = bookTicket(seats, scanner);
                    }
                    booked++;
                    earned += price;
                    price = 0;
                }
                case 3 -> printStats(row, col, booked, earned, totalIncome);
                case 0 -> notExit = false;
                default -> System.out.println("Wrong input!");
            }
        }
    }

    public static void printSeats(char[][] seats) {

        System.out.print("Cinema: \n  ");
        for (int i = 1; i <= seats[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < seats.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats[0].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int bookTicket(char[][] seats, Scanner scanner) {

        System.out.println("Enter a row number:");
        int rowNum = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        int seatNum = scanner.nextInt();

        int price = 10;
        if (seats.length * seats[0].length >= 60 && rowNum > seats.length / 2) {
            price = 8;
        }

        if (rowNum - 1 >= seats.length || seatNum - 1 >= seats[0].length) {
            System.out.println("Wrong input!");
            price = 0;
        } else if (seats[rowNum - 1][seatNum - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            price = 0;
        } else {
            System.out.println("Ticket price: $" + price);

            seats[rowNum - 1][seatNum - 1] = 'B';
        }
        return price;
    }

    public static void printStats(int r, int c, int b, int e, int t) {
        System.out.println("Number of purchased tickets: " + b);
        System.out.printf("Percentage: %.2f%% %n", ((float) b * 100) / (r * c));
        System.out.println("Current income: $" + e);
        System.out.println("Total income: $" + t);
    }
}