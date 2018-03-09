package exercise;

import de.hybris.platform.customerreview.CustomerReviewService;
import de.hybris.platform.customerreview.constants.CustomerReviewConstants;
import de.hybris.platform.customerreview.dao.CustomerReviewDao;
import de.hybris.platform.customerreview.jalo.CustomerReview;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DefaultExerciseService implements ExerciseService {


    @Resource
    private CustomerReviewService customerReviewService;

    @Override
    public Integer getNumberOfReviewsByRange(ProductModel paramProductModel, Double leftRange, Double rightRange) {
        List<CustomerReviewModel> listCrm = customerReviewService.getAllReviews(paramProductModel);
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

    @Override
    public CustomerReviewModel createCheckedCustomerReview(Double paramDouble, String paramString1, String paramString2,
                                                           UserModel paramUserModel, ProductModel paramProductModel,
                                                           String cwFilePath) throws JaloInvalidParameterException{
        //Check if Customer’s comment contains any of these curse words. If it does, throw an exception with a message.
        for(String cword : getCurseWords(cwFilePath)){
            if(paramString2.contains(cword)){
                throw new JaloInvalidParameterException("comment contains curse words!", 0);
            }
        }
        //Check if the rating is not < 0.  If it is < 0, throw an exception with a message.
        if(paramDouble < 0){
            throw new JaloInvalidParameterException("Rating is invalid!", 0);
        }
        return customerReviewService.createCustomerReview(paramDouble, paramString1, paramString2, paramUserModel,
                paramProductModel);
    }

    private Set<String> getCurseWords(String cwFilePath) throws JaloInvalidParameterException{
        Set<String> curseWords = new HashSet<>();
        try (Scanner scanner = new Scanner(new FileInputStream(cwFilePath))) {
            while (scanner.hasNext()) {
                String world = scanner.next();
                curseWords.add(world);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new JaloInvalidParameterException("file " + cwFilePath + " can not find", 0);
        }
        return curseWords;
    }
}
