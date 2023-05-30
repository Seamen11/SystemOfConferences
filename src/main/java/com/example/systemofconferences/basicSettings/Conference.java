package com.example.systemofconferences.basicSettings;

import jakarta.persistence.*;

@Entity
@Table(name = "conference")
public class Conference {
    @Column(name = "id")
    private Long id; // ID
    @Column(name = "organizer")
    private String organizer;
    @Column(name = "leader")
    private String leader;
    @Column(name = "date_of_start")
    private String date_of_start;
    @Column(name = "city")
    private String city;
    @Column(name = "date_of_finish")
    private String date_of_finish;

    protected Conference() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getDate_of_start() {
        return date_of_start;
    }

    public void setDate_of_start(String date_of_start) {this.date_of_start = date_of_start; }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate_of_finish() {
        return date_of_finish;
    }

    public void setDate_of_finish(String date_of_finish) {
        this.date_of_finish = date_of_finish;
    }

    @Override
    public String toString() {
        return "Logistic = [id=" + id + ", organizer=" + organizer + ", leader=" + leader + ", date_of_start=" + date_of_start + ", city=" + city + ", date_of_finish=" + date_of_finish + "]";
    }
}








