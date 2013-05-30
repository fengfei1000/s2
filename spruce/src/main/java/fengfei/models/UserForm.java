package fengfei.models;

import java.io.Serializable;

public class UserForm extends UserPwdForm implements Serializable {

    private static final long serialVersionUID = 1L;
    public String firstName;
    public String lastName;
    public String niceName;
    public String birthday;
    public Integer gender = 0;
    public String phoneNum;
    public String about;
    public String city;
    public String state;
    public String country;

    public UserForm(
        Integer idUser,
        String userName,
        String email,
        String firstName,
        String lastName,
        String birthday,
        Integer gender,
        String phoneNum,
        String about,
        String city,
        String state,
        String country) {
        super(idUser, userName, email, null);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.about = about;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public UserForm(
        Integer idUser,
        String userName,
        String email,
        String firstName,
        String lastName,
        String birthday,
        Integer gender,
        String phoneNum,
        String about,
        String city,
        String state,
        String country,
        int createAt,
        long updateAt) {
        super(idUser, userName, email, null);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.about = about;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public UserForm() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNiceName() {
        return niceName;
    }

    public void setNiceName(String niceName) {
        this.niceName = niceName;
    }

}
