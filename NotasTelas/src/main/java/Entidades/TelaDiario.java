package Entidades;

import Funcionalidades.EDiario;
import Funcionalidades.PDiario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TelaDiario {

    private static JFrame frame;
    private static JTable tabela;
    private static JTextField tfLocal, tfDisciplinas, tfQtdAlunos, tfFiltro;
    private static JTextField tfProfessoresId, tfAlunosId;
    private static DefaultTableModel modeloTabela;
    private static PDiario pDiario = new PDiario();
    private static int idSelecionado = -1;

    public static void montarTelaDiario() {
        frame = new JFrame("Gerenciar Diário");
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel lbLocal = new JLabel("Local:");
        lbLocal.setBounds(20, 20, 100, 25);
        frame.add(lbLocal);

        tfLocal = new JTextField();
        tfLocal.setBounds(120, 20, 200, 25);
        frame.add(tfLocal);

        JLabel lbDisciplinas = new JLabel("Disciplinas:");
        lbDisciplinas.setBounds(20, 60, 100, 25);
        frame.add(lbDisciplinas);

        tfDisciplinas = new JTextField();
        tfDisciplinas.setBounds(120, 60, 200, 25);
        frame.add(tfDisciplinas);

        JLabel lbQtdAlunos = new JLabel("Qtd Alunos:");
        lbQtdAlunos.setBounds(20, 100, 100, 25);
        frame.add(lbQtdAlunos);

        tfQtdAlunos = new JTextField();
        tfQtdAlunos.setBounds(120, 100, 200, 25);
        frame.add(tfQtdAlunos);

        JLabel lbProfessoresId = new JLabel("ID Professor:");
        lbProfessoresId.setBounds(20, 140, 100, 25);
        frame.add(lbProfessoresId);

        tfProfessoresId = new JTextField();
        tfProfessoresId.setBounds(120, 140, 200, 25);
        frame.add(tfProfessoresId);

        JLabel lbAlunosId = new JLabel("ID Aluno:");
        lbAlunosId.setBounds(20, 180, 100, 25);
        frame.add(lbAlunosId);

        tfAlunosId = new JTextField();
        tfAlunosId.setBounds(120, 180, 200, 25);
        frame.add(tfAlunosId);

        // Botões
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(350, 20, 100, 25);
        frame.add(btnSalvar);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(350, 60, 100, 25);
        frame.add(btnAlterar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(350, 100, 100, 25);
        frame.add(btnExcluir);

        JLabel lbFiltro = new JLabel("Filtrar Local:");
        lbFiltro.setBounds(20, 230, 100, 25);
        frame.add(lbFiltro);

        tfFiltro = new JTextField();
        tfFiltro.setBounds(120, 230, 200, 25);
        frame.add(tfFiltro);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(350, 230, 100, 25);
        frame.add(btnFiltrar);

        JButton btnGerarArquivo = new JButton("Gerar Arquivo");
        btnGerarArquivo.setBounds(480, 230, 140, 25);
        frame.add(btnGerarArquivo);

        // Tabela
        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Local", "Disciplinas", "Qtd Alunos", "ID Professor", "ID Aluno"}, 0
        );
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(20, 270, 740, 280);
        frame.add(scrollPane);

        // Ações dos botões
        btnSalvar.addActionListener(e -> salvarDiario());

        btnAlterar.addActionListener(e -> alterarDiario());

        btnExcluir.addActionListener(e -> excluirDiario());

        btnFiltrar.addActionListener(e -> listarDiarios(tfFiltro.getText().trim()));

        btnGerarArquivo.addActionListener(e -> gerarArquivo());

        // Evento para carregar dados ao clicar na tabela
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.getSelectedRow();
                if (linha != -1) {
                    idSelecionado = Integer.parseInt(modeloTabela.getValueAt(linha, 0).toString());
                    tfLocal.setText(modeloTabela.getValueAt(linha, 1).toString());
                    tfDisciplinas.setText(modeloTabela.getValueAt(linha, 2).toString());
                    tfQtdAlunos.setText(modeloTabela.getValueAt(linha, 3).toString());
                    tfProfessoresId.setText(modeloTabela.getValueAt(linha, 4).toString());
                    tfAlunosId.setText(modeloTabela.getValueAt(linha, 5).toString());
                }
            }
        });

        // Listar inicialmente tudo
        listarDiarios("");

        frame.setVisible(true);
    }

    private static void salvarDiario() {
        try {
            EDiario d = new EDiario();
            d.setDiarios_local(tfLocal.getText().trim());
            d.setDiarios_disciplinas(tfDisciplinas.getText().trim());
            d.setQtd_alunos(Integer.parseInt(tfQtdAlunos.getText().trim()));
            d.setFk_diarios_professores_id(Integer.parseInt(tfProfessoresId.getText().trim()));
            d.setFk_diarios_alunos_id(Integer.parseInt(tfAlunosId.getText().trim()));

            String msg = pDiario.salvar(d);
            JOptionPane.showMessageDialog(frame, msg);
            listarDiarios("");
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Preencha todos os campos corretamente (números).");
        }
    }

    private static void alterarDiario() {
        if (idSelecionado == -1) {
            JOptionPane.showMessageDialog(frame, "Selecione um diário na tabela para alterar.");
            return;
        }
        try {
            EDiario d = new EDiario();
            d.setDiarios_id(idSelecionado);
            d.setDiarios_local(tfLocal.getText().trim());
            d.setDiarios_disciplinas(tfDisciplinas.getText().trim());
            d.setQtd_alunos(Integer.parseInt(tfQtdAlunos.getText().trim()));
            d.setFk_diarios_professores_id(Integer.parseInt(tfProfessoresId.getText().trim()));
            d.setFk_diarios_alunos_id(Integer.parseInt(tfAlunosId.getText().trim()));

            String msg = pDiario.alterar(d);
            JOptionPane.showMessageDialog(frame, msg);
            listarDiarios("");
            limparCampos();
            idSelecionado = -1;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Preencha todos os campos corretamente (números).");
        }
    }

    private static void excluirDiario() {
        if (idSelecionado == -1) {
            JOptionPane.showMessageDialog(frame, "Selecione um diário na tabela para excluir.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(frame, "Confirma exclusão?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String msg = pDiario.deletar(idSelecionado);
            JOptionPane.showMessageDialog(frame, msg);
            listarDiarios("");
            limparCampos();
            idSelecionado = -1;
        }
    }

    private static void listarDiarios(String filtro) {
        ArrayList<EDiario> lista = pDiario.listar(filtro);
        modeloTabela.setRowCount(0); // limpa tabela

        for (EDiario d : lista) {
            modeloTabela.addRow(new Object[]{
                d.getDiarios_id(),
                d.getDiarios_local(),
                d.getDiarios_disciplinas(),
                d.getQtd_alunos(),
                d.getFk_diarios_professores_id(),
                d.getFk_diarios_alunos_id()
            });
        }
    }

    private static void limparCampos() {
        tfLocal.setText("");
        tfDisciplinas.setText("");
        tfQtdAlunos.setText("");
        tfProfessoresId.setText("");
        tfAlunosId.setText("");
        idSelecionado = -1;
    }

    private static void gerarArquivo() {
        ArrayList<String[]> dados = pDiario.listarDiarios();

        try (FileWriter fw = new FileWriter("Diarios_exportados.txt")) {
            for (String[] linha : dados) {
                fw.write(String.join(";", linha) + "\n");
            }
            JOptionPane.showMessageDialog(frame, "Arquivo gerado com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao gerar arquivo: " + e.getMessage());
        }
    }
}
