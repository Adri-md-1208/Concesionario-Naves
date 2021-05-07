import org.junit.jupiter.api.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
//import org.junit.platform.runner.JUnitPlatform;
//import org.junit.platform.suite.api.SelectClasses;
//import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitPlatform.class)
@SelectClasses( { LoginTest.class, LastTransactionsTest.class, MyRatingsTest.class, UnsubscribeToShipTest.class, SubscribeToShipTest.class,
    FilesTest.class, BuyOfferTest.class, SuscriptionManagerTest.class})

public class TestSuite {

}
