package com.sharifdev.torobche.model;

import java.util.List;

/**
 * A Quiz
 */
public class Quiz {
    private QuizType type;
    private List<String> categories;

}

enum QuizType{
    SINGLE_PLAYER,
    MULTI_PLAYER,
    GROUP_GAME
}
