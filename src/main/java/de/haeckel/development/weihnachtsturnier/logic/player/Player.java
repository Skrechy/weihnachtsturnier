package de.haeckel.development.weihnachtsturnier.logic.player;

import java.io.Serializable;

/**
 * Created by Timo Haeckel on 09.12.2016.
 */
public class Player implements Serializable{

    private int handicap = 0;

    private String lastName = "Unknown";

    private String firstName = "Unknown";

    private Group group = Group.UNKNOWN;

    private Gender gender = Gender.UNKNOWN;

    public int getHandicap() {
        return handicap;
    }

    public Player setHandicap(int handicap) {
        this.handicap = handicap;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Player setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Player setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Group getGroup() {
        return group;
    }

    public Player setGroup(Group group) {
        this.group = group;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public Player setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (!getLastName().equals(player.getLastName())) return false;
        return getFirstName().equals(player.getFirstName());
    }

    @Override
    public int hashCode() {
        int result = getLastName().hashCode();
        result = 31 * result + getFirstName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "handicap=" + handicap +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", group=" + group +
                ", gender=" + gender +
                '}';
    }
}
