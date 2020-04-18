package ua.nure.hrynko.walletservice;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ua.nure.hrynko.walletservice.entities.SonarIssue;
import ua.nure.hrynko.walletservice.utils.DBUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class SonarRequestSender {
    public static void main(String[] args) throws IOException {
      //  CustomLogger.setup();
        //String commitHash = args[0];
        String commitHash = "439a0a40d5bffdfa38059deb212d3658ea79adf1";
        //String projectKey = args[1];
        String projectKey = "org.apache:ambari";
        getInfoFromSonarScan(commitHash, projectKey);
    }

    public static void getInfoFromSonarScan(String commitHash, String projectKey) {
        List<String> components = DBUtils.getComponentsOfCommit(commitHash);
        System.out.println(commitHash);
        components.forEach(component -> {
            try {
                getIssuesFromSonar(commitHash, projectKey, component);
                System.out.println(component);
            } catch (IOException | java.text.ParseException | ParseException e) {
             //   LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        });
    }

    private static void getIssuesFromSonar(String commit, String projectKey, String componentKey) throws IOException, ParseException,
            java.text.ParseException {
        URL urlForGetRequest =
                new URL(String.format("http://localhost:9000/api/issues/search?projectKeys=%s&componentKeys=%s",
                        projectKey, componentKey));

        String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();

            List<SonarIssue> sonarIssues = getSonarIssues(commit, response.toString());
            DBUtils.saveSonarIssues(sonarIssues);
        } else {
          //  LOGGER.severe("GIT request failed");
        }
    }

    private static List<SonarIssue> getSonarIssues(String commit, String JSONString) throws ParseException, java.text.ParseException {
        List<SonarIssue> sonarIssues = new ArrayList<>();
        Object object = new JSONParser().parse(JSONString);

        JSONObject jsonObject = (JSONObject) object;
        JSONArray issues = (JSONArray) jsonObject.get("issues");
        Iterator iterator = issues.iterator();
        while (iterator.hasNext()) {
            JSONObject issue = (JSONObject) iterator.next();
            SonarIssue sonarIssue = new SonarIssue(commit);
            sonarIssue.setKey(Objects.nonNull(issue.get("key")) ? issue.get("key").toString() : "");
            sonarIssue.setRule(Objects.nonNull(issue.get("rule")) ? issue.get("rule").toString() : "");
            sonarIssue.setSeverity(Objects.nonNull(issue.get("severity")) ? issue.get("severity").toString() : "");
            sonarIssue.setComponent(Objects.nonNull(issue.get("component")) ? issue.get("component").toString() : "");
            sonarIssue.setProject(Objects.nonNull(issue.get("project")) ? issue.get("project").toString() : "");

            sonarIssue.setLine(Objects.nonNull(issue.get("line")) ? Integer.parseInt(issue.get("line").toString()) : 0);

            JSONObject textRange = (JSONObject) issue.get("textRange");

            if (Objects.nonNull(textRange)) {
                sonarIssue.setStartLine(Objects.nonNull(textRange.get("startLine"))
                        ? Integer.parseInt(textRange.get("startLine").toString()) : 0);

                sonarIssue.setEndLine(Objects.nonNull(textRange.get("endLine"))
                        ? Integer.parseInt(textRange.get("endLine").toString()) : Integer.MAX_VALUE);
            } else {
                sonarIssue.setStartLine(0);
                sonarIssue.setEndLine(Integer.MAX_VALUE);
            }

            sonarIssue.setStatus(Objects.nonNull(issue.get("status")) ? issue.get("status").toString() : "");
            sonarIssue.setMessage(Objects.nonNull(issue.get("message")) ? issue.get("message").toString() : "");
            sonarIssue.setEffort(Objects.nonNull(issue.get("effort")) ? issue.get("effort").toString() : "");
            sonarIssue.setDebt(Objects.nonNull(issue.get("debt")) ? issue.get("debt").toString() : "");
            sonarIssue.setCreationDate(issue.get("creationDate").toString());
            sonarIssue.setUpdateDate(issue.get("updateDate").toString());
            sonarIssue.setType(Objects.nonNull(issue.get("type")) ? issue.get("type").toString() : "");

            sonarIssues.add(sonarIssue);
        }
        return sonarIssues;
    }
}
