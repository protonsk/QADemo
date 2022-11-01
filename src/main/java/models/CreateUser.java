package models;

import lombok.Data;

@Data
public class CreateUser {
    private String name;
    private String job;
    private String id;
    private String createdAt;
    private String email;
    private String error;
    private String updatedAt;

    public CreateUser() {
    }

    public CreateUser(String name, String job) {
        this.name = name;
        this.job = job;
    }
}
