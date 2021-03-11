package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleChat {
    private static final Logger LOG = LoggerFactory.getLogger(ConsoleChat.class.getName());
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> answers;
    private List<String> chat = new ArrayList<>();
    private final String path;
    private final String botPath;

    public ConsoleChat(String path, String botPath) {
        this.path = path;
        this.botPath = botPath;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("/////////Chat//////////");
        String question = sc.nextLine();
        do {
            String answer = botAnswer();
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
                System.out.println(answer);
                chat.add(String.format("%s\r%s\n", question, answer));
                question = sc.nextLine();
            }
        } while (!question.equals(OUT));
        chat.add(String.format("%s\n", question));
    }

    public void readBot() {
        try (BufferedReader br = new BufferedReader(
                new FileReader(botPath, Charset.forName("WINDOWS-1251")))) {
            answers = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            LOG.error("wrong bot file", e);
        }
    }

    public void writeChat() {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            for (String e : chat) {
                writer.write(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("write chat exception", e);
        }
    }

    private String botAnswer() {
        return answers.get(new Random().nextInt(answers.size()));
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/chat/chatWithBot.txt",
                "./src/chat/botAnswers.txt");
        cc.readBot();
        cc.run();
        cc.writeChat();
    }
}