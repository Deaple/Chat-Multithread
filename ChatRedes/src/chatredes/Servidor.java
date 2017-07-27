package chatredes;

import threads.ConexaoTCP;
import chatredes.Usuario;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Servidor {

    //private static String servidor = "localhost"; //endereco do servidor
    private static final int portaTCPservidor = 6790; // porta do servidor
    private final String salas;
    private final String ipSalas;
    private Usuario usuario;
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    private final ServerSocket socketInicial; //socket TCP que vai ficar recebendo todas as conexoes na thread principal
    public DatagramSocket socketUDP;

    public Servidor() throws IOException {
        this.salas = "Redes de Computadores II;Programacao Movel;Banco de Dados;Administracao";
        this.ipSalas = "224.225.226.227;224.225.226.228;224.225.226.229;224.225.226.230";
        socketInicial = new ServerSocket(portaTCPservidor);
        conectar();
    }

    public void conectar() throws IOException {
        //metodo para receber as conexoes TCP iniciais.
        System.out.println("Servidor iniciado, porta TCP: " + portaTCPservidor);
        while (true) {
            System.out.println("Aguardando conexao...");
            Socket socketConexaoTCP = socketInicial.accept();//aceita a conexao
            System.out.println("Conexao aceita.");
            usuario = new Usuario(socketConexaoTCP);//inicializa um usuario com o seu socket
            usuarios.add(usuario);

            Runnable r = () -> {
                String[] splitSalas = salas.split(";");
                String[] splitIpSalas = ipSalas.split(";");

                String lista;
                ArrayList<String> listas = new ArrayList();

                while (true) {

                    for (int i = 0; i < splitSalas.length; i++) {
                        lista = "LISTA:" + splitSalas[i] + ":" + splitIpSalas[i] + ":";
                        if (usuarios.size() > 0) {
                            for (int j = 0; j < usuarios.size(); j++) {
                                if (splitSalas[i].equals(usuarios.get(j).getSala())) {
                                    lista += usuarios.get(j).getNomeUsuario() + ":";
                                }
                            }
                        }
                        listas.add(lista + "|");
                        //System.out.println(lista);
                    }

                    InetAddress ipGrupo;
                    MulticastSocket socketMulticast;
                    DatagramPacket dtgrm;

                    for (int i = 0; i < listas.size(); i++) {
                        try {
                            ipGrupo = InetAddress.getByName(splitIpSalas[i]);
                            socketMulticast = new MulticastSocket(6868);
                            socketMulticast.joinGroup(ipGrupo);
                            dtgrm = new DatagramPacket(listas.get(i).getBytes(),
                                    listas.get(i).length(),
                                    ipGrupo,
                                    6868);
                            
                            socketMulticast.send(dtgrm);
                        } 
                        catch (UnknownHostException ex) {
                            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    listas.clear();
                }
            };

            Thread threadMulticast = new Thread(r);
            threadMulticast.start();

            Thread thread = new Thread(new ConexaoTCP(usuario, socketConexaoTCP));
            thread.start();
        }
    }

    public static void main(String argv[]) throws Exception {
        Servidor servidor = new Servidor();
//        ArrayBlockingQueue pilha = new ArrayBlockingQueue(5);
    }
}

