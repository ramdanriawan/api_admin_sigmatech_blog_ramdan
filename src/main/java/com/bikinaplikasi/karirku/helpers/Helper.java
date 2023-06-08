package com.bikinaplikasi.karirku.helpers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bikinaplikasi.karirku.entity.User.User;
import com.bikinaplikasi.karirku.exceptions.AuthorizedException;
import com.google.gson.Gson;
import net.bytebuddy.implementation.bytecode.Throw;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

public class Helper {
    static private Algorithm algorithm = Algorithm.HMAC256("karirku");

    public static String createJwtToken(User user) {
        String userGson = new Gson()
                .newBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .toJson(user);

        String jwtToken = JWT.create()
                .withIssuer("karirku")
                .withClaim("user", userGson)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 60)))
                .withJWTId(UUID.randomUUID()
                        .toString())
                .sign(algorithm);

        return jwtToken;
    }

    public static Object decodeJwtToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("karirku")
                .build();
        System.out.println(verifier.verify(token).getClaims());
        try {
            String userGson = verifier.verify(token).getClaim("user").as(String.class);
            User user = new Gson().fromJson(userGson, User.class);

            return user;
        } catch (JWTVerificationException jwtVerificationException) {
            return null;
        }

    }

    public static Object decodeJwtTokenBearer(String bearer) {
        bearer = bearer.substring(7);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("karirku")
                .build();

        try {
            String userGson = verifier.verify(bearer).getClaim("user").as(String.class);

            User user = new Gson().fromJson(userGson, User.class);

            return user;
        } catch (JWTVerificationException jwtVerificationException) {
//            jwtVerificationException.getCause().printStackTrace();
//            System.out.println(jwtVerificationException.getCause().getMessage());
            return null;
        }

    }

    public static User checkIfUserHassBeenAuthorized(HttpServletRequest request) throws AuthorizedException {
        if (request.getHeader("Authorization") == null) {
            throw new AuthorizedException("Authorized required");
        }

        User user = (User) Helper.decodeJwtTokenBearer(request.getHeader("Authorization"));

        if (user == null) {
            throw new AuthorizedException("User Not Found!");
        }

        return user;
    }
}
