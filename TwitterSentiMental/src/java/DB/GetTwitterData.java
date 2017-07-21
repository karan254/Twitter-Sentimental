/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author DKG
 */
public class GetTwitterData {

    static ArrayList<String> lstTweet = new ArrayList();
    static ArrayList<String> lstType = new ArrayList();

    public static void main(String args[]) {
        authenticate("dengue");
    }

    public static void authenticate(String value) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("jNU5eXtnC07jtF1eK8I7MPg22")
                .setOAuthConsumerSecret(
                        "p2VlR9BNpHiCjTODkkPpcUbwEZqLDDuuCSpl1U5N7V4d8Dml1W")
                .setOAuthAccessToken(
                        "4562659459-9kZnpPr5MvZex1HBwuRqOZBGLneuWCsbDXVl03F")
                .setOAuthAccessTokenSecret(
                        "3QjGL0rL5ZAR2Pk54JGmFfkMcb6BuEzFwWMDJUNYSRrIy");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Query query = new Query(value);

        QueryResult result;
        try {
            result = twitter.search(query);
            Connect.openConnection();
            for (twitter4j.Status status : result.getTweets()) {
                System.out.println("@" + status.getUser().getScreenName()
                        + ":" + status.getText());
               // System.out.println("location=" + status.getGeoLocation() + "\n" + status.getPlace());

                //String tweet, String user, String location, String place,String created_at,String tweetid
                String location = "";
                try {
                    location = status.getUser().getLocation();
                } catch (Exception e) {
                }
                String country = "";
                try {
                    location = status.getPlace().getCountry();
                } catch (Exception e) {
                }
                Connect.saveTweets(status.getText(), status.getUser().getScreenName(), location, country, status.getCreatedAt().toLocaleString(), Long.toString(status.getId()),value);
            }
            Connect.closeConnection();
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.println(list);

    }
    
    static int positive_tweet = 0;
    static int negative_tweet = 0;
    static int neutral_tweet = 0;

    public static void classify(String text) {
        lstTweet.clear();
        lstType.clear();
        negative_tweet = 0;
        positive_tweet = 0;
        String sql = "select * from tbltweet where tweet like '%" + text + "%'";
        System.out.println("sql" + sql);
        try {
            Connect.openConnection();
            Connect.rs = Connect.stat.executeQuery(sql);
            while (Connect.rs.next()) {
                String output = DB.NavieBayesClassifier.analayseData(Connect.rs.getString("tweet"));
                lstTweet.add(Connect.rs.getString("tweet"));
                if (output.equals("positive")) {
                    output = "Positive Response";
                    positive_tweet++;

                } else if (output.equals("negative")) {
                    output = "Negative Response";
                    negative_tweet++;
                } else {
                    output = "No Tweets!!";
                    neutral_tweet++;
                }

                lstType.add(output);
            }
            Connect.closeConnection();
            if (positive_tweet + negative_tweet == 0) {
                lstTweet.add("No tweets found!!");
                lstType.add("No Data Found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetTwitterData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ArrayList<String> getTweet() {
        return lstTweet;
    }

    public static ArrayList<String> getResponse() {
        return lstType;
    }

    public static int getPositiveTweet() {
        return positive_tweet;
    }

    public static int getNegativeTweet() {
        return negative_tweet;
    }

    public static int getNeutralTweet() {
        return neutral_tweet;
    }
}
