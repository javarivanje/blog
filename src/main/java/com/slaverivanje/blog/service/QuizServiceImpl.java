package com.slaverivanje.blog.service;

import com.slaverivanje.blog.domain.Correct;
import com.slaverivanje.blog.domain.Question;
import com.slaverivanje.blog.domain.Quiz;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class QuizServiceImpl implements IQuizService {

    private Quiz quiz = new Quiz();

    @Override
    public Quiz findByUrl(String url) {
        ClassPathResource quizResource = new ClassPathResource("/static/quiz/" + url + ".adoc");
        return parse(quizResource);
    }

    private Quiz parse(ClassPathResource quizResource) {
        // TODO: ovde treba da dodas logiku
        Quiz quiz = new Quiz();
        try(InputStream stream = quizResource.getInputStream();
            Scanner sc = new Scanner(stream)){

            List<Question> pitanja = new ArrayList<>();
            String str;
            String [] stringNiz;
            while(sc.hasNextLine()){
                str = sc.next();
                if (str!=null && str.equals(":title:")){
                    str = sc.nextLine();
                    quiz.setTitle(str.substring(2,str.length()-1));
                }
                if(str!=null && str.equals(":url:")){
                    str = sc.nextLine();
                    quiz.setUrl(str.substring(2,str.length()-1));
                }
                if(str.equals("___")){
                    sc.nextLine();
                    long brojac = 0;
                    Correct correct = new Correct();
                    Question questions = new Question();
                    List <String> answers = new ArrayList<>();
                    str = sc.nextLine();
                    questions.setPrompt(str.substring(0, str.length()-2));
                    while(!sc.hasNext("___")) {
                        if(!sc.hasNextLine()){
                            break;
                        }
                        brojac++;
                        str = sc.nextLine();
                        stringNiz = str.split("::");
                        if(stringNiz.length==2){
                            answers.add(stringNiz[0].substring(1,stringNiz[0].length()-1));
                            correct.setIndex(brojac-1);
                            correct.setText(stringNiz[1].substring(1,stringNiz[1].length()-1));
                        } else answers.add(str.substring(1,str.length()-1));
                    }
                    questions.setAnswers(answers);
                    questions.setCorrect(correct);
                    pitanja.add(questions);
                }
            }
            quiz.setQuestions(pitanja);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage(), ioe);
        }
        return quiz;
    }

    @Override
    public Question answerQuestion(String url, Long questionNumber, Long answerIndex) {
        if(quiz.getUrl().equals(url)) {
            return (Question) quiz.getQuestions(questionNumber);
        }
        else System.out.println("Answer does not exist!");
            return null;//return null;
    }

}
