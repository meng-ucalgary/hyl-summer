package ca.hackyourlearning;

// import java.util.ArrayList;

public class BTCCalculations {

    private double miningTime[];
    private double totalMiningOutput;
    private double bitcoinVolume;
    private double profit;
    private double btcUsd;

    public BTCCalculations(double volume) {
        this.bitcoinVolume = volume;
        miningTime = new double[currentPull.minerArray.size()];
        totalMiningOutput = 0;
        this.btcUsd = Rates.bitcoinToUsd();

        for (int i = 0; i < currentPull.minerArray.size(); i++) {
            miningTime[i] = volume / currentPull.minerArray.get(i).daily_output;
            totalMiningOutput += currentPull.minerArray.get(i).daily_output;
        }
    }

    CSVPull currentPull = new CSVPull();

    /**
     * @return miningTime (double): the time it takes to mine the amount of
     *         requested Bitcoin
     *
     * @getter
     */
    public double[] miningTimeGetter() {
        return miningTime;
    }

    public String profitCalculator() {
        String str = "";

        double arr[] = new double[currentPull.minerArray.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = this.btcUsd * this.bitcoinVolume;
            arr[i] = arr[i] / miningTime[i];
        }

        EnergyRates er = new EnergyRates();

        for (int i = 0; i < currentPull.minerArray.size(); i++) {

            for (int j = 0; j < er.energy_rates.length; j++) {

                profit = arr[i] - (currentPull.minerArray.get(i).wattage / 1000) * (er.energy_rates[j] / 100);
                str = str + "Profit for Miner Rig " + currentPull.minerArray.get(i).name + " at hour " + j
                        + " is: " + String.format("%.5f", profit) + "\n";
            }
        }

        return str;
    }

    @Override
    public String toString() {
        String s = "";

        s += "The current USD for these bitcoins mined is: "
                + String.valueOf(String.format("%.2f", this.btcUsd * this.bitcoinVolume)) + "\n\n";

        for (int i = 0; i < currentPull.minerArray.size(); i++)
            s = s + "The total mining hours for " + currentPull.minerArray.get(i).name + " is: "
                    + String.format("%.2f", this.miningTime[i]) + "\n";
        s += "\nTime required to create " + this.bitcoinVolume + " bitcoins using all mining rigs at the same time: "
                + String.format("%.2f", bitcoinVolume / totalMiningOutput) + " hours";

        s += "\n\n" + this.profitCalculator();

        return s;
    }
}
