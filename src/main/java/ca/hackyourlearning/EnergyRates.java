package ca.hackyourlearning;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EnergyRates {
    double energy_rates[] = new double[24];

    /**
     *
     */
    public EnergyRates() {
        Scanner reader;
        boolean first_line = true;

        try {

            int hour = 0;
            reader = new Scanner(new File("EnergyRates.csv"), "UTF-8");
            reader.useDelimiter(",");
            while (reader.hasNextLine()) {
                String[] lines = reader.nextLine().split(",");
                if (first_line == true) {
                    hour = 0;
                    first_line = false;
                } else {
                    hour = Integer.parseInt(lines[0].split(":")[0].replace("?", ""));
                }

                double energy_rate = Double.parseDouble(lines[1]);
                if (hour < this.energy_rates.length) {
                    this.energy_rates[hour] = energy_rate;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public double[] getEnergy_rates() {
        return energy_rates;
    }

}
