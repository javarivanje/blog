package com.slaverivanje.blog.service;

import com.slaverivanje.blog.domain.Quiz;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class QuizParsing {

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        ClassPathResource quizResource = new ClassPathResource("/static/quiz/unicorns.adoc");
        try(InputStream stream = quizResource.getInputStream();
            Scanner sc = new Scanner(stream)){

            while(sc.hasNextLine()){
                if(sc.findInLine(":title:").equals(":title:")){
                    System.out.println("Uspesno!:)");
                }
                sc.nextLine();
            }

            System.out.println("Posle petlje while");
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage(), ioe);
        }


    }

}
