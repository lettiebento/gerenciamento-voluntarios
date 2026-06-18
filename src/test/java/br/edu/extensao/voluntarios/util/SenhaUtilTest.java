package br.edu.extensao.voluntarios.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SenhaUtilTest {
    private final SenhaUtil senhaUtil = new SenhaUtil();

    @Test
    void deveGerarHashEConferirSenha() {
        String hash = senhaUtil.gerarHash("senhaSegura123");
        assertTrue(senhaUtil.conferir("senhaSegura123", hash));
        assertFalse(senhaUtil.conferir("senhaErrada", hash));
        assertNotEquals("senhaSegura123", hash);
    }
}
