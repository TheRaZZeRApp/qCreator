package com.therazzerapp.qcreator;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 1.0
 */
public class Question {
    private int profectNumber;
    private int sentenceNumber;
    private int versionNumber;
    private String setupLine;
    private String replyLine;

    public Question(int profectNumber, int sentenceNumber, int versionNumber, String setupLine, String replyLine) {
        this.profectNumber = profectNumber;
        this.sentenceNumber = sentenceNumber;
        this.versionNumber = versionNumber;
        this.setupLine = setupLine;
        this.replyLine = replyLine;
    }

    public String getRawQuestion(){
        return "" + profectNumber + "\t" + sentenceNumber + "\t" + versionNumber + "\t" + setupLine + "!!! " + replyLine;
    }

    public String getSetupLine() {
        return setupLine;
    }

    public String getReplyLine() {
        return replyLine;
    }

    public int getProfectNumber() {
        return profectNumber;
    }

    public int getSentenceNumber() {
        return sentenceNumber;
    }

    public int getVersionNumber() {
        return versionNumber;
    }
}
