
package Entidades;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import Funcionalidades.PAlunos;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Carlos
 */
public class TelaAlunos {

    public static void montarTelaAlunos() throws IOException, SQLException {
    
        JFrame janela = new JFrame("INSERIR Aluno");
        janela.setBounds(200, 150, 600, 550);
        janela.setLayout(null);

        JLabel nomeUsuario = new JLabel("NOME:");
        nomeUsuario.setBounds(5, 20, 60, 25);
        nomeUsuario.setHorizontalAlignment(JLabel.RIGHT);
        janela.add(nomeUsuario);

        JTextField campoAluno = new JTextField();
        campoAluno.setBounds(20, 45, 250, 25);
        janela.add(campoAluno);

        JLabel nomeSala = new JLabel("SALA:");
        nomeSala.setBounds(5, 80, 80, 25);
        nomeSala.setHorizontalAlignment(JLabel.RIGHT);
        janela.add(nomeSala);

        JTextField campoSala = new JTextField();
        campoSala.setBounds(20, 108, 250, 25);
        janela.add(campoSala);

        JLabel nomeTurma = new JLabel("TURMA:");
        nomeTurma.setBounds(310, 20, 60, 25);
        nomeTurma.setHorizontalAlignment(JLabel.RIGHT);
        janela.add(nomeTurma);

        JTextField campoTurma = new JTextField();
        campoTurma.setBounds(340, 45, 250, 25);
        janela.add(campoTurma);

        JLabel campoQtd = new JLabel("Qtd Disciplinas:");
        campoQtd.setBounds(315, 80, 60, 25);
        campoQtd.setHorizontalAlignment(JLabel.RIGHT);
        janela.add(campoQtd);

        JTextField campoQTD = new JTextField();
        campoQTD.setBounds(338, 108, 120, 25);
        janela.add(campoQTD);


        JButton botaoSalvar = new JButton("SAVE");
        botaoSalvar.setBounds(350, 170, 80, 25);
        janela.add(botaoSalvar);

        JButton botaoCancelar = new JButton("CANCEL");
        botaoCancelar.setBounds(450, 170, 100, 25);
        janela.add(botaoCancelar);

        JButton botaoAlterar = new JButton("ALTER");
        botaoAlterar.setBounds(350, 205, 80, 25);
        botaoAlterar.setEnabled(false);
        janela.add(botaoAlterar);

        JButton botaoDeletar = new JButton("DEL");
        botaoDeletar.setBounds(450, 205, 100, 25);
        botaoDeletar.setEnabled(false);
        janela.add(botaoDeletar);

        JButton botaoFiltrar = new JButton("Filtrar");
        botaoFiltrar.setBounds(350, 240, 80, 25);
        janela.add(botaoFiltrar);

        JButton botaoArquivo = new JButton("GER.ARQ");
        botaoArquivo.setBounds(450, 240, 100, 25);
        janela.add(botaoArquivo);

        JLabel nomeFiltro = new JLabel("FILTRO:");
        nomeFiltro.setBounds(10, 205, 60, 25);
        nomeFiltro.setHorizontalAlignment(JLabel.RIGHT);
        janela.add(nomeFiltro);

        JTextField campoFiltro = new JTextField();
        campoFiltro.setBounds(20, 230, 250, 25);
        janela.add(campoFiltro);

        String[] colunas = {"ID", "Matriculado", "Sala", "Turma", "Quantidade de Disciplina"};
        PAlunos pAlunos = new PAlunos();

        JTable tabela = new JTable();
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(30, 270, 515, 220);
        janela.add(scroll);

        atualizarTabela(tabela, pAlunos.listarAlunos());

        botaoSalvar.addActionListener(e -> {
            if (validarCampos(campoAluno, campoSala, campoTurma, campoQTD)) {
                String resultado = pAlunos.incluirAlunos(
                        campoAluno.getText(),
                        campoSala.getText(),
                        campoTurma.getText(),
                        campoQTD.getText()
                );
                JOptionPane.showMessageDialog(null, resultado);
                try {
                    montarTelaAlunos();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
                janela.dispose();
            }
        });

        final int[] idSelecionado = {-1};
        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                idSelecionado[0] = Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
                campoAluno.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
                campoSala.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
                campoTurma.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
                campoQTD.setText(tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
                botaoAlterar.setEnabled(true);
                botaoDeletar.setEnabled(true);
            }
        });

      botaoAlterar.addActionListener(e -> {
            if (idSelecionado[0] != -1 && validarCampos(campoAluno, campoSala, campoTurma, campoQTD)) {
                String resultado = pAlunos.alterarAluno(
                        idSelecionado[0],
                        campoAluno.getText(),
                        campoSala.getText(),
                        campoTurma.getText(),
                        campoQTD.getText()
                );
                JOptionPane.showMessageDialog(null, resultado);
                try {
                    montarTelaAlunos();
                } catch (IOException | SQLException ex) {
                    ex.printStackTrace();
                }
                janela.dispose();
            }
        });


        botaoDeletar.addActionListener(e -> {
            if (idSelecionado[0] != -1) {
                String resultado = pAlunos.excluirAluno(idSelecionado[0]);
                JOptionPane.showMessageDialog(null, resultado);
                try {
                    montarTelaAlunos();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
                janela.dispose();
            }
        });

        botaoFiltrar.addActionListener(e -> {
            try {
                atualizarTabela(tabela, pAlunos.buscarAlunosPorSala(campoFiltro.getText()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        botaoCancelar.addActionListener(e -> {
            try {
                montarTelaAlunos();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            janela.dispose();
        });

        janela.setVisible(true);
    }

    private static boolean validarCampos(JTextField matriculado, JTextField sala, JTextField turma, JTextField qtdDisciplina) {
        return !matriculado.getText().trim().isEmpty()
                && !sala.getText().trim().isEmpty()
                && !turma.getText().trim().isEmpty()
                && !qtdDisciplina.getText().trim().isEmpty();
    }

    private static void atualizarTabela(JTable tabela, ArrayList<String[]> dados) {
        String[] colunas = {"ID", "Matriculado", "Sala", "Turma", "Quantidade de Disciplina"};
        String[][] linhas = dados.toArray(new String[0][]);
        tabela.setModel(new javax.swing.table.DefaultTableModel(linhas, colunas));
    }

    public static MaskFormatter mascaraCPF(String mascara) {
        try {
            MaskFormatter formatter = new MaskFormatter(mascara);
            formatter.setPlaceholder("000.000.000-00");
            return formatter;
        } catch (ParseException e) {
            return null;
        }
    }

    public static MaskFormatter mascaraData(String mascara) {
        try {
            MaskFormatter formatter = new MaskFormatter(mascara);
            formatter.setPlaceholderCharacter(' ');
            return formatter;
        } catch (ParseException e) {
            return null;
        }
    }
    
}
