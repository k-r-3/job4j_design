package ru.job4j.tdd.exceptions;

public class TicketCollisionException extends Exception {
    public TicketCollisionException(String message) {
        super(message);
    }
}
