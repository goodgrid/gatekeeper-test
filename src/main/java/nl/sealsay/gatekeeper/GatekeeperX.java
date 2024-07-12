package nl.sealsay.gatekeeper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.KeyStoreCallbackHandler;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;

@EnableWs
@SpringBootApplication
public class GatekeeperX extends WsConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(GatekeeperX.class, args);
    }

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() throws Exception {
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
        securityInterceptor.setSecurementActions("Signature");
        securityInterceptor.setSecurementUsername("server-alias");
        securityInterceptor.setSecurementPassword("server-password");
        securityInterceptor.setSecurementSignatureCrypto(cryptoFactoryBean().getObject());
        securityInterceptor.setSecurementSignatureParts("{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");

        return securityInterceptor;
    }

    @Bean
    public CryptoFactoryBean cryptoFactoryBean() {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStoreLocation(new ClassPathResource("server-keystore.jks"));
        cryptoFactoryBean.setKeyStorePassword("password");
        return cryptoFactoryBean;
    }

    @Bean
    public KeyStoreCallbackHandler securityCallbackHandler() {
        KeyStoreCallbackHandler callbackHandler = new KeyStoreCallbackHandler();
        callbackHandler.setKeyStoreLocation(new ClassPathResource("server-keystore.jks"));
        callbackHandler.setKeyStorePassword("password");
        return callbackHandler;
    }

    @Bean
    public HttpComponentsMessageSender httpComponentsMessageSender() {
        HttpComponentsMessageSender messageSender = new HttpComponentsMessageSender();
        messageSender.setConnectionTimeout(5000);
        messageSender.setReadTimeout(5000);
        return messageSender;
    }
}
