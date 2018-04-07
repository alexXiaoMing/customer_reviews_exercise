package de.hybris.platform.customerreview.impl;

import de.hybris.platform.jalo.JaloBusinessException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CustomerReviewServiceAop {

    private String fileName;

    protected String getFileName() {
        return this.fileName;
    }
    @Required
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Add the following additional checks before creating a customer review
     * @param joinPoint
     * @throws JaloInvalidParameterException
     */
    public void beforeCreateReview(JoinPoint joinPoint)  throws JaloInvalidParameterException {
        String comment = joinPoint.getArgs()[2];
        //Check if Customer’s comment contains any of these curse words. If it does, throw an exception with a message.
        Set<String> curseWords = new HashSet<>();
        try (Scanner scanner = new Scanner(new FileInputStream(getFileName()))) {
            while (scanner.hasNext()) {
                String world = scanner.next();
                curseWords.add(world);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new JaloInvalidParameterException("file " + cwFileName + " can not find", 0);
        }
        for(String commentWord : comment.split(" ")){
            if(comment.contains(cword)){
                throw new JaloInvalidParameterException("comment contains curse words!", 0);
            }
        }
        //Check if the rating is not < 0.  If it is < 0, throw an exception with a message.
        if(paramDouble < 0){
            throw new JaloInvalidParameterException("Rating is invalid!", 0);
        }
    }
}
