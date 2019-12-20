package com.techelevator.authentication;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.techelevator.model.User;
import com.techelevator.model.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;

/**
 * JwtTokenHandler
 */
@Component
public class JwtTokenHandler {
    private static final String SECRET_KEY = "T9GKvpb3oX5qqo3sd6+OJ2iqldexr32h7FHMpkgON+6yAtr2gfJOQkjt4mR9b7rCPL3wip8vgXzkr2LOpbAnitVQvHnIO2tlaQdnPo+xc10/KtcEBDMQV6nPn41+ScZ9wbvTIQn2+FUoJkQhXFcWRe4r4CbDKzZ73Z4ZyhMmJ4n8crYDl7dR3XjScLq4sO0BXYHK1qj6u7JPABoqQXv83uycysTt/TtDIxSl5r5+M7U/99hHvdLmfWBUlCoorhGodggZow/6qlSql7jRLENwebcxrd9Ggxaf5BA+oR6FJ1gwCEIPOoSs+Cbk7SQwIgOIw8Xmmn+nbZzPoDugv80Diy/QRUQ0rs/F8lgBG8P3dq7mLWaJknSlOquxBCcnkBaMHvtPARqrnNhlfXl5pTXvuGl+wbwDLi5WGOb0go+b+gv2Z/xtOu72t41sf/PjNkDQPiCzoSmagQA/aRAg2z5Mfewl16eeB96h8MHeDUjSzsVNpw8/NySUnVbRKXjxeZG2P6IsRoGMPeufEz/2uexIvoLCb76t+IjCDWwwdULrofFFMyb2wv3+7mpTlCkyAJp5x7SMFiBjqGRfa1Ziy1iq8uHxzVLJTKZp0w2EDzGI/uvn95b8gEtckWFkPmACi7ESXxVDRgWEFHW6Hr742uPl6walIEEZvxbIrgpaszaSonM=";
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private UserDao dao;

    public String createToken(String username, String role) {
        Date now = new Date();

        // We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setIssuedAt(now).setSubject(username).claim("rol", role)
                .signWith(signingKey, signatureAlgorithm);

        // if it has been specified, let's add the expiration

        long expMillis = System.currentTimeMillis() + 21600000;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public User getUser(String jwtString) throws IOException {
        if (jwtString == null || !jwtString.startsWith(BEARER_PREFIX)) {
            return null;
        }

        final String token = jwtString.substring(BEARER_PREFIX.length());

        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();
        User authedUser = dao.getUserByUsername(claims.getSubject());
        return authedUser;
    }
}