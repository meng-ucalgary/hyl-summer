package ca.hackyourlearning;

public class App {
    public void start() {
        System.out.println(BitcoinUSD.getUSD());
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.start();
    }

}
