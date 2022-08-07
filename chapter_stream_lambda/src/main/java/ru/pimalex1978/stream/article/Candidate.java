package ru.pimalex1978.stream.article;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
 * сериализация - объект -> строка json
 * десериализация - строка json -> объект
 * @JsonAutoDetect - Ставится перед классом. Помечает класс как готовый к сериализации в JSON.
 * @JsonAutoDetect используется для переопределения семантики по умолчанию о том, какие свойства являются видимыми, а какие нет.
 * @JsonIgnore - Ставится перед свойством. Свойство игнорируется при сериализации.
 * @JsonProperty	- Ставится перед свойством или getter’ом или setter’ом. Позволяет задать другое имя поля при сериализации.
 * @JsonProperty - используется для указания имени свойства в JSON.
 * @JsonPropertyOrder - Ставится перед классом. Позволяет задать порядок полей для сериализации.
 * @JsonGetter - аннотации является альтернативой @JsonProperty аннотации, который отмечает в качестве способа геттера.
 * при сериализации можно задать другое имя полю.
 * @JsonSetter - при десериализации
 * @JsonRawValue  - аннотаций для сериализации свойства точно так как это написано.
 * @JsonIgnoreProperties(ignoreUnknown = true) - любые поля, не связанные с полями класса, должны быть проигнорированы.
 * @JsonAnySetter аннотация указывает Джексону вызвать один и тот же метод установки
 * для всех нераспознанных полей в объекте JSON. Под «нераспознанными» подразумеваются все поля,
 * которые еще не сопоставлены со свойством или методом установки в объекте Java.
 * @JsonAnyGetter Аннотацию Джексон позволяет использовать Map в качестве контейнера для свойств,
 * которые вы хотите сериализовать в формате JSON.
 * @JsonInclude используется для исключения свойств с пустыми / нулевыми / значениями по умолчанию (исключение нулей из сериализации).
 * @JsonFormat можно использовать для указания формата при сериализации значений даты и времени.
 * @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
 * @JsonUnwrapped используется для определения значений, которые следует развернуть / развернуть при сериализации / десериализации.
 *
 *
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthday;
    private String login;
    private Integer femidaId;
    private String gender;
    private String country;
    private String city;

    private boolean isActive;
    private String type;
    private boolean isMain;
    private String normalizedAccountId;


    //    @JsonProperty("first_name")
    @JsonGetter("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //    @JsonProperty("last_name")
    @JsonGetter("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //    @JsonProperty("middle_name")
    @JsonGetter("middleName")
    public String getMiddleName() {
        return middleName;
    }

    @JsonProperty("middle_name")
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @JsonProperty("birthday")
//    @JsonGetter("Birthday")
    public LocalDate getBirthday() {
        return birthday;
    }

    @JsonProperty("birthday")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @JsonProperty("login")
//    @JsonGetter("Login")
    public String getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    @JsonProperty("femida_id")
//    @JsonGetter("Femida id")
    public Integer getFemidaId() {
        return femidaId;
    }

    @JsonProperty("femida_id")
    public void setFemidaId(Integer femidaId) {
        this.femidaId = femidaId;
    }

    @JsonProperty("gender")
//    @JsonGetter("Gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "Candidate{"
                + "firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", middleName='" + middleName + '\''
                + ", birthday=" + birthday
                + ", login='" + login + '\''
                + ", femidaId=" + femidaId
                + ", gender='" + gender + '\''
                + ", country='" + country + '\''
                + ", city='" + city + '\''
                + '}';
    }
}