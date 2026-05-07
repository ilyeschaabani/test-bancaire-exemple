package suites.unit;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.math.BigDecimal;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

interface TauxService {
    BigDecimal getTaux(String source, String cible);
}

class CompteService {
    private final TauxService tauxService;
    CompteService(TauxService tauxService) { this.tauxService = tauxService; }
    public BigDecimal convertir(BigDecimal montant, String source, String cible) {
        return montant.multiply(tauxService.getTaux(source, cible));
    }
}

public class CompteServiceTest {
    private TauxService tauxServiceMock;
    private CompteService compteService;

    @BeforeMethod
    public void setUp() {
        tauxServiceMock = Mockito.mock(TauxService.class);
        compteService = new CompteService(tauxServiceMock);
    }

    @Test
    public void testConversionEURtoUSD() {
        Mockito.when(tauxServiceMock.getTaux("EUR", "USD")).thenReturn(new BigDecimal("1.08"));
        BigDecimal result = compteService.convertir(new BigDecimal("100"), "EUR", "USD");
        assertEquals(result, new BigDecimal("108.00"));
    }

    @Test
    public void testConversionZero() {
        Mockito.when(tauxServiceMock.getTaux("EUR", "USD")).thenReturn(new BigDecimal("1.08"));
        BigDecimal result = compteService.convertir(BigDecimal.ZERO, "EUR", "USD");
        assertTrue(result.compareTo(BigDecimal.ZERO) == 0, "Le résultat doit être zéro");
    }
}