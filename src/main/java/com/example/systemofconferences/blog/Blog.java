package com.example.systemofconferences.blog;

import jakarta.persistence.*;

@Entity
@Table(name = "blog")
public class Blog {
    private Long post_id;
    private String post_name;
    private String publish_date;
    private String text;
    private String client_name;

    public Blog() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    @Override
    public String toString() {
        return "post [post_id=" + post_id + ", post_name=" + post_name + ", publish_date=" + publish_date + ", text=" + text + ", client_name=" + client_name + "]";
    }
}



