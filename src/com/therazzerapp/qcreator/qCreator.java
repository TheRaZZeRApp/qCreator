package com.therazzerapp.qcreator;

import java.io.File;
import java.net.URISyntaxException;
import java.util.LinkedList;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 1.0
 */
public class qCreator {
    public static String jarPath = "";
    public static void main(String[] args) {

        String jarPath = "C:\\";
        try {
            jarPath = qCreator.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            jarPath = jarPath.replace("qCreator.jar","");
        } catch (URISyntaxException e) {
            Utils.writeError("Error: Jar path not found!");
            e.printStackTrace();
        }

        if(args.length < 1){
            Utils.writeError("Parameter missing: No questions file specified!");
            System.exit(0);
        }
        if (args.length < 2){
            Utils.writeError("Parameter missing: No max filler amount specified!");
            System.exit(0);
        }

        File texTemplate = new File(jarPath +"texTemplate.tex");
        File fileQuestions = new File(args[0]);
        File fillerFile = new File(args[0]+".filler");

        Project project = new Project(FileManager.getQeustions(fileQuestions));
        Project filler = new Project(FileManager.getQeustions(fillerFile));

        Utils.generateTexFile(FileManager.getTexTemplate(texTemplate),Utils.getConnectedQuestionList(project,filler,Integer.parseInt(args[1])),jarPath);

    }
}
