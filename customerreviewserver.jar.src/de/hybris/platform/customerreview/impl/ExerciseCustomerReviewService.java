package de.hybris.platform.customerreview.impl;

import de.hybris.platform.customerreview.CustomerReviewService;
import exercise.ExerciseService;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 */
public class ExerciseCustomerReviewService extends DefaultCustomerReviewService {


    /**
     * Provide a way to get a product’s total number of customer reviews whose ratings are within a given range (inclusive).
     * @param paramProductModel
     * @param leftRange
     * @param rightRange
     * @return
     */
    public Integer getNumberOfReviewsByRange(ProductModel paramProductModel, Double leftRange, Double rightRange) {
        List<CustomerReviewModel> listCrm = super.getAllReviews(paramProductModel);
        double rating;
        int count = 0;
        for (CustomerReviewModel review : listCrm) {
            rating = review.getRating;
            if (leftRange <= rating && rating <= rightRange) {
                count++;
            }
        }
        return count;
    }
}
