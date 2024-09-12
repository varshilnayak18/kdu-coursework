package org.example;

import java.util.Arrays;
public class SentimentAnalyzer {
    /**
     * returns list of integers having values 0,1,-1 based on review for each feature
     * @param review String to be analyzed
     * @param featureSet set of different features along with alias names
     * @param posOpinionWords positive words about features
     * @param negOpinionWords negative words about features
     * @return int[] list containing values 0,1 or -1
     */
    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords,
                                          String[] negOpinionWords) {
        int features = featureSet.length;
        int[] featureOpinions = new int[features];
        review = review.toLowerCase();
        for(int i = 0; i < features; i++){
            for(int j = 0; j < featureSet[i].length; j++){
                featureOpinions[i] = getOpinionOnFeature(review,featureSet[i][j],posOpinionWords,negOpinionWords);
                if(featureOpinions[i] != 0){
                    break;
                }
            }
        }
        return featureOpinions;
    }

    /**
     * uses two methods to find review pattern for each feature
     * @param review String to be analyzed
     * @param feature feature whose review is to be checked
     * @param posOpinionWords positive words about features
     * @param negOpinionWords negative words about features
     * @return int conveying review for each feature
     */
    private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords,
                                           String[] negOpinionWords) {
        int opinion = checkForWasPhrasePattern(review,feature,posOpinionWords,negOpinionWords);
        if(opinion != 0){
            return opinion;
        }
        opinion = checkForOpinionFirstPattern(review,feature,posOpinionWords,negOpinionWords);
        return opinion;

    }

    /**
     * checks for pattern {feature} was {opinion} for each opinion and feature
     */
    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords,
                                                String[] negOpinionWords) {
        int opinion = 0;
        String pattern = feature + " was ";
        for(String posWord : posOpinionWords){
            if(review.contains(pattern+posWord)){
                return 1;
            }
        }
        for(String negWord : negOpinionWords){
            if(review.contains(pattern+negWord)){
                return -1;
            }
        }
        return opinion;
    }

    /**
     * checks for pattern {opinion} {feature} for each opinion and feature
     */
    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords,
                                                   String[] negOpinionWords) {
        int opinion = 0;
        String pattern = " " + feature;
        for(String posWord : posOpinionWords){
            if(review.contains(posWord+pattern)){
                return 1;
            }
        }
        for(String negWord : negOpinionWords){
            if(review.contains(negWord+pattern)){
                return -1;
            }
        }
        return opinion;
    }
    public static void main(String[] args) {
        Logging logging = new Logging();
        String review = "Haven't been here in years! Fantastic service and the food was delicious! " +
                "Definetly will be a frequent flyer! Francisco was very attentive";
//        String review = "Sorry OG, but you just lost some loyal customers. Horrible service," +
//                "no smile or greeting just attitude. The breadsticks were stale and burnt, appetizer was cold" +
//                "and the food cameout before the salad.";
        String[][] featureSet = {
                { "ambiance", "ambience", "atmosphere", "decor" },
                { "dessert", "ice cream", "desert" },
                { "food" },
                { "soup" },
                { "service", "management", "waiter", "waitress", "bartender", "staff", "server" } };
        String[] posOpinionWords = { "good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome", "delicious" };
        String[] negOpinionWords = { "slow", "bad", "horrible", "awful", "unprofessional", "poor" };
        int[] featureOpinions = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
        logging.logInfo("Opinions on Features: " + Arrays.toString(featureOpinions));
    }
}