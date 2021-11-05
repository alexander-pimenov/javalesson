package ru.pimalex;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class CandidateFromCSV {
    //Email,First Name,Middle Name,Last Name,Birthdate,
    // Company,Role,Address,City,Country,PostalCode,
    // School,Field of study,Grade,Skills,Links,
    // I agree to give to Yandex Company my survey,
    // I want receive information about vacancies,
    // UID,Yandex login,Profile Tags,Global Tags

//    @JsonProperty("Email")
    private List<String> emails;
    private String firstName;
    private List<String> middleNames;
    private String lastName;
    private String birthdate;
    private String company;
    private String role;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String school;
    private String fieldOfStudy;
    private String grade;
    private List<String> skills;
    private List<String> links;
    private String iAgreeToGiveToYandexCompanyMySurvey;
    private String iWantReceiveInformationAboutVacancies;
    private String uid;
    private String yandexLogin;
    private List<String> profileTags;
    private List<String> globalTags;

    @Builder
    public CandidateFromCSV(List<String> emails, String firstName, List<String> middleNames, String lastName, String birthdate,
                            String company, String role, String address, String city, String country,
                            String postalCode, String school, String fieldOfStudy, String grade, List<String> skills,
                            List<String> links, String iAgreeToGiveToYandexCompanyMySurvey,
                            String iWantReceiveInformationAboutVacancies, String uid, String yandexLogin,
                            List<String> profileTags, List<String> globalTags) {
        this.emails = emails;
        this.firstName = firstName;
        this.middleNames = middleNames;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.company = company;
        this.role = role;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.school = school;
        this.fieldOfStudy = fieldOfStudy;
        this.grade = grade;
        this.skills = skills;
        this.links = links;
        this.iAgreeToGiveToYandexCompanyMySurvey = iAgreeToGiveToYandexCompanyMySurvey;
        this.iWantReceiveInformationAboutVacancies = iWantReceiveInformationAboutVacancies;
        this.uid = uid;
        this.yandexLogin = yandexLogin;
        this.profileTags = profileTags;
        this.globalTags = globalTags;
    }


}
