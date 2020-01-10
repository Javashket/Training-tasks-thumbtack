package net.thumbtack.school.elections.dto.response;

public class ElectionDtoResponse {

    private String result;

    public ElectionDtoResponse() {
    }

    public ElectionDtoResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
