/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.tools;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stk
 */
public class WolframAlphaRequester {

    private static String appid = "XXXXX";

    public String requestAnswer(String request) {

        String answer = "Please specify!";

        /*
         * input validation
         */
        if (request.length() == 0) {
            return "Request error!";
        }

        WAEngine engine = new WAEngine();
        engine.setAppID(appid);
        engine.addFormat("plaintext");
        WAQuery query = engine.createQuery();
        query.setInput(request);

        try {
            WAQueryResult queryResult = engine.performQuery(query);
            if (queryResult.isError()) {
                return "Query error!";
            } else if (!queryResult.isSuccess()) {
                return "Request was not understood!";
            } else {


                answer = "";
                
                for (WAPod pod : queryResult.getPods()) {
                    
                    if(!pod.isError()) {
                        answer += "\n" + pod.getTitle() + ": ";
                        for (WASubpod subpod : pod.getSubpods()) {
                            for (Object element : subpod.getContents()) {
                                if(element instanceof WAPlainText) {
                                    answer += ((WAPlainText) element).getText();
                                }
                            }
                        }
                    }
                    
                    
                }




            }


        } catch (WAException ex) {
            Logger.getLogger(WolframAlphaRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
}
