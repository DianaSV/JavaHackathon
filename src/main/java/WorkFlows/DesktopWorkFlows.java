package WorkFlows;

import Extensions.UIActions;
import Utilities.CommonOps;

/**
 * @author Rolan Abdualiev , Dec 2021
 * @project QA-AutomationDemonstration
 */
public class DesktopWorkFlows extends CommonOps {

    public static void addition() {
        UIActions.clickElement(calculatorPage.getNumber(getData("FirstNumber")));
        UIActions.clickElement(calculatorPage.getMathSign(getData("AdditionSign")));
        UIActions.clickElement(calculatorPage.getNumber(getData("SecondNumber")));
        UIActions.clickElement(calculatorPage.getMathSign(getData("Equals")));
    }

    public static void mul() {
        UIActions.clickElement(calculatorPage.getNumber(getData("FirstNumber")));
        UIActions.clickElement(calculatorPage.getMathSign(getData("MulSign")));
        UIActions.clickElement(calculatorPage.getNumber(getData("SecondNumber")));
        UIActions.clickElement(calculatorPage.getMathSign(getData("Equals")));
    }

    public static void cleanCalc() {
        UIActions.clickElement(calculatorPage.getClean());
    }

}
