package com.example.suren.randomusers.api;

import com.example.suren.randomusers.model.User;

import java.util.List;

public class UserResponse {
    private List<User> results;
    private Info info;

    public UserResponse() {
    }

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
