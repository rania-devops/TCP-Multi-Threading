# TCP-Multi-Threading
C'est un exemple de serveur TCP en Java qui peut gérer jusqu'à 10 clients simultanément. Chaque client est traité dans un thread dédié, inversant les chaînes de caractères reçues avec un délai simulé.
Cet exemple illustre la mise en place d'un serveur TCP multithread en Java. Il utilise les classes ServerSocket, Socket, BufferedReader, et PrintWriter pour établir et gérer les connexions.
>> Voici une courte description des principales étapes effectuées :

1- Configuration du serveur :
   Le serveur est configuré pour écouter les connexions entrantes sur le port 10000 (par exemple)

2- Acceptation des connexions : 
   Lorsqu'une connexion est établie, le serveur accepte la demande de connexion du client.

3- Gestion des clients en parallèle : 
   Si le nombre actif de threads (clients) est inférieur à un certain seuil (10 dans cet exemple), le serveur crée un nouveau thread pour gérer ce client spécifique.

4- Traitement des données : 
   Le thread client lit les données envoyées par le client, inverse la chaîne de caractères, simule un traitement avec un délai (Thread.sleep), puis envoie la chaîne inversée au client.

5- Limite de clients atteinte : 
  Si le nombre maximal de clients est atteint, le serveur rejette la connexion, fermant ainsi le socket du client.
