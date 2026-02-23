package org.example;

import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        DataModel model = new FileDataModel(
                new File("src/main/resources/data.csv"));

        UserSimilarity similarity =
                new PearsonCorrelationSimilarity(model);

        UserNeighborhood neighborhood =
                new NearestNUserNeighborhood(2, similarity, model);

        UserBasedRecommender recommender =
                new GenericUserBasedRecommender(
                        model, neighborhood, similarity);

        List<RecommendedItem> recommendations =
                recommender.recommend(1, 2);

        System.out.println("Recommendations for User 1:");

        for (RecommendedItem recommendation : recommendations) {
            System.out.println("Item ID: "
                    + recommendation.getItemID()
                    + " Strength: "
                    + recommendation.getValue());
        }
    }
}
