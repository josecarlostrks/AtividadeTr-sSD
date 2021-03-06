/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meuchatdois;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author jcarlos
 */
public class ChatCliente {
    
    static JFrame chatJanela = new JFrame("Meu Chat");
    static JTextArea chatMensagens = new JTextArea(22,44);
    static JTextField chatNovaMensagem = new JTextField(40);
    static JButton chatBotaoEnviar = new JButton("Enviar");
    static BufferedReader entrada;
    static PrintWriter saida;
    static String mensagem = "";
    
    public ChatCliente() throws IOException{
        chatJanela.setLayout(new FlowLayout());
        chatJanela.add(new JScrollPane(chatMensagens));
        chatJanela.add(chatNovaMensagem);
        chatJanela.add(chatBotaoEnviar);
        
        chatJanela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatJanela.setSize(475, 500);
        chatJanela.setVisible(true);
        chatMensagens.setEditable(false);
        chatNovaMensagem.setEditable(false);
        
        chatBotaoEnviar.addActionListener(new Listener());
        chatNovaMensagem.addActionListener(new Listener());
    }    
    public void iniciarChat() throws IOException{    
        Socket usuario = new Socket("localhost",5577);
        this.entrada = new BufferedReader(new InputStreamReader(usuario.getInputStream()));
        this.saida = new PrintWriter(usuario.getOutputStream(),true);
        
        while(true){
            String msg = entrada.readLine();
            
            String[] nomesToken = msg.split("-");
            String nomeToken = nomesToken[0];            

                if(msg.equals("EMAIL_REQUERIDO")){
                    String email = JOptionPane.showInputDialog(chatJanela, "Email de usu??rio",
                            "Informa????o", JOptionPane.PLAIN_MESSAGE);
                    saida.println(email);
                }else if(msg.equals("EMAIL_EXISTENTE")){
                    String email = JOptionPane.showInputDialog(chatJanela, "Informe outro nome de usu??rio",
                            "nome duplicado", JOptionPane.WARNING_MESSAGE);
                    saida.println(email);
                }else if(msg.equals("EMAIL_INV??LIDO")){
                    String email = JOptionPane.showInputDialog(chatJanela, "Informe um email v??lido",
                            "email inv??lido", JOptionPane.WARNING_MESSAGE);
                    saida.println(email);
                }else
                    
                if(msg.equals("NOME_REQUERIDO")){

                    String nome = JOptionPane.showInputDialog(chatJanela, "Nome de usu??rio",
                            "Informa????o", JOptionPane.PLAIN_MESSAGE);
                    saida.println(nome);

                }else if(msg.equals("NOME_EXISTENTE")){

                    String nome = JOptionPane.showInputDialog(chatJanela, "Informe outro nome de usu??rio",
                            "nome duplicado", JOptionPane.WARNING_MESSAGE);
                    saida.println(nome);
                }else if(msg.equals("NOME_ACEITO")){
                    chatNovaMensagem.setEditable(true);
                }else if(nomeToken.equals("oashgsidhdishishiiiiiiiuiuuuiuiuiuuuuuuuiuiufffd")){
                    ChatCliente.saida.println(mensagem +"-oas");
                    mensagem="";
                }else{                     
                    chatMensagens.append(msg + "\n");
                    ChatCliente.saida.println(mensagem +"-oas");
                    mensagem="";

                }
                
            
        }
    }    
    
    public static void main(String[] args) throws IOException{
               
        ChatCliente chatCliente = new ChatCliente();
        chatCliente.iniciarChat();
    }
}

class Listener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //ChatCliente.saida.println(ChatCliente.chatNovaMensagem.getText());
        ChatCliente.mensagem=ChatCliente.chatNovaMensagem.getText();
        ChatCliente.chatNovaMensagem.setText("");
    }
    
}