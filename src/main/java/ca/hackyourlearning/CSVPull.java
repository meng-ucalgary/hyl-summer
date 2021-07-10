package ca.hackyourlearning;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Reads the CSV file MiningSetup.csv and loads all Miners
 */
public class CSVPull {
    public ArrayList<Miner> minerArray;

    /**
    * Constructs CSVPull object
    */
    public CSVPull() {
        this.minerArray = new ArrayList<Miner>();
        this.puller();
    }

    /**
     * Load all miners from MiningSetup.csv into minerArray
     */
    public void puller() {
        Scanner reader;

        try {
            reader = new Scanner(new File("MiningSetup.csv"), "UTF-8");
            reader.useDelimiter(",");

            while (reader.hasNextLine()) {
                String[] lines = reader.nextLine().split(",");

                Miner mine = new Miner(lines[0], Double.parseDouble(lines[1]), Double.parseDouble(lines[2]));
                this.minerArray.add(mine);
            }
            reader.close();
        }

        catch (FileNotFoundException e) {
            System.err.printf("%n%nPlease make sure \'MiningSetup.csv\' is present at the project directory");
        }
    }
}
