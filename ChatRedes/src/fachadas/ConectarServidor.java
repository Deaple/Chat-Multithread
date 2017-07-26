package fachadas;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class ConectarServidor implements Runnable {
    
    public InetAddress enderecoIP = InetAddress.getByName("localhost"); //do servidor
    private final int portaTCP = 6790; //do servidor
    private final int portaTCPreceber = 6791;
    //variaveis tcp
    private final Socket socketClienteTCP;
    private final DataOutputStream paraServidorTCP;
    private final BufferedReader doServidorTCP;
    private final String listaSalas;
    private String dados;
    private String ipGrupo;
    private String nomeSala;
    
    
    //SOBRE CONEXAO TCP:
    //conexao inicial TCP estabelecida na abertura da janelaLogin
    public ConectarServidor() throws IOException{
        socketClienteTCP = new Socket(enderecoIP, portaTCP); // cria um novo socket TCP
        doServidorTCP = new BufferedReader(new InputStreamReader(socketClienteTCP.getInputStream()));//cria uma variavel para receber os dados do servidor
        paraServidorTCP = new DataOutputStream(socketClienteTCP.getOutputStream());//cria uma variavel para enviar os dados para o servidor e ja conecta com ele
        listaSalas = doServidorTCP.readLine();//recebe as salas do servidor
    }
    public void setDadosLogin(String login, String sala, String arquivosPasta, String caminhoPasta) throws IOException{
        dados = login+";"+sala+";"+arquivosPasta+";"+caminhoPasta+"\n";
        nomeSala = sala;
    }
    public void enviarDadosLogin() throws IOException{
        paraServidorTCP.writeBytes(dados);
        ipGrupo = doServidorTCP.readLine();
    } 
    public String getListaSalas(){
        return listaSalas;
    }
    public String getIpGrupo(){
        return ipGrupo;
    }
    public String getNomeSala(){
        return nomeSala;
    }
 

    //PARA DESCONECTAR DE TUDO:
    public void desconetar() throws IOException{
//        socketMulticast.leaveGroup(ipMulticast);//sai primeiro do grupo multicast
        socketClienteTCP.close(); //encerra conexao TCP com servidor 
    }

    @Override
    public void run() {
        
    }
}
