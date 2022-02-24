package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
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
        Scanner in = new Scanner(System.in);
        List<String> botPhrases = readPhrases();
        List<String> rslLog = new ArrayList<>();
        boolean status = true;
        boolean run = true;
        while (run) {
            System.out.print("User: ");
            String userWord = in.nextLine();
            rslLog.add("user say's: " + userWord);
            String bot = botPhrases.get((int) (Math.random() * botPhrases.size()));
            switch (userWord) {
                case STOP -> {
                    status = false;
                }
                case CONTINUE -> {
                    status = true;
                    System.out.println("ChatBot: " + bot);
                    rslLog.add("bot answer's: " + bot);
                }
                case OUT -> {
                    saveLog(rslLog);
                    run = false;
                }
                default -> {
                    if (status) {
                        System.out.println("ChatBot: " + bot);
                        rslLog.add("bot answer's: " + bot);
                    }
                }
            }
        }
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            br.lines().forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("UTF-8"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./bot.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
