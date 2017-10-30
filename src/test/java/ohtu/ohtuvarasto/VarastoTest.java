package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuontiVirheellisellaMaaralla() {
        varasto = new Varasto(-10);
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void tyhjaVarastoOikeallaNollaAlkusaldolla() {
        varasto = new Varasto(10, 0);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tyhjaVarastoSuuremmallaTilavuudellaKuinAlkusaldo() {
        varasto = new Varasto(4, 2);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tyhjaVarastoSuuremmallaAlkusaldollaKuinTilavuus() {
        varasto = new Varasto(6, 8);
        assertEquals(6, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudenVarastonTilavuusPienempiKuinNolla() {
        varasto = new Varasto(-2, 4);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldoPienempiKuinNolla() {
        varasto = new Varasto(7, -2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void maaraPienempiKuinNollaEiMuutaMitaan() {
        double vanhaSaldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-242);
        assertEquals(vanhaSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otetaanNegatiivinenMaara() {
        double vanhaSaldo = varasto.getSaldo();
        varasto.otaVarastosta(-24);
        assertEquals(vanhaSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otetaanVarastostaKaikkiMitaSaadaan() {
        varasto.otaVarastosta(4000);

        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void palauttaaOikeanMerkkijonon() {
        String testi = "saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu();
        String tulos = varasto.toString();

        assertEquals(testi, tulos);
    }

}