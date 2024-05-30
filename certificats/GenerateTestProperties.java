import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GenerateTestProperties {

    public static final void main(String[] args) {

        try {
            String currentPath = "../certificats/";

            File baseDir = new File(".");

            File[] list = baseDir.listFiles();

            List<String> tests = new ArrayList<String>();

            for (File file : list) {
                if (file.isDirectory()) {

                    processDir(file, tests, currentPath + file.getName());
                }

            }

            int count = 1;

            StringBuilder sb = new StringBuilder();
            for (String test : tests) {
                sb.append(String.valueOf(count) + "=" + test + "\n");
                count++;
            }

            FileWriter fw = new FileWriter("./test.properties.sample");

            fw.write("\n");
            fw.write("tests=1\n");
            fw.write("\n");
            fw.write("# Tots els certificats\n");

            fw.write("#tests=");
            for (int i = 1; i < count; i++) {
                fw.write(i + ",");
            }
            fw.write("\n");
            fw.write("\n");

            fw.write(sb.toString());

            fw.flush();
            fw.close();
            
            System.out.println("S'ha generat un fitxer test.properties.sample. S'haura de renombrar a test.properties");

        } catch (Throwable th) {
            th.printStackTrace();
        }

    }

    public static void processDir(File baseDir, List<String> files, String currentPath) {

        File[] list = baseDir.listFiles();

        for (File file : list) {
            if (file.isDirectory()) {

                processDir(file, new ArrayList<String>(), currentPath + file.getName());
            } else {
                files.add(currentPath + "/" + file.getName());
            }
        }

    }

}