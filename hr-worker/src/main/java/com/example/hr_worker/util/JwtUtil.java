package com.example.hr_worker.util;

import com.example.hr_worker.dto.JwtDecodedDTO;
import com.nimbusds.jwt.SignedJWT;
import org.apache.http.ParseException;

import java.util.Base64;
import java.util.Map;

public class JwtUtil {

    public static JwtDecodedDTO decodeToken(String jwtToken) {
        try {
            // Parseando o token JWT
            SignedJWT signedJWT = SignedJWT.parse(jwtToken);

            // ✅ Extraindo Header sem erro de Base64
            Map<String, Object> header = signedJWT.getHeader().toJSONObject();

            // ✅ Extraindo Claims (Payload)
            Map<String, Object> payload = signedJWT.getJWTClaimsSet().getClaims();

            // ✅ Extraindo Assinatura
            String signature = jwtToken.substring(jwtToken.lastIndexOf('.') + 1);

            return new JwtDecodedDTO(header, payload, signature);
        } catch (ParseException e) {
            throw new RuntimeException("Erro ao decodificar JWT: " + e.getMessage());
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
