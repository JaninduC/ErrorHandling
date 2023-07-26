package com.example.shop.service.impl;

import com.example.shop.exception.InvalidRefreshToken;
import com.example.shop.model.auth.Authenticate;
import com.example.shop.model.auth.Token;
import com.example.shop.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {
    static final Random random = new Random();
    private static final HashMap<String, String> tokensMAP = new HashMap<>(); // key is Refresh token. value is token
    private static final String KEY = "86c84b812976fd51c5ae82b173ea8d8728a0d999153c9b7f81b16635f21e8394";

    public static String getRefreshToken() {
        int maxLength = 32;
        String allowedCharacters = "123456789abcdefghijklmnopqrstuvwxyz";


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxLength; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());
            char randomChar = allowedCharacters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();

    }

    @Override
    public Token getToken(Authenticate authenticate) {
        Map<String, Object> claims = new HashMap<>();
        return getTokenR(claims, authenticate.getUsername());
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public Boolean validateToken(String token, Authenticate userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Token getRefreshToken(String token) throws InvalidRefreshToken {

        for (String s :
                tokensMAP.keySet()) {
            if (s.equals(token)) {
                String jwtToken = tokensMAP.get(token);
                Token tokenR = getTokenR(new HashMap<>(), extractUsername(jwtToken));

                tokensMAP.remove(token);

                return tokenR;
            }
        }
        if (tokensMAP.get(token) == null) {
            throw new InvalidRefreshToken("invalid refresh token");
        }
        return null;
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    private Token getTokenR(Map<String, Object> map, String username) {
        String jwtToken = Jwts.builder()
                .setClaims(map)
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        String refreshToken = getRefreshToken();
        tokensMAP.put(refreshToken, jwtToken);
        return new Token(jwtToken, refreshToken);
    }

    private Key getKey() {
        byte[] key = Decoders.BASE64.decode(KEY);
        return Keys.hmacShaKeyFor(key);
    }


}
