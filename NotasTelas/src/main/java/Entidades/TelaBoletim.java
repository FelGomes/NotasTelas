package Entidades;

import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Funcionalidades.PBoletim;



public class TelaBoletim {

    private static JTable tabelaBoletim;
    private static DefaultTableModel modelBoletim;

    public static void montarTelaVisualizacao() throws IOException {

        final JFrame oJFrame = new JFrame("Boletim");
        oJFrame.setBounds(450, 170, 580, 500);
        oJFrame.setLayout(null);
        JButton oJButtonBoletim = new JButton("Filtrar");
        oJButtonBoletim.setBounds(420, 230, 100, 25);

   
        String[] colunasBoletim = {
            "Nome", "Sexo", "Sala", "Turma", "Média", "Faltas", "Total Aulas"
        };
        
        modelBoletim = new DefaultTableModel(colunasBoletim, 0); 
        tabelaBoletim = new JTable(modelBoletim);
        JScrollPane scrollBoletim = new JScrollPane(tabelaBoletim);
        scrollBoletim.setBounds(20, 30, 530, 200); 
        oJFrame.add(scrollBoletim);

        oJButtonBoletim.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String inputId = JOptionPane.showInputDialog("Digite o ID do aluno:");
                if (inputId == null || inputId.trim().isEmpty()) {
                    return;
                }

                int alunoId;
                try {
                    alunoId = Integer.parseInt(inputId);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido. Por favor, digite um número.");
                    return;
                }

                PBoletim boletim = new PBoletim(); 
                List<String[]> dados = new ArrayList<>();
                try {
                    dados = boletim.listarBoletimParaTabela(alunoId);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados: " + ex.getMessage());
                    ex.printStackTrace(); 
                    return;
                }


                if (dados.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum boletim encontrado para o ID " + alunoId + ".");
                    modelBoletim.setRowCount(0);
                    return;
                }

                modelBoletim.setRowCount(0);

                for (String[] row : dados) {
                    modelBoletim.addRow(row);
                }
             
            }
        });

        oJFrame.add(oJButtonBoletim);
        oJFrame.setVisible(true);
        oJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
    }
}