package ru.otus.homework.providers.impl;

public class ResourceFileNameProvider {

    private final String questionsResource;
    private final String answersResource;
    private final String tasksResource;

    public ResourceFileNameProvider(String questionsResource, String answersResource, String tasksResource) {
        this.questionsResource = questionsResource;
        this.answersResource = answersResource;
        this.tasksResource = tasksResource;
    }
    public String getAnswersResource() {
        return answersResource;
    }

    public String getTasksResource() {
        return tasksResource;
    }

    public String getQuestionsResource() {
        return questionsResource;
    }

}
