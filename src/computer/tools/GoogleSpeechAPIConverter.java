/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.tools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author stk
 */
public class GoogleSpeechAPIConverter {

    public GoogleSpeechAPIConverter() {
    }

    public String convert(File file) throws IOException {

        Path path = Paths.get(file.getAbsolutePath());
        byte[] data = Files.readAllBytes(path);

        String request = "https://www.google.com/"
                + "speech-api/v1/recognize?"
                + "xjerr=1&client=speech2text&lang=en-US&maxresults=10";

        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "audio/x-flac; rate=16000");
        connection.setRequestProperty("User-Agent", "speech2text");
        connection.setConnectTimeout(60000);
        connection.setUseCaches(false);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.write(data);
        wr.flush();
        wr.close();
        connection.disconnect();

        System.out.println("Done");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                connection.getInputStream()));
        String decodedString;
        while ((decodedString = in.readLine()) != null) {
            System.out.println(decodedString);
        }

        return decodedString;
    }
}
