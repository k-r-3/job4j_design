package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String question = "";
        System.out.println("/////////Chat//////////");
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            do {
                question = sc.nextLine();
                if (question.equals(STOP)) {
                    while (!question.equals(CONTINUE)) {
                        writer.write(String.format("%s\n", question));
                        System.out.println("--------");
                        question = sc.nextLine();
                        if (question.equals(OUT)) {
                            writer.write(question);
                            System.exit(0);
                        }
                    }
                } else {
                    writer.write(String.format("%s\n", botReader(question)));
                }
            } while (!question.equals(OUT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String botReader(String question) {
        String answer = "";
        try (BufferedReader br = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            Random rand = new Random();
            int line = rand.nextInt(5) + 1;
            answer = br.lines()
                    .filter(s -> s.matches(String.valueOf(line) + ".*"))
                    .findFirst()
                    .get()
                    .split("\\d")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(answer);
        return String.format("%s\r\n-%s", question, answer);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/chat/chatWithBot.txt",
                "./src/chat/botAnswers.txt");
        cc.run();
    }
}