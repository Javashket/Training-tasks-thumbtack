package net.thumbtack.school.elections.errors.election;

public class FileErrorCode {

    private String fileNotFound = "Указанного файла для записи не существует.";

    private String errorString;

    public FileErrorCode() {

    }

    public String getFileNotFound() {
        return fileNotFound;
    }

    public void setFileNotFound(String fileNotFound) {
        this.fileNotFound = fileNotFound;
    }

    public String getErrorString() {
        return errorString;
    }

    public FileErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }

}
