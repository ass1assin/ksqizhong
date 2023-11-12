//package com.exam.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import java.util.Date;
//import java.util.Map;
//
//public class JwtUtils {
//   private static String signKey="jjbj";
////生成令牌
//    public static String generateJWT(Map<String,Object> claims){
//        String jwt= Jwts.builder()
//                .signWith(SignatureAlgorithm.HS256,signKey)
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))
//                .compact();
//        return jwt;
//    }
////解析令牌
//    public static Claims parseJWT(String jwt){
//        Claims claims = Jwts.parser()
//                .setSigningKey(signKey)
//                .parseClaimsJws(jwt)
//                .getBody();
//        return claims;
//    }
//
//}
