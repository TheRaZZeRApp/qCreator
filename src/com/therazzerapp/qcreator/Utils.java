package com.therazzerapp.qcreator;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 1.0
 */
public class Utils {

    public static LinkedList<LinkedList<Question>> getCommutedQuestionList(Project project){
        LinkedList<LinkedList<Question>> commutedList = new LinkedList<>();
        for (int i = 0; i < project.getVersions(); i++) {
            LinkedList<Question> tempList = new LinkedList<>();
            for (int j = 0; j < project.getSentences();j++) {
                tempList.add(project.getQuestion(j+1,((j+i)%project.getVersions()+1)));
            }
            commutedList.add(tempList);
        }
        return commutedList;
    }

    /**
     *
     * @param questions
     * @param filler
     * @return          null if filler doesn't contain fillers
     */
    public static LinkedList<LinkedList<Question>>  getConnectedQuestionList(Project questions, Project filler, int maxFillerAmount){
        LinkedList<LinkedList<Question>>  mergedQuestions = new LinkedList<>();
        for (LinkedList<Question> questionLinkedList : questions.getCommutedQuestions()) {
            LinkedList<Question> tempMergedQuestions = new LinkedList<>();
            LinkedList<Question> fillerPool = new LinkedList<>();
            fillerPool.addAll(filler.getQuestions());
            int fillerCounter = 0;
            boolean questionAdded = false;
            while (!questionLinkedList.isEmpty()){
                int randomQuestion = (int )(Math. random() * questionLinkedList.size());
                int randomFiller= (int )(Math. random() * fillerPool.size());
                int randomCase= (int )(Math. random() * 2 + 1);
                if(questionAdded){
                    randomCase = 2;
                }
                if(fillerCounter == maxFillerAmount){
                    randomCase = 1;
                    fillerCounter = 0;
                }
                switch (randomCase){
                    case 1:
                        tempMergedQuestions.add(questionLinkedList.get(randomQuestion));
                        questionLinkedList.remove(randomQuestion);
                        questionAdded = true;
                        break;
                    case 2:
                        try{
                            tempMergedQuestions.add(fillerPool.get(randomFiller));
                        } catch (IndexOutOfBoundsException ex){
                            writeError("Index Out Of Bounds: Not enough filler!");
                            System.exit(0);
                        }
                        fillerPool.remove(randomFiller);
                        questionAdded = false;
                        fillerCounter++;
                        break;
                    default:
                        break;
                }
            }
            mergedQuestions.add(tempMergedQuestions);
        }
        return mergedQuestions;
    }

    public static void generateTexFile(String texTemplate, LinkedList<LinkedList<Question>> questions, String path){

        final String questionRegEx = "!QUESTIONS!\\n(?<qT>[^!]{1,})\\n";

        int index = 0;

        for (LinkedList<Question> questionLinkedList : questions) {

            Matcher matcher;
            String output = texTemplate;
            output = output.replace("!MARKRIGHT!", "Fragebogen-"+(index+1));

            matcher = Pattern.compile(questionRegEx).matcher(output);

            String questionTexTemplate = "";

            matcher.find();
            questionTexTemplate = matcher.group("qT");

            output = output.replace(questionTexTemplate,"");
            StringBuilder stringBuilder = new StringBuilder();

            for (Question question : questionLinkedList) {
                String temp = questionTexTemplate;

                temp = temp.replaceAll("SENTENCE",question.getSetupLine());
                temp = temp.replaceAll("PRO",question.getReplyLine());
                stringBuilder.append(temp);
                stringBuilder.append("\n");
            }

            output = output.replaceFirst("!QUESTIONS!","");
            output = output.replace("!QUESTIONS!", stringBuilder.toString());

            FileManager.exportTexFile(output,path + "Questionnaire-"+(index+1)+".tex");
            index++;
        }

    }

    public static void writeError(String error){
        FileManager.exportTexFile(error, qCreator.jarPath+"error.log");
    }
}