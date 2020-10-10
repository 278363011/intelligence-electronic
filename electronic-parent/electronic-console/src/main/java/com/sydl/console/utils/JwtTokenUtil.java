package com.sydl.console.utils;

import com.sydl.console.security.JwtUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtTokenUtil {
    private String secret;

    // 过期时间 毫秒
    private Long expiration;

    private String header;


    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    public String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject((String) claims.get(Claims.SUBJECT))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成令牌
     *
     * @param userDetails 用户
     * @return 令牌
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(Claims.SUBJECT, userDetails.getUsername());
        claims.put(Claims.ISSUED_AT, new Date());
        return generateToken(claims);
    }



    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(Claims.ISSUED_AT, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证令牌
     *
     * @param token       令牌
     * @param userDetails 用户
     * @return 是否有效
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUserDetails user = (JwtUserDetails) userDetails;
        String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }





//    public static void main(String[] args) {
//        JwtTokenUtil ju=new JwtTokenUtil("csdlelectronic",36000L,"Authorization");
//
//
//        Map<String, Object> claims = new HashMap<>(2);
//        claims.put(Claims.SUBJECT, "admin");
//        claims.put(Claims.ISSUED_AT, new Date());
//        String token = ju.generateToken(claims);
//        System.out.println(token);
//
//        System.out.println("开始验证");
//        String claimsFromToken = ju.getUsernameFromToken("eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2MDIzMDc5NzQsInN1YiI6ImFkbWluIiwiaWF0IjoxNjAyMzA3OTM4MTk1fQ.4MrwLsZVJyohwO48HGgTIUHVk1NFe0Tcb2M-ieM0r4Dpi5b4WZwFaQd7zOyiU8qk7rmydc6Y5J5L9pJ9edLKpA");
//        System.out.println(claimsFromToken);
//
//        System.out.println("开始验证");
//        String claimsFromTokens = ju.getUsernameFromToken(token);
//        System.out.println(claimsFromToken);
//    }
//
//    JwtTokenUtil(String secret,Long expiration,String header){
//        this.secret=secret;
//        this.expiration=expiration;
//        this.header=header;
//    }










}
