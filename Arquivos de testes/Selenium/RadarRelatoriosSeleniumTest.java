package uc6;

import static org.junit.Assert.assertTrue;
import java.time.Duration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RadarRelatoriosSeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void iniciar() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void ct20_ValidarRadarERelatoriosSindico() {
       
        driver.get("http://localhost:5173/login");

        // Ajuste o e-mail e a senha para um síndico válido cadastrado no EcoCondo
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")))
            .sendKeys("sindico@ecocondo.com");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Senha@123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Aguarda carregar o painel e valida se Radar de Sustentabilidade ou Relatórios aparece
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        String tela = driver.findElement(By.tagName("body")).getText();

        boolean ok = tela.contains("Radar") || tela.contains("Sustentabilidade") || tela.contains("Relatório");
        assertTrue("Radar ou Relatórios não apareceu na tela.", ok);
    }

    @After
    public void fechar() {
        if (driver != null) {
            driver.quit();
        }
    }
}