import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurTCPMultithread {

    public static void main(String[] args) {
        final int NUMERO_PORT = 10000;
        final int NOMBRE_MAX_CLIENTS = 10;

        try (ServerSocket socketServeur = new ServerSocket(NUMERO_PORT)) {
            System.out.println("Serveur démarré. En écoute sur le port " + NUMERO_PORT);

            while (true) {
                Socket socketClient = socketServeur.accept();
                System.out.println("Connexion acceptée depuis : " + socketClient.getInetAddress());

                // Limiter le nombre de clients simultanés
                if (Thread.activeCount() - 1 <= NOMBRE_MAX_CLIENTS) {
                    new Thread(new GestionnaireClient(socketClient)).start();
                } else {
                    System.out.println("Nombre maximal de clients atteint. Connexion rejetée.");
                    socketClient.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class GestionnaireClient implements Runnable {
        private final Socket socketClient;

        public GestionnaireClient(Socket socketClient) {
            this.socketClient = socketClient;
        }

        @Override
        public void run() {
            try (
                    BufferedReader lecteur = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                    PrintWriter redacteur = new PrintWriter(socketClient.getOutputStream(), true)
            ) {
                String ligneEntrante;

                while ((ligneEntrante = lecteur.readLine()) != null) {
                    // Simuler un temps de traitement
                    Thread.sleep(1000);

                    // Inverser la chaîne de caractères
                    String chaineInverse = new StringBuilder(ligneEntrante).reverse().toString();

                    // Envoyer la chaîne inversée au client
                    redacteur.println(chaineInverse);
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    socketClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
