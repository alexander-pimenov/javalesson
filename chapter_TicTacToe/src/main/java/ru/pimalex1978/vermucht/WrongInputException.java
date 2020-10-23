package ru.pimalex1978.vermucht;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Checked analog to IllegalArgumentException.
 * https://github.com/vermucht/asapozhnikov/tree/bc84e0d206e15c4127a06ee42b63966a46016276/002_junior/006_ood/src/main/java/ru/job4j
 *
 * @author Aleksei Sapozhnikov (vermucht@gmail.com)
 * @version 0.1
 * @since 0.1
 */
public class WrongInputException extends Exception {
    /**
     * Logger.
     */
    private static final Logger LOG = LogManager.getLogger(WrongInputException.class);

    /**
     * Constructs exception with message.
     *
     * @param message Message.
     */
    public WrongInputException(String message) {
        super(message);
    }
}