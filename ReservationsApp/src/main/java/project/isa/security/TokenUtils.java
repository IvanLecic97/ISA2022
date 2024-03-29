package project.isa.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import project.isa.TimeProvider;
import project.isa.model.users.RegUser;
import project.isa.repository.RegUserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {
    @Value("ReservationsAppBack")
    private String APP_NAME;

    @Value("abcdefghijklmnOPQRSTUVWXYZ")
    private String SECRET;


    @Value("3600000") // 1h
    private int EXPIRES_IN;

    @Value("3600000") // 1h
    private int MOBILE_EXPIRES_IN;

    @Value("Authorization")
    private String AUTH_HEADER;

    static final String AUDIENCE_UNKNOWN = "unknown";
    static final String AUDIENCE_WEB     = "web";
    static final String AUDIENCE_MOBILE  = "mobile";
    static final String AUDIENCE_TABLET  = "tablet";

    @Autowired
    private TimeProvider timeProvider;

    @Autowired
    private RegUserRepository regUserRepository;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;


    public String generateToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("authority", regUserRepository.findByUsernameEquals(username).getRole());


        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(username)
                .claim("authority", regUserRepository.findByUsernameEquals(username).getRole().toString())
                //.setClaims(claims)
//                .setAudience(generateAudience(device))
                .setAudience(AUDIENCE_WEB)
                .setIssuedAt(timeProvider.now())
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    private Date generateExpirationDate() {
        return new Date(timeProvider.now().getTime() + EXPIRES_IN * 10000);
    }

    //Function for refreshing jwt token
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            claims.setIssuedAt(timeProvider.now());
            refreshedToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(generateExpirationDate())
                    .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = this.getIssuedAtDateFromToken(token);
        return (this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token)));
    }

    // Functions for validating JWT token data

    public Boolean validateToken(String token, UserDetails userDetails) {
        RegUser user = (RegUser) userDetails;
        final String mail = getMailFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);

        return (mail != null && mail.equals(userDetails.getUsername()) /*&& !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate())*/);
    }

    public String getMailFromToken(String token) {
        String mail;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            mail = claims.getSubject();
        } catch (Exception e) {
            mail = null;
        }
        return mail;
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(timeProvider.now());
    }

    private Boolean ignoreTokenExpiration(String token) {
        String audience = this.getAudienceFromToken(token);
        return (audience.equals(AUDIENCE_TABLET) || audience.equals(AUDIENCE_MOBILE));
    }

    // Functions for getting data from token

    public Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (Exception e) {
            issueAt = null;
        }
        return issueAt;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            audience = claims.getAudience();
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public int getExpiredIn() {
//        return device.isMobile() || device.isTablet() ? MOBILE_EXPIRES_IN : EXPIRES_IN;
        return EXPIRES_IN;
    }

    /* Functions for getting JWT token out of HTTP request */

    public String getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }


}
