package com.therazzerapp.qcreator;

import java.io.*;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <description>
 * 
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 1.0
 */
public class FileManager {
    public static LinkedList<Question> getQeustions(File file){
        BufferedReader bufferedReader;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader;
        LinkedList<Question> questions = new LinkedList<>();

        final String questionRegEx = "(?<pN>[0-9]{1,}) (?<sN>[0-9]{1,}) (?<vN>[0-9]{1,}) (?<sQ>[^!!!]{1,})!!! (?<qQ>.{1,})";
        Matcher matcher;

        try{
            inputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(inputStream,Charset.forName("Cp1252"));
            bufferedReader = new BufferedReader(inputStreamReader);

            String text = bufferedReader.readLine();
            while (text != null){

                matcher = Pattern.compile(questionRegEx).matcher(text);
                Question question;

                if (!text.equals("\n")){
                    while (matcher.find()){
                        try{
                            question = new Question(
                                    Integer.parseInt(matcher.group("pN")),
                                    Integer.parseInt(matcher.group("sN")),
                                    Integer.parseInt(matcher.group("vN")),
                                    matcher.group("sQ"),
                                    matcher.group("qQ"));
                            questions.add(question);
                        } catch (NumberFormatException nfe){
                        }
                    }
                    text = bufferedReader.readLine();
                }
            }
        } catch (IOException ioe){
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ioe) {
            }
        }
        return questions;
    }

    public static String getTexTemplate(File file){
        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader bufferedReader;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader;

        try{
            inputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(inputStream,Charset.forName("Cp1252"));
            bufferedReader = new BufferedReader(inputStreamReader);
            String text = bufferedReader.readLine();
            while (text != null){
                stringBuilder.append(text + "\n");
                text = bufferedReader.readLine();
            }
        } catch (IOException ioe){
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ioe) {
            }
        }
        return stringBuilder.toString();
    }

    public static void exportTexFile(String texCode, String fileName){

        File file = new File(fileName);
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),"ISO-8859-1"));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
        }
        try {
            file.createNewFile();
            writer.write(texCode);
        } catch (IOException e) {
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
            }
        }
    }
}
