package com.ljp.basicplatform.utils;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

    private static final String EXP = "exp";

    private static final String PAYLOAD = "payload";

    private static final JWTSigner signer = new JWTSigner(SECRET);
    private static final JWTVerifier verifier = new JWTVerifier(SECRET);


    //加密，传入一个id和有效期
    public static String sign(Integer id, long maxAge) {
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put(PAYLOAD, id);
            claims.put(EXP, System.currentTimeMillis() + maxAge);
            return signer.sign(claims);
        } catch(Exception e) {
            return null;
        }
    }

    //解密，传入一个加密后的token字符串和解密后的类型
    public static Integer unsign(String jwt) {
        try {
            Map<String,Object> claims= verifier.verify(jwt);
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                long exp = (Long)claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis) {
                    return (Integer) claims.get(PAYLOAD);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}
