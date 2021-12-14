import Extensions.Verifications;
import Utilities.ManageDB;
import WorkFlows.MortgageWorkFlows;
import org.testng.annotations.Test;
import java.sql.SQLException;

public class MortgageTests extends ManageDB {

    @Test(description = "Calculate using data from DB and verify input is above zero")
    public void Test01_calculateUsingDataFromDB() throws SQLException {
        MortgageWorkFlows.enterDataFromDB();
        softAssert.assertAll();
    }

    @Test(description = "Verify last one added was saved")
    public void test02_verifySaved() throws SQLException {
        MortgageWorkFlows.checkIfSaved();
        Verifications.verifyLastObjSaved(mortgagePage.getLastSavedRepayment().getText(), getData("ExpectedRepaymentSaved"));
    }

}