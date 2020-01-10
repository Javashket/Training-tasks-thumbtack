package net.thumbtack.school.elections.dto.request;

public class ElectionDtoRequest {

    private String pathFileWrite;

    public ElectionDtoRequest() {
    }

    public ElectionDtoRequest(String pathFileWrite) {
        this.pathFileWrite = pathFileWrite;
    }

    public String getPathFileWrite() {
        return pathFileWrite;
    }

    public void setPathFileWrite(String pathFileWrite) {
        this.pathFileWrite = pathFileWrite;
    }
}
