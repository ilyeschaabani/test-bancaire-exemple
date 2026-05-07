package suites.unit;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class TaxeCalculatorTest {
    @Test
    public void testTaxeStandard() {
        double prixHT = 100.0;
        double taux = 0.20;
        double prixTTC = prixHT * (1 + taux);
        assertEquals(prixTTC, 120.0, 0.001);
    }
}