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

    public String[] convert(File file) throws IOException {

        Path path = Paths.get(file.getAbsolutePath());
        byte[] data = Files.readAllBytes(path);

        String request = "https://www.google.com/"
                + "speech-api/v1/recognize?"
                + "xjerr=1&client=speech2text&lang=en-EN&maxresults=1";

        System.out.println("ANALYSIS STARTED");
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

        System.out.println("ANALYSIS STOPPED");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                connection.getInputStream()));
        String decodedString;
        String responseString = "";
        while ((decodedString = in.readLine()) != null) {
            responseString += decodedString;
        }

        System.out.println(responseString);
        
        return parse(responseString);
    }

    private String[] parse(String in) {

        String[] response = null;

        String[] resp = in.split("utterance\":\"");
        if (resp.length > 1) {
            resp = resp[1].split("\",\"confidence");
            response = resp[0].split(" ");
        } else {
            response = null;
        }
        return response;
    }
}
