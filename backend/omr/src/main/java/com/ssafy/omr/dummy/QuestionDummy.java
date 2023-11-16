package com.ssafy.omr.dummy;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.repository.InterviewQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@RequiredArgsConstructor
@Component
public class QuestionDummy {

    private final InterviewQuestionRepository interviewQuestionRepository;

    private List<InterviewCategory> interviewCategories = new ArrayList<>() {
        {
            add(InterviewCategory.DATA_STRUCTURE);
            add(InterviewCategory.ALGORITHM);
            add(InterviewCategory.GIT);
            add(InterviewCategory.JAVA);
            add(InterviewCategory.COMPUTER_ARCHITECTURE);
            add(InterviewCategory.DATABASE);
        }
    };

    private List<String> contents = new ArrayList<>() {
        {
            add("스택과 큐의 차이점을 설명하고 사용되는 예시를 설명하세요");
            add("Bubble sort, Insertion sort, Quick sort를 설명하고 시간복잡도를 설명하세요");
            add("git의 merge와 rebase의 차이를 설명하세요");
            add("Garbage Collector의 동작 방식에 대해 설명하세요");
            add("싱글톤에 대해 설명해보세요");
            add("Transaction Isolation Level에서 read committed에 대해 설명해보세요");
        }
    };

    public List<InterviewQuestion> createInterviewQuestionDummies() {
        List<InterviewQuestion> interviewQuestions = new ArrayList<>();

        for(int i = 0; i < contents.size(); i++) {
            interviewQuestions.add(InterviewQuestion.builder()
                    .interviewCategory(interviewCategories.get(i))
                    .content(contents.get(i))
                    .build());
        }

        interviewQuestionRepository.saveAll(interviewQuestions);
        return interviewQuestions;
    };
}
