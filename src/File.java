import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class File {
    private String path;
    private String content;

    public File(String path) {
        this.path = path;
        setContent(path);
    }

    public void setContent(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder stringBuilder = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = reader.readLine();
            }

            reader.close();

            stringBuilder.insert(0, "{");
            stringBuilder.append("}");

            this.content = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getContent() {
        return content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
