package ru.job4j.tdd;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import ru.job4j.tdd.exceptions.ImpossibleDateException;
import ru.job4j.tdd.exceptions.MoneyNotEnoughException;
import ru.job4j.tdd.exceptions.TicketCollisionException;
import ru.job4j.tdd.exceptions.TicketWrongException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void add() {
        Cinema cinema = new Cinema3D();
        cinema.add(new SessionIMax());
        assertThat(!(List.of(cinema.find(session -> true)).isEmpty()), is(true));
    }

    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test(expected = TicketCollisionException.class)
    public void whenTicketCollision() {
        Account accountFirst = new AccountCinema();
        Account accountSecond = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticketFirst = cinema.buy(accountFirst, 1, 1, date);
        Ticket ticketSecond = cinema.buy(accountSecond, 1, 1, date);
    }

    @Test(expected = TicketWrongException.class)
    public void whenWrongTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, -1, 1, date);
    }

    @Test(expected = MoneyNotEnoughException.class)
    public void whenNotMoney() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

    @Test(expected = ImpossibleDateException.class)
    public void whenImpossDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }
}