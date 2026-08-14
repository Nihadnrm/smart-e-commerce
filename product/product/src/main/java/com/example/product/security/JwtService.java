package com.example.product.security;


import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
@Value("${jwt.secret}")
private String secretKey;

private Key getKey(){
    return Keys.hmacShaKeyFor(secretKey.getBytes());
}
public String generateToken(String email,List< String> roles, Long referenceId){
   
    return Jwts.builder()
    .setSubject(email)
    .claim("roles", roles)
    .claim("referenceId", referenceId)
    .setIssuedAt(new Date())
    .setExpiration(new Date(System.currentTimeMillis()+1000*60*60)) 
    .signWith(getKey())
    .compact();
}

private Claims extractClaims(String token){
    return Jwts.parserBuilder()
    .setSigningKey(getKey())
    .build()
    .parseClaimsJws(token)
    .getBody();
}

public  String extractEmail(String token){
    return extractClaims(token).getSubject();
}
public List< String> extractRole(String token){
    return extractClaims(token).get("roles",List.class);
}
public Long extractReferenceId(String token){
    return extractClaims(token).get("referenceId", Long.class);
}
public Boolean isTokenValid( String token,String email){
    return extractEmail(token).equals(email)&&! isTokenExpired(token);
}
public Boolean isTokenExpired(String token){
    return extractClaims(token).getExpiration().before(new Date());
}


}
