package com.miumg.copiararchivos;

import java.io.*;
import java.net.*;
/**
 *
 * Jenny Sofía Morales López 7690-08-6790
 * Cristian Alejandro Melgar Ordoñez 7690 21 8342
 */
public class FileServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Servidor iniciado y esperando conexiones...");

            while (true) {
                socket = serverSocket.accept();
                System.out.println("Conexión aceptada: " + socket);

                // Aquí se guarda el archivo recibido en una ubicación fija por simplicidad.
                // Puedes modificar esta parte para que el destino sea dinámico.
                File file = new File("archivo_recibido");
                fileOutputStream = new FileOutputStream(file);
                inputStream = socket.getInputStream();

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("Archivo recibido y guardado en: " + file.getAbsolutePath());

                fileOutputStream.close();
                inputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

