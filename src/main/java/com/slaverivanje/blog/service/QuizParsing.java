package com.slaverivanje.blog.service;

import com.slaverivanje.blog.domain.Quiz;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class QuizParsing {

    public static void main(String[] args) {

        /*Pristup source file-u i pravljenje stream-a,
        koji ce da posluzi kao parametar konstruktora scanner klase
         */
        ClassPathResource quizResource = new ClassPathResource("/static/quiz/unicorns.adoc");
        try(InputStream stream = quizResource.getInputStream();
            Scanner sc = new Scanner(stream)){
            Quiz quiz = new Quiz();
            String str;
            while(sc.hasNextLine()) //dokle god postoji nova linija teksta proveravati dole navedene "if" uslove
            {
                if(sc.findInLine(":title:").equals(":title:"))//ako nadje match u liniji tj. ako je naisao na "title" liniju
                {
                   //postaviti vrednost quiz.title koju cemo dobiti kada prosledimo ostatak linije posle ":title:" kojoj skidamo navodnike
                   str = sc.nextLine();
                   quiz.setTitle(str.substring(1,str.length()-1));
                }

                if(sc.findInLine(":url:").equals(":url:"))//ponavljamo isto, ovoga puta za ":url:"
                {
                    str = sc.nextLine();
                    quiz.setTitle(str.substring(1,str.length()-1));
                }

            }

            System.out.println("Posle petlje while");
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage(), ioe);
        }


    }


}
