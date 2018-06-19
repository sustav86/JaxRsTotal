package academy.learnprogramming.service;

import academy.learnprogramming.entities.ApplicationUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.util.ByteSource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.xml.registry.infomodel.User;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RequestScoped
public class SecurityUtil {
    private final PasswordService passwordService = new DefaultPasswordService();

    @Inject
    private QueryService queryService;


    public String encryptText(String plainText) {
        return passwordService.encryptPassword(CodecSupport.toBytes(plainText.toCharArray()));
    }

    public boolean passwordsMatch(String plainTextPassword, String encryptedPassword) {
        return passwordService.passwordsMatch(plainTextPassword, encryptedPassword);
    }

    public Key generateKey() {
        AesCipherService cipher = new AesCipherService();

        return cipher.generateNewKey(256);

    }

    public ApplicationUser authenticateUser(String email, String password) throws Exception {
        return queryService.findUserByCredentials(email, password);

    }

    public Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }



}
