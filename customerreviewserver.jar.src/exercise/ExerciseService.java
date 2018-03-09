package exercise;
import de.hybris.platform.customerreview.*;

public interface ExerciseService {

    Integer getNumberOfReviewsByRange(ProductModel paramProductModel, Double leftRange, Double rightRange);

    CustomerReviewModel createCheckedCustomerReview(Double paramDouble, String paramString1, String paramString2,
                                                    UserModel paramUserModel, ProductModel paramProductModel,
                                                    String cwFilePath) throws JaloInvalidParameterException;
}
