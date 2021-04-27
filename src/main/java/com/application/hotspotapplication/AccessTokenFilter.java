package com.application.hotspotapplication;

import com.application.hotspotapplication.exceptions.ApiRequestException;
import com.application.hotspotapplication.utils.Constants;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AccessTokenFilter extends OncePerRequestFilter {
    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(checkAccessToken(request,response))
        {
            authToken(getUserDetails(request.getHeader(HEADER)));
        }
        else
        {
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkAccessToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)) {
            SecurityContextHolder.clearContext();
            return false;
        }
        return true;
    }
    @SneakyThrows
    public String  getUserDetails( String token)
    {
        try
        {
            StringBuffer response = null;
            String authHeader = token.split(" ")[1];
            String uri = Constants.GOOGLE_API_URL +authHeader;
            URL obj = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("GET Response Code :: " + responseCode);
                System.out.println(response.toString());
                return response.toString();
            }
            throw new ApiRequestException("Autherization issue occured", HttpStatus.UNAUTHORIZED);
        }
        catch (ApiRequestException e) {
            throw new ApiRequestException(e.getMessage(), e, e.getHttpStatus());
        }
    }
    private void authToken(String user) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
