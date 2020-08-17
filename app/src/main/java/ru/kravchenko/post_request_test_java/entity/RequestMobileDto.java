package ru.kravchenko.post_request_test_java.entity;

public class RequestMobileDto {

    private String codeActivate;

    private String token;

    public RequestMobileDto() {
    }

    public RequestMobileDto(String codeActivate, String token) {
        this.codeActivate = codeActivate;
        this.token = token;
    }

    public String getCodeActivate() {
        return codeActivate;
    }

    public void setCodeActivate(String codeActivate) {
        this.codeActivate = codeActivate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
