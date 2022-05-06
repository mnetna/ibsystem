package com.slee.web.config.auth.jwt;

import java.io.Serializable;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.slee.web.api.exception.ApiException;
import com.slee.web.api.exception.ErrorCode;
import com.slee.web.config.auth.model.TokenDto;
import com.slee.web.config.auth.userdetails.BasicUserDetailService;
import com.slee.web.constant.TokenConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider implements Serializable {
	private static final long serialVersionUID = -6478201166145254915L;
	private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);
    private final Key key;

    @Autowired
    private BasicUserDetailService userDetailsService;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto generateTokenDto(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        Date accessTokenExpiresIn = new Date(now + TokenConstants.ACCESS_TOKEN_EXPIRE_TIME);

        String accessToken = Jwts.builder()
                .setSubject(((User) authentication.getPrincipal()).getUsername())       // payload "sub": "name"
                .claim(TokenConstants.AUTHORITIES_KEY, authorities)        // payload "auth": "ROLE_USER"
                .setExpiration(accessTokenExpiresIn)        // payload "exp": 1516239022
                .signWith(key, SignatureAlgorithm.HS512)    // header "alg": "HS512"
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + TokenConstants.REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenDto.builder()
                .grantType(TokenConstants.BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = getAllClaimsFromToken(accessToken);

        if (claims.get(TokenConstants.AUTHORITIES_KEY) == null) {
            throw new ApiException(ErrorCode.ACCESSTOKEN_NOTEXIST_AUTHORITY_ERROR);
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(TokenConstants.AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String accessToken) {
        try {
            String userid = getUseridFromToken(accessToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userid);
            if (userid != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                return (userid.equals(userDetails.getUsername()) && !isTokenExpired(accessToken));
            }
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    private Boolean isTokenExpired(String accessToken) {
        final Date expiration = getExpirationDateFromToken(accessToken);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String accessToken) {
        final Claims claims = getAllClaimsFromToken(accessToken);
        return claims.getExpiration();
    }

    public String getUseridFromToken(String accessToken) {
        final Claims claims = getAllClaimsFromToken(accessToken);
        return claims.getSubject();
    }

    private Claims getAllClaimsFromToken(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
