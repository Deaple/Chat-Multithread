
import fachadas.ConectarServidor;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;

public class JanelaChat extends javax.swing.JFrame implements Runnable {

    //private ConectarServidor conexao;
    private String login;
    private InetAddress ipMulticast = null;
    private final int portaMulticast = 6868;
    private MulticastSocket socketMulticast = null;
    private final String ipGrupo;

    /**
     * Creates new form JanelaChat
     */
    public JanelaChat(String login, ConectarServidor conectar) throws IOException {
        initComponents();
        this.login = login;

        //this.conexao = conectar; //copia a instancia de conectar criada em janelaLogin para cá
        conectar.enviarDadosLogin(); //termina de mandar os dados do usuario para o servidor
        String temp = conectar.getIpGrupo();//pega o ip do grupo multicast no qual entrou
        ipGrupo = new String(temp.substring(1));//remove uma / que esta vindo a mais

        //usuario.getSalaChat();
        jLabel7.setText(login);
        jLabel8.setText(login);
        jLabel9.setText(conectar.getNomeSala());
        jLabel10.setText(conectar.getNomeSala());
        jTextField1.requestFocus();
        jList1.setSelectedIndex(0);//deixa o primeiro item da lista selecionado de padrao, q sera o TODOS (multicast)

        //placeholder do campo ESCREVER (aba conversas):
        TextPrompt placeholder = new TextPrompt("Escreva uma mensagem", jTextField1);
        placeholder.setForeground(Color.GRAY);
        placeholder.changeAlpha(0.8f);
        placeholder.changeStyle(Font.ITALIC);
        //placeholder do campo SALVAR EM (aba arquivos):
        TextPrompt placeholder2 = new TextPrompt("D:\\teste\\downloads", jTextField2);
        placeholder2.setForeground(Color.GRAY);
        placeholder2.changeAlpha(0.8f);
        placeholder2.changeStyle(Font.ITALIC);
        //placeholder do campo ARQUIVO (aba arquivos):
        TextPrompt placeholder3 = new TextPrompt("arquivo_exemplo.txt", jTextField3);
        placeholder3.setForeground(Color.GRAY);
        placeholder3.changeAlpha(0.8f);
        placeholder3.changeStyle(Font.ITALIC);

        //INICIALIZAR O LADO SERVIDOR MULTICAST DO CLIENTE:
        jTextArea1.setText(login + " entrou.");
        Thread thread = new Thread(new JanelaChat(jTextArea1, ipGrupo));//thread para ficar recebendo as mensagens multicast
        thread.start();
    }

    private JanelaChat(JTextArea historicoMensagens, String ipGrupo) {
        System.out.println("Thread para receber multicast criada.");
        this.jTextArea1 = historicoMensagens;
        this.ipGrupo = ipGrupo;
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setAutoscrolls(true);

        jLabel1.setText("Mensagens:");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setFocusable(false);
        jTextArea1.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("Online em");

        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField1.setNextFocusableComponent(jButton1);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("ENVIAR");
        jButton1.setNextFocusableComponent(jTabbedPane1);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));
        jLabel7.setText("username");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jList1);

        jLabel9.setText("nomeSala");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );

        jTabbedPane1.addTab("CONVERSAS", jPanel1);

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setNextFocusableComponent(jTextField2);
        jTextArea3.setRequestFocusEnabled(false);
        jScrollPane3.setViewportView(jTextArea3);

        jLabel3.setText("Arquivos disponíveis:");

        jLabel4.setText("Online em");

        jButton2.setText("BAIXAR");
        jButton2.setNextFocusableComponent(jTabbedPane1);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Salvar em:");

        jTextField2.setNextFocusableComponent(jButton2);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setFocusable(false);

        jLabel6.setText("Arquivo:");

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(jList2);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 255));
        jLabel8.setText("username");

        jLabel10.setText("nomeSala");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane3))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField3)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton2))))))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );

        jTabbedPane1.addTab("ARQUIVOS", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // QUANDO O USUARIO CLICAR EM ENVIAR:
        String mensagem = jTextField1.getText();
        String usuarioDestino = jList1.getSelectedValue().toString();

        //se mensagem for multicast:
        if ("Item 1".equals(usuarioDestino)) {
            try {
                enviarMensagemMulticast(login + " diz: " + mensagem + "|");
                jTextField1.setText("");
                jTextField1.requestFocus();
            } catch (IOException ex) {
                Logger.getLogger(JanelaChat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }                                        

    //SOBRE CONEXAO MULTICAST:
    public void conectarMulticast() throws IOException {
        ipMulticast = InetAddress.getByName(ipGrupo);
        socketMulticast = new MulticastSocket(portaMulticast);
        socketMulticast.joinGroup(ipMulticast);
    }

    public void enviarMensagemMulticast(String mensagem) throws IOException {
        conectarMulticast();
        try {
            DatagramPacket pacote = new DatagramPacket(mensagem.getBytes(), mensagem.length(), ipMulticast, portaMulticast);
            socketMulticast.send(pacote);
        } catch (IOException ex) {
            Logger.getLogger(ConectarServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        //socketMulticast.leaveGroup(ipMulticast);
    }

    public void mostrarConversas(String mensagem) {
        if (mensagem.startsWith("LISTA:")) {
            String[] split = mensagem.split(":");

            for (int i = 3; i < split.length; i++) {

                // AQUI É PARA ADICIONAR TODOS OS split[i] NA jList1.
                // NÃO CONSEGUI FAZER ISSO
            }

        } else {

            jTextArea1.setText(jTextArea1.getText() + "\n" + mensagem);
            jTextArea1.repaint();
        }
    }

    @Override
    public void run() {
        try {
            conectarMulticast();
        } catch (IOException ex) {
            Logger.getLogger(JanelaChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] buffer = new byte[5000];
        while (true) {
            try {
                DatagramPacket recebido = new DatagramPacket(buffer, buffer.length);
                socketMulticast.setSoTimeout(500000);
                socketMulticast.receive(recebido);

                String temp = new String(recebido.getData()); //converte os dados enviados em string
                String mensagem = temp.substring(0, temp.indexOf("|"));//pra nao aparecer lixo usei esse limite de caracter que é adicionado na captura da escrita em JanelaChat

                mostrarConversas(mensagem);

                //System.out.println("Mensagem multicast recebida: " + mensagem);
            } catch (IOException ex) {
                Logger.getLogger(JanelaChat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                //new JanelaChat().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration                   

}
