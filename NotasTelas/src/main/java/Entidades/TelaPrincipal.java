/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Carlos
 */
public class TelaPrincipal {
    
    
    public static void montarTelaInicial() throws IOException {
        
        final JFrame jFrameInicial = new JFrame("Sistema de notas");
        
        jFrameInicial.setBounds(200, 170, 850, 600);
        jFrameInicial.setLayout(null);
        
        JLabel labelUsuario = new JLabel("Usuarios");
        labelUsuario.setBounds(20, 20, 100, 100);
        labelUsuario.setHorizontalAlignment(JLabel.CENTER);
        
        JButton buttonUsuario = new JButton();
        buttonUsuario.setBounds(75,175, 100, 50);
        buttonUsuario.add(labelUsuario);
        jFrameInicial.add(buttonUsuario);
        
        JLabel labelAlunos = new JLabel("Alunos");
        labelAlunos.setBounds(20, 20, 100, 100);
        labelAlunos.setHorizontalAlignment(JLabel.CENTER);
        
        JButton buttonAlunos = new JButton();
        buttonAlunos.setBounds(200, 175, 100, 50);
        buttonAlunos.add(labelAlunos);
        jFrameInicial.add(buttonAlunos);
        
        JLabel labelBoletim = new JLabel("Boletim");
        labelBoletim.setBounds(20, 20, 100, 100);
        labelBoletim.setHorizontalAlignment(JLabel.CENTER);
        
        JButton buttonBoletim = new JButton();
        buttonBoletim.setBounds(325, 175, 100, 50);
        buttonBoletim.add(labelBoletim);
        jFrameInicial.add(buttonBoletim);
        
        JLabel labelNotas = new JLabel("Notas");
        labelNotas.setBounds(20, 20, 100, 100);
        labelNotas.setHorizontalAlignment(JLabel.CENTER);
        
        JButton buttonNotas = new JButton();
        buttonNotas.setBounds(75, 275, 100, 50);
        buttonNotas.add(labelNotas);
        jFrameInicial.add(buttonNotas);
        
        JLabel labelInstit = new JLabel("Instituição");
        labelInstit.setBounds(20, 20, 100, 100);
        labelInstit.setHorizontalAlignment(JLabel.CENTER);
        
        JButton buttonInstit = new JButton();
        buttonInstit.setBounds(200, 275, 100, 50);
        buttonInstit.add(labelInstit);
        jFrameInicial.add(buttonInstit);
        
        JLabel labelProf = new JLabel("Professores");
        labelProf.setBounds(20, 20, 100, 100);
        labelProf.setHorizontalAlignment(JLabel.CENTER);
        
        JButton buttonProf = new JButton();
        buttonProf.setBounds(325, 275, 100, 50);
        buttonProf.add(labelProf);
        jFrameInicial.add(buttonProf);
        
        JLabel labelMatr = new JLabel("Matricula");
        labelMatr.setBounds(20, 20, 100, 100);
        labelMatr.setHorizontalAlignment(JLabel.CENTER);
        
        JButton buttonMatr = new JButton();
        buttonMatr.setBounds(450, 275, 100, 50);
        buttonMatr.add(labelMatr);
        jFrameInicial.add(buttonMatr);
        
        JLabel labelDia = new JLabel("Diário");
        labelDia.setBounds(20, 20, 100, 100);
        labelDia.setHorizontalAlignment(JLabel.CENTER);
        
        JButton buttonDia = new JButton();
        buttonDia.setBounds(575, 275, 100, 50);
        buttonDia.add(labelDia);
        jFrameInicial.add(buttonDia);
        
        JLabel labelFreq = new JLabel("Frequencia");
        labelFreq.setBounds(20, 20, 100, 100);
        labelFreq.setHorizontalAlignment(JLabel.CENTER);
        
        JButton buttonFreq = new JButton();
        buttonFreq.setBounds(450, 175, 100, 50);
        buttonFreq.add(labelFreq);
        jFrameInicial.add(buttonFreq);
        
        buttonFreq.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                jFrameInicial.dispose(); // TODO Auto-generated catch block
                TelaFrequencias.montarTelaFrequencia();
                

            }
        });
        
        buttonDia.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                jFrameInicial.dispose(); // TODO Auto-generated catch block
                TelaDiario.montarTelaDiario();
                

            }
        });
            
        
        buttonMatr.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    jFrameInicial.dispose();
                    TelaMatricula.montarTelaMatricula();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    System.out.println(e1.getMessage());
                } catch (SQLException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        buttonUsuario.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    jFrameInicial.dispose();
                    Usuarios.montarTelaUsuario();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    System.out.println(e1.getMessage());
                } catch (SQLException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        buttonAlunos.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    jFrameInicial.dispose();
                    TelaAlunos.montarTelaAlunos();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    System.out.println(e1.getMessage());
                } catch (SQLException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        buttonBoletim.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    jFrameInicial.dispose();
                    TelaBoletim.montarTelaVisualizacao();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    System.out.println(e1.getMessage());
                }

            }
        });
        
        buttonNotas.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                jFrameInicial.dispose(); // TODO Auto-generated catch block
                TelaNotas.montarTelaNotas();

            }
        });
        
        buttonInstit.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                jFrameInicial.dispose(); // TODO Auto-generated catch block
                TelaInstituicao.montarTelaInstituicao();

            }
        });
        
        buttonProf.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    jFrameInicial.dispose();
                    TelaProfessores.montarTelaProfessor();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    System.out.println(e1.getMessage());
                }

            }
        });

        
        
        
        // ainda falta adicionar as funcionalidades!
        jFrameInicial.setVisible(true);
        
    }
    
}
