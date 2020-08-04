package com.sharifdev.torobche.model;

import com.parse.ParseObject;

/**
 * A Question
 */
public class Question {
    String text;
    ParseObject[] choices = new ParseObject[]{};
    String topic;

    public Question(String text, ParseObject choice1,
                    ParseObject choice2,
                    ParseObject choice3,
                    ParseObject choice4,
                    String topic) {
        this.text = text;
        this.topic = topic;
        this.choices = new ParseObject[]{
                choice1,
                choice2,
                choice3,
                choice4
        };
    }

    public String getText() {
        return text;
    }

    public String getTopic() {
        return topic;
    }
}
