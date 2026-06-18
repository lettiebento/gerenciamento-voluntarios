package br.edu.extensao.voluntarios.util;

import org.springframework.stereotype.Component;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class SenhaUtil {
    private static final int ITERACOES = 65_536;
    private static final int TAMANHO_CHAVE = 256;
    private static final int TAMANHO_SALT = 16;

    public String gerarHash(String senha) {
        byte[] salt = new byte[TAMANHO_SALT];
        new SecureRandom().nextBytes(salt);
        byte[] hash = pbkdf2(senha.toCharArray(), salt, ITERACOES, TAMANHO_CHAVE);
        return ITERACOES + ":" + Base64.getEncoder().encodeToString(salt) + ":"
                + Base64.getEncoder().encodeToString(hash);
    }

    public boolean conferir(String senha, String armazenado) {
        try {
            String[] partes = armazenado.split(":");
            int iteracoes = Integer.parseInt(partes[0]);
            byte[] salt = Base64.getDecoder().decode(partes[1]);
            byte[] hashEsperado = Base64.getDecoder().decode(partes[2]);
            byte[] hashCalculado = pbkdf2(senha.toCharArray(), salt, iteracoes, hashEsperado.length * 8);
            return MessageDigest.isEqual(hashEsperado, hashCalculado);
        } catch (RuntimeException e) {
            return false;
        }
    }

    private byte[] pbkdf2(char[] senha, byte[] salt, int iteracoes, int tamanho) {
        try {
            PBEKeySpec spec = new PBEKeySpec(senha, salt, iteracoes, tamanho);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new IllegalStateException("Não foi possível proteger a senha.", e);
        }
    }
}
