package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> rsl = new ArrayList<>();
        StringBuilder tmp = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                String[] temp = line.split(" ");
                if (tmp.isEmpty() && (Integer.parseInt(temp[0]) > 300)) {
                    tmp.append(temp[1] + ";");
                } else if (!tmp.isEmpty() && !((Integer.parseInt(temp[0]) > 300))) {
                    tmp.append(temp[1]);
                    rsl.add(tmp.toString());
                    tmp = new StringBuilder();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (String line : rsl) {
                out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        String source1 = "./data/log1.txt";
        String source2 = "./data/log2.txt";
        String target1 = "./data/target1.txt";
        String target2 = "./data/target2.txt";
        analizy.unavailable(source1, target1);
        analizy.unavailable(source2, target2);
    }
}
