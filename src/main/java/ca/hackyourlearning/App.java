package ca.hackyourlearning;

import org.fusesource.jansi.AnsiConsole;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Driver class
 */
public class App {
    BufferedReader br;

    /**
     * Contructs object of App and initializes fields to default value
     */
    public App() {
        AnsiConsole.systemInstall();
        this.br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Rates.bitcoinToUsd());
    }

    /**
     * Clears the console
     */
    public static void clearConsole() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.indexOf("win") > -1) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        else {
            System.out.print("\033\143");
        }
    }

    /**
     * Provides a basic command line interface to program
     */
    public void cli() {
        // priming input ch
        int choice = 9;

        while (choice != 0) {
            App.clearConsole();

            System.out.printf("%n%s", ColorText.text("Welcome to Bitcoin Mining Estimator", Color.MAGENTA));
            System.out.printf("%n%s", ColorText.text("-----------------------------------", Color.MAGENTA));

            System.out.printf("%n%nProgram Menu");
            System.out.printf("%n%n[1] BTC Estimator");
            System.out.printf("%n[0] Exit");

            try {
                System.out.printf("%n%n%n[%s] Please enter your numerical choice: ",
                        ColorText.text("QUES", Color.YELLOW));
                choice = Integer.parseInt(this.br.readLine());

                switch (choice) {
                    case 1: {
                        this.btc();

                        System.out.printf("%n%n%nPress enter to return to the menu ");

                        try {
                            this.br.readLine();
                        } catch (IOException bre) {
                            // do nothing
                        }
                    }

                        break;

                    // exit by changing choice
                    case 0: {
                        choice = 0;
                    }
                        break;

                    default: {
                        throw new NumberFormatException();
                    }
                }
            }

            catch (NumberFormatException e) {
                System.err.printf("%n%n[%s] Please enter a valid choice", ColorText.text("FAIL", Color.RED));
                System.out.printf("%n%n%nPress enter to try again ");

                // wait for user to press enter
                try {
                    this.br.readLine();
                } catch (IOException bre) {
                    ; // do nothing
                }
            }

            catch (IOException e) {
                System.err.printf("%n%n[%s] IOException. Please enter a valid choice",
                        ColorText.text("FAIL", Color.RED));
                System.out.printf("%n%n%nPress enter to try again ");

                // wait for user to press enter
                try {
                    this.br.readLine();
                } catch (IOException bre) {
                    ; // do nothing
                }
            }
        }
    }

    public void btc() {
        App.clearConsole();

        while (true) {

            try {
                System.out.printf("%n%n[%s] Please enter number of Bitcoins you would like to mine: ",
                        ColorText.text("QUES", Color.YELLOW));

                Double btc = Double.parseDouble(this.br.readLine());
                System.out.println(btc);
                System.out.println(Rates.bitcoinToUsd());


                break;
            }

            catch (NumberFormatException e) {
                System.err.printf("%n%n[%s] Please enter a valid choice", ColorText.text("FAIL", Color.RED));
                System.out.printf("%n%n%nPress enter to try again ");

                // wait for user to press enter
                try {
                    this.br.readLine();
                } catch (IOException bre) {
                    ; // do nothing
                }
            }

            catch (IOException e) {
                System.err.printf("%n%n[%s] IOException. Please enter a valid choice",
                        ColorText.text("FAIL", Color.RED));
                System.out.printf("%n%n%nPress enter to try again ");

                // wait for user to press enter
                try {
                    this.br.readLine();
                } catch (IOException bre) {
                    ; // do nothing
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.cli();
    }
}
