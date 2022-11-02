package models;

import lombok.Data;

@Data
public class CreateUser {
    private String id;
    private String email;
    private String name;
    private String job;
    private String createdAt;
    private String updatedAt;
    private String error;

    public CreateUser() {
    }

    public CreateUser(String name, String job) {
        this.name = name;
        this.job = job;
    }
}
