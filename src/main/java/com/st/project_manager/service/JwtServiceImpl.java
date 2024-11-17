package com.st.project_manager.service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

  private static String SECRET_KEY = "345345LK34J5LKL5H4KH5L3K4G43JGHF534DR4U5Y3O4I5P3";

  @Override
  public String getToken(UserDetails user) {
    return getToken(user.getAuthorities(), user);
  }

  private String getToken(Collection<? extends GrantedAuthority> roles, UserDetails user) {
    Claims claims = Jwts.claims().setSubject(user.getUsername());
    claims.put("roles", roles.stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList()));

    return Jwts.builder().setClaims(claims).signWith(getKey(), SignatureAlgorithm.HS256)
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
        .setIssuedAt(new Date(System.currentTimeMillis())).setSubject(user.getUsername()).compact();
  }

  private Key getKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private Date getExpiration(String token) {
    return getClaim(token, Claims::getExpiration);
  }

  private Boolean isTokenExpired(String token) {
    return getExpiration(token).before(new Date());
  }

  private Claims getAllClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
  }

  public <T> T getClaim(String token, Function<Claims, T> claimResolver) {
    final Claims claims = getAllClaims(token);
    return claimResolver.apply(claims);
  }

  @Override
  public String getUserNameFromToken(String token) {
    return getClaim(token, Claims::getSubject);
  }

  @Override
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = getUserNameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

}
