package net.thumbtack.school.file;

import com.google.gson.Gson;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.figures.v3.ColoredRectangle;
import net.thumbtack.school.figures.v3.Rectangle;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;

public class FileService {

    public static void  writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFile(new File(fileName), array);
    }

    public static void  writeByteArrayToBinaryFile(File file, byte[] array) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        fileOutputStream.write(array);
        fileOutputStream.close();
    }

    public static byte[]  readByteArrayFromBinaryFile(String fileName) throws IOException {
       return readByteArrayFromBinaryFile(new File(fileName));
    }

    public static byte[]  readByteArrayFromBinaryFile(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    public static byte[]  writeAndReadByteArrayUsingByteStream( byte[] array) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(array);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        byte[] array1 = new byte[Math.round(array.length / 2)];
        for (int i = 0, j = 0; i < array.length; i++) {
            if(i % 2 == 0) {
                array1[j] = (byte) byteArrayInputStream.read();
                j++;
            } else {
                byteArrayInputStream.read();
            }
        }
        byteArrayInputStream.close();
        byteArrayOutputStream.close();
        return array1;
    }

    public static void  writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFileBuffered(new File(fileName), array);
    }

    public static void  writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(array);
        bufferedOutputStream.close();
        fileOutputStream.close();
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
        return readByteArrayFromBinaryFileBuffered( new File(fileName));
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
        byte[] array = Files.readAllBytes(file.toPath());;
        return array;
    }

    public static void  writeRectangleToBinaryFile(File file, Rectangle rect) throws IOException {
        writeByteArrayToBinaryFile(file, ByteBuffer.allocate(4).putInt(rect.getTopLeft().getX()).array());
        writeByteArrayToBinaryFile(file, ByteBuffer.allocate(4).putInt(rect.getTopLeft().getY()).array());
        writeByteArrayToBinaryFile(file, ByteBuffer.allocate(4).putInt(rect.getBottomRight().getX()).array());
        writeByteArrayToBinaryFile(file, ByteBuffer.allocate(4).putInt(rect.getBottomRight().getY()).array());
    }

    public static Rectangle  readRectangleFromBinaryFile(File file) throws IOException {
        ByteBuffer wrapped = ByteBuffer.wrap(readByteArrayFromBinaryFile(file));
        return new Rectangle(wrapped.getInt(), wrapped.getInt(), wrapped.getInt(), wrapped.getInt());
    }

    public static void  writeColoredRectangleToBinaryFile(File file, ColoredRectangle rect) throws IOException {
        writeRectangleToBinaryFile(file, rect);
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
        dataOutputStream.writeUTF(rect.getColor().name());
        dataOutputStream.close();
        fileOutputStream.close();
    }

    public static ColoredRectangle  readColoredRectangleFromBinaryFile(File file) throws IOException, ColorException {
        Rectangle rectangle = readRectangleFromBinaryFile(file);
        byte[] array = Files.readAllBytes(file.toPath());
        byte[] color = new byte[(int)file.length() - 18];
        for(int i = 0, j = 18; i < color.length; i++, j++) {
            color[i] = array[j];
        }
        String colorText = new String(color);
        return  new ColoredRectangle(rectangle.getTopLeft(), rectangle.getBottomRight(), colorText);
    }

    public static void  writeRectangleArrayToBinaryFile(File file, Rectangle[] rects ) throws IOException {
        for (Rectangle rect : rects) {
            writeRectangleToBinaryFile(file, rect);
        }
    }

    public static Rectangle[]  readRectangleArrayFromBinaryFileReverse(File file) throws IOException {
        Rectangle[] rectangles = new Rectangle[(int)file.length() / 16];
        ByteBuffer wrapped = ByteBuffer.wrap(readByteArrayFromBinaryFile(file));
        for(int i = 0; i < rectangles.length; i++) {
           rectangles[rectangles.length - i - 1] = new Rectangle(wrapped.getInt(),
                   wrapped.getInt(), wrapped.getInt(), wrapped.getInt());
        }
        return rectangles;
    }

    public static void  writeRectangleToTextFileOneLine(File file, Rectangle rect) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(rect.getTopLeft().getX() + " " + rect.getTopLeft().getY() + " "
                + rect.getBottomRight().getX() + " " + rect.getBottomRight().getY());
        fileWriter.close();
    }

    public static Rectangle  readRectangleFromTextFileOneLine(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String text = bufferedReader.readLine();
        String[] array = text.split(" ");
        bufferedReader.close();
        fileReader.close();
        return new Rectangle(Integer.parseInt(array[0]), Integer.parseInt(array[1]),
                Integer.parseInt(array[2]), Integer.parseInt(array[3]));
    }

    public static void  writeRectangleToTextFileFourLines(File file, Rectangle rect) throws IOException{
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(rect.getTopLeft().getX() + "\n" + rect.getTopLeft().getY() + "\n"
                + rect.getBottomRight().getX() + "\n" + rect.getBottomRight().getY());
        fileWriter.close();
    }

    public static Rectangle  readRectangleFromTextFileFourLines(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String[] array = {bufferedReader.readLine(), bufferedReader.readLine(),
                        bufferedReader.readLine(), bufferedReader.readLine()};
        bufferedReader.close();
        fileReader.close();
        return new Rectangle(Integer.parseInt(array[0]), Integer.parseInt(array[1]),
                Integer.parseInt(array[2]), Integer.parseInt(array[3]));
    }

    public static void  writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(trainee.getFullName() + " " + trainee.getRating());
        fileWriter.close();
    }

    public static Trainee  readTraineeFromTextFileOneLine(File file) throws IOException, TrainingException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String text = bufferedReader.readLine();
        String[] array = text.split(" ");
        bufferedReader.close();
        fileReader.close();
        return new Trainee(array[0], array[1], Integer.parseInt(array[2]));
    }

    public static void  writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(trainee.getFirstName() + "\n" + trainee.getLastName() + "\n" + trainee.getRating());
        fileWriter.close();
    }

    public static Trainee  readTraineeFromTextFileThreeLines(File file) throws IOException, TrainingException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String[] array = {bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine()};
        bufferedReader.close();
        fileReader.close();
        return new Trainee(array[0], array[1], Integer.parseInt(array[2]));
    }

    public static void  serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        String str = new Gson().toJson(trainee);
        fileOutputStream.write(str.getBytes());
        fileOutputStream.close();
    }

    public static Trainee  deserializeTraineeFromBinaryFile(File file) throws IOException {
        byte[] array = Files.readAllBytes(file.toPath());
        return new Gson().fromJson(new String(array), Trainee.class);
    }

    public static String  serializeTraineeToJsonString(Trainee trainee) {
        return new Gson().toJson(trainee);
    }

    public static Trainee  deserializeTraineeFromJsonString(String json) {
        return new Gson().fromJson(json,Trainee.class);
    }

    public static void  serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        String str = new Gson().toJson(trainee);
        fileOutputStream.write(str.getBytes());
        fileOutputStream.close();
    }

    public static Trainee  deserializeTraineeFromJsonFile(File file) throws IOException {
        byte[] array = Files.readAllBytes(file.toPath());
        return new Gson().fromJson(new String(array), Trainee.class);
    }
}
