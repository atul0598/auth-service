package org.example.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    public static final String SECRET = "a7f3e9d2c8b54a1f6e8d9c2b7a5f4e3d1c9b8a7f6e5d4c3b2a1f9e8d7c6b5a4f";//this is 256 bit randomly generated key which is usually either store in key manager or in enviroment file;

    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims,String username) {
        //Start building a JWT token
        return Jwts.builder()
                .setClaims(claims) //Adds all claims from the Map to JWT payload.
                .setSubject(username) //Sets the "sub" claim
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*1))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

    }

    private Claims extractAllClaims(String token) {
        return  Jwts
                .parser() //read and verify token
                .setSigningKey(getSignKey()) //tells parser to use this key to verify token signature
                .build() //this tells to finalize parser configuration
                //Splits token into: header.payload.signature
                // Verifies signature using the signing key
                // If invalid → throws SignatureException
               //If valid → returns a Jws<Claims> object
                .parseClaimsJws(token)
                .getBody(); //once token is parsed...get claims object containing all the data or in simple word (once token is parsed get all the claims from it)
    }
//    converting secret key into proper string object
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}


