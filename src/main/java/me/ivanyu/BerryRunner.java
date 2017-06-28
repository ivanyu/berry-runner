package me.ivanyu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BerryRunner extends Thread {
    private final Logger logger = LoggerFactory.getLogger(BerryRunner.class);

    private final String berryFileName;
    private final String berryDirectory;

    /**
     * @param berryFileName Berry file name, e.g. "berry.yaml"
     * @param berryDirectory Berry working directory, e.g. ".".
     */
    public BerryRunner(final String berryFileName,
                       final String berryDirectory) {
        super();
        this.setDaemon(true);

        this.berryFileName = berryFileName;
        this.berryDirectory = berryDirectory;
    }

    @Override
    public void run() {
        logger.info("Running berry");

        try {
            final Process berryProcess = new ProcessBuilder()
                .redirectErrorStream(true)
                .command("berry", "-f", this.berryFileName, this.berryDirectory)
                .start();

            InputStreamReader inputReader = null;
            BufferedReader bufferedReader = null;

            try {
                inputReader = new InputStreamReader(berryProcess.getInputStream());
                bufferedReader = new BufferedReader(inputReader);

                while (!Thread.interrupted()) {
                    logger.info("[Berry output] {}", bufferedReader.readLine());

                    if (!berryProcess.isAlive()) {
                        logger.warn("Berry exited");
                        break;
                    }

                    Thread.sleep(100);
                }
            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
            }
        } catch (Exception e) {
            logger.error("Error running berry", e);
        }
    }
}
