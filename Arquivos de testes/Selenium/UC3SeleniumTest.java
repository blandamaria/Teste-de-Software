package ecocondo.testes;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class UC3SeleniumTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
        driver.get("https://ecocondo-front.onrender.com/"); 
    }

    @Test
    @DisplayName("CT11: Realizar login com credenciais válidas de síndico")
    public void testLoginCredenciaisValidas() {
        driver.findElement(By.cssSelector("a[href='/login/sindico']")).click();

        // Credenciais reais do Síndico atualizadas
        driver.findElement(By.xpath("//input[@placeholder='Digite seu CNPJ']")).sendKeys("23717859000111"); 
        driver.findElement(By.xpath("//input[@placeholder='Digite sua senha']")).sendKeys("Teste1234"); 
        driver.findElement(By.xpath("//button[contains(text(), 'Entrar')]")).click();

        String urlAtual = driver.getCurrentUrl();
        assertTrue(urlAtual.contains("/painel-administrativo"), "O sistema deveria redirecionar para o painel após login válido.");
    }

    @Test
    @DisplayName("CT12: Tentar realizar login com senha incorreta")
    public void testLoginSenhaIncorreta() {
        driver.findElement(By.cssSelector("a[href='/login/sindico']")).click();

        // CNPJ real do Síndico com senha incorreta para forçar a validação
        driver.findElement(By.xpath("//input[@placeholder='Digite seu CNPJ']")).sendKeys("23717859000111");
        driver.findElement(By.xpath("//input[@placeholder='Digite sua senha']")).sendKeys("SenhaIncorreta123");
        driver.findElement(By.xpath("//button[contains(text(), 'Entrar')]")).click();

        // Valida mensagem de erro procurando pelo texto real que a tela exibe (Erro 500)
        WebElement mensagemErro = driver.findElement(By.xpath("//*[contains(text(), 'Erro interno no servidor.')]")); 
        assertTrue(mensagemErro.isDisplayed(), "A mensagem de erro deve ficar visível na tela.");
        assertEquals("Erro interno no servidor.", mensagemErro.getText(), "O texto da mensagem de erro não corresponde ao esperado.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}