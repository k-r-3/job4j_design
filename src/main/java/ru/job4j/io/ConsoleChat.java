package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> answers;
    private final String path;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        try (BufferedReader br = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            answers = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        List<String> chat = new ArrayList<>();
        System.out.println("/////////Chat//////////");
        String question = sc.nextLine();
        do {
            if (question.equals(STOP)) {
                while (!question.equals(CONTINUE)) {
                    chat.add(String.format("%s\n", question));
                    System.out.println("--------");
                    question = sc.nextLine();
                    if (question.equals(OUT)) {
                        chat.add(question);
                        break;
                    }
                }
            } else {
                chat.add(String.format("%s\n", botReader(question)));
                question = sc.nextLine();
            }
        } while (!question.equals(OUT));
        chat.add(String.format("%s\n", question));
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            for (String e : chat) {
                writer.write(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String botReader(String question) {
        Random rand = new Random();
        int line = rand.nextInt(5) + 1;
        String answer = answers.stream()
                .filter(s -> s.matches(String.valueOf(line) + ".*"))
                .findFirst()
                .get()
                .split("\\d")[1];
        System.out.println(answer);
        return String.format("%s\r\n-%s", question, answer);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/chat/chatWithBot.txt",
                "./src/chat/botAnswers.txt");
        cc.run();
    }
}