package Entidades;

import Funcionalidades.EAlunos;
import Funcionalidades.EFrequencias;
import Funcionalidades.EProfessor;
import Funcionalidades.PFrequencias;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaFrequencias {

    public static void montarTelaFrequencia() {
        JFrame janela = new JFrame("Cadastro de Frequência");
        janela.setSize(600, 500);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setLocationRelativeTo(null);

        JLabel rotuloTotalAulas = new JLabel("Total de Aulas:");
        rotuloTotalAulas.setBounds(30, 20, 120, 20);
        janela.add(rotuloTotalAulas);

        JTextField campoTotalAulas = new JTextField();
        campoTotalAulas.setBounds(180, 20, 200, 20);
        janela.add(campoTotalAulas);

        JLabel rotuloAulasMinistradas = new JLabel("Aulas Ministradas:");
        rotuloAulasMinistradas.setBounds(30, 50, 120, 20);
        janela.add(rotuloAulasMinistradas);

        JTextField campoAulasMinistradas = new JTextField();
        campoAulasMinistradas.setBounds(180, 50, 200, 20);
        janela.add(campoAulasMinistradas);

        JLabel rotuloFaltas = new JLabel("Faltas:");
        rotuloFaltas.setBounds(30, 80, 120, 20);
        janela.add(rotuloFaltas);

        JTextField campoFaltas = new JTextField();
        campoFaltas.setBounds(180, 80, 200, 20);
        janela.add(campoFaltas);

        JLabel rotuloDisciplina = new JLabel("Disciplina:");
        rotuloDisciplina.setBounds(30, 110, 120, 20);
        janela.add(rotuloDisciplina);

        JTextField campoDisciplina = new JTextField();
        campoDisciplina.setBounds(180, 110, 200, 20);
        janela.add(campoDisciplina);

        JLabel rotuloIdProfessor = new JLabel("ID Professor:");
        rotuloIdProfessor.setBounds(30, 140, 120, 20);
        janela.add(rotuloIdProfessor);

        JTextField campoIdProfessor = new JTextField();
        campoIdProfessor.setBounds(180, 140, 200, 20);
        janela.add(campoIdProfessor);

        JLabel rotuloIdAluno = new JLabel("ID Aluno:");
        rotuloIdAluno.setBounds(30, 170, 120, 20);
        janela.add(rotuloIdAluno);

        JTextField campoIdAluno = new JTextField();
        campoIdAluno.setBounds(180, 170, 200, 20);
        janela.add(campoIdAluno);

        JButton botaoSalvar = new JButton("SALVAR");
        botaoSalvar.setBounds(180, 210, 100, 30);
        janela.add(botaoSalvar);

        JButton botaoGerarArquivo = new JButton("GERAR ARQUIVO");
        botaoGerarArquivo.setBounds(300, 210, 150, 30);
        janela.add(botaoGerarArquivo);

        botaoGerarArquivo.addActionListener(e -> {
            try {
                PFrequencias pFrequencias = new PFrequencias();
                List<EFrequencias> lista = pFrequencias.listarFrequencias();
                String resultado = pFrequencias.gerarArquivoFrequencias(lista);
                JOptionPane.showMessageDialog(null, resultado);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao gerar arquivo: " + ex.getMessage());
            }
        });

        JTable tabela = new JTable();
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(30, 260, 520, 180);
        janela.add(scroll);

        PFrequencias pFrequencias = new PFrequencias();
        atualizarTabela(tabela, pFrequencias.listarFrequencias());

        botaoSalvar.addActionListener(e -> {
            if (validarCampos(campoTotalAulas, campoAulasMinistradas, campoFaltas, campoDisciplina, campoIdProfessor, campoIdAluno)) {
                EFrequencias freq = new EFrequencias();
                freq.setTotal_aulas(Integer.parseInt(campoTotalAulas.getText()));
                freq.setAulas_ministradas(Integer.parseInt(campoAulasMinistradas.getText()));
                freq.setFrequencias_faltas(Integer.parseInt(campoFaltas.getText()));
                freq.setFrequencias_disciplinas(campoDisciplina.getText());

                EProfessor prof = new EProfessor();
                prof.setIdProfessor(Integer.parseInt(campoIdProfessor.getText()));
                freq.setProfessores(prof);

                EAlunos aluno = new EAlunos();
                aluno.setUsuario_id(Integer.parseInt(campoIdAluno.getText()));
                freq.setAluno(aluno);

                String resultado = pFrequencias.incluirFrequencia(freq);
                JOptionPane.showMessageDialog(null, resultado);
                atualizarTabela(tabela, pFrequencias.listarFrequencias());
                limparCampos(campoTotalAulas, campoAulasMinistradas, campoFaltas, campoDisciplina, campoIdProfessor, campoIdAluno);
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente.");
            }
        });

        janela.setVisible(true);
    }

    private static void atualizarTabela(JTable tabela, List<EFrequencias> lista) {
        String[] colunas = {"ID", "Total Aulas", "Aulas Ministradas", "Faltas", "Disciplina", "ID Professor", "ID Aluno"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        for (EFrequencias f : lista) {
            modelo.addRow(new Object[]{
                f.getFrequencias_id(),
                f.getTotal_aulas(),
                f.getAulas_ministradas(),
                f.getFrequencias_faltas(),
                f.getFrequencias_disciplinas(),
                f.getProfessores() != null ? f.getProfessores().getIdProfessor() : null,
                f.getAluno() != null ? f.getAluno().getUsuario_id() : null
            });
        }

        tabela.setModel(modelo);
    }

    private static boolean validarCampos(JTextField... campos) {
        for (JTextField campo : campos) {
            if (campo.getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static void limparCampos(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }
}
