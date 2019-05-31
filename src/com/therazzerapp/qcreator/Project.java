package com.therazzerapp.qcreator;

import java.util.LinkedList;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 1.0
 */
public class Project {
    private int projectNumber;
    private int sentences;
    private int versions;
    private LinkedList<Question> questions;
    private LinkedList<LinkedList<Question>> commutedQuestions;

    public Project(LinkedList<Question> questions) {
        if (questions.size() > 0){
            projectNumber = questions.get(questions.size()-1).getProjectNumber();
            sentences = questions.get(questions.size()-1).getSentenceNumber();
            versions = questions.get(questions.size()-1).getVersionNumber();
        }
        this.questions = questions;
        if (projectNumber != 0){
            this.commutedQuestions = Utils.getCommutedQuestionList(this);
        }
    }

    public LinkedList<Question> getQuestionsByVersion (int version){
        LinkedList<Question> questions = new LinkedList<>();
        for (Question question : this.questions) {
            if (question.getVersionNumber() == version){
                questions.add(question);
            }
        }
        return questions;
    }

    public LinkedList<Question> getQuestionsBySentence (int sentence){
        LinkedList<Question> questions = new LinkedList<>();
        for (Question question : this.questions) {
            if (question.getSentenceNumber() == sentence){
                questions.add(question);
            }
        }
        return questions;
    }

    public Question getQuestion (int sentence, int version){
        for (Question question : this.questions) {
            if (question.getSentenceNumber() == sentence && question.getVersionNumber() == version){
                return question;
            }
        }
        return null;
    }

    public int getProjectNumber() {
        return projectNumber;
    }

    public int getSentences() {
        return sentences;
    }

    public int getVersions() {
        return versions;
    }

    public LinkedList<Question> getQuestions() {
        return questions;
    }

    public boolean isFiller(){
        return projectNumber == 0;
    }

    /**
     * Returns projects questions commuted by {@link Utils} getCommutedQuestionList
     *
     * @return  null if project contains filler
     */
    public LinkedList<LinkedList<Question>> getCommutedQuestions() {
        return commutedQuestions;
    }
}
