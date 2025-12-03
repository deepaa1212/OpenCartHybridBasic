package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    // ============================
    // DATA PROVIDER FOR LOGIN TEST
    // ============================
    @DataProvider(name = "LoginData")
    public String [][] getLoginData() throws IOException {

        String path = ".\\src\\test\\resources\\testData\\LoginData.xlsx";

        ExcelUtility xlutil = new ExcelUtility(path);

        int totalrows = xlutil.getRowCount("LoginData");
        int totalcols = 3;   // Username, Password, ExpectedResult

        String loginData[][] = new String[totalrows][totalcols];

        for (int i = 1; i <= totalrows; i++) {
            for (int j = 0; j < totalcols; j++) {
                loginData[i - 1][j] = xlutil.getCellData("LoginData", i, j);
            }
        }
        return loginData;
    }

}
