package me.ivanyu;

public class DeveloperApp {
    public static void main(String[] args) throws InterruptedException {
        final String berryYamlPath =
                DeveloperApp.class.getClassLoader().getResource("berry.yaml").getPath();

        final BerryRunner berryRunner = new BerryRunner(berryYamlPath, ".");
        berryRunner.start();
        berryRunner.join(60000);
    }
}
