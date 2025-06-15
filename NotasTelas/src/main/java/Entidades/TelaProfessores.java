package Entidades;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.List;
import Funcionalidades.PProfessor;
import java.util.ArrayList;

public class TelaProfessores {

    public static void montarTelaProfessor() throws IOException {

        final JFrame oJFrame = new JFrame("Professores");
        oJFrame.setBounds(450, 170, 800, 650);
        oJFrame.setLayout(null);
        oJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        oJFrame.setLocationRelativeTo(null);

        PProfessor dao = new PProfessor();
      
        JLabel lblUsuarios = new JLabel("Listagem de Todos os Usuários:");
        lblUsuarios.setBounds(10, 10, 200, 25);
        oJFrame.add(lblUsuarios);

        String[] colunasUsuarios = {"ID", "Nome", "CPF", "Data Nascimento"};
        DefaultTableModel modeloUsuarios = new DefaultTableModel(colunasUsuarios, 0);
        JTable tabelaUsuarios = new JTable(modeloUsuarios);
        JScrollPane scrollUsuarios = new JScrollPane(tabelaUsuarios);
        scrollUsuarios.setBounds(10, 40, 760, 150);
        oJFrame.add(scrollUsuarios);
        
        Runnable atualizarUsuarios = () -> {
            modeloUsuarios.setRowCount(0);
            List<Object[]> usuariosAll = dao.listarTodosUsuarios();
            for (Object[] usuario : usuariosAll) {
                modeloUsuarios.addRow(usuario);
            }
        };
        atualizarUsuarios.run();
       
        JLabel lblProfessores = new JLabel("Listagem de Professores:");
        lblProfessores.setBounds(10, 170, 200, 25);
        oJFrame.add(lblProfessores);

        String[] colunas = {"ID", "Nome", "Disciplina", "Turma", "Titularidade"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(10, 200, 760, 200);
        oJFrame.add(scrollPane);

        Runnable atualizarTabela = () -> {
            modelo.setRowCount(0);
            List<Object[]> professores = dao.listarProfessores();
            for (Object[] prof : professores) {
                modelo.addRow(prof);
            }
        };
        atualizarTabela.run();

        JLabel oJLabelID = new JLabel("ID Usuário:");
        oJLabelID.setBounds(10, 405, 80, 25);
        oJLabelID.setHorizontalAlignment(JLabel.RIGHT);
        oJFrame.add(oJLabelID);

        final JTextField oJTextID = new JTextField();
        oJTextID.setBounds(100, 405, 150, 25);
        oJFrame.add(oJTextID);

        JLabel oJLabelDISCIPLINA = new JLabel("Disciplina:");
        oJLabelDISCIPLINA.setBounds(10, 440, 80, 25);
        oJLabelDISCIPLINA.setHorizontalAlignment(JLabel.RIGHT);
        oJFrame.add(oJLabelDISCIPLINA);

        final JTextField oJTextDISCIPLINA = new JTextField();
        oJTextDISCIPLINA.setBounds(100, 440, 400, 25);
        oJFrame.add(oJTextDISCIPLINA);

        JLabel oJLabelTURMA = new JLabel("Turma:");
        oJLabelTURMA.setBounds(10, 475, 80, 25);
        oJLabelTURMA.setHorizontalAlignment(JLabel.RIGHT);
        oJFrame.add(oJLabelTURMA);

        final JTextField oJTextTURMA = new JTextField();
        oJTextTURMA.setBounds(100, 475, 150, 25);
        oJFrame.add(oJTextTURMA);

        JLabel oJLabelTITULARIDADE = new JLabel("Titularidade:");
        oJLabelTITULARIDADE.setBounds(10, 510, 80, 25);
        oJLabelTITULARIDADE.setHorizontalAlignment(JLabel.RIGHT);
        oJFrame.add(oJLabelTITULARIDADE);

        final JTextField oJTextTITULARIDADE = new JTextField();
        oJTextTITULARIDADE.setBounds(100, 510, 200, 25);
        oJFrame.add(oJTextTITULARIDADE);

        JButton btnInserir = new JButton("Inserir");
        btnInserir.setBounds(100, 555, 100, 30);
        oJFrame.add(btnInserir);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(210, 555, 100, 30);
        oJFrame.add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(320, 555, 100, 30);
        oJFrame.add(btnExcluir);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(430, 555, 100, 30);
        oJFrame.add(btnLimpar);
        
        JButton btnGerarArquivo = new JButton("Gerar arquivo");
        btnGerarArquivo.setBounds(540, 555, 130, 30);
        oJFrame.add(btnGerarArquivo);
        
        
        JButton btnFiltar = new JButton("Filtar");
        btnFiltar.setBounds(620, 510, 130, 30);
        oJFrame.add(btnFiltar);
        
        JTextField campoFiltro = new JTextField();
        campoFiltro.setBounds(430, 510, 180, 30);
        oJFrame.add(campoFiltro);

        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabela.getSelectedRow();
                if (row != -1) {
                    oJTextID.setText(modelo.getValueAt(row, 0).toString());
                    oJTextDISCIPLINA.setText(modelo.getValueAt(row, 2).toString());
                    oJTextTURMA.setText(modelo.getValueAt(row, 3).toString());
                    oJTextTITULARIDADE.setText(modelo.getValueAt(row, 4).toString());
                }
            }
        });
        
        
        btnFiltar.addActionListener(e -> {
            String nomeBusca = campoFiltro.getText().trim();
            if (nomeBusca.isEmpty()) {
                atualizarTabela.run(); // Volta para a lista completa
                return;
            }
            try {
                List<Object[]> filtrados = dao.buscarProfessoresPorNome(nomeBusca);
                atualizarTabela(modelo, filtrados); // Agora funciona corretamente
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao filtrar professores.");
            }
        });




        btnInserir.addActionListener(e -> {
            // Verificação de campos obrigatórios
            if (oJTextID.getText().trim().isEmpty()
                    || oJTextDISCIPLINA.getText().trim().isEmpty()
                    || oJTextTURMA.getText().trim().isEmpty()
                    || oJTextTITULARIDADE.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(oJTextID.getText());

                if (dao.usuarioEhAluno(id)) {
                    JOptionPane.showMessageDialog(null, "Este usuário já é um aluno!");
                    return;
                }

                if (dao.usuarioEhProfessor(id)) {
                    JOptionPane.showMessageDialog(null, "Este usuário já é um professor!");
                    return;
                }

                dao.inserirProfessor(id,
                        oJTextDISCIPLINA.getText(),
                        oJTextTURMA.getText(),
                        oJTextTITULARIDADE.getText());

                JOptionPane.showMessageDialog(null, "Professor inserido com sucesso!");
                atualizarTabela.run();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID inválido.");
            }
        });


        btnAtualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(oJTextID.getText());
                dao.atualizarProfessor(id, oJTextDISCIPLINA.getText(), oJTextTURMA.getText(), oJTextTITULARIDADE.getText());
                JOptionPane.showMessageDialog(null, "Professor atualizado com sucesso!");
                atualizarTabela.run();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID inválido.");
            }
        });

        btnExcluir.addActionListener(e -> {
            try {
                int id = Integer.parseInt(oJTextID.getText());
                dao.excluirProfessor(id);
                JOptionPane.showMessageDialog(null, "Professor excluído com sucesso!");
                atualizarTabela.run();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID inválido.");
            }
        });

        btnLimpar.addActionListener(e -> {
            oJTextID.setText("");
            oJTextDISCIPLINA.setText("");
            oJTextTURMA.setText("");
            oJTextTITULARIDADE.setText("");
        });
        
         btnGerarArquivo.addActionListener(e -> {
            try {
                dao.gerarArquivoProfessores();
                JOptionPane.showMessageDialog(null, "Arquivo gerado");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao gerar arquivo");
            }
        });
         
        oJFrame.setVisible(true);
    }
    
    
    
     private static void atualizarTabela(DefaultTableModel modelo, List<Object[]> dados) {
        modelo.setRowCount(0); // Limpa o conteúdo atual
        for (Object[] linha : dados) {
            modelo.addRow(linha); // Adiciona cada nova linha
        }
    }

}
