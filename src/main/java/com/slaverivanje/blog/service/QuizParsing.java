package com.slaverivanje.blog.service;

import com.slaverivanje.blog.domain.Quiz;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class QuizParsing {

    public static void main(String[] args) {

        ClassPathResource quizResource = new ClassPathResource("/static/quiz/unicorns.adoc");
        try(InputStream stream = quizResource.getInputStream();
            Scanner sc = new Scanner(stream)){
            Quiz quiz = new Quiz();
            String str;
            while(sc.hasNextLine()){
                str = sc.next();
                if (str!=null && str.equals(":title:")){
                   str = sc.nextLine();
                   quiz.setTitle(str.substring(2,str.length()-1));
                   continue;
                }
                if(str!=null && str.equals(":url:")){
                    str = sc.nextLine();
                    quiz.setUrl(str.substring(2,str.length()-1));
                    continue;
                }
                sc.nextLine();
            }
            System.out.println(quiz);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage(), ioe);
        }
    }
}
