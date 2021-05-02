import concesionario.ClientManager;
import concesionario.FilesCreator;
import org.junit.jupiter.api.*;


import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class ClientManagerTest {

    @BeforeEach
    void setUp() {
        ClientManager clientManager = new ClientManager();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void usuarioBloqueado() throws IOException {

    }


}
