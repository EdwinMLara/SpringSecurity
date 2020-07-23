package com.securyti.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.security.core.userdetails.UserDetails;

public class jwtDemo {
    public final static String SECRET_KEY = "secret";

    public String generatedJwt(final UserDetails userDetails) {
        final Map<String, Object> claims = new HashMap<>();
        return createJWT(claims, userDetails.getUsername());
    }

    public static String createJWT(final Map<String, Object> claims, final String subject) {
        final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        final long nowMillis = System.currentTimeMillis();
        final Date now = new Date(nowMillis);

        final long ttMillis = 5 * 60 * 60;

        final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        final Key signedKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        final JwtBuilder jwt = Jwts.builder().setClaims(claims).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
                .signWith(signatureAlgorithm, signedKey);

        if (ttMillis > 0) {
            final long expMillis = nowMillis + ttMillis;
            final Date exp = new Date(expMillis);
            jwt.setExpiration(exp);
        }

        return jwt.compact();
    }

    public static Claims getAllClaimsFromToken(final String jwt) {
        final Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    private <T> T getClaimFromToken(final String jwt, final Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(jwt);
        return claimsResolver.apply(claims);
    }

    public Date getExpirationDateFromToken(final String jwt) {
        return getClaimFromToken(jwt, Claims::getExpiration);
    }

    public String getUsernameFromToken(final String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Boolean isJwtExpired(final String jwt) {
        final Date expiration = getExpirationDateFromToken(jwt);
        return expiration.before(new Date());
    }

    public Boolean validateToken(final String jwt, final UserDetails userDetails) {
        final String username = getUsernameFromToken(jwt);
        return (username.equals(userDetails.getUsername()) && !isJwtExpired(jwt));
    }
    
}